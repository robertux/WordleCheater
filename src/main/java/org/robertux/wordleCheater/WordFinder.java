package org.robertux.wordleCheater;

import java.util.List;
import java.util.stream.Collectors;

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
	
	public Word(String content) {
		if (content.contains("|")) {
			String[] parsed = content.split("\\|");
			this.matchLetters = parsed[0];
			this.noMatchLetters = parsed[1];
		}
	}
	
	public boolean matches(String other) {
		if (this.matchLetters.length() == 0) return false;
		if (this.matchLetters.length() != other.length()) return false;
		
		for (int i = 0; i < matchLetters.length() - 1; i++) {
			if (Character.isUpperCase(this.matchLetters.charAt(i)) && this.matchLetters.toUpperCase().charAt(i) != other.toUpperCase().charAt(i)) {
				//Exact match(uppercase): debe tener la letra en la misma posicion
				return false;
			}
			if (Character.isLowerCase(this.matchLetters.charAt(i)) && this.matchLetters.toUpperCase().charAt(i) == other.toUpperCase().charAt(i)) {
				//Partial match()lowercase: debe contener la letra pero en una posicion diferente
				return false;
			}
			if (Character.isLowerCase(this.matchLetters.charAt(i)) && other.toUpperCase().indexOf(this.matchLetters.toUpperCase().charAt(i)) < 0) {
				//Partial match(lowercase): debe contener la letra
				return false;
			}
		}
		for (char c: this.noMatchLetters.toUpperCase().toCharArray()) {
			if (other.toUpperCase().indexOf(c) >= 0) {
				return false;
			}
		}
		
		return true;
	}
}