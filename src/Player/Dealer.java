package Player;

import card.Card;
import deck.Deck;
import hand.Hand;


public class Dealer extends Player {

	private Deck deck;
	//private Hand hand;

	public Dealer (Deck deck, Hand hand) {
		super("Dealer", "Dealer", 0, hand);
		this.deck  = deck;
		
		prepareDeck();
	//	this.hand = hand;
	}
	
	public Dealer(Deck deck) {
		this.deck = deck;
		
		prepareDeck();
	}
	
	private void prepareDeck() {
		deck.createDeck();
		deck.shuffleDeck();
		
	}
	
	public void dealCard(Player player) {
		//Get card from deck
		Card tempCard = deck.removeCard(0);
		
		//Get pointer to player's hand object
		Hand tempHand = player.getHand();
		
		//Use hand object to add the card to players hand
		tempHand.addCard(tempCard);
		//Deal the card at index to the player's hand
		//Assignment 1.2  
		//playe.gethand is one of the object
	}
	
	
	public Card getCard(int index) {
		Card tempCard = deck.getCard(index);
		
		return tempCard;
	}
	
	public void reshuffleDeck() {
		
		Card[] tempDeck = deck.getCards();
		
		Card[] tempDiscard = deck.getDiscardPile();
		
		int totalCards = tempDeck.length + tempDiscard.length;
		
		int reshuffleCount = (int)(totalCards * .7);
		
		if(tempDeck.length < reshuffleCount) {
			
			deck.restack();
		}
		
		//Lab 11.1, replace with logic to reShuffle
	}

	public Deck getDeck() {
		return deck;
	}
	
	
}
