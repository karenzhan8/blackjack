import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class TestFrame extends JFrame {

    private DefaultListModel<String> model;

    public TestFrame() {
        setBet();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setBet() {
        final JTextField userInput = new JTextField(10);
        JButton set = new JButton("SET BET");
        set.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String text = userInput.getText();
                //bet = Integer.parseInt(text.getText());
                model.addElement(text);
            }
        });

        this.add(userInput);
        this.add(set);


    }




    public static void main(String args[]) {
        new TestFrame();
    }

}