package uk.co.boombastech.photos.importer;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import uk.co.boombastech.photos.models.Photo;
import uk.co.boombastech.photos.PhotoBuilder;
import uk.co.boombastech.solr.converters.SolrDocumentConverter;

import java.util.*;

public class PhotoSolrDocumentConverter implements SolrDocumentConverter<Photo> {

	@Override
	public SolrInputDocument convertTo(Photo photo) {
		SolrInputDocument document = new SolrInputDocument();

		document.addField("id", photo.getId());

		document.addField("filename", photo.getFilename());

		document.addField("date", photo.getDate());

		Map<String, Object> albums = new HashMap<>();
		albums.put("set", photo.getAlbums());
		document.addField("album", albums);

		Map<String, Object> categories = new HashMap<>();
		categories.put("set", photo.getCategories());
		document.addField("category", categories);

		return document;
	}

	@Override
	public Optional<Photo> convertFrom(SolrDocument solrDocument) {
		PhotoBuilder photoBuilder = new PhotoBuilder();
		if (solrDocument.containsKey("filename")) {

			String filename = (String) ((List) solrDocument.getFieldValues("filename")).get(0);
			photoBuilder.withFilename(filename);

			if (solrDocument.containsKey("id")) {
				photoBuilder.withId((String) ((List) solrDocument.getFieldValues("id")).get(0));
			}

			if (solrDocument.containsKey("_version_")) {
				photoBuilder.withVersion((Long) ((List) solrDocument.getFieldValues("_version_")).get(0));
			}

			if (solrDocument.containsKey("date")) {
				Date date = (Date) ((List) solrDocument.getFieldValues("date")).get(0);
				photoBuilder.withDate(date);
			}
			if (solrDocument.containsKey("album")) {
				for (Object album : ((List) solrDocument.getFieldValues("album"))) {
					photoBuilder.withAlbum((String) album);
				}
			}

			if (solrDocument.containsKey("category")) {
				for (Object category : ((List) solrDocument.getFieldValues("category"))) {
					photoBuilder.withCategory((String) category);
				}
			}

			return Optional.of(photoBuilder.build());
		}

		return Optional.empty();
	}
}