package com.casino.blackjack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLIBlackJack extends AbstractBlackJack implements IBlackJack {

  private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  @Override
  public boolean continueBlackJack() {
    try {
      System.out.print("Do you want to continue? (y/n): ");
      String line = this.br.readLine();
      return line.toLowerCase().equals("y") ? true : false;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public PlayerChoice getPlayerChoice() {
    try {
      System.out.print("1.Hit (1)\n2.Stand (2)\nYour Choice (1/2): ");
      String line = this.br.readLine();
      return line.toLowerCase().equals("1") ? PlayerChoice.HIT : PlayerChoice.STAND;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return PlayerChoice.STAND;
  }

  @Override
  public void printMessage(GameResult g) {
    System.out.println(this.p.getName() + " " + g.name());
  }

  @Override
  public void printGameState() {
    switch (this.gs) {
      case PREINIT:
      case INITDONE:
      case NEWGAME:
        System.out.println("********************************************");
        System.out.println("Player: " + this.p.getName() + " Money: " + this.p.getMoney() + " Bet: " + this.p.getBet()
            + " \nCards: " + this.p.getCards() + " Sum: " + this.p.getSum());
        System.out.println("Dealer: " + this.d.getName() + " Cards: " + this.d.getCards().get(0));
        // + " Sum: " + this.d.getSum());
        // System.out.println("Table Money: " + t.getMoney() + " Bet: " +
        // t.getBet());
        System.out.println("********************************************");
        break;
      case GAMEOVER:
        System.out.println("********************************************");
        System.out.println("*                 Game Over                *");
        System.out.println("********************************************");
        System.out.println("Player: " + this.p.getName() + " Money: " + this.p.getMoney() + " Bet: " + this.p.getBet()
            + " \nCards: " + this.p.getCards() + " Sum: " + this.p.getSum());
        System.out.println("Dealer: " + this.d.getName() + " Cards: " + this.d.getCards() + " Sum: " + this.d.getSum());
        // System.out.println("Table Money: " + t.getMoney() + " Bet: " +
        // t.getBet());
        System.out.println("********************************************");
        break;
    }
  }

  @Override
  public int getBetInfo(int game, String playerName) {
    System.out.println("********************************************");
    System.out.println(" Enter bet amount: ");
    System.out.print("1. 10\n2. 20\n3. 50\n4. 100\nYour Choice : ");
    try {
      String line = this.br.readLine();
      int bet = 0;
      switch (new Integer(line)) {
        case 1:
          bet = 10;
          break;
        case 2:
          bet = 20;
          break;
        case 3:
          bet = 50;
          break;
        case 4:
          bet = 100;
          break;
      }
      return bet;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 10;
  }

  @Override
  public void printMessage() {
    switch (this.gs) {
      case PREINIT:
        System.out.println("********************************************");
        System.out.println("*    Welcome to Black Jack Table " + this.p.getName() + "      *");
        System.out.println("********************************************");
        break;
      case INITDONE:
      case NEWGAME:
        break;
      case GAMEOVER:
        System.out.println(this.p.getName() + " " + this.gResult);
        break;
    }
  }

  @Override
  public Player getPlayerInfo() {
    return new Player("Paul", 1000);
  }

  public static void main(String[] args) {
    IBlackJack bj = new CLIBlackJack();
    bj.run();
  }
}
