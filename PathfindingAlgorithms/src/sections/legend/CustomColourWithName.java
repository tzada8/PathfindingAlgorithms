package sections.legend;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import customswing.CustomBox;
import customswing.CustomLabel;
import main.PathfindingMain;

/**
 * 
 * The following CustomColourWithName class extends JPanel and acts as an
 * individual label for one entry of the legend.
 * 
 * @author Troy Zada
 *
 */

public class CustomColourWithName extends JPanel {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Constants
    private static final int BOX_SIZE = 15;

    /**
     * Used for one entry of This constructor creates a CustomColourWithName object
     * which is used for one entry of the legend, consisting of a colour and a label
     * describing what the colour represents.
     * 
     * @param colour   - The colour of the box.
     * @param name     - The label to describe what the colour represents.
     * @param fontSize - The font size for the label.
     */
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
	this.add(new CustomLabel("= " + name, CustomLabel.MAIN_TEXT), constraints);
    }

}
