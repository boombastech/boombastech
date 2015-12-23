package uk.co.boombastech.photos;

import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import uk.co.boombastech.photos.models.Photo;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhotoCreator {

	public Photo create(File fileName, Metadata metadata) {
		String filename = fileName.getName();
		ExifIFD0Directory exifIFD0Directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
		String timestamp = exifIFD0Directory.getDescription(306);
		DateFormat format = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");
		Date date;
		try {
			date = format.parse(timestamp);
			return new Photo(filename, date, null, null);
		} catch (ParseException exception) {
			return new Photo(filename, null, null, null);
		}
	}
}