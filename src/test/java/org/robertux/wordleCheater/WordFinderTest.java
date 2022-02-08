package org.robertux.wordleCheater;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WordFinderTest {
	private WordFinder finder;
	private Logger logger = LogManager.getLogger(this.getClass());
	
	@BeforeEach
	void setup() {
		this.finder = new WordFinder(Arrays.asList(new String[] {"angel", "ansel", "aseno", "aesco", "cerco"}));
	}

	@Test
	void testBasic() {
		List<String> results = this.finder.find(new Word("A__e_"));
		logger.debug("results: {}", Arrays.toString(results.toArray()));
		assertTrue(results.contains("aesco"), "results must containg word 'aesco'");
		assertTrue(results.contains("aseno"), "results must contain word 'aseno'");
		assertFalse(results.contains("angel"), "results must NOT containt word 'angel'");
	}

	@Test
	void testNoMatchLetters() {
		List<String> results = this.finder.find(new Word("A__e_|c"));
		logger.debug("results: {}", Arrays.toString(results.toArray()));
		assertFalse(results.contains("aesco"), "results must NOT contain word 'aesco'");
		assertTrue(results.contains("aseno"), "results must contain word 'aseno'");
		assertFalse(results.contains("angel"), "results must NOT contain word 'angel'");
	}
	
	@Test
	void testMultipleMatch() {
		List<String> results = this.finder.find(new Word("A___L"));
		logger.debug("results: {}", Arrays.toString(results.toArray()));
		assertTrue(results.contains("angel"), "results must contain word 'angel'");
		assertTrue(results.contains("ansel"), "results must contain word 'ansel'");
		assertFalse(results.contains("aseno"), "results must NOT contain word 'aseno'");
		assertFalse(results.contains("aesco"), "results must NOT contain word 'aesco'");
	}
	
	@Test
	void testNoResults() {
		List<String> results = this.finder.find(new Word("A___Z"));
		logger.debug("results: {}", Arrays.toString(results.toArray()));
		assertEquals(0, results.size(), "results must be empty");
	}
	
	@Test
	void testOneResult() {
		List<String> results = this.finder.find(new Word("C__r_"));
		logger.debug("results: {}", Arrays.toString(results.toArray()));
		assertTrue(results.contains("cerco"), "results must contain word 'cerco'");
		assertEquals(1, results.size(), "results must contain only one member");
	}
}