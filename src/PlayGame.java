import Player.Player;
import game.TioliGame;
import gamedata.GameData;
//import gameobjects.PayoutTable;
//import gameobjects.ScoreBoard;
//import gameobjects.Wager;
import hand.PokerHand;
import javafx.application.Application;
import javafx.stage.Stage;

public class PlayGame extends Application {
	
	public void start(Stage primaryStage) {
		GameData databaseObj = new GameData();
		//databaseObj.getRandomPlayer();
		Player player = databaseObj.getRandomPlayer();
		
		//Player player = new Player("FastFreddy", "9765467", 1450, new PokerHand());
		
		//PayoutTable payoutTable = new PayoutTable();
		//Wager wager = new Wager(player, 0);
	//	ScoreBoard scoreBoard = new ScoreBoard(player);
		
		new TioliGame(player);
		
	}

	public static void main(String[] args) {
		Application.launch(args);

	}

}
