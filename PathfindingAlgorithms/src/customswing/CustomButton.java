package customSwing;

import java.awt.Font;

import javax.swing.JButton;

import main.PathfindingMain;

public class CustomButton extends JButton {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // A JButton but with specific properties I prefer
    public CustomButton(String name) {
	this.setFocusable(false);
	this.setText(name);
	this.setFont(new Font(PathfindingMain.GAME_FONT, Font.PLAIN, 16));
    }

}