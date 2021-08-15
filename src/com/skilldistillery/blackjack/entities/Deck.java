package com.skilldistillery.blackjack.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private List<Card> deck;

	public Deck() {
		this.deck = new ArrayList<>();
		for(Suit suit : Suit.values()) {
			for(Rank rank : Rank.values()) {
				Card currentCar = new Card(suit, rank);
				deck.add(currentCar);
			}
		}
	}

	public int cardsLeftInDeck() {
		return deck.size();
	}
	
	public Card dealCard() {
		return deck.remove(0);
	}
	
	public void shuffle() {
		Collections.shuffle(deck);
	}

	@Override
	public String toString() {
		return "Deck [deck=" + deck + "]";
	}
	
}
