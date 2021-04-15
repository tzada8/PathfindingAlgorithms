package sections.legend;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import customswing.CustomBox;
import customswing.CustomLabel;

public class CustomColourWithName extends JPanel {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Constants
    private static final int BOX_SIZE = 15;

    // Creates desciption stating what colour represents
    public CustomColourWithName(Color colour, String name, int fontSize) {
	this.setBackground(null);
	this.setLayout(new GridBagLayout());

	GridBagConstraints constraints = new GridBagConstraints();

	// Padding around all constraints
	constraints.insets = new Insets(2, 2, 2, 2);

	// For coloured box
	constraints.gridx = 0; // column = 0
	constraints.gridy = 0; // row = 0
	this.add(new CustomBox(colour, BOX_SIZE), constraints); // Adding constraint

	// For text description of coloured box
	constraints.gridx = 1;
	constraints.gridy = 0;
	constraints.ipadx = 0;
	constraints.ipady = 0;
	this.add(new CustomLabel("= " + name, fontSize), constraints);
    }

}
