package uk.co.boombastech.photos;

import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import uk.co.boombastech.photos.models.Photo;
import uk.co.boombastech.utils.DateUtils;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PhotoCreator {

	public Optional<Photo> create(File fileName, Metadata metadata) {
		String filename = fileName.getName();
		ExifIFD0Directory exifIFD0Directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
		String timestamp = exifIFD0Directory.getDescription(306);
		DateFormat format = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");
		Date date;
		try {
			date = format.parse(timestamp);
			return Optional.of(new Photo(null, 0, filename, date, null, null, 0, 0, 0));
		} catch (ParseException exception) {
			return Optional.empty();
		}
	}

	public Photo fromMap(Map map) {
		PhotoBuilder photoBuilder = new PhotoBuilder();

		if (map.containsKey("filename")) {
			photoBuilder.withFilename((String) map.get("filename"));
		}

		if (map.containsKey("id")) {
			photoBuilder.withId((String) map.get("id"));
		}

		if (map.containsKey("_version_")) {
			photoBuilder.withId((String) map.get("_version_"));
		}

		if (map.containsKey("categories")) {
			for (Object category : (List) map.get("categories")) {
				photoBuilder.withCategory((String) category);
			}
		}

		if (map.containsKey("albums")) {
			for (Object album : (List) map.get("albums")) {
				photoBuilder.withAlbum((String) album);
			}
		}

		if (map.containsKey("date")) {
			Optional<Date> dateOptional = DateUtils.parseDate((String) map.get("date"));
			dateOptional.ifPresent(date -> photoBuilder.withDate(date));
		}

		return photoBuilder.build();
	}
}