package deck;

import java.util.ArrayList;

import card.Card;

public abstract class Deck {
	//Attributes go here
	protected ArrayList < Card > cards;
	private ArrayList < Card > discardPile;
	
	// Or you can use This to write the ArrayList method
	//private ArrayList < Card > cards = new ArrayList < Card > ();
	//private ArrayList < Card > discardPile = = new ArrayList < Card >();
	
	public Deck() {
		//Create the attribute arrays with a size of 52
		cards = new ArrayList < Card > ();
		discardPile = new ArrayList < Card >();
	}
	
	//This method populates the deck object with cards
	public abstract void createDeck(); 

	public void shuffleDeck() {
		
		//Create a routine to mix up the deck
		Card tempCards;
		for(int i = 0; i < cards.size(); i++) {
			int randomCard = (int)(Math.random() * cards.size());
			
			tempCards = cards.get(randomCard);
			cards.set(randomCard, cards.get(i));
			cards.set(i, tempCards);
			}
	}

	public void addDiscard(Card card) {
		//Lab 11.1
		discardPile.add(card);
	}

	public void addDiscard(Card[] discards) {
		//Lab 11.1
		for (int i = 0; i < discards.length; i++) {
			discardPile.add(discards[i]);
		}
	}

	public void restack() {
		//Lab 11.1
		// Remove card from discardPile
		//Add to cards array
		while (discardPile.size() > 0) {
			cards.add(discardPile.remove(0));
		}
		
		// Now Shuffle the deck
		shuffleDeck();
	}
	
	

	//To return a single Card object and leave in the Deck
	public Card getCard(int index) {
		
		return cards.get(index);
	}
	
	//To return a Card object and remove from the Deck
	public Card removeCard(int index) {
		
		return cards.remove(index);
	}

	//To return the entire deck
	
	//To return the discardPile
	public Card[] getCards() {
		//Convert the arrayList to an array
		Card[] tempCards = new Card[cards.size()];
		cards.toArray(tempCards);
		return tempCards;
	}

	public Card[] getDiscardPile() {
		//Convert the arrayList to an array
		Card[] tempCards = new Card[discardPile.size()];
		discardPile.toArray(tempCards);		
		return tempCards;
	}
	

	//We need a toString()
	@Override
	public String toString() {
		String deckString = "Deck : \n";
		for(int i = 0; i < cards.size(); i++) {
			if(i !=0 && i % 13 == 0) {
				deckString += "\n";
			}
			deckString += cards.get(i) + " ";
		}
		deckString += "\nDiscard Pile";
		for(int i = 0; i < discardPile.size(); i++) {
			if(i !=0 && i % 13 == 0) {
				deckString += "\n";
			}
			deckString += discardPile.get(i) + " ";
		}
		
		return deckString;
	}
	
}
