import deck.Deck;
import Player.Dealer;
import card.Card;

public class Lab10_1 {

	public static void main(String[] args) {
		Dealer fred = new Dealer(new Deck());
		
		System.out.println(fred.getDeck() + "\n");

		Card[] hand = new Card[5];
		
		for (int i = 0; i < hand.length; i++) {
			hand[i] = fred.getCard(i);
		}
		
		for(int i = 0; i < hand.length; i++) {
			System.out.print(hand[i] + " ");
		}
		
		System.out.println("\nNumber of Cards created:" + Card.getCardCreated());
		
		//Print the color of the first in the deck
		for(int i = 0; i < 5; i++) {
			//Get the deck from the dealer
			Deck tempDeck = fred.getDeck();
			
			//Get the Card from the Deck
			Card tempCard = tempDeck.getCard(i);
			
			//Get the color from the card
			String tempColor = tempCard.getColor();
			
			System.out.println("Card " + (i+1) + " Color: " + tempColor);
			
			//String tempColor2 = fred.getDeck().getCard(i).getColor();
			//System.out.println("Card " + (i+1) + " Color: " + tempColor);
		}
	}

}
