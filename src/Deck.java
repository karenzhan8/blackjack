import java.util.*;

public class Deck {
	
	//OBJECT VARIABLES
	private ArrayList<Card> deck = new ArrayList<Card>();
	
	//CONSTRUCTOR METHODS
	public Deck()
	{
		//Going through all of the ranks
		for(int r = 1; r < 14; r++)
		{
			//Going through all the suits
			for(int s = 1; s < 5; s++)
			{
				deck.add(new Card(r,s));
			}
		}
	}
	
	//SHUFFLE METHOD
	public void shuffle()
	{
		//Filler arraylist that stores the new cards
		ArrayList<Card> filler = new ArrayList<Card>();
		Random rand = new Random();
		//Randomly select a card from the deck and place it into the filler deck until the deck is empty
		while(deck.size() > 0)
		{
			int i = rand.nextInt(deck.size());
			filler.add(deck.get(i));
			deck.remove(i);
		}
		//Have the deck equal the filler
		deck = filler;
	}
	
	//DRAW METHOD
	public Card draw()
	{
		//Card r is the card at the top of the deck
		Card r = deck.get(deck.size()-1);
		deck.remove(deck.size()-1);
		return r;
	}
}