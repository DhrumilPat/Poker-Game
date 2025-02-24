package hand;

import java.util.ArrayList;

import card.Card;
import deck.Deck;
//import helpers.PokerSolver;

public abstract class Hand {
	//Create the attributes
	private ArrayList < Card > cards;
	private int handScore;
	private int handRank;
	private int highCard;
	
	//Attributes used use by the PokerSolver
	
	//Use this to prevent adding too many items to the cards array
	//private int cardCounter = 0;

	public Hand() {
		//Only need to establish an empty cards array here
		cards = new ArrayList < Card > ();
		//handDescr = " " ;
		handScore = 0;
		handRank= 0;
		highCard = 0;
	}
	
	
	public Hand(Card[] dealtCards) {
		cards = new ArrayList < Card > ();
		//Create the empty array
		for (int i = 0; i < cards.size(); i++) {
			//cards.set(i, dealtCards[i]);
			cards.add(dealtCards[i]);
		}
		//Loop through the dealtCards array and add each card object to the cards array
		
	}
	
	public void addCard(Card dealtCard) {
		//We are sent 1 card
		//If the array is not full, then add the card
		//Otherwise, just ignore it
		//Keep track of the number of cards added to the cards array
		cards.add(dealtCard);
	}
	
	public void addCard(Card[] dealtCards) {
		//We are sent multiple cards
		//Loop through the array and try to add each card to the cards array
		//If the array is not full, then add the card
		//Otherwise, just ignore it
		//Keep track of the number of cards added to the cards array
		for (int i = 0; i < dealtCards.length; i++) {		
				cards.add(dealtCards[i]);

			
		}
	}
	
	//Put a card into a specific element within the Hand
    public void setCard(int index, Card dealtCard){
    	cards.set(index, dealtCard);
    }
	
	//Get a specific Card object at a specific index
	public Card getCard(int index) {
		return cards.get(index);
	}
	
	//Get a specific Card object at a specific index and remove from Hand
	public Card removeCard(int index) {
		//Assignment 2.1
		Card removedCard = cards.get(index);
		cards.remove(index);
		return removedCard;
	}
	
	public abstract void evaluateHand();
	
	public void discard(Deck deck, int index) {
		//Assignment 2.1
		Card discardedCard = removeCard(index);
		deck.addDiscard(discardedCard);
	}
	
	public void discardAll(Deck deck) {
		//Assignment 2.1
		while (cards.size() > 0) {
			Card discardedCard = cards.get(0);
			deck.addDiscard(discardedCard);
			cards.remove(0);
		}
	}
	
	@Override
	public String toString() {
		//Return a string with the card objects in the hand
		String handString = " ";
		for (int i = 0; i < cards.size(); i++) {
			handString += cards.get(i) + "\t";
		
		}
		return handString;
	}

	//Add the typical getters/setters
	public int getHandScore() {
		return handScore;
	}

	public void setHandScore(int handScore) {
		this.handScore = handScore;
	}

	public int getHandRank() {
		return handRank;
	}

	public void setHandRank(int handRank) {
		this.handRank = handRank;
	}

	public int getHighCard() {
		return highCard;
	}

	public void setHighCard(int highCard) {
		this.highCard = highCard;
	}

	public Card[] getCards() {
		Card[] tempCards = new Card[cards.size()];
		cards.toArray(tempCards);
		return tempCards;
	}

 
}
