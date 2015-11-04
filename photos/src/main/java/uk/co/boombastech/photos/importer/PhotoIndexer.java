package uk.co.boombastech.photos.importer;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import uk.co.boombastech.photos.Photo;
import uk.co.boombastech.photos.PhotoCreator;
import uk.co.boombastech.solr.Repository;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

public class PhotoIndexer {

	private final Repository<Photo> repository;
	private final PhotoCreator photoCreator;

	@Inject
	public PhotoIndexer(Repository<Photo> repository, PhotoCreator photoCreator) {
		this.repository = repository;
		this.photoCreator = photoCreator;
	}

	public void index(File file) {
		Metadata metadata = null;
		try {
			metadata = ImageMetadataReader.readMetadata(file);
		} catch (ImageProcessingException | IOException e) {
			e.printStackTrace();
		}
		Photo photo = photoCreator.create(file, metadata);
		repository.create(photo);
	}

	public void commit() {
		repository.commit();
	}
}