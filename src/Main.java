import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JPanel implements ActionListener, Blackjack{
	//The frame and panels
	JFrame frame = new JFrame("BlackJack");
	JPanel gamePanel = new JPanel();
	//Timer for the animation
	Timer timer = new Timer(5, new ActionListener(){
		public void actionPerformed(ActionEvent event) {
			if(playerAnimating) {
				posx += intX;
				posy += intY;
				
				if(posx <= numCards*100) {
					intX = 0;
				}
				if(posy >= 490) {
					intY = 0;
				}
				if(posx <= numCards*100 && posy >= 490) {
					posx = 1000;
					posy = 300;
					drawPlayerCard();
					timer.stop();
				}
			}
			else {
				posx += intX;
				posy += intY;
				
				if(posx <= numCards*100) {
					intX = 0;
				}
				if(posy <= 50) {
					intY = 0;
				}
				if(posx <= numCards*100 && posy <= 50) {
					posx = 1000;
					posy = 300;
					dealerHit();
					timer.stop();
				}
			}
			repaint();
		}
	});
	
	//Cards
	static Deck deck;
	
	Card ace;

	public static Hand playerHand;
	public static Hand dealerHand;

	//Things that appear on the screen (labels, buttons, the back of cards)
	Font font = new Font("Courier", Font.BOLD,25);
	String action = "Welcome to Blackjack";
	
	JButton hit = new JButton();
	JButton stand = new JButton();
	JButton aceOne = new JButton();
	JButton aceEleven = new JButton();
	
	ImageIcon HIT = new ImageIcon("hit.gif");
	ImageIcon STAND = new ImageIcon("stand.gif");
	ImageIcon ONE = new ImageIcon("one.gif");
	ImageIcon ELEVEN = new ImageIcon("eleven.gif");
	//ImageIcon STAND = new ImageIcon("stand.gif");
	//ImageIcon ONE = new ImageIcon("back.gif");
	//ImageIcon ELEVEN = new ImageIcon("eleven.gif");
	
	ImageIcon back = new ImageIcon("Back.png");
	
	//Coordinates for dealing animations
	int posx = 1000;
	int posy = 300;
	int intX = 4;
	int intY = 1;
	
	int numCards = 2;
	//Conditionals for when the player and dealer can do actions
	static boolean playerCanPress = false;
	boolean dealerActions = false;
	boolean playerAnimating = true;
	boolean animationCanMoveOn = false;
	
	//For the start and end
	static String winner;
	static int bet;
	static int amount = 1500;
	
	//======================PAINTING COMPONENTS========================
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		hit.setBounds(850, 620, 120, 40);
		stand.setBounds(1000, 620, 120, 40);
		//Font
		g.setFont(font);
		FontMetrics fontmetric = g.getFontMetrics();
		g.setColor(Color.white);
		//Text
		g.drawString("Dealer", centreStringX(fontmetric, "Dealer"), 25);
		g.drawString("Player", centreStringX(fontmetric, "Player"), 660);
		
 		g.drawString(action, centreStringX(fontmetric, action), 325);
 		//Cards
 		back.paintIcon(this, g, 1000, 300);
 		back.paintIcon(this, g, posx, posy);
		drawPlayerCards(g);
		drawDealerCards(g);
	}
	//Helper method to return the center coordinates of the string
	public int centreStringX(FontMetrics metric, String text) {
		return (this.getWidth() - metric.stringWidth(text))/2;
	}
	//Draws hands
	public void drawPlayerCards(Graphics g) {
		int i = 1;
		for(Card card : playerHand.getHand()) {
			card.getImage().paintIcon(this, g, i*100, 490);//change this to actual card later
			i++;
		}
	}
	public void drawDealerCards(Graphics g) {
		int i = 1;
		for(Card card : dealerHand.getHand()) {
			if(i <= 1) {
				card.getImage().paintIcon(this, g, i*100, 50);//change this to actual card later
			}
			else {
				back.paintIcon(this, g, i*100, 50);
			}
			i++;
		}
	}
	//Animate draw action
	public void animateDrawPlayer(Graphics g, int i) {
		numCards = i;
		
		intY = 1;
		intX = (1000 - i*100) / -230;
		
		timer.start();
	}
	public void animateDrawDealer(Graphics g, int i) {
		numCards = i;
		
		intY = -1;
		intX = -(1000 - i*100) / 280;
		
		timer.start();
	}
	//This draws the player's buttons for hit and stand
	public void playerButtons(Graphics g) {
		hit.setBounds(1000, 645, 71, 20);
		this.add(hit);
		stand.setBounds(900, 645, 71, 20);
		this.add(stand);
	}
	//======================ACTIONS========================
	public void deal()
	{
		//Filler card, 
		Card draw;
		//Two cards are added to each hand
		for(int i = 0; i < 2; i ++)
		{
			draw = deck.draw();
			if(draw.getValue() == 11 && playerHand.getTotalValue() + draw.getValue() > 21) {
				draw.setValue(1);
			}
			
			playerHand.addCard(draw);
				
			draw = deck.draw();
			dealerHand.addCard(draw);
			
			if(draw.getRank() == 1)
			{
				if(dealerHand.getTotalValue() + 11 > 21) draw.setValue(1);
				else draw.setValue(11);
			}
		}
	}
	//Player actions
	public void stand() {
		playerCanPress = false;
		playerAnimating = false;
		hit.setVisible(false);
		stand.setVisible(false);
		
		dealerTurn();
	}
	public void hit(Card draw) {
		playerHand.addCard(draw);
		action = "You drew a " + draw.toString() + ". Your hand's value is " + playerHand.getTotalValue();
		
		drawPlayerCards(this.getGraphics());
		
		if(playerHand.getTotalValue() > 21) {
			playerCanPress = false;
			hit.setVisible(false);
			stand.setVisible(false);
			repaint();
			if (determineWinner() == "player") {
				amount += bet;
			}
			else if (determineWinner() == "dealer") {
				amount -= bet;
			}
			new End(playerHand.getTotalValue(), dealerHand.getTotalValue(), bet, amount);
		}
		else {
			repaint();
			playerTurn();
		}
	}
	public void drawPlayerCard() {
		Card draw = deck.draw();
		if(draw.getValue() == 11) {
			setAce(draw);
		}
		else {
			hit(draw);
		}
	}
	public void dealerHit() {
		Card draw;
		//the dealer draws cards
		draw = deck.draw();
		dealerHand.addCard(draw);
		
		dealerTurn();
	}
	//What happens on the turns
	public void playerTurn(){
		playerCanPress = true;
		playerButtons(this.getGraphics());
	}
	public void dealerTurn() {
		//Draws multiple times
		if(dealerHand.getTotalValue() < 17) {
			action = "Dealer hits";
			animateDrawDealer(this.getGraphics(), dealerHand.getLength()+1);
		}
		else {
			//Stands
			action = "Dealer stands";
			repaint();
			//Moves to end sequence
			if (determineWinner() == "player") {
				amount += bet;
			}
			else if (determineWinner() == "dealer") {
				amount -= bet;
			}
			new End(playerHand.getTotalValue(), dealerHand.getTotalValue(), bet, amount);
		}
	}
	
	public String determineWinner() {
		if(playerHand.getTotalValue() > 21) {
			return "dealer";
		}
		else if(dealerHand.getTotalValue() > 21) {
			return "player";
		}
		else if(playerHand.getTotalValue() > dealerHand.getTotalValue()) {
			return "player";
		}
		else if (playerHand.getTotalValue() == dealerHand.getTotalValue()) {
			return "tie";
		}
		else {
			return "dealer";
		}
	}
	
	//If an ace is drawn
	public void setAce(Card ace) {
		action = "You drew an ace! What value do you want it to have?";
		this.ace = ace;
		
		aceButtons();
		repaint();
	}
	public void defaultButtons() {
		hit.setIcon(HIT);
		stand.setIcon(STAND);
		hit.setActionCommand("HIT");
		stand.setActionCommand("STAND");
	}
	public void aceButtons() {
		playerCanPress = true;
		hit.setIcon(ONE);
		stand.setIcon(ELEVEN);
		hit.setActionCommand("ONE");
		stand.setActionCommand("ELEVEN");
	}
	public void aceValue(int v) {
		ace.setValue(v);
		
		defaultButtons();
		playerCanPress = false;
		
		hit(ace);
	}
	//====================CONSTRUCTOR=====================
	public Main(int bet, int amount){
		this.bet = bet;
		this.amount = amount;
		
		frame.setSize(1200, 700);
		//new Start();

		deck = new Deck();
		deck.shuffle();
		//Establish hands
		playerHand = new Hand(false);
		dealerHand = new Hand(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setBackground(Color.DARK_GRAY);
		this.setSize(1200,700);
		
		frame.add(this);
		
		//Activates buttons but does not draw them
		hit.addActionListener(this);
		hit.setIcon(HIT);
		hit.setActionCommand("HIT");
		
		stand.addActionListener(this);
		stand.setIcon(STAND);
		stand.setActionCommand("STAND");
		
		//frame.pack();
		frame.setVisible(true);
		
		deal();
		action = "Your hand's current value is " + playerHand.getTotalValue();
		
		playerTurn();
	}
	
	//===========================MAIN==============================
	
	//===================ACTIONLISTENER FOR BUTTONS===================
	public void actionPerformed(ActionEvent event){
		String action = event.getActionCommand();
		
		if(playerCanPress) {
			if(action.equals("HIT")) {
				playerCanPress = false;
				animateDrawPlayer(this.getGraphics(), playerHand.getLength() + 1);
			}
			else if(action.equals("STAND")) {
				stand();
			}
			else if(action.equals("ONE")) {
				aceValue(1);
			}
			else if(action.equals("ELEVEN")) {
				aceValue(11);
			}
		}
	}
}
