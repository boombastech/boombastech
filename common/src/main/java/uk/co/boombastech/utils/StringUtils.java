package uk.co.boombastech.utils;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class StringUtils {

	public static List<String> split(String string, String regex) {
		List<String> strings = newArrayList();
		for (String s : string.split(regex)) {
			strings.add(s.trim());
		}

		return strings;
	}
}