package main;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import algorithms.AlgorithmPanel;

public class HomeFrame extends JFrame {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    private static final Color BACKGROUND_COLOUR = Color.LIGHT_GRAY;

    public HomeFrame() {
	// Adjusting basic properties of my main screen frame
	this.setTitle("Pathfinding Algorithms");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	this.setSize(500, 500); // MIGHT NEED TO ADJUST SIZE TO FIT NECESSARY ALGORITHMS
	ImageIcon image = new ImageIcon("images/pathfinding_icon.png");
	this.setIconImage(image.getImage());
	this.getContentPane().setBackground(BACKGROUND_COLOUR);
	this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

	// All algorithms
	this.add(new AlgorithmPanel(Color.red));
	this.add(new AlgorithmPanel(Color.blue));
	this.add(new AlgorithmPanel(Color.green));
	
	this.setLocationRelativeTo(null);
	this.setVisible(true);
    }

}
