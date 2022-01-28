import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class End extends JPanel implements ActionListener{
	//Frames and Panels
	JFrame frame = new JFrame ("END");
	
	//Formatting for the end
	JPanel end2 = new JPanel(new GridLayout(2,0));
	JPanel end1 = new JPanel(new BorderLayout());
	JPanel end3 = new JPanel(new BorderLayout());
	JPanel end4 = new JPanel (new GridLayout(4,0));
	
	ImageIcon north = new ImageIcon("north.png");
	ImageIcon south = new ImageIcon("south.png");
	ImageIcon west = new ImageIcon("west.png");
	ImageIcon east = new ImageIcon("east.png");
	ImageIcon win = new ImageIcon ("win.png");
	ImageIcon loss = new ImageIcon ("loss.png");
	ImageIcon tie = new ImageIcon ("tie.png");
	ImageIcon black = new ImageIcon ("black.png");
	ImageIcon black1 = new ImageIcon ("black1.png");
	ImageIcon busted = new ImageIcon("busted.png");
	
	JLabel northLabel = new JLabel(north);
	JLabel southLabel = new JLabel(south);
	JLabel westLabel = new JLabel(west);
	JLabel eastLabel = new JLabel(east);
	JLabel winLabel = new JLabel (win);
	JLabel lossLabel = new JLabel(loss);
	JLabel bustedLabel = new JLabel(busted);
	JLabel tieLabel = new JLabel (tie);
	JLabel blackLabel = new JLabel (black);
	JLabel blackLabel1 = new JLabel (black);
	JLabel blackLabel2 = new JLabel (black1);
	JLabel blackLabel3 = new JLabel (black1);
	JLabel blackLabel4 = new JLabel (black1);
	
	JLabel info;
	JButton playAgain = new JButton ("PLAY AGAIN");
	JButton restart = new JButton ("RESTART");
	int bet; 
	int amount;
	
	public End (int player, int dealer, int bet, int amount) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1200, 700)); 
		
		end1.setBackground(Color.black);
		end2.setBackground(Color.black);
		end3.setBackground(Color.black);
		end4.setBackground(Color.black);
		
		this.amount = amount;
		this.bet = bet;
		playAgain.addActionListener(this);
		restart.addActionListener(this);
		
		end1.add(northLabel,BorderLayout.NORTH);
		end1.add(eastLabel,BorderLayout.EAST);
		end1.add(southLabel,BorderLayout.SOUTH);
		end1.add(westLabel,BorderLayout.WEST);
		end1.add(end2,BorderLayout.CENTER);
		
		//TIE
		if (player == dealer) {
			end2.add(tieLabel);
		}
		//IF PLAYER LOSES
		else if (determineWinner(player, dealer).equals("dealer")) {
			end2.add(lossLabel);
		}
		//IF PLAYER WINS
		else if(determineWinner(player, dealer).equals("player")) {
			end2.add(winLabel);
		}
		

		info = new JLabel( "Your hand's value is " + player + ". The dealer's hand's value is " + dealer + ". You currently have $" + Integer.toString(amount) + "!", SwingConstants.CENTER);
		info.setOpaque(true);
		info.setBackground(Color.black);
		info.setForeground(Color.white);
		
		end3.add(info, BorderLayout.NORTH);
		end4.add(blackLabel2);
		if (amount > 0) {
			end4.add(playAgain);
			end4.add(restart);
		}
		else {
			end4.add(restart);
			end4.add(blackLabel4);
		}
		end4.add(blackLabel3);
		end3.add(end4, BorderLayout.CENTER);
		end3.add(blackLabel,BorderLayout.EAST);
		end3.add(blackLabel1,BorderLayout.WEST);
		end2.add(end3);
		
		frame.add(end1);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	private String determineWinner (int p, int d) {
		if (p > 21) return "dealer";
		if (d > 21) return "player";
		else if (p == 0) return "dealer";
		else if (d == 0) return "player";
		else return determineWinner(p-1,d-1);
	}
	
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.contentEquals("RESTART")) {
			new Start(1500);
		}
		else if (command.contentEquals("PLAY AGAIN")) {
			new Main(bet, amount);
		}
		
	}
	
}
