package uk.co.boombastech.photos;

import uk.co.boombastech.photos.models.Photo;
import uk.co.boombastech.utils.Builder;

import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class PhotoBuilder implements Builder<Photo> {

	private String id;
	private String filename;
	private Date date;
	private List<String> albums = newArrayList();
	private List<String> categories = newArrayList();
	private long version;
	private int year;
	private int month;
	private int day;

	public PhotoBuilder withId(String id) {
		this.id = id;
		return this;
	}

	public PhotoBuilder withVersion(long version) {
		this.version = version;
		return this;
	}

	public PhotoBuilder withFilename(String filename) {
		this.filename = filename;
		return this;
	}

	public PhotoBuilder withDate(Date date) {
		this.date = date;
		return this;
	}

	public PhotoBuilder withAlbum(String album) {
		albums.add(album);
		return this;
	}

	public PhotoBuilder withCategory(String category) {
		categories.add(category);
		return this;
	}

	@Override
	public Photo build() {
		return new Photo(id, version, filename, date, albums, categories, year, month, day);
	}

	public static PhotoBuilder newPhoto() {
		return new PhotoBuilder();
	}

	public PhotoBuilder withYear(int year) {
		this.year = year;
		return this;
	}

	public PhotoBuilder withMonth(int month) {
		this.month = month;
		return this;
	}

	public PhotoBuilder withDay(int day) {
		this.day = day;
		return this;
	}
}