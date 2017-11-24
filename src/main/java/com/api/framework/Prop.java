package com.api.framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class Prop {
	private static String proppath = "src/main/resources/test.properties";

	public static String getProp(String key) {
		String value = StringUtils.EMPTY;
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(proppath);
			prop.load(input);
			value=prop.getProperty(key);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}
}