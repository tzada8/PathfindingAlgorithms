package windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import main.PathfindingMain;

public class SettingsWindow extends JFrame implements ActionListener {
    
    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;
    
    // Fields
    private JButton finish; // submit all settings
    private JTextField widthField;
    private JTextField heightField;

    public SettingsWindow() {
	// Settings Window properties
	this.setTitle("Settings");
	this.setIconImage(new ImageIcon("images/pathfinding_icon.png").getImage());
	this.setResizable(false);
	this.getContentPane().setBackground(PathfindingMain.BACKGROUND_COLOUR);
	this.setLayout(new FlowLayout());

	// Finish button to submit settings
	finish = new JButton("Finish");
	finish.setFont(new Font(PathfindingMain.GAME_FONT, Font.PLAIN, 16));
	finish.addActionListener(this);

	// Labels for maze size (width and height)
	JLabel heightLabel = new JLabel("Height:");
	JLabel widthLabel = new JLabel("Width:");
	
	// Textfields for maze size (width and height)
	heightField = new JTextField("Height");
	heightField.setPreferredSize(new Dimension(100, 20));
	heightField.setFont(new Font(PathfindingMain.GAME_FONT, Font.PLAIN, 16));
	heightField.setForeground(new Color(0x00FF00));
	heightField.setBackground(new Color(255, 0, 0));
	heightField.setCaretColor(Color.white);
	
	widthField = new JTextField("Width");
	widthField.setPreferredSize(new Dimension(100, 20));
	widthField.setFont(new Font(PathfindingMain.GAME_FONT, Font.PLAIN, 16));
	widthField.setForeground(new Color(0x00FF00));
	widthField.setBackground(new Color(255, 0, 0));
	widthField.setCaretColor(Color.white);

	// Adding all elements
	this.add(heightLabel);
	this.add(heightField);
	this.add(widthLabel);
	this.add(widthField);
	this.add(finish);
	
	this.pack();
	this.setLocationRelativeTo(null);
	this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == finish) {
	    System.out.println("Hello " + widthField.getText());
	    this.dispose();
	}
    }
    
    // Instance method that returns the value the user set for the WIDTH field
    public String getWidthField() {
	return widthField.getText();
    }
    
    // Instance method that returns the value the user set for the HEIGHT field
    public String getHeightField() {
	return heightField.getText();
    }

}
