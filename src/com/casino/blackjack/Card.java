package com.casino.blackjack;

public class Card {

	public int suite;
	public int val;
	
	public Card(int suite, int val) {
		this.suite = suite;
		this.val = val;
	}
	
	@Override
	public String toString() {
		return suite+ ":" + val;
	}
}
