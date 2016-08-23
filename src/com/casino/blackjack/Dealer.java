package com.casino.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
  
  private String     name;
  private List<Card> cards;
  
  public String getName() {
    return this.name;
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
    return this.cards;
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
    for (Card c : this.cards) {
      if (c.val == 1) {
        sum += 10;
      } else {
        sum += c.val;
      }
    }
    return sum;
  }
  
  public void clearCards() {
    this.cards.clear();
  }
}
