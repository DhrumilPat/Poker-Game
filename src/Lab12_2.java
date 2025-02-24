import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import Player.Dealer;
import Player.Player;
import deck.StandardDeck;
import hand.Hand;
import hand.PokerHand;

public class Lab12_2 {

	public static void main(String[] args) {
		Dealer dealer = new Dealer (new StandardDeck());

		Player [] player = new Player[2];
		player [0] = new Player("Joe", "1717", new PokerHand());
		player [1] = new Player("Marry", "2231", 3000, new PokerHand());
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < player.length; j++) {
				dealer.dealCard(player[j]);
			}
		}
		
		for(int i = 0; i < player.length; i++) {
			Hand tempHand = player[i].getHand();
			tempHand.evaluateHand();
			
			PokerHand tempPokerHand = (PokerHand)tempHand;
			
			System.out.println(player[i].getName() + "'s Hand:\t" + tempPokerHand + " " +
			tempPokerHand.getHandDescr());
		}
		
		File file = new File("files/playerdata.csv");
		if(file.exists()) {
			file.delete();
		}
		
		try {
			PrintWriter output = new PrintWriter(file);
			
			
			output.print("ID,Name,Hand Descr,Bank\n");
			for(int i = 0; i < player.length; i++) {
				PokerHand tempHand = (PokerHand)player[i].getHand();
				
				output.print(player[i].getId());
				output.print(",");
				output.print(player[i].getName());
				output.print(",");
				output.print(tempHand.getHandDescr());
				output.print(",");
				output.print(player[i].getBank());
				output.print("\n");
			}
			
			output.close();
		} catch(IOException ex) {
			System.out.println("can't open the files");
			
		}
		
		
		String inputData;
		try(Scanner input = new Scanner(file)){
			System.out.println("\n");
			
			while(input.hasNext()) {
				inputData = input.nextLine();
				String[] dataArray = inputData.split(",");
				for (int i = 0; i < dataArray.length; i++) {
					System.out.print(dataArray[i] + "\t");
				}
				System.out.print("\n");
			}
			
		} catch (IOException ex) {
			System.out.println("Error opeing the files for reading");
		}
		
	}

}
