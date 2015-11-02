package uk.co.boombastech.photos;

import java.util.Date;
import java.util.List;

public class Photo {
	private final String filename;
	private final Date date;

	public Photo(String filename, Date date) {
		this.filename = filename;
		this.date = date;
	}

	public String getFilename() {
		return filename;
	}

	public Date getDate() {
		return date;
	}
}