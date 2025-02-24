import Player.Dealer;
import Player.Player;
import deck.Deck;
import hand.BlackjackHand;
import hand.Hand;
import hand.PokerHand;
import helpers.PokerSolver;

public class Lab13_1 {

	public static void main(String[] args) {
	
		Dealer pokerDealer = new Dealer(new Deck(), new PokerHand());
		
		Player fastFreddy = new Player("Fast Freddy", "1234", new PokerHand());
		
		for(int i = 0; i < 10; i++) {
			if (i % 2 == 0) {
				pokerDealer.dealCard(fastFreddy);
			} else {
				pokerDealer.dealCard(pokerDealer);
			}
		}
		
		PokerHand dealerHand = (PokerHand)pokerDealer.getHand();
		PokerHand freddyHand = (PokerHand)fastFreddy.getHand();
		
		int[] handResult = PokerSolver.evaluatePokerGame(dealerHand, freddyHand);
		
		System.out.println("Poker: \nPlayer Hand:\n");
		System.out.println(fastFreddy.getName() + "\t" + freddyHand.getHandDescr());
		System.out.println(pokerDealer.getName() + "\t" + dealerHand.getHandDescr());
		
		//System.out.println(pokerDealer.getName() + "\t" + pokerDealer.getHand());
		//System.out.println(fastFreddy.getName() + "\t" + fastFreddy.getHand());

		if(handResult[0] == 1) { 
			System.out.println(pokerDealer.getName() + " Wins! ");
		} else if(handResult[1] == 1) {
			System.out.println(fastFreddy.getName() + " Wins! ");
		} else {
			System.out.println("It's a tie!");
		}
		
		Dealer deaeler21 = new Dealer(new Deck(), new BlackjackHand());
		Player cardshark = new Player("Card Shark", "1234", new BlackjackHand());
		
		for(int i = 0; i < 4; i++) {
			if (i % 2 == 0) {
				deaeler21.dealCard(cardshark);
			} else {
				deaeler21.dealCard(deaeler21);
			}
		}
		
		System.out.println("\nBlackjack: \n");
		System.out.println(cardshark.getName() + " 's Hand: \t" + cardshark.getHand());
		System.out.println(deaeler21.getName() + " 's Hand: \t" + deaeler21.getHand());
		
		Hand tempCardHand = cardshark.getHand();
		
		tempCardHand.evaluateHand();
		deaeler21.getHand().evaluateHand();
		
		BlackjackHand cardHand = (BlackjackHand)(tempCardHand);
		if(cardHand.isBlackjack()) {
			System.out.println(cardshark.getName() + " Has BlackJack!");
		} else {
			System.out.println(cardshark.getName() + " Does not Has BlackJack!");
		}
		
		if(((BlackjackHand)deaeler21.getHand()).isBlackjack()) {
			System.out.println(deaeler21.getName() + " Has BlackJack!");
		} else {
			System.out.println(deaeler21.getName() + " Does not Has BlackJack!");
		}
			
		
		Hand cardTemp = cardshark.getHand();
		int cardScore = cardTemp.getHandScore();
		System.out.println("\n" + cardshark.getName() + " has a score of: " + cardScore);
	
		Hand dealerTemp = deaeler21.getHand();
		int dealerScore = dealerTemp.getHandScore();
		System.out.println("\n" + deaeler21.getName() + " has a score of: " + dealerScore);

		
	}

}
