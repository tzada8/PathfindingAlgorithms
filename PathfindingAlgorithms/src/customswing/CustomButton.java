package customswing;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * 
 * The following CustomButton class extends a Swing JButton, but includes all
 * the specific properties I prefer.
 * 
 * @author Troy Zada
 *
 */

public class CustomButton extends JButton {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // A JButton but with specific properties I prefer
    /**
     * This constructor creates a CustomButton object which has the properties of a
     * normal button, but with a few adjustments to it.
     * 
     * @param name - Title of Button.
     */
    public CustomButton(String name) {
	this.setForeground(Color.BLACK);
	this.setBackground(Color.WHITE);
	this.setFocusable(false);
	this.setText(name);
	this.setFont(CustomLabel.MAIN_TEXT);
	this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2),
		BorderFactory.createLineBorder(Color.WHITE, 3)));
	this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

}