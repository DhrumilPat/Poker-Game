import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import Player.Dealer;
import Player.Player;
import deck.StandardDeck;
import hand.BlackjackHand;

public class Assignment3_1 {

	public static void main(String[] args) {
			Dealer blackjackDealer = new Dealer(new StandardDeck(), new BlackjackHand());
			
			Player[] player = new Player[4];
			player[0] = new Player("SmokeyJoe", "00064", new BlackjackHand());
			player[1] = new Player("HitMe!", "67456", 3000, new BlackjackHand());
			player[2] = new Player("ForeverFolding", "90821", 9000, new BlackjackHand());
			player[3] = new Player("BlackjackQueen", "77892", new BlackjackHand());
			
			
			for(int i=0; i<2; i++) {
				for(int j=0; j<player.length; j++) {
					blackjackDealer.dealCard(player[j]);
				}
				blackjackDealer.dealCard(blackjackDealer);
			}
			
			//Evaluate hands
			blackjackDealer.getHand().evaluateHand();
			for(int i=0; i<player.length; i++) {
				player[i].getHand().evaluateHand();
			}

			//Display Hands
			System.out.printf("%-22s  %s Score: %s\n", blackjackDealer.getName() + "'s Hand:", blackjackDealer.getHand(), blackjackDealer.getHand().getHandScore());
			for(int i=0; i<player.length; i++) {
				System.out.printf("%-22s  %s Score: %s\n", player[i].getName() + "'s Hand:", player[i].getHand(), player[i].getHand().getHandScore());
			}
					
			File file = new File("files/blackjackdata.csv");
			if(file.exists()) {
				//Let's delete the file (not in book)
				file.delete();
			}

			//Write the file
			//Use the autoclose method for the heck of it
			try (PrintWriter output = new PrintWriter(file);){

				//Print Header
				output.print("Player ID,Player Name,Player Bank,Blackjack?,Hand Score\n");
				
				//Print Dealer
				BlackjackHand dealerHand = (BlackjackHand)blackjackDealer.getHand();
				output.print(blackjackDealer.getId());
				output.print(",");
				output.print(blackjackDealer.getName());
				output.print(",");
				output.print(blackjackDealer.getBank());
				output.print(",");
				output.print((dealerHand.isBlackjack())?'Y':'N');
				output.print(",");
				output.print(dealerHand.getHandScore());
				output.print("\n");  //To move to next record
				
				//Print players
				for(int i=0; i<player.length; i++) {
					BlackjackHand playerHand = (BlackjackHand)player[i].getHand();
					
					output.print(player[i].getId());
					output.print(",");
					output.print(player[i].getName());
					output.print(",");
					output.print(player[i].getBank());
					output.print(",");
					output.print((playerHand.isBlackjack())?'Y':'N');
					output.print(",");
					output.print(playerHand.getHandScore());
					output.print("\n");  //To move to next record				
				}
				
			} catch (IOException ex) {
				System.out.println("Error opening file for writing!");
			}
			
			//Now read the data back
			String inputData;
			try {
				Scanner input = new Scanner(file);
				
				System.out.print("\n");
				boolean headerRowPrinted = false;
				while(input.hasNext()) {
					inputData = input.nextLine();
					String[] dataArray = inputData.split(",");
					
					if(!headerRowPrinted) {
						System.out.printf(
							"%-11s   %-22s   %s\t%s\t%s\n", 
							dataArray[0],
							dataArray[1],
							dataArray[2],
							dataArray[3],
							dataArray[4]
						);
						
						headerRowPrinted = true;
					} else {
						//Set bank amount as currency format
						//1. Convert to BigDecimal wrapper class
						BigDecimal valueToFormat = new BigDecimal(dataArray[2]);
						//2.  Use NumberFormat to format US currency
						NumberFormat currencyFormat = NumberFormat.getNumberInstance();
						currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
						//Remove the decimals
						currencyFormat.setMaximumFractionDigits(0);
						//Format the BigDecimal
				        String formattedBank = currencyFormat.format(valueToFormat);
				        
				        //Right justify the bank amount
						int bankPadding = 34 - formattedBank.length();
						System.out.printf(
								"%-11s   %-"+bankPadding+"s  %s\t%4s\t%14s\n", 
								dataArray[0],
								dataArray[1],
								formattedBank,
								dataArray[3],
								dataArray[4]
							);
						
					}
									
				}
				input.close();
				
			} catch(IOException ex) {
				System.out.println("Error opening file for reading!");			
			}
			
	}
}