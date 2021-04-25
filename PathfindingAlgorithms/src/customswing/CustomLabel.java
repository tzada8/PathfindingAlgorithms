package customswing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * 
 * The following CustomLabelclass extends a Swing JLabel, but includes all the
 * specific properties I prefer.
 * 
 * @author Troy Zada
 *
 */

public class CustomLabel extends JLabel {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Constants
    private static final String GAME_FONT = "Helvetica";
    public static final Font SMALL_TEXT = new Font(GAME_FONT, Font.PLAIN, 14);
    public static final Font MAIN_TEXT = new Font(GAME_FONT, Font.PLAIN, 16);
    public static final Font HEADER_TEXT = new Font(GAME_FONT, Font.BOLD, 20);

    /**
     * This constructor creates a CustomLabel object which has the properties of a
     * normal label, but with a few adjustments to it.
     * 
     * @param title - Title of Label.
     * @param font  - Font style and size of Label.
     */
    public CustomLabel(String title, Font font) {
	this.setForeground(Color.BLACK);
	this.setBackground(null);
	this.setFocusable(false);
	this.setText(title);
	this.setFont(font);
    }

}
