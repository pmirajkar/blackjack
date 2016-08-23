package com.casino.blackjack;

public class Card {
  
  public int    suite;
  public int    val;
  public String suiteName;
  
  public Card(int suite, int val) {
    this.suite = suite;
    this.val = val;
    switch (this.suite) {
      case 0:
        this.suiteName = "Spade";
        break;
      case 1:
        this.suiteName = "Heart";
        break;
      case 2:
        this.suiteName = "Club";
        break;
      case 3:
        this.suiteName = "Diamond";
        break;
      default:
        break;
    }
  }
  
  @Override
  public String toString() {
    return this.suiteName + ":" + this.val;
  }
}
