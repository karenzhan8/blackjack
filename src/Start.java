import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Start extends JPanel implements ActionListener{
	JFrame frame = new JFrame ("BlackJack");
	
	//Buttons
	JButton in = new JButton ("INSTRUCTIONS");
	JButton start = new JButton ("START GAME");
	JButton value = new JButton ("SEE VALUES OF CARDS");
	JButton start2 = new JButton ("START GAME");
	
	//Images
	ImageIcon logo = new ImageIcon("BlackJack.png");
	ImageIcon instructions = new ImageIcon ("Instructions.jpg");
	ImageIcon values = new ImageIcon("Values.png");
	ImageIcon header = new ImageIcon ("UserInput.png");
	JLabel logoJLabel = new JLabel(logo);
	JLabel instructionJLabel = new JLabel(instructions);
	JLabel valuesJLabel = new JLabel (values);
	JLabel headerJLabel = new JLabel (header);
	
	//Bets
	int bet = 0;
	int amount = 1500;
	JTextField userInput = new JTextField(100);
	JButton enter = new JButton ("SET BET");
	JLabel error = new JLabel ("Please enter an integer within $0 and $" + amount + " to bet!", SwingConstants.CENTER);
	
	public Start(int amount)
	{
		this.amount = amount;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1200, 700)); 
		this.setBackground(Color.black);
				
		in.addActionListener(this); 
		in.setPreferredSize(new Dimension(500, 40));
		
		start.addActionListener(this); 
		start.setPreferredSize(new Dimension(500, 40));
				
		value.setPreferredSize(new Dimension(500, 40));
		value.addActionListener(this);	
		
		start2.setPreferredSize(new Dimension (500, 40));
		start2.addActionListener(this);
		
		error.setOpaque(true);
		error.setForeground(Color.white);
		error.setBackground(Color.black);
		userInput.setPreferredSize(new Dimension(10000, 100));
		userInput.setBackground(Color.darkGray);
		userInput.setForeground(Color.white);
		
		enter.addActionListener(this);
		enter.setPreferredSize(new Dimension(1005, 40));
		
		frame.add(this); 
		this.add(logoJLabel);
		this.add(instructionJLabel);
		instructionJLabel.setVisible(false);
		this.add(in);
		this.add(start);
		this.add(value);
		value.setVisible(false);
		this.add(valuesJLabel);
		valuesJLabel.setVisible(false);
		this.add(start2);
		start2.setVisible(false);
		this.add(headerJLabel);
		headerJLabel.setVisible(false);
		this.add(error);
		error.setVisible(false);
		this.add(userInput);
		userInput.setVisible(false);
		this.add(enter);
		enter.setVisible(false);
		frame.pack(); 
		frame.setVisible(true); 
	}
	
	private void setBet() {
        try {
        	String text = userInput.getText();
        	 bet = Integer.parseInt(text);
        	 if (setBet(bet)) {
           		 new Main(bet, amount);
           		 frame.setVisible(false);
           	 }
        }
       catch (NumberFormatException e1) {
       }
    }
	
	private boolean setBet(int n) {
		if (bet > 0 && bet <= amount) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void actionPerformed(ActionEvent event)
	{
		String command = event.getActionCommand(); 
		if(command.equals("INSTRUCTIONS"))
		{
			in.setVisible(false);
			start.setVisible(false);
			logoJLabel.setVisible(false);
			
			instructionJLabel.setVisible(true);		
			value.setVisible(true);
		}
		else if(command.contentEquals("START GAME") || command.contentEquals("")){
			this.setBackground(Color.white);
			in.setVisible(false);
			start2.setVisible(false);
			start.setVisible(false);	
			value.setVisible(false);
			logoJLabel.setVisible(false);
			instructionJLabel.setVisible(false);
			valuesJLabel.setVisible(false);
			
			this.setBackground(Color.black);
			headerJLabel.setVisible(true);
			error.setVisible(true);
			userInput.setVisible(true);
			enter.setVisible(true);
		}
		else if (command.contentEquals("SEE VALUES OF CARDS")) {
			instructionJLabel.setVisible(false);
			value.setVisible(false);
			this.setBackground(new Color(0, 0, 0));
			valuesJLabel.setVisible(true);
			start2.setVisible(true);

		}
		else if (command.contentEquals("SET BET")) {
			setBet();
		}
	}

	public static void main(String[] args)
	{
		new Start(1500);
	}
}

