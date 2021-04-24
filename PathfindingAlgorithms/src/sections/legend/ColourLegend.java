package sections.legend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import customswing.CustomLabel;
import main.PathfindingMain;

/**
 * 
 * The following ColourLegend class extends JPanel and acts as a legend to
 * dictate what each colour represents. This panel uses the CustomColourWithName
 * class, which is another JPanel that holds an individual colour and it's
 * corresponding label.
 * 
 * @author Troy Zada
 *
 */

public class ColourLegend extends JPanel {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    /**
     * This constructor creates a ColourLegend object which is a panel of colours
     * dictating what each colour represents.
     */
    public ColourLegend() {
	this.setPreferredSize(new Dimension(140, 205));
	this.setBackground(PathfindingMain.COMPONENT_COLOUR);
	this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

	// "Legend:" title for panel
	this.add(new CustomLabel("Legend:", CustomLabel.HEADER_FONT_SIZE));

	// Adding all colours with label describing what the colour represents
	this.add(new CustomColourWithName(Color.WHITE, "Open", 16));
	this.add(new CustomColourWithName(Color.BLACK, "Barrier", 16));
	this.add(new CustomColourWithName(Color.ORANGE, "Start", 16));
	this.add(new CustomColourWithName(Color.CYAN, "End", 16));
	this.add(new CustomColourWithName(Color.RED, "Checked", 16));
	this.add(new CustomColourWithName(Color.GREEN, "Unchecked", 16));
	this.add(new CustomColourWithName(Color.MAGENTA, "Solution", 16));
    }

}
