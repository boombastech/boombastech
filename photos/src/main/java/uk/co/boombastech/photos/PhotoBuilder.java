package uk.co.boombastech.photos;

import uk.co.boombastech.photos.models.Photo;
import uk.co.boombastech.utils.Builder;

import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class PhotoBuilder implements Builder<Photo> {

	private String filename;
	private Date date;
	private List<String> albums = newArrayList();
	private List<String> categories = newArrayList();

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
		return new Photo(filename, date, albums, categories);
	}
}