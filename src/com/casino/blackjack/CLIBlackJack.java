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
			String line = br.readLine();
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
			String line = br.readLine();
			return line.toLowerCase().equals("1") ? PlayerChoice.HIT : PlayerChoice.STAND;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return PlayerChoice.STAND;
	}

	@Override
	public void printMessage(GameResult g) {
		System.out.println(p.getName() + " " + g.name());
	}

	@Override
	public void printGameState() {
		switch (gs) {
		case PREINIT:
		case INITDONE:
		case NEWGAME:
			System.out.println("********************************************");
			System.out.println("Player: " + p.getName() + " Money: " + p.getMoney() + " Bet: " + p.getBet()
					+ " \nCards: " + p.getCards() + " Sum: " + p.getSum());
			// System.out.println("Dealer: " + d.getName() + " Cards: " +
			// d.getCards() + " Sum: " + d.getSum());
			// System.out.println("Table Money: " + t.getMoney() + " Bet: " +
			// t.getBet());
			System.out.println("********************************************");
			break;
		case GAMEOVER:
			System.out.println("********************************************");
			System.out.println("Player: " + p.getName() + " Money: " + p.getMoney() + " Bet: " + p.getBet()
					+ " \nCards: " + p.getCards() + " Sum: " + p.getSum());
			System.out.println("Dealer: " + d.getName() + " Cards: " + d.getCards() + " Sum: " + d.getSum());
			// System.out.println("Table Money: " + t.getMoney() + " Bet: " +
			// t.getBet());
			System.out.println("********************************************");
			break;
		}
	}

	@Override
	public int getBetInfo(int game, String playerName) {
		return 10;
	}

	@Override
	public void printMessage() {
		switch (gs) {
		case PREINIT:
			System.out.println("********************************************");
			System.out.println("*    Welcome to Black Jack Table " + p.getName() + "      *");
			System.out.println("********************************************");
			break;
		case INITDONE:
		case NEWGAME:
			break;
		case GAMEOVER:
			System.out.println(p.getName() + " " + gResult);
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
