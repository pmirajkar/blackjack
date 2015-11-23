package com.casino.blackjack;

import java.io.IOException;
import java.util.Scanner;

public class Casino {

	private Player p;
	private Dealer d;
	private Table t;
	private GameResult g = GameResult.NONE;
	private Scanner scanner = new Scanner(System.in);

	private boolean continueGame() {
		System.out.print("Continue (y/n): ");
		String cont = scanner.next();
		if (cont.toLowerCase().equals("n")) {
			return false;
		} else {
			return true;
		}
	}

	private void endGame() {
		p.clearCards();
		d.clearCards();
	}

	private int getChoice() {
		System.out.print("1. Hit\n2. Stand\nYour choice: ");
		return scanner.nextInt();
	}

	private void dealInitialCards() {
		p.addCards(t.dealCards());
		d.addCards(t.dealCards());
	}

	private int getBetInfo(int game, String playerName, Scanner scanner) {
		System.out.println("Please place your bet for Round " + game + ", " + playerName);
		System.out.print("Bet Amount: ");
		return scanner.nextInt();
	}

	private void getPlayerInfo() {
		System.out.print("Your Name Please: ");
		String name = scanner.nextLine();
		System.out.print("Your Money: ");
		int money = scanner.nextInt();
		p = new Player(name, money);
		System.out.println("Welcome to BlackJack Table " + p.getName() + ".\nI am John, your dealer.");
		d = new Dealer("John");
		t = new Table(10000);
	}

	public void placeBets(int bet) {
		p.setBet(bet);
		t.acceptBet(p.getBet());
	}

	public int hit() {
		p.addCard(t.dealCard());
		return p.isValid();
	}

	private void processResult(GameResult g) {
		switch (g) {
		case LOSE:
			p.loseBet();
			t.winBet();
			System.out.println(p.getName() + " loses!");
			break;
		case BLACKJACK:
			p.winBlackJack();
			t.loseBlackJack();
			System.out.println(p.getName() + ", It's a BlackJack!");
			break;
		case WIN:
			p.winBet();
			t.loseBet();
			System.out.println(p.getName() + " wins!");
			break;
		case TIE:
			p.pushBet();
			t.pushBet();
			System.out.println(p.getName() + " tied!");
		default:
			break;
		}
	}

	private void printGameState() {
		System.out.println("***********************************************");
		System.out.println("Player: " + p.getName() + " Money: " + p.getMoney() + " Bet: " + p.getBet() + " Cards: "
				+ p.getCards() + " Sum: " + p.getSum());
		System.out.println("Dealer: " + d.getName() + " Cards: " + d.getCards() + " Sum: " + d.getSum());
		System.out.println("Table Money: " + t.getMoney() + " Bet: " + t.getBet());
		System.out.println("***********************************************");
	}

	private void runGame(int game) {
		placeBets(getBetInfo(game, p.getName(), scanner));
		dealInitialCards();

		printGameState();

		if (p.getSum() == 21) {
			if (d.getSum() == 21) {
				System.out.println("It's a tie!");
				g = GameResult.TIE;
			} else {
				System.out.println("BlackJack! " + p.getName() + " wins!");
				g = GameResult.BLACKJACK;
			}
		}

		int choice = 1;
		while (choice == 1) {
			choice = getChoice();
			if (choice == 2)
				break;
			hit();

			printGameState();

			if (p.getSum() > 21) {
				g = GameResult.LOSE;
				break;
			} else if (p.getSum() < 21) {
				continue;
			} else {
				if (d.getSum() == 21) {
					g = GameResult.TIE;
				}
				g = GameResult.WIN;
				break;
			}
		}

		if (g == GameResult.NONE) {
			while (d.getSum() <= 17) {
				d.addCard(t.dealCard());
			}

			if (p.getSum() > d.getSum()) {
				g = GameResult.WIN;
			} else if (p.getSum() < d.getSum()) {
				g = GameResult.LOSE;
			} else {
				g = GameResult.TIE;
			}
		}

		processResult(g);

		printGameState();
		
		endGame();
	}

	
	private void runBlackJack() {
		int game = 1;
		while (true) {
			runGame(game);
			if (continueGame()) {
				break;
			}
			game++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Casino casino = new Casino();
		casino.getPlayerInfo();
		casino.runBlackJack();
		casino.scanner.close();
	}

}
