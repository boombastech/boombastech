package uk.co.boombastech.photos.importer;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import uk.co.boombastech.photos.Photo;
import uk.co.boombastech.photos.PhotoCreator;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class ImporterService {

	private static final String WATCH_DIRECTORY = "C:\\photos";

	public static void main(String[] args) throws IOException, InterruptedException, ImageProcessingException {
		WatchService watchService = FileSystems.getDefault().newWatchService();

		Path path = FileSystems.getDefault().getPath(WATCH_DIRECTORY);

		path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

		WatchKey key;
		while (true) {
			key = watchService.take();

			for (WatchEvent<?> event : key.pollEvents()) {
				WatchEvent.Kind<?> kind = event.kind();

				@SuppressWarnings("unchecked")
				WatchEvent<Path> ev = (WatchEvent<Path>) event;
				Path fileName = ev.context();

				if (kind == StandardWatchEventKinds.OVERFLOW) {
					continue;
				} else if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
					File file = new File(WATCH_DIRECTORY, fileName.getFileName().toString());
					Metadata metadata = ImageMetadataReader.readMetadata(file);
					Photo photo = new PhotoCreator(fileName, metadata).create();


				}
			}

			boolean valid = key.reset();
			if (!valid) {
				break;
			}
		}
	}
}