package uk.co.boombastech.photos.importer;

import org.apache.solr.common.SolrInputDocument;
import uk.co.boombastech.photos.Photo;
import uk.co.boombastech.solr.SolrDocumentConverter;

public class PhotoSolrDocumentConverter implements SolrDocumentConverter<Photo> {

	@Override
	public SolrInputDocument convertTo(Photo photo) {
		SolrInputDocument document = new SolrInputDocument();

		document.addField("filename", photo.getFilename());
		document.addField("date", photo.getDate());

		return document;
	}
}