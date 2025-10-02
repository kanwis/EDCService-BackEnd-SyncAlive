package com.cs.edcSyncAlive.controllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cs.edcSyncAlive.constants.Constants;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class JacksonListDeserializer extends JsonDeserializer<List<String>>{

	@Override
	public List<String> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		JsonNode node = p.getCodec().readTree(p);
        List<String> result = new ArrayList<>();

//        if (node.isTextual() && Constants.NO_DATA.equalsIgnoreCase(node.asText())) {
        if (node.isTextual() && Constants.NO_DATA.equalsIgnoreCase(node.asText())) {
        	result.add(Constants.NO_DATA);
//            return result; // คืนค่าเป็น List ว่าง []
        }else if (node.isArray()) {
            for (JsonNode item : node) {
                result.add(item.asText());
            }
        }else {
        	result.add(Constants.NO_DATA);
        }
        return result;
//        throw new IOException("Invalid data format for dataUsage");
	}

}
