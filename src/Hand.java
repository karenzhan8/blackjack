import java.util.*;
public class Hand {
	//OBJECT VARIABLES
	private ArrayList<Card> hand = new ArrayList<Card>();
	private int totalValue = 0;
	private boolean isDealer;
	
	//CONSTRUCTOR
	public Hand(boolean d)
	{
		isDealer = d;
	}
	
	//GET TOTAL VALUE METHOD
	public int getTotalValue()
	{
		return totalValue;
	}
	
	//ADD CARD METHOD
	public void addCard(Card c)
	{
		hand.add(c);
		totalValue += c.getValue();
	}
	
	//RETURNS THE THE FIRST CARD OF A HAND IN A STRING FORM
	//(USED IN THE BLACKJACK CLASS IN ORDER TO REVEAL THE DEALER'S HIDDEN CARD
	public String firstCard()
	{
		String s = "";
		s += hand.get(0);
		return s;
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	//CONVERTS THE HAND TO STRING
	public String toString()
	{
		String s = "";
		//If the hand belongs to the dealer, hide the first card
		if(isDealer)
		{
			s += "Dealer's Hand:\n";
			s += "\tCard: ?????";
		}
		//Otherwise show the first card
		else
		{
			s += "Player's Hand\n\t";
			s += hand.get(0);
		}
		//Print out all remaining cards in the hand
		for(int i = 1; i < hand.size(); i++)
		{
			s += "\n\t";
			s += hand.get(i);
		}
		//If it is the player's hand, display the total value of the hand
		if(!isDealer)
		{
			s += "\n\tCurrent Total: " + this.getTotalValue();
		}
		return s;
	}

	public int getLength() {
		return hand.size();
	}
}
