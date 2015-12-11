package uk.co.boombastech.photos.importer;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import uk.co.boombastech.photos.models.Photo;
import uk.co.boombastech.photos.PhotoBuilder;
import uk.co.boombastech.solr.converters.SolrDocumentConverter;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PhotoSolrDocumentConverter implements SolrDocumentConverter<Photo> {

	@Override
	public SolrInputDocument convertTo(Photo photo) {
		SolrInputDocument document = new SolrInputDocument();

		document.addField("filename", photo.getFilename());
		document.addField("date", photo.getDate());

		return document;
	}

	@Override
	public Optional<Photo> convertFrom(SolrDocument solrDocument) {
		PhotoBuilder photoBuilder = new PhotoBuilder();
		if (solrDocument.containsKey("filename")) {
			String filename = (String) ((List) solrDocument.getFieldValues("filename")).get(0);
			photoBuilder.withFilename(filename);
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