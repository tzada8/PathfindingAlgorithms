package sections.legend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import customswing.CustomLabel;
import main.PathfindingMain;

public class ColourLegend extends JPanel {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Holds all info about what each colour represents
    public ColourLegend() {
	this.setPreferredSize(new Dimension(140, 205));
	this.setBackground(PathfindingMain.COMPONENT_COLOUR);
	this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

	// "Legend:" title for frame
	this.add(new CustomLabel("Legend:", CustomLabel.HEADER_FONT_SIZE));

	// Adding all colours with label describing colour
	this.add(new CustomColourWithName(Color.WHITE, "Open", 16));
	this.add(new CustomColourWithName(Color.BLACK, "Barrier", 16));
	this.add(new CustomColourWithName(Color.ORANGE, "Start", 16));
	this.add(new CustomColourWithName(Color.CYAN, "End", 16));
	this.add(new CustomColourWithName(Color.RED, "Checked", 16));
	this.add(new CustomColourWithName(Color.GREEN, "Unchecked", 16));
	this.add(new CustomColourWithName(Color.MAGENTA, "Solution", 16));
    }

}
