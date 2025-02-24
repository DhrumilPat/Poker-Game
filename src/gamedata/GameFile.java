package gamedata;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import Player.Player;
import hand.PokerHand;

public class GameFile {

	public static void writeData(String filename, Player playerObj, int winAmount) {
		try(DataOutputStream output = new DataOutputStream(new FileOutputStream(filename, true))){
			//for(int i = 0; i < 5; i++) {
			output.writeUTF(playerObj.getId());
            output.writeUTF(playerObj.getName());
            PokerHand hand = (PokerHand) playerObj.getHand();
            output.writeUTF(hand.getHandDescr());
            output.writeInt(winAmount);
            output.writeInt(playerObj.getBank());
			//}
		} catch (IOException ex){
			System.out.println("Error writing data!");
		}		

	}
	
	
}


