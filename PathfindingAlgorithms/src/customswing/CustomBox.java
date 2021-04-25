package customswing;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * 
 * The following CustomBox class extends a Swing JPanel, but includes all the
 * specific properties I prefer to create a simple square box.
 * 
 * @author Troy Zada
 *
 */

public class CustomBox extends JPanel {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    /**
     * This constructor creates a CustomBox object which has the properties of a
     * normal panel, but with a few adjustments to it such that it creates a squared
     * box with the given colour and size.
     * 
     * @param background - Colour of the box.
     * @param size       - Size of the box to form a square.
     */
    public CustomBox(Color background, int size) {
	this.setPreferredSize(new Dimension(size, size));
	this.setBackground(background);
	this.setOpaque(true);
    }

}
