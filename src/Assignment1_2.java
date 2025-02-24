import Player.Player;
import deck.Deck;
import hand.Hand;
import helpers.PokerSolver;
import Player.Dealer;

public class Assignment1_2 {

	public static void main(String[] args) {

		Dealer fred = new Dealer(new Deck());
		
		Player player1 = new Player("Sean", "12468", new Hand());
		Player player2 = new Player("Alex", "13579", new Hand());	
		

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
	    
		
	}
		
}
