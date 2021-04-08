package customswing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class CustomLabel extends JLabel {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Constants
    public static final int HEADER_FONT_SIZE = 20;
    public static final int PARAGRAPH_FONT_SIZE = 14;

    // A JLabel but with specific properties I prefer
    public CustomLabel(String title, int fontSize) {
	this.setForeground(new Color(0xFF00FF));
	this.setText(title);
	this.setFont(new Font("Helvetica", Font.PLAIN, fontSize));
    }

}
