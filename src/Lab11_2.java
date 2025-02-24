import Player.Dealer;
import Player.Player;
import deck.Deck;
import hand.BlackjackHand;
import hand.Hand;
import helpers.PokerSolver;

public class Lab11_2 {

	public static void main(String[] args) {
		
		Dealer fred = new Dealer(new Deck());

		Player joe = new Player("Joe", "1234", new Hand());
		Player sean = new Player("Sean", "4567", new Hand());
		
		//Dealer Poker Hand
		for(int i = 0; i < 10; i++) {
			if (i % 2 == 0) {
				fred.dealCard(sean);
			} else {
				fred.dealCard(joe);
			}
		}
		
		int[] handResult = PokerSolver.evaluatePokerGame(sean.getHand(), joe.getHand());
		
		System.out.println("Poker: \nPlayer Hand: \n");
		System.out.println(sean.getName() + "\t" + sean.getHand() + "\t" + sean.getHand().getHandDescr());
		System.out.println(joe.getName() + "\t" + joe.getHand() + "\t" + joe.getHand().getHandDescr());

		if(handResult[0] == 1) {
			System.out.println(sean.getName() + "Wins!");
		} else if(handResult[1] == 1) {
			System.out.println(joe.getName() + "Wins!");
		} else {
			System.out.println("It's a tie!");
		}
		
		//Play Blackjack
		Dealer dealer21 = new Dealer(new Deck());
		
		Player eunice = new Player("Dealer", "Deealer", new BlackjackHand());
		
		Player jimBob = new Player("JimBob", "9876", new BlackjackHand());
		
		for(int i = 0; i < 4; i++) {
			if (i % 2 == 0) {
				dealer21.dealCard(jimBob);
			} else {
				dealer21.dealCard(eunice);
			}
		}
	
		
		System.out.println("\nBlackjack: \n");
		System.out.println(jimBob.getName() + " 's Hand: \t" + jimBob.getHand());
		System.out.println(eunice.getName() + " 's Hand: \t" + eunice.getHand());
		
		Hand tempJimHand = jimBob.getHand();
		
		tempJimHand.evaluateHand();
		eunice.getHand().evaluateHand();
		
		BlackjackHand jimBobHand = (BlackjackHand)(tempJimHand);
		if(jimBobHand.isBlackjack()) {
			System.out.println(jimBob.getName() + " Has BlackJack!");
		} else {
			System.out.println(jimBob.getName() + " Does not Has BlackJack!");
		}
		
		//BlackjackHand euniceHand = (BlackjackHand) eunice.getHand();
		
		//In one step
		if(((BlackjackHand)eunice.getHand()).isBlackjack()) {
				System.out.println(eunice.getName() + " Has BlackJack!");
			} else {
				System.out.println(eunice.getName() + " Does not Has BlackJack!");
			}
		Hand jimTemp = jimBob.getHand();
		int jimScore = jimTemp.getHandScore();
		System.out.println("\n" + jimBob.getName() + " has a score of: " + jimScore);
		
		Hand euniceTemp = eunice.getHand();
		int euniceScore = euniceTemp.getHandScore();
		System.out.println("\n" + eunice.getName() + " has a score of: " + euniceScore);
	}
	

}
