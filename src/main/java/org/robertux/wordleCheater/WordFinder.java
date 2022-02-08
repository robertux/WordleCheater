package org.robertux.wordleCheater;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordFinder {
	private List<String> dictionary;
	
	public WordFinder(List<String> dictionary) {
		this.dictionary = dictionary;
	}
	
	public List<String> find(Word word) {
		return dictionary.stream().filter(w -> word.matches(w)).collect(Collectors.toList());
	}
}

class Word {
	private String matchLetters;
	private String noMatchLetters;
	private Logger logger = LogManager.getLogger(getClass());
	
	public Word(String content) {
		if (content.contains("|")) {
			String[] parsed = content.split("\\|");
			this.matchLetters = parsed[0];
			this.noMatchLetters = parsed[1];
		} else {
			this.matchLetters = content;
		}
	}
	
	public boolean matches(String word) {
		if (this.matchLetters == null) return false;
		if (this.matchLetters.length() == 0) return false;
		if (this.matchLetters.length() != word.length()) return false;
		
		logger.debug("matchLetters: {} word to match: {}", this.matchLetters, word.toUpperCase());
		for (int i = 0; i < matchLetters.length(); i++) {
			if (Character.isUpperCase(this.matchLetters.charAt(i)) && this.matchLetters.toUpperCase().charAt(i) != word.toUpperCase().charAt(i)) {
				//Exact match(uppercase): debe tener la letra en la misma posicion
				this.logger.debug("{} not an exact match with {}", this.matchLetters.toUpperCase().charAt(i), word.toUpperCase().charAt(i));
				return false;
			}
			if (Character.isLowerCase(this.matchLetters.charAt(i)) && this.matchLetters.toUpperCase().charAt(i) == word.toUpperCase().charAt(i)) {
				//Partial match(lowercase): debe contener la letra pero en una posicion diferente
				this.logger.debug("Word {} contains letter {} but in wrong position", word.toUpperCase(), this.matchLetters.toUpperCase().charAt(i));
				return false;
			}
			if (Character.isLowerCase(this.matchLetters.charAt(i)) && word.toUpperCase().indexOf(this.matchLetters.toUpperCase().charAt(i)) < 0) {
				//Partial match(lowercase): debe contener la letra
				this.logger.debug("Word {} doesn't contain letter {}", word.toUpperCase(), this.matchLetters.toUpperCase().charAt(i));
				return false;
			}
		}
		if (this.noMatchLetters != null) {
			for (char c: this.noMatchLetters.toUpperCase().toCharArray()) {
				if (word.toUpperCase().indexOf(c) >= 0) {
					this.logger.debug("Word {} shouldn't contain character {}", word.toUpperCase(), Character.toUpperCase(c));
					return false;
				}
			}
		}
		
		return true;
	}
}