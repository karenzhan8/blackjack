import javax.swing.ImageIcon;

public class Card {
	//CLASS VARIABLES
	//arrays containing ranks and suits to make toString simpler
	private static String[] ranks = {"X","Ace","2","3","4","5","6","7","8","9","10","Jack", "Queen", "King"};
	private static String[] suits = {"X", "Spades", "Hearts", "Clubs", "Diamonds"};
	private static String[] suitLetter = {"X", "s", "h", "c", "d"};
	//OBJECT VARIABLES
	private int rank;
	private int suit;
	private int value;
	private ImageIcon image;
	//THIS IS A RANDOM CHANGE
	//CONSTRUCTOR
	public Card(int r, int s)
	{
		rank = r;
		suit = s;
		//If the rank is from JACK - KING the VALUE is 10
		if(r > 9)
		{
			value = 10;
		}
		//If the rank is ACE, the VALUE is 11
		else if(r == 1)
		{
			value = 11;
		}
		//Else the VALUE is equal to the RANK
		else
		{
			value = r;
		}
		
		//String containing the rank
		String rankNum = String.format("%d02", r);
		//String containing the suit
		String suitLet = suitLetter[s];
		//Finds the right image
		image = new ImageIcon(rankNum + suitLet + ".gif");
	}
	
	//SETTER METHODS
	public void setRank(int r)
	{
		rank = r;
	}
	public void setSuit(int s)
	{
		suit = s;
	}
	public void setValue(int v)
	{
		value = v;
	}
	
	//GETTER METHODS
	public int getRank()
	{
		return rank;
	}
	public int getSuit()
	{
		return suit;
	}
	public int getValue()
	{
		return value;
	}
	public ImageIcon getImage()
	{
		return image;
	}
	
	//TOSTRING METHODS
	public String rankToString()
	{
		return ranks[rank];
	}
	public String suitToString()
	{
		return suits[suit];
	}
	public String toString()
	{
		return "Card: " + this.rankToString() + " of "+ this.suitToString();
	}
}

