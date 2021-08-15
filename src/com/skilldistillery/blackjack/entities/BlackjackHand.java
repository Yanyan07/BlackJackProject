package com.skilldistillery.blackjack.entities;

public class BlackjackHand extends Hand{

	@Override
	public int getHandValue() {
		int sum = 0;
		for (Card card : cards) {
			sum += card.getValue();
		}
		return sum;
	}

	public boolean isBlackjack() {
		if(cards.size()==2) {
			return cards.get(0).getValue()+cards.get(1).getValue() == 21;
		}
		return false;
	}
	
	public boolean isBust() {
		int sum = 0;
		for (Card card : cards) {
			sum += card.getValue();
		}
		return sum > 21;
	}
}
