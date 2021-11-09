package RackO;
import java.util.*;

public class Main_PlayingTheGame {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Rack-O!");
		System.out.print("How many players will be playing? (2/3/4): ");

		int playerAmount = input.nextInt();
		
		while(playerAmount < 2 || playerAmount > 4) {
			
			System.out.print("Please enter a valid player amount: ");
			playerAmount = input.nextInt();
		}
		
		input.nextLine();
		
		//initialize Players, RackO_Game and CardStack Objects
		CardStack cardStack = new CardStack(playerAmount);
		RackO_Game RackO_Game = new RackO_Game(playerAmount, cardStack);
		
		ArrayList<Player> players = new ArrayList<Player>();
		
		for(int i = 0; i < playerAmount; i++) {
			
			System.out.print("Enter the name of Player " + (i+1) + ": ");
			String name = input.nextLine();
			players.add(RackO_Game.setUpPlayer(name));
		}
		
		//play the game until winner is returned
		String winner = RackO_Game.playGame(players, input);
		
		System.out.print("Winner: " + winner);
		System.out.print("I hope you enjoyed!");
	}
}
