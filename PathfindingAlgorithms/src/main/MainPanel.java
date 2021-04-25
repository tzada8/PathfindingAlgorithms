package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import sections.board.GridPanel;
import sections.legend.ColourLegend;
import sections.settings.SettingsPanel;
import sections.signature.SignaturePanel;

/**
 * 
 * The following MainPanel class extends JPanel and acts as a container for
 * everything.
 * 
 * @author Troy Zada
 *
 */

public class MainPanel extends JPanel {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Constants
    public static final Color COMPONENT_COLOUR = new Color(140, 140, 140);
    private static final int SPACING = 10;
    private static final int COMPONENT_WIDTH = 180;
    private static final int WIDTH = GridPanel.MAP_SIZE_WIDTH + COMPONENT_WIDTH + 3 * SPACING;
    private static final int HEIGHT = GridPanel.MAP_SIZE_HEIGHT + 2 * SPACING;
    private static final int COMPONENT_X = WIDTH - COMPONENT_WIDTH - SPACING;

    // Fields
    private GridPanel mainGrid = new GridPanel();
    private SettingsPanel settings = new SettingsPanel(mainGrid);
    private ColourLegend colLegend = new ColourLegend();
    private SignaturePanel signature = new SignaturePanel();

    /**
     * This constructor adds all the respective Panels for the grid, settings, and
     * legend into one main panel. The locations of each panel is hard-coded using
     * setBounds().
     */
    public MainPanel() {
	this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	this.setBackground(Color.LIGHT_GRAY);
	this.setLayout(null);

	// Legend of what colours represent, placed top-right of screen
	colLegend.setBounds(COMPONENT_X, SPACING, COMPONENT_WIDTH, ColourLegend.HEIGHT);
	this.add(colLegend);

	// Settings to change settings of grid/algorithms, placed right of screen
	settings.setBounds(COMPONENT_X, 2 * SPACING + SPACING / 2 + ColourLegend.HEIGHT, COMPONENT_WIDTH,
		SettingsPanel.HEIGHT);
	this.add(settings);

	// Signature of who created the project, placed bottom-left of screen
	signature.setBounds(COMPONENT_X, HEIGHT - SignaturePanel.HEIGHT - SPACING, COMPONENT_WIDTH,
		SignaturePanel.HEIGHT);
	this.add(signature);

	// Grid for obstacle placement, placed left side of screen
	mainGrid.setBounds(SPACING, SPACING, GridPanel.MAP_SIZE_WIDTH, GridPanel.MAP_SIZE_HEIGHT);
	this.add(mainGrid);
    }

}
