package uk.co.boombastech.photos.importer;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import uk.co.boombastech.photos.models.Photo;
import uk.co.boombastech.photos.PhotoCreator;
import uk.co.boombastech.solr.search.SolrService;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

public class PhotoIndexer {

	private final SolrService<Photo> solrService;
	private final PhotoCreator photoCreator;

	@Inject
	public PhotoIndexer(SolrService<Photo> repository, PhotoCreator photoCreator) {
		this.solrService = repository;
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
		solrService.update(photo);
	}

	public void commit() {
		solrService.commit();
	}
}