package settingsSections;

import java.awt.Color;

import javax.swing.JLabel;

public class SectionHeader extends JLabel {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Title for a given setting section with corresponding properties
    public SectionHeader(String title) {
	this.setForeground(new Color(0xFF00FF));
	this.setText("This is a test");
    }

}
