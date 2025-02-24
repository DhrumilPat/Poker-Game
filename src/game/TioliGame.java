package game;

import java.sql.SQLException;

import Player.Dealer;
import Player.Player;
import card.Card;
import deck.StandardDeck;
import gamedata.GameData;
import gamedata.GameFile;
import gameobjects.CardSelector;
import gameobjects.GameOptions;
import gameobjects.GameTimer;
import gameobjects.PayoutTable;
import gameobjects.ScoreBoard;
import gameobjects.Wager;
import hand.PokerHand;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import reports.GameReport;

public class TioliGame {

	
	//We use a BorderPane to hold all object
	private BorderPane gameScreen = new BorderPane();
		
	//Panes to hold the nodes in each section
	private HBox topSection = new HBox(10);
	private HBox bottomSection = new HBox(10);
	private VBox leftSection = new VBox(10);
	private VBox rightSection = new VBox (10);
	private VBox centerSection = new VBox(10);
	
	
	//Out game people
	private Dealer dealer;
	private Player player;
	
	//The play area
	private PlayerArea playerArea;
	private DealerArea dealerArea;
	
	//HBox for the header
	private HBox header;
	
	//Declare 4 new attributes
	private PayoutTable payoutTable;
    private Wager wager;
    private ScoreBoard scoreBoard;
    private GameTimer timerObj;
    private GameOptions gameOptions;
    
    private int maxTioliCards = 5; 
    private int tioliCardsDealt = 0;
    
    //Instantiate 5 new attributes 4.2
    private Button btnDeal = new Button("Deal");
    private Button btnTake = new Button("Take It");
    private Button btnLeave = new Button("Leave it");
    private Button btnExit = new Button("Exit");
    private VBox takeLeaveButtonPane = new VBox(10, btnTake, btnLeave);
   
    
    //Lab16.6
    private CardSelector cardSelector = new CardSelector(5);
    //private VBox takeLeaveButtonPane;
    
    //Finial project
    private Button btnReport = new Button("Report");
    
