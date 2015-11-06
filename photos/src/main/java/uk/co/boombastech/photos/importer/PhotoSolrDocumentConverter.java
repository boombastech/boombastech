package uk.co.boombastech.photos.importer;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import uk.co.boombastech.photos.Photo;
import uk.co.boombastech.solr.SolrDocumentConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PhotoSolrDocumentConverter implements SolrDocumentConverter<Photo> {

	@Override
	public SolrInputDocument convertTo(Photo photo) {
		SolrInputDocument document = new SolrInputDocument();

		document.addField("filename", photo.getFilename());
		document.addField("date", photo.getDate());

		return document;
	}

	@Override
	public Photo convertFrom(SolrDocument solrDocument) {
		if (solrDocument.containsKey("filename") && solrDocument.containsKey("date")) {
			String filename = (String) ((List) solrDocument.getFieldValues("filename")).get(0);
			Date date = (Date) ((List) solrDocument.getFieldValues("date")).get(0);
			return new Photo(filename, date);
		}
		return null;
	}
}