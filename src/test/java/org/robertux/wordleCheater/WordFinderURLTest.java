package org.robertux.wordleCheater;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WordFinderURLTest {
	private WordFinder finder;
	private Logger logger = LogManager.getLogger(this.getClass());
	private List<String> dictionary;

	@BeforeEach
	void setUp() throws Exception {
		this.dictionary = DictionaryFecther.fetch("https://robertux-words.s3.amazonaws.com/es.txt");
		this.finder = new WordFinder(dictionary);
	}

	@Test
	void testDictionaryLoaded() {
		assertNotNull(this.dictionary, "dictionary object must NOT be null");
		assertNotEquals(0, this.dictionary, "dictionary object must NOT be empty");
		assertTrue(this.dictionary.contains("agape"), "dictionary object must contain word 'agape'");
	}

	@Test
	void testWordFinder() {
		List<String> results = this.finder.find(new Word("A__gE"));
		logger.debug("results: {}", Arrays.toString(results.toArray()));
		assertTrue(results.contains("agape"), "results must contain word 'agape'");
	}
}
