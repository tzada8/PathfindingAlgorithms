package customSwing;

import javax.swing.JCheckBox;

public class CustomCheckBox extends JCheckBox {
    
    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // A JCheckBox but with specific properties I prefer
    public CustomCheckBox(String name) {
	this.setFocusable(false);
	this.setText(name);
    }

}
