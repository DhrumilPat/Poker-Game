import card.Card;
import deck.Deck;
import deck.PinochleDeck;
import deck.StandardDeck;

public class Assignment2_3 {

	public static void main(String[] args) {
		
		Deck standardDeck = new StandardDeck();
		standardDeck.createDeck(); 
		
		Deck pinochleDeck = new PinochleDeck();
		pinochleDeck.createDeck();
				
		System.out.println("standard deck: " + standardDeck);
		System.out.println("Pinochle deck: " + pinochleDeck);
		
		System.out.println("The number of Total number cards: " + Card.getCardCreated());
		
		System.out.println("The number of Pinochel cards created: " + pinochleDeck.getCards().length);
		System.out.println("The number of Standard cards created: " + standardDeck.getCards().length);

	}

}
