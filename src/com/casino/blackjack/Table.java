package com.casino.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Table {
  
  private List<Card> cards;
  private int        money;
  private int        bet;
  private int        index;
  
  public Table(int money) {
    this.money = money;
    this.index = 0;
    this.cards = new ArrayList<Card>();
    for (int i = 0; i < 4; i++) {
      for (int j = 1; j < 14; j++) {
        Card c = new Card(i, (j > 10) ? 10 : j);
        this.cards.add(c);
      }
    }
    Collections.shuffle(this.cards);
  }
  
  public void acceptBet(int bet) {
    this.bet = bet;
  }
  
  public List<Card> dealCards() {
    List<Card> dealtCards = new ArrayList<>();
    dealtCards.add(this.cards.get(this.index++));
    dealtCards.add(this.cards.get(this.index++));
    return dealtCards;
  }
  
  public Card dealCard() {
    return this.cards.get(this.index++);
  }
  
  public void loseBet() {
    this.money -= this.bet;
    this.bet = 0;
  }
  
  public void loseBlackJack() {
    this.money -= (1.5 * this.bet);
    this.bet = 0;
  }
  
  public void winBet() {
    this.money += this.bet;
    this.bet = 0;
  }
  
  public int getMoney() {
    return this.money;
  }
  
  public int getBet() {
    return this.bet;
  }
  
  public void pushBet() {
    // this.money += bet;
  }
}
