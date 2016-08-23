package com.casino.blackjack;

public interface IBlackJack {
  
  public boolean continueBlackJack();
  
  public void endGame();
  
  public void processGameResult(GameResult g);
  
  public int hit();
  
  public PlayerChoice getPlayerChoice();
  
  public void printMessage(GameResult g);
  
  public void printGameState();
  
  public void dealCards(int game);
  
  public int getBetInfo(int game, String playerName);
  
  public void placeBets(int bet);
  
  public void executeGame(int game);
  
  public void printMessage();
  
  public Player getPlayerInfo();
  
  public void init();
  
  public void run();
}
