package customswing;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class CustomBox extends JPanel {
    
    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // A JLabel but with no text, and only used for the box 
    public CustomBox(Color background, int size) {
	this.setPreferredSize(new Dimension(size, size));
	this.setBackground(background);
	this.setOpaque(true);
    }

}
