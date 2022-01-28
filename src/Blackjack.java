interface Blackjack {

	//The deck
	public static Deck deck = new Deck();
	//The hands
	public static Hand playerHand = new Hand(false);
	public static Hand dealerHand = new Hand(true);
	//Money the player starts with
	static int amount = 1500;
	
	public static void playerTurn() {
	}
	
	public static void dealerTurn() {
	}
	
	public static void deal() {
	}
	
	public static void setAce() {
	}
}
