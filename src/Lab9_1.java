import Player.Player;

public class Lab9_1 {

	public static void main(String[] args) {
		Player player1 = new Player(null);
		Player player2 = new Player("Joelene", "98765 ", null);
		Player player3 = new Player("Fred", "097843 ", 3000, null);
		
		System.out.println(player1.toString());
		System.out.println(player2);
		System.out.println(player3);
	}

}
