package com.clarkrao.springboot.test;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonArrayTest {
    public static void main(String[] args) {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < 20; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("key","key->"+i);
            jsonArray.put(jsonObject);
        }
        jsonArray = make(jsonArray);
        int length = jsonArray.length();
        for (int i = 0; i < length; i++) {
            System.out.println(jsonArray.getJSONObject(i).get("key"));
            System.out.println(jsonArray.getJSONObject(i).get("name"));
        }
    }

    private static JSONArray make(JSONArray jsonArray) {
        int length = jsonArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            jsonObject.put("name","name->" +i);
        }
        return jsonArray;
    }
}