	public TioliGame(Player player) {
		//Initialize our player
		this.player = player;
		
		//The Constructor needs to instantiate 3 of the 4 new attributes
		this.payoutTable = new PayoutTable();
		this.wager = new Wager(player, 10);
	    this.scoreBoard = new ScoreBoard(player);
	    this.timerObj = new GameTimer(30, btnLeave);

	   
		
		//Instantiate dealer
		dealer = new Dealer(new StandardDeck(), new PokerHand());
		
		//Instantiate our Player and Dealer areas
		dealerArea = new DealerArea(dealer);
		playerArea = new PlayerArea(this.player);
		this.gameOptions = new GameOptions(dealerArea, timerObj);
		
		 /** Instantiate buttons 4.2
        btnDeal = new Button("Deal");
        btnTake = new Button("Take It");
        btnLeave = new Button("Leave It");
        btnExit = new Button("Exit"); 
        takeLeaveButtonPane = new VBox(btnTake, btnLeave); **/
		
        
		//Create header
		createHeader();
		
		//Add object to each section
		addObjectsToTopSection();
		addObjectsToBottomSection();
		addObjectsToLeftSection();
		addObjectsToRightSection();
		addObjectsToCenterSection();
	//	takeLeaveButtonPane();
	//	playerArea();
		
		//Add section to the gameScreen
		addObjectsToGameScreen();
		
		cardSelector.setDisable(true);
		//btnTake.setDisable(true);
		//btnLeave.setDisable(true);
		takeLeaveButtonPane.setDisable(true);
		
		showGame();
		
		 btnTake.setOnAction(e -> {
				takeIt();
			});
		
		btnLeave.setOnAction(e -> {
			leaveIt();
		});
		
		btnReport.setOnAction(e -> {
            try {
				GameReport report = new GameReport(player);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
		
		/*btnReport.setOnAction(e -> {
			try {
				report();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}); */
		
		
		//Temporary until we have buttons;
		//startDeal();
		
	}

	private void createHeader() {
		//Create the header
		//Text headerText = new Text("Take It Or Leave It");
		Text headerText = new Text("Welcome " + player.getName());
		headerText.setFont(Font.font("Arial", 37));
		
		header = new HBox (headerText);
		
	}

	private void addObjectsToTopSection() {
		topSection.getChildren().addAll(header);
		topSection.setAlignment(Pos.BASELINE_RIGHT);
		topSection.setPadding(new Insets(10, 10, 10, 10));
		
	} 
	
	private void addObjectsToBottomSection() {
		//Add items to the Bottom Section
		
	}
	
	private void addObjectsToLeftSection() {
		leftSection.getChildren().addAll(gameOptions);
		leftSection.getChildren().addAll(btnReport);
		btnReport.setAlignment(Pos.CENTER_LEFT);
		
	}
	
	private void addObjectsToRightSection() {
		
		// Add items to the Right Section
		rightSection.getChildren().addAll(payoutTable, wager, scoreBoard);

		rightSection.getChildren().addAll(btnExit);
		btnExit.setAlignment(Pos.CENTER_RIGHT);
		btnExit.setOnAction(e -> {
			Platform.exit();
			System.exit(0);
			
		});
		//btnExit.setAlignment(Pos.CENTER_RIGHT);
		
	}
	
	private void addObjectsToCenterSection() {
		centerSection.getChildren().addAll(timerObj, dealerArea, playerArea);
		
		playerArea.getChildren().addAll(cardSelector);
		cardSelector.setPadding(new Insets(0, 0, 0, 80));
		
		playerArea.getChildren().addAll(btnDeal);
		btnDeal.setAlignment(Pos.CENTER);
		
		//btnDeal.setPadding(new Insets(0, 0, 0, 80));
		dealerArea.getChildren().addAll(takeLeaveButtonPane);
		takeLeaveButtonPane.setAlignment(Pos.CENTER);

		btnDeal.setOnAction(e -> {
			startDeal();
		});
		
	}
	
	/** private void takeLeaveButtonPane() {
		takeLeaveButtonPane.getChildren().addAll(btnTake, btnLeave);
		centerSection.setPadding(new Insets(80, 10, 10, 10));
	} **/

	/**private void playerArea() {
		
	} **/

	private void addObjectsToGameScreen() {
		gameScreen.setTop(topSection);
		gameScreen.setCenter(centerSection);
		//gameScreen.setCenter(takeLeaveButtonPane);
		gameScreen.setRight(rightSection);
		gameScreen.setLeft(leftSection);
		
		//Just some additional styling
		BorderPane.setMargin(playerArea, new Insets(0, 0, 50, 0));
		
	}
	
	private void showGame() {
		//Instantiate a Scene object 
		Scene scene = new Scene(gameScreen, 1000, 650);
		
		//Instantiate a stage object
		Stage primaryStage = new Stage();
		
		//Set title
		primaryStage.setTitle("Dhrumil's TIOLI");
		
		//Add the scene to the stage
		primaryStage.setScene(scene);
		
		//Show the stage 
		primaryStage.show();
		
		
	}
	
//===================================================================================
//===================================================================================
//Code for playing the game 
	
	private void startDeal() {
		
		wager.setDisable(true); //Extra Credit
		
		clearCards();
		playerArea.clearPlayerHand();
		dealerArea.clearDiscardHolder();
		
		dealPlayer();
		evaluateHand();
		playerArea.showCards();
		playerArea.showHandDescr();
		
		dealDealer();
		
		cardSelector.setDisable(false);
		//btnTake.setDisable(false);
		//btnLeave.setDisable(false);
		takeLeaveButtonPane.setDisable(false);
		// Disable the Deal button
	    btnDeal.setDisable(true);
	    
	    // Start the timer
	    timerObj.startTimer();
	    
		
	}

	private void dealPlayer() {
		for(int i = 0; i < 5; i++) {
			dealer.dealCard(player);
		}
		
	}
	
	private void evaluateHand() {
		player.getHand().evaluateHand();
		
	}

	private void dealDealer() {
		dealer.dealCard(dealer);
		
		dealerArea.showTioliCard();
	
	}
	
	private int getPayout() {
		int handRank = player.getHand().getHandRank();
		int wagerAmount = wager.getWagerAmount();
		int payoutAmount = payoutTable.getPayout(handRank, wagerAmount);
		return payoutAmount;
	}
	
	private void displayFinalResult(int amountWon) {
		//int amounts = wager.getWagerAmount();
		scoreBoard.setWinAmount(amountWon);
		player.setBank(player.getBank() + amountWon);
		scoreBoard.updateBank();
		
		
	}
	
	private void endHand() {
		int amountWon = getPayout();
		//System.out.println(amountWon);

	    tioliCardsDealt = 0; 
	    takeLeaveButtonPane.setDisable(true);
		//btnTake.setDisable(false);
		//btnLeave.setDisable(false);
	    btnDeal.setDisable(true);
	    cardSelector.setDisable(true);
	   // timerObj.stopTimer();
	  displayFinalResult(amountWon);
	  writeDataToFile(amountWon);
	  saveDataInDatabase(amountWon);
	  
	  wager.setDisable(false); //Extra Credit
	}


	private void writeDataToFile(int amountWon) {
        GameFile.writeData("files/gamedata.dat", player, amountWon);

	}
	
	private void saveDataInDatabase(int amountWon) {
		GameData gameData = new GameData();
		
		gameData.insertHand(player);
		gameData.updateBank(player);
		gameData.insertResults(player, amountWon);
		
		//When done with the database, close it
		gameData.close();
	}

	private void takeIt() {
	   // System.out.println("Take It");
		
	  // Stop the timer 
		timerObj.stopTimer();
	    
	    int cardIndex = cardSelector.getCardSelected() - 1;
	    Card cardTemp = player. getHand () .getCard(cardIndex);
	
	    player.getHand().setCard(cardIndex, dealer.getHand().getCard(0));   
	    dealer.getHand().removeCard(0);    
	    dealer.getDeck().addDiscard(cardTemp);
	    dealerArea.clearTioliHolder();
	    dealerArea.showDiscardedCard(cardTemp);
	    playerArea.showCards();
	    playerArea.showHandDescr();
	    evaluateHand(); // Extra Credit EC12
	    tioliCardsDealt++;
	    
	    if(tioliCardsDealt == maxTioliCards) {
	    	endHand();
	    	tioliCardsDealt = 0;
	    	btnDeal.setDisable(false);
	    	takeLeaveButtonPane.setDisable(true);
	    	
	    } else {
	    	
	    	dealDealer();
	    	timerObj.startTimer();
	    	
	    }
	    
	    
	}

	private void leaveIt() {
	   //System.out.println("Leave It");
	   timerObj.stopTimer();
	  // Card tiolicard = dealer.getCard(0);
	   Card tiolicard = dealer.getHand().removeCard(0);
	   dealer.getDeck().addDiscard(tiolicard);
	   dealerArea.clearTioliHolder();
	   dealerArea.showDiscardedCard(tiolicard);
	   
	   tioliCardsDealt++;
	    
	    if(tioliCardsDealt == maxTioliCards) {
	    	endHand();
	    	tioliCardsDealt = 0;
	    	btnDeal.setDisable(false);
	    	takeLeaveButtonPane.setDisable(true);
	    	
	    } else {
	    	
	    	dealDealer();
	    	timerObj.startTimer();
	    }
	   
	  
	}
	
	private void clearCards() {
		player.getHand().discardAll(dealer.getDeck());
		dealer.getHand().discardAll(dealer.getDeck());
	}
	
	/*private void report() throws SQLException, ClassNotFoundException {
		GameReport gameReport = new GameReport(player);
	} */
	
	private void exitGame() {
		Platform.exit();
		System.exit(0);
	}
}
//======================================================
//======================================================
//Stuff for EC
//  public Dealer getDealer() {
//      return dealer;
//  }
//
//
//  public Player getPlayer() {
//      return player;
//  }
//
//  public Wager getWager() {
//      return wager;
//  }
//
//  public GameOptions getGameOptions() {
//      return gameOptions;
//  }
//
//  public ScoreBoard getScoreBoard() {
//      return scoreBoard;
//  }
//
//  public CardSelector getCardSelector() {
//      return cardSelector;
//  }
//
//  public Button getBtnDeal() {
//      return btnDeal;
//  }