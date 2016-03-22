package uk.co.boombastech.photos.models;

import uk.co.boombastech.solr.search.Document;

import java.util.Date;
import java.util.List;

public class Photo implements Document {
	private String id;
	private final long version;
	private final String filename;
	private final Date date;
	private final List<String> albums;
	private final List<String> categories;

	public Photo(String filename, Date date, List<String> albums, List<String> categories) {
		this(null, 0, filename, date, albums, categories);
	}

	public Photo(String id, long version, String filename, Date date, List<String> albums, List<String> categories) {
		this.id = id;
		this.version = version;
		this.filename = filename;
		this.date = date;
		this.albums = albums;
		this.categories = categories;
	}

	public String getId() {
		return id;
	}

	public long getVersion() {
		return version;
	}

	public String getFilename() {
		return filename;
	}

	public Date getDate() {
		return date;
	}

	public List<String> getAlbums() {
		return albums;
	}

	public List<String> getCategories() {
		return categories;
	}
}