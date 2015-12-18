package uk.co.boombastech.photos;

import javax.inject.Singleton;

@Singleton
public class PhotosConfig {

	private String photoLocation;

	public String getPhotoLocation() {
		return this.photoLocation;
	}

	public void setPhotoLocation(String photoLocation) {
		this.photoLocation = photoLocation;
	}
}