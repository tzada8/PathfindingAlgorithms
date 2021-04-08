package customswing;

import javax.swing.JRadioButton;

public class CustomRadioButton extends JRadioButton {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // A JRadioButton but with specific properties I prefer
    public CustomRadioButton(String name) {
	this.setFocusable(false);
	this.setText(name);
    }

}
