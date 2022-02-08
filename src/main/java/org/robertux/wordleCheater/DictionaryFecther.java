package org.robertux.wordleCheater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DictionaryFecther {
	private static Logger logger = LogManager.getFormatterLogger(DictionaryFecther.class);
	
	public static List<String> fetch(String urlString) {
		List<String> words = new ArrayList<>();
		String word = null;
		
		try {
			URL url = new URL(urlString);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			while((word = br.readLine()) != null) {
				words.add(word);
			}
		} catch (IOException e) {
			logger.error("IOException from URL " + urlString + ": " + e.getMessage(), e);
		}
		
		return words;
	}
}
