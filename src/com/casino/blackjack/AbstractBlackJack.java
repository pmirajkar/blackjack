package com.casino.blackjack;

public abstract class AbstractBlackJack implements IBlackJack {

  protected Player     p;
  protected Dealer     d;
  protected Table      t;
  protected GameResult gResult = GameResult.NONE;
  protected GameState  gs      = GameState.NONE;

  @Override
  public abstract boolean continueBlackJack();

  @Override
  public void endGame() {
    this.p.clearCards();
    this.d.clearCards();
    this.gResult = GameResult.NONE;
  }

  @Override
  public void processGameResult(GameResult g) {
    switch (g) {
      case LOSE:
        this.p.loseBet();
        this.t.winBet();
        break;
      case BLACKJACK:
        this.p.winBlackJack();
        this.t.loseBlackJack();
        break;
      case WIN:
        this.p.winBet();
        this.t.loseBet();
        break;
      case TIE:
        this.p.pushBet();
        this.t.pushBet();
      default:
        break;
    }
  }

  @Override
  public int hit() {
    this.p.addCard(this.t.dealCard());
    return this.p.isValid();
  }

  @Override
  public abstract PlayerChoice getPlayerChoice();

  @Override
  public abstract void printMessage(GameResult g);

  @Override
  public abstract void printGameState();

  @Override
  public void dealCards(int game) {
    this.p.addCards(this.t.dealCards());
    this.d.addCards(this.t.dealCards());
  }

  @Override
  public abstract int getBetInfo(int game, String playerName);

  @Override
  public void placeBets(int bet) {
    this.p.setBet(bet);
    this.t.acceptBet(this.p.getBet());
  }

  @Override
  public void executeGame(int game) {
    placeBets(getBetInfo(game, this.p.getName()));
    dealCards(game);
    printGameState();

    if (this.p.getSum() == 21) {
      if (this.d.getSum() == 21) {
        this.gResult = GameResult.TIE;
      } else {
        this.gResult = GameResult.BLACKJACK;
      }
    } else if (this.d.getSum() == 21) {
      this.gResult = GameResult.LOSE;
    }

    while ((this.gResult == GameResult.NONE) && (PlayerChoice.HIT == getPlayerChoice())) {
      hit();
      printGameState();

      if (this.p.getSum() > 21) {
        this.gResult = GameResult.LOSE;
        break;
      } else if (this.p.getSum() < 21) {
        continue;
      } else {
        if (this.d.getSum() == 21) {
          this.gResult = GameResult.TIE;
        } else {
          this.gResult = GameResult.WIN;
        }
        break;
      }
    }

    if (this.gResult == GameResult.NONE) {
      while (this.d.getSum() <= 17) {
        this.d.addCard(this.t.dealCard());
      }

      if (this.d.getSum() > 21) {
        this.gResult = GameResult.WIN;
      } else if (this.p.getSum() > this.d.getSum()) {
        this.gResult = GameResult.WIN;
      } else if (this.p.getSum() < this.d.getSum()) {
        this.gResult = GameResult.LOSE;
      } else {
        this.gResult = GameResult.TIE;
      }
    }
    this.gs = GameState.GAMEOVER;
    processGameResult(this.gResult);

    printGameState();
    printMessage();

    endGame();
  }

  @Override
  public abstract void printMessage();

  @Override
  public abstract Player getPlayerInfo();

  @Override
  public void init() {
    this.gResult = GameResult.NONE;
    this.p = getPlayerInfo();
    this.d = new Dealer("John");
    this.t = new Table(10000);
    printMessage();
  }

  @Override
  public void run() {
    this.gs = GameState.PREINIT;
    int game = 1;
    init();
    this.gs = GameState.INITDONE;
    while (true) {
      this.gs = GameState.NEWGAME;
      printMessage();
      if (this.p.getMoney() < 0) {
        break;
      }
      executeGame(game);
      this.gs = GameState.ENDGAME;
      printMessage();
      if (!continueBlackJack()) {
        break;
      }
      game++;
    }
  }
}
