package com.skilldistillery.blackjack.entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand {
	List<Card> cards;
	
	public Hand() {
		this.cards = new ArrayList<>();
	}

	public void addCard(Card card) {
		cards.add(card);
	}
	
	public void clear() {
		cards.clear();
	}
	
	public abstract int getHandValue();
	
	@Override
	public String toString() {
		return "[cards=" + cards + "]";
	}
	
}
