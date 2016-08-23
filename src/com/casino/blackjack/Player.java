package com.casino.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {
  
  private String     name;
  private int        money;
  private int        bet;
  private List<Card> cards;
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public int getMoney() {
    return this.money;
  }
  
  public void setMoney(int money) {
    this.money = money;
  }
  
  public int getBet() {
    return this.bet;
  }
  
  public void setBet(int bet) {
    this.money -= bet;
    this.bet = bet;
  }
  
  public Player(String name, int money) {
    super();
    this.cards = new ArrayList<Card>();
    this.name = name;
    this.money = money;
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
  
  public int isValid() {
    return (getSum() == 21) ? 0 : (getSum() < 21) ? 1 : -1;
  }
  
  public int getSum() {
    int sum = 0;
    for (Card c : this.cards) {
      sum += c.val;
    }
    return sum;
  }
  
  public void loseBet() {
    this.bet = 0;
  }
  
  public void winBet() {
    this.money += (2 * this.bet);
    this.bet = 0;
  }
  
  public void winBlackJack() {
    this.money += (this.bet * 2.5);
    this.bet = 0;
  }
  
  public void pushBet() {
    this.money += this.bet;
    this.bet = 0;
  }
  
  public void clearCards() {
    this.cards.clear();
  }
}
