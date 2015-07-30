package com.casino.blackjack;

public class Casino {

	public static void main(String[] args) {
		Casino casino = new Casino();
		Player p = new Player("Abel", 100);
		Dealer d = new Dealer("John");
		Table t = new Table(10000);

		/*
		 * Player starts the game
		 */
		casino.startGame(p, d, t);

		/*
		 * Players place the bet
		 */
		casino.placeBets(p, t);
		casino.printGameState(p, d, t);

		/*
		 * Player Action
		 */
		int result;
		if ((result = casino.hit(p, d, t)) == -1) {
			casino.processResult(result, p, t);
		}
		casino.printGameState(p, d, t);

		/*
		 * Player Action
		 */
		casino.processResult(casino.stand(p, d, t), p, t);
		casino.printGameState(p, d, t);
	}

	public void startGame(Player p, Dealer d, Table t) {
		t.createGame();
		p.addCards(t.dealCards());
		d.addCards(t.dealCards());
	}

	public void placeBets(Player p, Table t) {
		p.setBet(10);
		t.acceptBet(p.getBet());
	}

	public int hit(Player p, Dealer d, Table t) {
		p.addCard(t.dealCard());
		return p.isValid();
	}

	public int stand(Player p, Dealer d, Table t) {
		while (d.getSum() < 17) {
			d.addCard(t.dealCard());
		}
		return checkResult(p, d);
	}

	public int checkResult(Player p, Dealer d) {
		if (p.isValid() < 0) {
			return p.isValid();
		} else {
			if (!d.isValid()) {
				return p.isValid();
			} else {
				if (p.getSum() > d.getSum()) {
					return p.isValid();
				} else if (p.getSum() < d.getSum()) {
					return -1;
				} else {
					if (0 == p.isValid()) {
						return p.isValid();
					}
					return 2;
				}
			}
		}
	}

	private void processResult(int result, Player p, Table t) {
		switch (result) {
		case -1:
			p.loseBet();
			t.winBet();
			System.exit(0);
			break;
		case 0:
			p.winBet();
			p.winBet();
			t.loseBet();
			t.loseBet();
			break;
		case 1:
			p.winBet();
			t.loseBet();
			break;
		case 2:
		default:
			break;
		}
	}

	private void printGameState(Player p, Dealer d, Table t) {
		System.out.println("Player: " + p.getName() + " Money: " + p.getMoney()
				+ " Bet: " + p.getBet() + " Cards: " + p.getCards());
		System.out
				.println("Dealer: " + d.getName() + " Cards: " + d.getCards());
		System.out.println("Table Money: " + t.getMoney() + " Bet: "
				+ t.getBet() + " Cards: " + p.getCards());
		System.out.println("***********************************************");
	}
}
