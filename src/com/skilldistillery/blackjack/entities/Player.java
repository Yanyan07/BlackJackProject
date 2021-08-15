package com.skilldistillery.blackjack.entities;

public class Player{
	private BlackjackHand hand;
	
	public Player() {
		this.hand = new BlackjackHand();
	}
	
	public void addCardHand(Card card) {
		hand.addCard(card);
	}
	
	public void clear() {
		hand.clear();
	}
	
	public int getFirstValue() {
		return hand.cards.get(0).getValue();
	}
	
	public void showFirstCard() {
		System.out.println("[cards=" +"["+ hand.cards.get(0) + "]]"); 
	}
	
	public int getHandValue() {
		return hand.getHandValue();
	}
	
	public boolean isBlackjack() {
		return hand.isBlackjack();
	}
	
	public boolean isBust() {
		return hand.isBust();
	}

	@Override
	public String toString() {
		return "" + hand;
	}
	
}

