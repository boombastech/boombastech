package uk.co.boombastech.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class DateUtils {

	public static Optional<Date> parseDate(String dateToParse) {
		return parseDate(dateToParse, "dd MMMMM yyyy");
	}

	public static Optional<Date> parseDate(String dateToParse, String dateFormat) {
		try {
			DateFormat format = new SimpleDateFormat(dateFormat);
			return Optional.of(format.parse(dateToParse));
		} catch (ParseException e) {
			return Optional.empty();
		}
	}
}