package org.robertux.wordleCheater;

import static spark.Spark.get;
import static spark.Spark.port;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.http.HttpStatus;

import com.google.gson.Gson;

public class Main {
	public static String DEFAULT_PORT = "8888";
	public static String SPANISH_DICTIONARY_URL = "https://robertux-words.s3.amazonaws.com/es.txt";
	public static String ENGLISH_DICTIONARY_URL = "https://robertux-words.s3.amazonaws.com/en.txt";
	private static Logger logger = LogManager.getLogger(Main.class);
	
	public static void main(String[] args) {
		Gson gson = new Gson();
		
		logger.debug("Setting port {}", System.getProperty("port", DEFAULT_PORT));
		port(Integer.parseInt(System.getProperty("port", DEFAULT_PORT)));
		
		Map<String, WordFinder> finders = new HashMap<>();
		finders.put("es", new WordFinder(DictionaryFecther.fetch(SPANISH_DICTIONARY_URL)));
		finders.put("en", new WordFinder(DictionaryFecther.fetch(ENGLISH_DICTIONARY_URL)));
		
		get("/find/:lang/:word", (req, res) -> {
			String lang = req.params(":lang");
			String word = req.params(":word");
			logger.debug("Query for partial word {} in language", word, lang);
			
			if (!finders.containsKey(lang)) {
				res.status(HttpStatus.NOT_FOUND_404);
				res.type("text/plain");
				return "Language not found";
			}
			
			res.type("application/json");
			return gson.toJson(finders.get(lang).find(new Word(word)));
		});
	}
}
