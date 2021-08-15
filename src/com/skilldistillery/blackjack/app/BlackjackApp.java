package com.skilldistillery.blackjack.app;

import java.util.Scanner;
import com.skilldistillery.blackjack.entities.Dealer;
import com.skilldistillery.blackjack.entities.Player;

public class BlackjackApp {
	Player player = new Player();
	Dealer dealer = new Dealer();

	public static void main(String[] args) {
		BlackjackApp bja = new BlackjackApp();
		bja.run();
	}

	private void run() {
		Scanner sc = new Scanner(System.in);
		boolean keepPlaying = true;
		boolean playerGoing = true;
		boolean dealerGoing = true;

		while (keepPlaying) {
			if (dealer.cardNumInDeck() < 4) {
				System.out.println("Not enough cards left, please exit to restart game!");
				break;
			}
			dealer.dealerShuffle();
			for (int i = 0; i < 2; i++) {
				dealer.dealCard(dealer);
				dealer.dealCard(player);
			}
			displayFirstTwoCards();
			if(player.isBlackjack()) {
				System.out.println("Player : Blackjack!!!");
				playerGoing = false;
			}
			while (playerGoing) {
				if (dealer.cardNumInDeck() < 1) {
					System.out.println("Not enough cards left, please exit to restart game!");
					playerGoing = false;
					dealerGoing = false;
					keepPlaying = false;
					break;
				}
				System.out.println("Enter your choice: ");
				System.out.println("1. Hit");
				System.out.println("2. Stand");
				int choice = sc.nextInt();
				if (choice == 1) {
					dealer.dealCard(player);
					displayCardsAndValue(player);
					if(player.isBust()) {
						displayCardsAndValue(dealer);
						System.out.println("Player Bust! Dealer Win!");
						dealerGoing = false;
						break;
					}
				} else {
					break;
				}
			}
			while (dealerGoing) {
				if(dealer.isBlackjack()) {
					System.out.println("Dealer : Blackjack!!!");
					dealerGoing = false;
					
				}
				while (dealer.getHandValue() < 17) {
					if (dealer.cardNumInDeck() < 1) {
						System.out.println("Not enough cards left, please exit to restart game!");
						playerGoing = false;
						dealerGoing = false;
						break;
					}
					dealer.dealCard(dealer);
				}
				displayCardsAndValue(player);
				displayCardsAndValue(dealer);
				displayResult();
				break;
			}
			
			System.out.print("Enter Y/y to keep playing, N/n to exit: ");
			String select = sc.next();
			if (select.equalsIgnoreCase("Y")) {
				keepPlaying = true;
				playerGoing = true;
				dealerGoing = true;
				player.clear();
				dealer.clear();
			} else {
				System.out.println("Goodbye!");
				keepPlaying = false;
			}
			System.out.println();
		}
		sc.close();
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

	private void displayResult() {
		int dealerValue = dealer.getHandValue();
		int playerValue = player.getHandValue();

		if (dealer.isBust()) {
			System.out.println("Dealer Bust! Player Win!");
		} 
		if(player.isBlackjack() && dealer.isBlackjack()) {
			System.out.println("Push!");
		}else if(player.isBlackjack()) {
			System.out.println("Player Win!");
		}else if(dealer.isBlackjack()) {
			System.out.println("Dealer Win!");
		}
		if (dealerValue <= 21 && !dealer.isBlackjack() && !player.isBlackjack()) {
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
