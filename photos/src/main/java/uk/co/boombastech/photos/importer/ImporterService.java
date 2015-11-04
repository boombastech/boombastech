package uk.co.boombastech.photos.importer;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import uk.co.boombastech.photos.Photo;
import uk.co.boombastech.photos.PhotoCreator;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

public class ImporterService {

	private static final String WATCH_DIRECTORY = "C:\\photos";

	private final WatchService watchService;
	private final PhotoIndexer photoIndexer;

	@Inject
	public ImporterService(PhotoIndexer photoIndexer) {
		WatchService tempWatchService = null;
		try {
			tempWatchService = FileSystems.getDefault().newWatchService();
			Path path = FileSystems.getDefault().getPath(WATCH_DIRECTORY);
			path.register(tempWatchService, StandardWatchEventKinds.ENTRY_CREATE);
		} catch (IOException e) {
			System.out.println("error creating ImporterService");
		}

		this.watchService = tempWatchService;
		this.photoIndexer = photoIndexer;
	}

	public boolean run() {
		try {
				System.out.println("looking for update");
				WatchKey key = watchService.poll(1, TimeUnit.SECONDS);

				if (key != null) {
					System.out.println("found update");
					for (WatchEvent<?> event : key.pollEvents()) {
						WatchEvent.Kind<?> kind = event.kind();

						WatchEvent<Path> ev = (WatchEvent<Path>) event;
						Path fileName = ev.context();

						if (kind == StandardWatchEventKinds.OVERFLOW) {
							continue;
						} else if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
							File file = new File(WATCH_DIRECTORY, fileName.getFileName().toString());
							photoIndexer.index(file);
						}
					}

					photoIndexer.commit();

					key.reset();
					return true;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return false;
	}
}