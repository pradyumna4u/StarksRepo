package com.api.framework;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtil {

	public static String getJsonValue(String path, String jsonelementpath)
			throws FileNotFoundException, IOException, JSONException {
		String JsonElementAsString = null;
		JsonElement JsonElement1 = null;
		try {
			JsonParser parser = new JsonParser();

			Object obj = parser.parse(new FileReader(path));

			JsonObject jsonObject = (JsonObject) obj;

			String[] ElementsarrayFromjsonPath = jsonelementpath.split(":");
			for (String elementnodeInjsonFile : ElementsarrayFromjsonPath) {
				if (ElementsarrayFromjsonPath[0] == elementnodeInjsonFile) {
					JsonElement1 = jsonObject.getAsJsonObject().get(elementnodeInjsonFile);
				} else {
					if (JsonElement1 != null) {
						JsonElement1 = JsonElement1.getAsJsonObject().get(elementnodeInjsonFile);
						JsonElementAsString = JsonElement1.toString();
						JsonElementAsString = JsonElementAsString.substring(1, JsonElementAsString.length() - 1);
					}
				}
			}

		} catch (Exception e) {

		}
		return JsonElementAsString;
	}

	public static void validateJson(String Actual, String ExpectedResponseFile) throws Exception {
		JsonParser parser = new JsonParser();
		JsonElement o1 = parser.parse(Actual);
		JsonElement o2 = parser.parse(Utility.readtxt(ExpectedResponseFile));
		Assert.assertTrue((o1.equals(o2)));

	}

	private static JSONObject getRESTData(String string) throws JSONException {
		JSONObject jsonObj = new JSONObject(string);
		return jsonObj;
	}
}
