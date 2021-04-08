package customSwing;

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

    // Title for a given setting section with corresponding properties
    public CustomLabel(String title, int fontSize) {
	this.setForeground(new Color(0xFF00FF));
	this.setText("This is a test");
	this.setFont(new Font("Helvetica", Font.PLAIN, fontSize));
    }

}
