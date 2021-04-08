package settingsSections;

import javax.swing.JCheckBox;

public class CustomCheckBox extends JCheckBox {
    
    // A JCheckBox but with specific properties I prefer
    public CustomCheckBox(String name) {
	this.setFocusable(false);
	this.setText(name);
    }

}
