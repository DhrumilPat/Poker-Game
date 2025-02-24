package gamedata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import Player.Player;
import card.Card;
import hand.Hand;
import hand.PokerHand;
//import players.Player;


public class GameData {
	//Lab 32.2
	 Connection connection;
	 Statement statement;
	 ResultSet results;
	
	public GameData() {
		//Lab 32.2

		//Load the Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Establish a connection
		try {
			 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tioli", 
					"root", "Dp7112002");
			System.out.println("Database Connected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Create a statement object
		try {
			 statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertHand(Player player) {
		//Lab 32.2
		String sqlStatement;
		Card[] tempCards = player.getHand().getCards();
		String handId = player.getId() + new Date().getTime();
		
		for(int i = 0; i < tempCards.length; i++) {
			sqlStatement = 
					"Insert Into hands (hand_id, card_num, player_id, face, suit) VALUES(" +
			"'" + handId + "', " +
			(i + 1) + ", " +
			"'" + player.getId() + "', " +
			"'" + tempCards[i].getFace() + "', " +
			"'" + tempCards[i].getSuit() + "'" +
			")";
			
			try {
				statement.executeUpdate(sqlStatement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void updateBank(Player player) {
		//PA 6.2
		String sqlStatement;
		
		sqlStatement = 
				"Update player " + 
				"Set bank = " + "'" + player.getBank() + "'" +
				"Where player_id = " + "'" + player.getId() + "'" ;
		
		try {
			statement.executeUpdate(sqlStatement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void insertResults(Player player, int amountWon) {
        //PA 6.2
        String sqlStatement;
        String gameID = player.getId() + new Date().getTime();

        sqlStatement = 
            "INSERT INTO game_results (game_id, player_id, hand_descr, amount_won, player_bank) VALUES("
            + "'" + gameID + "', " +
            "'" + player.getId() + "', " +
            "'" + ((PokerHand)player.getHand()).getHandDescr() + "', " +
            amountWon + ", " + 
            "'" + player.getBank() + "'" +
            ")";

        try {
            statement.executeUpdate(sqlStatement);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	private ResultSet getPlayers() {
		//String sqlStatement;
		ResultSet results = null;
		
		try {
			results = statement.executeQuery("SELECT * FROM player");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return results;
	}
	
	public Player getRandomPlayer() {
		//For use with PA 6.2 and beyond
		Player player = null;
		
		try {
			//Get the list of players
			ResultSet playerData = getPlayers();
			
			//Now determine the size of the list
			playerData.last();
			int size = playerData.getRow() - 1;

			//Get random player and move to that place in the ResultSet
			int randomPlayer = (int)(Math.random() * size) + 1;
			playerData.absolute(randomPlayer);

			//Create a new player object
			player = new Player(playerData.getString(2), playerData.getString(1), playerData.getInt(3), new PokerHand()); 
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return player; 
	}
	

	public ResultSet getReportData(Player player) {
		//Final Project
		String playerId = player.getId();
        String sqlStatement = "SELECT * FROM game_results WHERE player_id = '" + playerId + "'";

        try {
            results = statement.executeQuery(sqlStatement);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return results;
    }
		
	

	public void close() {
		//Lab 32.2
		//Close the database object connection
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
