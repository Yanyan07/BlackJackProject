package com.skilldistillery.blackjack.entities;

public class Dealer extends Player{
	private Deck deck;
	
	public Dealer() {
		this.deck = new Deck();
	}
	
	public void dealCard(Player player) {
		player.addCardHand(deck.dealCard());
	}
	
	public int cardNumInDeck() {
		return deck.cardsLeftInDeck();
	}
	
	public void dealerShuffle() {
		deck.shuffle();
	}
}

