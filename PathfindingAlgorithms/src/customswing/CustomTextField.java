package customswing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;

import main.PathfindingMain;

public class CustomTextField extends JTextField {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // A JTextFieldf but with specific properties I prefer
    public CustomTextField(String name) {
	this.setText(name);
	this.setPreferredSize(new Dimension(100, 20));
	this.setFont(new Font(PathfindingMain.GAME_FONT, Font.PLAIN, 16));
	this.setForeground(new Color(0x00FF00));
	this.setBackground(new Color(255, 0, 0));
	this.setCaretColor(Color.white);
    }

}