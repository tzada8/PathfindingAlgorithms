import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class HomeFrame extends JFrame {
    
    private static final Color BACKGROUND_COLOUR = Color.LIGHT_GRAY;

    public HomeFrame() {
	// Adjusting basic properties of my main screen frame
	this.setTitle("Pathfinding Algorithms");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	this.setSize(500, 500); // MIGHT NEED TO ADJUST SIZE TO FIT NECESSARY ALGORITHMS
	this.setVisible(true);
	ImageIcon image = new ImageIcon("images/pathfinding_icon.png");
	this.setIconImage(image.getImage());
	this.getContentPane().setBackground(BACKGROUND_COLOUR);
    }

}
