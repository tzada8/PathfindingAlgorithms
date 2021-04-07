package settingsSections;

import java.awt.Color;

import javax.swing.JLabel;

public class SectionHeader extends JLabel {
    
    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;
    
    // Fields
    private JLabel header;
    
    // Title for a given setting section with corresponding properties
    public SectionHeader(String title) {
	header = new JLabel(title);
	header.setForeground(new Color(0xFF00FF));
	header.setText("This is a test");
    }
    
    public JLabel getHeader() {
	return header;
    }

}
