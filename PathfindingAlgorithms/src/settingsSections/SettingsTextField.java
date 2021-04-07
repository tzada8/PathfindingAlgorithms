package settingsSections;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.PathfindingMain;

public class SettingsTextField extends JPanel {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Fields
    private JLabel textFieldLabel;
    private JTextField entry;

    // Each text field in the Settings menu has a label describing the textfield,
    // and a textfield for the entry
    public SettingsTextField(String name) {
	this.setBackground(null);

	// Label for the text field
	textFieldLabel = new JLabel(name + ":");

	// Properties for settings textfield
	entry = new JTextField(name);
	entry.setPreferredSize(new Dimension(100, 20));
	entry.setFont(new Font(PathfindingMain.GAME_FONT, Font.PLAIN, 16));
	entry.setForeground(new Color(0x00FF00));
	entry.setBackground(new Color(255, 0, 0));
	entry.setCaretColor(Color.white);

	this.add(textFieldLabel);
	this.add(entry);
    }

    // Instance method that returns the textfield for the specific setting
    public String getEntryTextField() {
	return entry.getText();
    }

}
