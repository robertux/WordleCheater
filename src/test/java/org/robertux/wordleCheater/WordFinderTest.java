package org.robertux.wordleCheater;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

class WordFinderTest {
	private Logger logger = LogManager.getLogger(this.getClass());

	@Test
	void testBasic() {
		WordFinder finder = new WordFinder(Arrays.asList(new String[] {"angel", "ansel", "aseno", "aesco", "cerco"}));
		List<String> results = finder.find(new Word("A__e_"));
		logger.debug("results: {}", Arrays.toString(results.toArray()));
		assertTrue(results.contains("aesco"));
		assertTrue(results.contains("aseno"));
		assertFalse(results.contains("angel"));
	}

}