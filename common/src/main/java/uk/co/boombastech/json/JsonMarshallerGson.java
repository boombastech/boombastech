package uk.co.boombastech.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.BufferedReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Singleton
public class JsonMarshallerGson implements JsonMarshaller {

	private final Gson gson;

	@Inject
	public JsonMarshallerGson(Gson gson) {
		this.gson = gson;
	}

	@Override
	public <T> String toJson(T object) {
		return gson.toJson(object);
	}

	@Override
	public <T> T fromJson(String json, Class<T> clazz) {
		return gson.fromJson(json, clazz);
	}

	@Override
	public List<Map<String, String>> fromJson(BufferedReader reader) {
		Type listType = new TypeToken<ArrayList<Map>>() {}.getType();
		return gson.fromJson(reader, listType);
	}
}