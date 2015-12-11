package uk.co.boombastech.photos.models;

import java.util.Date;
import java.util.List;

public class Photo {
	private final String filename;
	private final Date date;
	private final List<String> albums;
	private final List<String> categories;

	public Photo(String filename, Date date, List<String> albums, List<String> categories) {
		this.filename = filename;
		this.date = date;
		this.albums = albums;
		this.categories = categories;
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