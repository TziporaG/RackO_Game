package RackO;

import java.util.ArrayList;
import java.util.Scanner;

public class RackO_Game {

	private int playerAmount;
	private CardStack cardStack;

	public RackO_Game(int playerAmount, CardStack cardStack) {

		if (playerAmount < 2 || playerAmount > 4) {

			throw new IllegalArgumentException("Illegal player amount");
		}

		this.playerAmount = playerAmount;
		this.cardStack = cardStack;

	}

	//give each player 10 cards from the stack
	public Player setUpPlayer(String name) {

		int[] initialCards = new int[10];

		for (int i = 0; i < initialCards.length; i++) {

			initialCards[i] = cardStack.dealCard();
		}

		return new Player(name, initialCards);

	}
	
	//method to play the game
	public String playGame(ArrayList<Player> players, Scanner input) {

		System.out.println("\nLet's get started!");
		boolean winner = false;
		
		//set up discard pile with one card in it
		this.cardStack.putTopOnDiscardPile();
		
		int chosenCard;
		String winnerName = "";
		
		//play until there is a winner
		while (!winner) {

			for (int i = 0; i < players.size(); i++) {
				
				chosenCard = 0;
				printRackOBoard(players.get(i));
				
				//print current discard stack top
				System.out.print("\nCurrent Top Of Discard pile: ");

				System.out.print(this.cardStack.getDiscardPileTop());
				
				//allow player to draw a new card or take from discard pile
				System.out.print("\nWould you like to draw a new card or take from the top card discard pile?");
				System.out.print("\nEnter 'take' or 'draw': ");
				
				String choice = input.nextLine();

				while(!choice.toLowerCase().equals("take") && !choice.toLowerCase().equals("draw")) {
					System.out.print("\nERROR: Please only enter 'take' or 'draw': ");
					choice = input.nextLine();
					
				}
				
				if (choice.toLowerCase().equals("take")) {
					
					chosenCard = this.cardStack.takeFromDiscard();

				}

				else if (choice.toLowerCase().equals("draw")) {

					chosenCard = this.cardStack.draw();
					System.out.println("chosen: " + chosenCard);

				}

				//swap with a card on the RackO, 1 being the bottom rack
				System.out.print("What card postion would you like to swap it with (1-10): ");
				int swapIndex = input.nextInt();
				input.nextLine();
				
				while(swapIndex < 0 || swapIndex > 10) {
					System.out.print("ERROR: Please enter a valid position (1-10): ");
					swapIndex = input.nextInt();
					
				}
				
				//check if there is a winner and print their name after the while loop if so
				winner = players.get(i).takeATurn(chosenCard, swapIndex, cardStack);
				
				if(winner) {
				winnerName = players.get(i).getName();
				}
			}	
			
		}
		
		return winnerName;
			


	}

	//print the rackO of the current player
	public static void printRackOBoard(Player player) {
		System.out.println("\n" + player.getName() + "'s Rack-O board: ");
		System.out.println("------------------------------------------------------------------------");

		int[] playerRackO = player.getRackO();

		System.out.print("Bottom Rack ");
		for (int i = 0; i < playerRackO.length; i++) {

			System.out.print(" | " + playerRackO[i]);

		}
		System.out.print(" | Top Rack\n");
		System.out.println("------------------------------------------------------------------------");
	}

}
