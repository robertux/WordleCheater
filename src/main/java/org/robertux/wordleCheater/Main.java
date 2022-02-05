package org.robertux.wordleCheater;

import static spark.Spark.*;

public class Main {
	public static void main(String[] args) {
		port(Integer.parseInt(System.getProperty("port")));
		
		get("/query/:name", (req, res) -> {
			return "Hello: " + req.params(":name");
		});
	}
}
