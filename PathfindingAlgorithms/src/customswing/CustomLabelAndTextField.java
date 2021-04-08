package customswing;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomLabelAndTextField extends JPanel {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Fields
    private CustomLabel textFieldLabel;
    private CustomTextField entry;

    // Each TextField has a Label beside it that describes the TextField
    public CustomLabelAndTextField(String name) {
	this.setBackground(null);

	// Label for the text field
	textFieldLabel = new CustomLabel(name + ":", CustomLabel.PARAGRAPH_FONT_SIZE);

	// Properties for settings textfield
	entry = new CustomTextField(name);

	this.add(textFieldLabel);
	this.add(entry);
    }

    // Instance method that returns the textfield for the specific setting
    public String getEntryTextField() {
	return entry.getText();
    }

}
