package com.cs.edcSyncAlive.controllers;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.cs.edcSyncAlive.constants.Constants;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class GsonListDeserializer implements JsonDeserializer<List<String>> {

	@Override
	public List<String> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		List<String> result = new ArrayList<>();

		if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isString()) {
			String value = json.getAsString();
			System.err.println(">>"+value);
			if (Constants.NO_DATA.replaceAll("\\s+", "").equalsIgnoreCase(value.replaceAll("\\s+", ""))) {
				result.add(Constants.NO_DATA);
			} else {
				result.add(Constants.NO_DATA);
			}
		} else if (json.isJsonArray()) {
			for (JsonElement element : json.getAsJsonArray()) {
				result.add(element.getAsString());
			}
		}else {
			result.add(null);
		}
		return result;
	}

}
