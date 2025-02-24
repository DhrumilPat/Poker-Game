

import Player.Dealer;
import Player.Player;
import card.Card;
import deck.Deck;
import hand.Hand;
import helpers.PokerSolver;

public class Lab11_1 {

	public static void main(String[] args) {
		// Create a Dealer
        Dealer fred = new Dealer(new Deck());
		
		Player player1 = new Player("Sean", "12468", new Hand());
		Player player2 = new Player("Alex", "13579", new Hand());	
		
		System.out.println(fred.getDeck());

		for (int i = 0; i < 10; i ++) {
			if(i % 2 == 0) {
				//fred.dealCard(player1.getHand());
				fred.dealCard(player1);
			}
			else {
				//dealer.dealCard(player2.getHand());
				fred.dealCard(player2);
			}
		}
		
		System.out.println(fred.getDeck());

		int[] results = PokerSolver.evaluatePokerGame(player1.getHand(), player2.getHand());
		
		System.out.println("Player Hands:");
		System.out.println("Player 1: " + player1.getHand() + "\t" + player1.getHand().getHandDescr());
		System.out.println("Player 2: " + player2.getHand() + "\t" + player2.getHand().getHandDescr() + "\n");
		

	    if (results[0] == 1) {
	      System.out.println("Player 1 wins");
	    } else if (results[1] == 1) {
	      System.out.println("Player 2 wins");
	    } else {
	      System.out.println("It's a tie");
	    }
	    
		//Send the hand to the discard pile
		
		//1. Get the hand
	    Hand tempHand1 = player1.getHand();
	    Hand tempHand2 = player2.getHand();
	    
	    // 2. Get the card array from each hand
	    Card[] tempCards1 = tempHand1.getCards();
	    Card[] tempCards2 = tempHand2.getCards();
	    
	    //3. Get deck from dealer
	    Deck tempDeck = fred.getDeck();
	    
	    //4. Call the Deck's addDiscard
	    tempDeck.addDiscard(tempCards1);
	    tempDeck.addDiscard(tempCards2);
	    
	    //5. Print the deck again
		System.out.println(fred.getDeck());
		
		//6. Restack the deck
		fred.getDeck().restack();
		
		//7. Print the deck yet again
		System.out.println(fred.getDeck());


	}
	

}
