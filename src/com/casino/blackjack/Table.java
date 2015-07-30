package com.casino.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Table {

	private List<Card> cards;
	private int money;
	private int bet;
	private int index;

	public Table(int money) {
		this.money = money;
		index = 0;
		cards = new ArrayList<Card>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				Card c = new Card(i, j);
				cards.add(c);
			}
		}
		Collections.shuffle(cards);
	}
	
	public void createGame() {
		index = 0;
		cards = new ArrayList<Card>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				Card c = new Card(i, j);
				cards.add(c);
			}
		}
		Collections.shuffle(cards);
	}

	public void acceptBet(int bet) {
		this.bet = bet;
	}

	public List<Card> dealCards() {
		List<Card> dealtCards = new ArrayList<>();
		dealtCards.add(cards.get(index++));
		dealtCards.add(cards.get(index++));
		return dealtCards;
	}

	public Card dealCard() {
		return cards.get(index++);
	}

	public void loseBet() {
		this.money -= bet;
	}
	
	public void winBet() {
		this.money += bet;
	}

	public int getMoney() {
		return money;
	}

	public int getBet() {
		return bet;
	}
}
