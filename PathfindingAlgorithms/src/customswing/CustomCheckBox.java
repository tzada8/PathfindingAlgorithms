package customswing;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JCheckBox;

/**
 * 
 * The following CustomCheckBox class extends a Swing JCheckBox, but includes
 * all the specific properties I prefer.
 * 
 * @author Troy Zada
 *
 */

public class CustomCheckBox extends JCheckBox {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    /**
     * This constructor creates a CustomCheckBox object which has the properties of
     * a normal check box, but with a few adjustments to it.
     * 
     * @param name - Title of CheckBox.
     */
    public CustomCheckBox(String name) {
	this.setForeground(Color.BLACK);
	this.setBackground(null);
	this.setFocusable(false);
	this.setText(name);
	this.setFont(CustomLabel.SMALL_TEXT);
	this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

}
