package com.delices.utils;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class Tools {
	public static JSONObject erreur(String s){
		JSONObject json = new JSONObject();
		try {
			json.put("error", s);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}
}
