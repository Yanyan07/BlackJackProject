package com.skilldistillery.blackjack.app;

import java.util.Scanner;
import com.skilldistillery.blackjack.entities.Dealer;
import com.skilldistillery.blackjack.entities.Player;

public class BlackjackApp {
	Player player = new Player();
	Dealer dealer = new Dealer();
	Scanner sc = new Scanner(System.in);
	static boolean keepPlaying = true;

	public static void main(String[] args) {
		BlackjackApp bja = new BlackjackApp();
		bja.run();
		bja.sc.close();
	}
	
	private void run() {
		while(keepPlaying) {
			startGame();
			playerTurn();
			dealerTurn();
			displayResult();
			checkContinue();
		}
	}	
		
	private void startGame() {
		dealer.dealerShuffle();
		for (int i = 0; i < 2; i++) {
			dealer.dealCard(dealer);
			dealer.dealCard(player);
		}
		displayFirstTwoCards();
		if(player.isBlackjack()) {
			System.out.println("Player : Blackjack!!!");
		}
	}	
		
	private void playerTurn() {
		while (!player.isBlackjack() && dealer.cardNumInDeck()>=1) {
			System.out.println("Enter your choice: ");
			System.out.println("1. Hit");
			System.out.println("2. Stand");
			int choice = sc.nextInt();
			if (choice == 1) {
				dealer.dealCard(player);
				displayCardsAndValue(player);
				if(player.isBust()) {
					break;
				}
			} else {
				break;
			}
		}
	}
	
	private void dealerTurn() {
		while(!player.isBust() && dealer.cardNumInDeck()>=1 && dealer.getHandValue()<17) {
				dealer.dealCard(dealer);
		}
		if(dealer.isBlackjack()) {
			System.out.println("Dealer : Blackjack!!!");
		}
	}
	
	private void displayResult() {
		System.out.println("Display result...");
		
		if(dealer.cardNumInDeck() < 1) {
			System.out.println("Not enough cards left, please exit to restart game.");
		}else {
			displayCardsAndValue(player);
			displayCardsAndValue(dealer);
			int dealerValue = dealer.getHandValue();
			int playerValue = player.getHandValue();
			if(player.isBust()) {
				System.out.println("Player Bust! Dealer Win!");
			}else if (dealer.isBust()) {
				System.out.println("Dealer Bust! Player Win!");
			}else if(player.isBlackjack() && dealer.isBlackjack()) {
				System.out.println("Push!");
			}else if(player.isBlackjack()) {
				System.out.println("Player Win!");
			}else if(dealer.isBlackjack()) {
				System.out.println("Dealer Win!");
			}else if (dealerValue <= 21 && !dealer.isBlackjack() && playerValue<=21 && !player.isBlackjack()) {
				if (dealerValue > playerValue) {
					System.out.println("Dealer Win!");
				} else if (dealerValue < playerValue) {
					System.out.println("Player Win!");
				} else {
					System.out.println("Push!");
				}
			}
		}
	}
	
	private void checkContinue() {
		System.out.print("Enter Y/y to keep playing, N/n to exit: ");
		String select = sc.next();
		if (select.equalsIgnoreCase("Y") && dealer.cardNumInDeck()>=4) {
			keepPlaying = true;
		} else {
			if(dealer.cardNumInDeck() < 4) {
				System.out.println("Not enough cards left. ");
			}
			System.out.println("Goodbye!");
			keepPlaying = false;
		}
		player.clear();
		dealer.clear();
		System.out.println();
	}
	
	private void displayFirstTwoCards() {
		System.out.println("Player: ");
		System.out.println(player);
		System.out.println("total value: " + player.getHandValue());
		System.out.println("--------------------------------------");
		System.out.println("Dealer: ");
		dealer.showFirstCard();
		System.out.println("total value: " + dealer.getFirstValue());
		System.out.println("--------------------------------------");
	}

	private void displayCardsAndValue(Player player) {
		System.out.println(player.getClass().getSimpleName());
		System.out.println(player);
		System.out.println("total value: " + player.getHandValue());
		System.out.println("--------------------------------------");
	}

}


