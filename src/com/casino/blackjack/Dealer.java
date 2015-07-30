package com.casino.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

	private String name;
	private List<Card> cards;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
		
	public Dealer(String name) {
		super();
		this.cards = new ArrayList<Card>();
		this.name = name;
	}
	
	public List<Card> getCards() {
		return cards;
	}
	
	public void addCard(Card card) {
		this.cards.add(card);
	}
	
	public void addCards(List<Card> pCards) {
		this.cards.addAll(pCards);
	}
	
	public boolean isValid() {
		return (getSum() <= 21) ? true : false;
	}

	public int getSum() {
		int sum = 0;
		for (Card c : cards) {
			sum += c.val;
		}
		return sum;
	}
}
