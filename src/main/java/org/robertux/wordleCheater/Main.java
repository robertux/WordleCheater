package org.robertux.wordleCheater;

import static spark.Spark.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
	public static String DEFAULT_PORT = "8888";
	private static Logger logger = LogManager.getLogger(Main.class);
	
	public static void main(String[] args) {
		logger.debug("Setting port {}", System.getProperty("port", DEFAULT_PORT));
		port(Integer.parseInt(System.getProperty("port", DEFAULT_PORT)));
		
		get("/query/:name", (req, res) -> {
			logger.debug("Query for partial word {}", req.params(":name"));
			return "Hello: " + req.params(":name");
		});
	}
}
