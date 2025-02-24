import Player.Dealer;
import Player.Player;
import card.Card;
import deck.Deck;
import deck.PinochleDeck;
import hand.BlackjackHand;
import hand.Hand;

public class Assignment2_2 {

	public static void main(String[] args) {
		
		Dealer fred = new Dealer(new Deck());
		
		System.out.println("Deck of standard cards");
		//fred.getDeck();
		
		
		Dealer fred2 = new Dealer(new PinochleDeck());
		//fred2.getDeck();
		
				
		System.out.println("standard deck: " + fred.getDeck());
		System.out.println("Pinochle deck: " + fred2.getDeck());
		
		System.out.println("The number of Total number cards: " + Card.getCardCreated());
		
		System.out.println("The standard deck size: " + fred.getDeck().getCards().length);
		System.out.println("The pincohle deck size: " + fred2.getDeck().getCards().length);
		
		
		Dealer eunice = new Dealer(new Deck(), new BlackjackHand());
		Player jhon = new Player("Jhon", "1234", new BlackjackHand());
		
		for(int i = 0; i < 4; i++) {
			if (i % 2 == 0) {
				eunice.dealCard(jhon);
			} else {
				eunice.dealCard(eunice);
			}
		}
		
		System.out.println("\nBlackjack: \n");
		System.out.println(jhon.getName() + " 's Hand: \t" + jhon.getHand());
		System.out.println(eunice.getName() + " 's Hand: \t" + eunice.getHand());
		
		Hand tempJhonHand = jhon.getHand();
		
		tempJhonHand.evaluateHand();
		eunice.getHand().evaluateHand();
		
		BlackjackHand jhonHand = (BlackjackHand)(tempJhonHand);
		if(jhonHand.isBlackjack()) {
			System.out.println(jhon.getName() + " Has BlackJack!");
		} else {
			System.out.println(jhon.getName() + " Does not Has BlackJack!");
		}
		
		if(((BlackjackHand)eunice.getHand()).isBlackjack()) {
			System.out.println(eunice.getName() + " Has BlackJack!");
		} else {
			System.out.println(eunice.getName() + " Does not Has BlackJack!");
		}
			
		
		Hand jhonTemp = jhon.getHand();
		int jhonScore = jhonTemp.getHandScore();
		System.out.println("\n" + jhon.getName() + " has a score of: " + jhonScore);
	
		Hand euniceTemp = eunice.getHand();
		int euniceScore = euniceTemp.getHandScore();
		System.out.println("\n" + eunice.getName() + " has a score of: " + euniceScore);

	}

}
