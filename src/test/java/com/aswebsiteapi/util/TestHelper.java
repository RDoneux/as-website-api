package com.aswebsiteapi.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class TestHelper {

    public static <T> String serializeObject(T object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

    public static JSONObject parseJson(String input) throws JSONException {
        return (JSONObject) JSONParser.parseJSON(input);
    }

    public static JSONArray parseJsonArray(String input) throws JSONException {
        return (JSONArray) JSONParser.parseJSON(input);
    }

    public static HttpEntity<String> getHttpEntity(String payload) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        return new HttpEntity<>(payload, headers);
    }

}
