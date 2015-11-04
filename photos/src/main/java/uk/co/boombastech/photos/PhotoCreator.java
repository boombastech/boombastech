package uk.co.boombastech.photos;

import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Descriptor;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDescriptor;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.file.FileMetadataDescriptor;
import com.drew.metadata.file.FileMetadataDirectory;
import com.google.common.collect.Lists;

import java.io.File;
import java.nio.file.Path;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.google.common.collect.Lists.newArrayList;

public class PhotoCreator {

	public Photo create(File fileName, Metadata metadata) {
		String filename = fileName.getName().toString();
		ExifIFD0Directory exifIFD0Directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
		String timestamp = exifIFD0Directory.getDescription(306);
		DateFormat format = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");
		Date date;
		try {
			date = format.parse(timestamp);
		} catch (ParseException exception) {
			date = null;
		}
		return new Photo(filename, date);
	}
}