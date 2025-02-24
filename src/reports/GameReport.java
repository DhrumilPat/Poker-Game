package reports;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import gamedata.GameData;
import Player.Player;
import javafx.application.Platform;
//import players.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameReport {
	//Window dimensions - adjust as needed
	int windowWidth = 1200;
	int windowHeight = 550;
	
	//Instantiate a new Stage (window object)
	Stage reportStage = new Stage();

	//To do: We need to Declare a player object as an attribute
	private Player player;
	
	//To do: Declare a Text Object field for the page title
	private Text pageTitle = new Text();
	
	//The panes to hold our nodes
	BorderPane pane = new BorderPane(); //Main pain for the scene
	GridPane dataGrid = new GridPane();  //To display our data (from the Text objects)
	ScrollPane scrollPane;  //So our data will scroll
	HBox titleContainer;  //A container for our report title
	
	//To do: We need to instantiate database object
	GameData gameData = new GameData();

	
	//Create array lists to hold each piece of data in a Text object
	ArrayList<Text> gameId = new ArrayList<Text>();
	//To do: Now create the array lists that are needed for the other data being reported
	ArrayList<Text> descr = new ArrayList<Text>();
    ArrayList<Text> winAmount = new ArrayList<Text>();
    ArrayList<Text> bank = new ArrayList<Text>();

	//We will need to close the screen when done
	private Button btnExit = new Button("Exit");

	
	public GameReport(Player player) throws SQLException, ClassNotFoundException{
		//First, use the parameter to set the appropriate attribute
		this.player = player;
		//Step 1:
		//Get our data from the database
		getData();

		//Step 2:
		//Put our Text objects into the GridPane
		populateGridPane();

		//Step 3:
		//Put the grid pane into a scroll pane
		addGridToScroll();

		//Step 4:
		//Set the listener for the Exit button
		createExitButtonListener();

		//Step 5:
		//Create a report title
		createReportTitle();
				
		
		//Step 6:
		//Put our objects into the BorderPane
		addObjectsToPage();
		
		//Step 7:
		//Add styling to make it look pretty
		styleStuff();

		//Make the screen appear
		showScene();

	}
	
	private void getData() throws SQLException, ClassNotFoundException{
		//1A
		//Call the getReportData method you created
		//Use your GameData object 
		//Put the data returned into a local result set 
		ResultSet resultSet = gameData.getReportData(player);

		
		//Add the column headers to the Text object ArrayLists
		gameId.add(new Text("Game ID"));
		descr.add(new Text("Hand Descr"));
		winAmount.add(new Text("Amount Won"));
		bank.add(new Text("Player Bank"));
		
		//1B
		//Now loop through the result set
		//Add each of the appropriate columns to Text objects
		//Add the Text Objects to the appropriate Array List
		//The format is like the header but instead of harcoded text, use the
		//information from the ResultSet
		 while (resultSet.next()) {
			 gameId.add(new Text(resultSet.getString("game_id")));
			 descr.add(new Text(resultSet.getString("hand_descr")));
			 winAmount.add(new Text(resultSet.getString("amount_won")));
			 bank.add(new Text(resultSet.getString("player_bank")));
		 }
	}
	
	private void populateGridPane() {
		//2
		//We use this method to add the Text objects to the GridPane
		//Again, you will have to loop through the Text ArrayLists
		//Hint...all the Text ArrayLists are the same size
		for (int i = 0; i < gameId.size(); i++) {
	        // Get the Text objects from the ArrayLists
	        Text gameIdText = gameId.get(i);
	        Text descrText = descr.get(i);
	        Text winAmountText = winAmount.get(i);
	        Text bankText = bank.get(i);

	        // Add the Text objects to the GridPane
	        dataGrid.add(gameIdText, 0, i + 1);
	        dataGrid.add(descrText, 1, i + 1);
	        dataGrid.add(winAmountText, 2, i + 1);
	        dataGrid.add(bankText, 3, i + 1);
	    }
		
	}
	
	private void addGridToScroll() {
		//3
		//Here you add the GridPane to the ScrollPane by
		//Instantiating your scrollPane object and feed the grid pane to the ScrollPane constructor
		scrollPane = new ScrollPane(dataGrid);
	}
	
	private void createExitButtonListener() {
		//4A
		//Define the exit button listener to call exitReport()
		btnExit.setOnAction(e -> {
			exitReport();
		});
		//4B - complete the exitReport method (already defined below)
	}
	
	private void createReportTitle() {
		
		//5A
		//Instantiate the Text object attribute for the title at the top of the page 
		//Title should include the player's name and "Game Data" or "Report", etc.
		String titleText = player.getName() + " Game Data"; // Update with appropriate title format
	    pageTitle = new Text(titleText);
		
		//5B
		//Instantiate the titleContainer and put the titleText into it
	    titleContainer = new HBox();
	    titleContainer.getChildren().add(pageTitle);
		
	}
	
	private void addObjectsToPage() {
		//Here we will add our objects to the page (the BorderPane):
		
		//6A
		//Put the title in the top
		pane.setTop(pageTitle);
		
		//6B
		//Put the ScrollPane in the center
		pane.setCenter(scrollPane);
		
		//6C
		//Put the Button object in the bottom
		//See if you can center it (width-wise) in the styling method
		btnExit.setLayoutX(10);
		btnExit.setLayoutY(10);
		pane.setBottom(btnExit);

	}
	
	private void exitReport() {
		//4B
		//Close the database object before closing the window
		Platform.exit();
		System.exit(0);
		
		//Close the window
		reportStage.close();
	}
	
	private void styleStuff() {
		//Some styling for the GridPane...should work ok
		//Feel free to adjust as needed
		dataGrid.setHgap(175);
		dataGrid.setVgap(20);

		//Here is some styling to help keep the scroll pane from squishing to the edge of the window
		//Feel free to modify
		int leftRightSpace = 40;
		scrollPane.setPrefWidth(windowWidth - leftRightSpace);
		scrollPane.setMaxWidth(windowWidth - leftRightSpace);
		
		//Any styling for the Page title?
		Text titleText = new Text("Draw Poker Data For " + player.getName());
	    titleText.setFont(Font.font("Times New Roman", 50));
		
		//Any styling for your exit button?
	    
		
		//Can you make the exit button centered horizontally?
		//Hint: note there is a window width attribute
		

		//Style your Text objects?
		//Consider font type, font size, color, etc.
		//Remember you will have to loop through the Text object ArrayLists
		//Hint...all the Text ArrayLists are the same size
	    for (Text text : gameId) {
	        text.setFont(Font.font("Arial", 16));
	        text.setFill(Color.RED);
	    }
	    for (Text text : descr) {
	        text.setFont(Font.font("Arial", 16));
	        text.setFill(Color.RED);
	    }
	    for (Text text : winAmount) {
	        text.setFont(Font.font("Arial", 16));
	        text.setFill(Color.RED);
	    }
	    for (Text text : bank) {
	        text.setFont(Font.font("Arial", 16));
	        text.setFill(Color.RED);
	    }
		
		
		//Change column headers' size/color?
		
		//Maybe style the scroll pane so there is some padding on top of the GridPane?
		
	}
		
	private void showScene() {
		//This will make our report show
		//No changes needed
		Scene scene = new Scene(pane, windowWidth, windowHeight);
		reportStage.setTitle("Draw Poker Data For " + player.getName());
		reportStage.setScene(scene);
		reportStage.show();		
	}
}
