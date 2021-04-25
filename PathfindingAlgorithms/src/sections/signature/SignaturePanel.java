package sections.signature;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import customswing.CustomLabel;
import main.MainPanel;

/**
 * 
 * The following SignaturePanel class acts as a container for the words "By:
 * Troy Zada". To distinguish that the entire program was created solely by Troy
 * Zada.
 * 
 * @author Troy Zada
 *
 */

public class SignaturePanel extends JPanel {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Constants
    public static final int HEIGHT = 30;

    /**
     * This constructor creates a SignaturePanel object which is a panel that holds
     * the text of the author of the program "Troy Zada".
     */
    public SignaturePanel() {
	this.setBackground(MainPanel.COMPONENT_COLOUR);
	this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

	this.add(new CustomLabel("Troy Zada", CustomLabel.HEADER_TEXT));

    }

}
