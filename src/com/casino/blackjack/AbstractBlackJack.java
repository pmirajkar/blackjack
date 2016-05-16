package com.casino.blackjack;

public abstract class AbstractBlackJack implements IBlackJack {

	protected Player p;
	protected Dealer d;
	protected Table t;
	protected GameResult gResult = GameResult.NONE;
	protected GameState gs = GameState.NONE;

	public abstract boolean continueBlackJack();

	public void endGame() {
		p.clearCards();
		d.clearCards();
		gResult = GameResult.NONE;
	}

	public void processGameResult(GameResult g) {
		switch (g) {
		case LOSE:
			p.loseBet();
			t.winBet();
			break;
		case BLACKJACK:
			p.winBlackJack();
			t.loseBlackJack();
			break;
		case WIN:
			p.winBet();
			t.loseBet();
			break;
		case TIE:
			p.pushBet();
			t.pushBet();
		default:
			break;
		}
	}

	public int hit() {
		p.addCard(t.dealCard());
		return p.isValid();
	}

	public abstract PlayerChoice getPlayerChoice();

	public abstract void printMessage(GameResult g);

	public abstract void printGameState();

	public void dealCards(int game) {
		p.addCards(t.dealCards());
		d.addCards(t.dealCards());
	}

	public abstract int getBetInfo(int game, String playerName);

	public void placeBets(int bet) {
		p.setBet(bet);
		t.acceptBet(p.getBet());
	}

	public void executeGame(int game) {

		placeBets(getBetInfo(game, p.getName()));
		dealCards(game);
		printGameState();

		if (p.getSum() == 21) {
			if (d.getSum() == 21) {
				gResult = GameResult.TIE;
			} else {
				gResult = GameResult.BLACKJACK;
			}
		}

		while (PlayerChoice.HIT == getPlayerChoice()) {
			hit();

			printGameState();

			if (p.getSum() > 21) {
				gResult = GameResult.LOSE;
				break;
			} else if (p.getSum() < 21) {
				continue;
			} else {
				if (d.getSum() == 21) {
					gResult = GameResult.TIE;
				} else {
					gResult = GameResult.WIN;
				}
				break;
			}
		}

		if (gResult == GameResult.NONE) {
			while (d.getSum() <= 17) {
				d.addCard(t.dealCard());
			}

			if (d.getSum() > 21) {
				gResult = GameResult.WIN;
			} else if (p.getSum() > d.getSum()) {
				gResult = GameResult.WIN;
			} else if (p.getSum() < d.getSum()) {
				gResult = GameResult.LOSE;
			} else {
				gResult = GameResult.TIE;
			}
		}
		gs = GameState.GAMEOVER;
		processGameResult(gResult);

		printGameState();
		printMessage();

		endGame();
	}

	public abstract void printMessage();

	public abstract Player getPlayerInfo();

	public void init() {
		gResult = GameResult.NONE;
		p = getPlayerInfo();
		d = new Dealer("John");
		t = new Table(10000);
		printMessage();
	}

	public void run() {
		gs = GameState.PREINIT;
		int game = 1;
		init();
		gs = GameState.INITDONE;
		while (true) {
			gs = GameState.NEWGAME;
			printMessage();
			if (p.getMoney() < 0)
				break;
			executeGame(game);
			gs = GameState.ENDGAME;
			printMessage();
			if (!continueBlackJack())
				break;
			game++;
		}
	}
}
