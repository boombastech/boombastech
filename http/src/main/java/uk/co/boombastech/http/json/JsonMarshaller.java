package uk.co.boombastech.http.json;

import java.io.BufferedReader;
import java.util.List;
import java.util.Map;

public interface JsonMarshaller {
	<T> String toJson(T object);
	<T> T fromJson(String json, Class<T> clazz);
	Map<String, Map> fromJson(BufferedReader reader);
}