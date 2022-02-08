package org.robertux.wordleCheater;

import static spark.Spark.*;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
	public static String DEFAULT_PORT = "8888";
	private static Logger logger = LogManager.getLogger(Main.class);
	
	public static void main(String[] args) {
		logger.debug("Setting port {}", System.getProperty("port", DEFAULT_PORT));
		port(Integer.parseInt(System.getProperty("port", DEFAULT_PORT)));
		
		get("/find/:name", (req, res) -> {
			logger.debug("Query for partial word {}", req.params(":name"));
			WordFinder finder = new WordFinder(Arrays.asList(new String[] {"angel", "ansel", "aseno", "aesco", "cerco"}));
			res.type("application/json");
			return Arrays.toString(finder.find(new Word(req.params(":word"))).toArray());
		});
	}
}
