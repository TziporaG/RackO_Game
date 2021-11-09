package RackO;

import java.util.Arrays;

public class Player {
	
	private String name;
	private int[] rackO;
	
	//copy the dealt cards into the player's RackO
	public Player(String name, int[] initialRackO) {
		
		this.name = name;
		this.rackO = Arrays.copyOf(initialRackO, 10);
	}
	
	public String getName() {
		
		return this.name;
	}
	
	public int swapCard(int cardValue, int cardIndex) {
		
		int currentCard = rackO[cardIndex-1];
		rackO[cardIndex-1] = cardValue;
		
		return currentCard;
		
	}
	
	//players can take a turn by swapping their chosen card with a card on their RackO
	public boolean takeATurn(int chosenCard, int swapCardIndex, CardStack cardStack) {
		

		int swappedOutCard = this.swapCard(chosenCard, swapCardIndex);
		cardStack.discardCard(swappedOutCard);

			for(int i = 0; i < rackO.length; i++) {
				
				if( rackO[i] > rackO[i+1]) {
					
					return false;
				}
			}
			
			return true;
		}
	

	public int[] getRackO() {
		
		return Arrays.copyOf(this.rackO, 10);
	}
	


}
