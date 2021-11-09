package RackO;
import java.util.*;

public class CardStack {
	
	private Stack<Integer> cardStack = new Stack<Integer>();
	private Stack<Integer> discardStack = new Stack<Integer>();
	private int playerAmount;
	
	//create a deck of cards based on player amount
	public CardStack(int playerAmount) {
		
		this.playerAmount = playerAmount;
		
		switch (this.playerAmount) {
		
		case 2:
			for(int i = 1; i <= 40; i ++) {
				cardStack.push(i);
			}
			
			break;
		case 3:
			for(int i = 1; i <= 50; i ++) {
				cardStack.push(i);
			}
			break;
		case 4:
			for(int i = 1; i <= 60; i ++) {
				cardStack.push(i);
			}
			break;
			
		default:
			throw new IllegalArgumentException("Player amount must be 2, 3, or 4");
		}
		
		Collections.shuffle(cardStack);
	}
	
	public int draw() {
		
		if(cardStack.isEmpty()) {
			
			refillStack();
		}
		
		int drawnCard = cardStack.pop();

		return drawnCard;
	}
	
	public int takeFromDiscard() {
		
		if(!discardStack.isEmpty()) {
			
			return discardStack.pop();
		}
		
		throw new EmptyStackException();
		
	}
	
	//refill stack from discard pile if reach the end of your deck
	public void refillStack() {
		
		if(discardStack.isEmpty()) {
			throw new EmptyStackException();
		}
		
		while(!discardStack.isEmpty()) {
			
			cardStack.push(discardStack.pop());
		}
		Collections.shuffle(cardStack);
	}

	//deal cards at the start of the game
	public int dealCard() {
		
		return cardStack.pop();
	}
	
	public int getDiscardPileTop() {
		
		if(!discardStack.isEmpty()) {
		
			return discardStack.peek();
		}
		
		throw new EmptyStackException();
	}
	
	public void discardCard(int cardValue) {
		
		discardStack.push(cardValue);
	}
	
	//set up the discard stack with one card in it at the start of the game
	public void putTopOnDiscardPile() {
		int drawnCard = cardStack.pop();
		discardStack.push(drawnCard);
	}
}
