package Player;

import hand.Hand;

public class Player {
	private String name;
	private String id;
	private int bank;
	private Hand hand;
	
	public Player() {
		// NO-ARG CONSTRUTOR THAT DOES NOTHING 
	}
	
	public Player(Hand hand) {
		name = "JaeCool";
		id = "123432";
		bank = 1000;
		this.hand = hand;
		}
	
	public Player(String name, String newID, Hand hand) {
		this.name = name;
		id = newID;
		bank = 1000;
		this.hand = hand;
	}
	
	public Player(String name, String id, int bank, Hand hand) {
		this.name = name;
		this.id = id;
		this.bank = bank;
		this.hand = hand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getBank() {
		return bank;
	}

	public void setBank(int bank) {
		this.bank = bank;
	}
	
	public Hand getHand() {
		return hand;
	}

	@Override
	public String toString() {
		return "Players " + name + ", id: " + id + "has a bank of $" + bank;
	}
}



