package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import sections.board.GridPanel;
import sections.legend.ColourLegend;
import sections.settings.SettingsPanel;

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
    private static final int SPACING = 10;
    private static final int WIDTH = GridPanel.MAP_SIZE_WIDTH + SettingsPanel.WIDTH + 3 * SPACING;
    private static final int HEIGHT = GridPanel.MAP_SIZE_HEIGHT + 2 * SPACING;

    // Fields
    private GridPanel mainGrid = new GridPanel();
    private SettingsPanel settings = new SettingsPanel(mainGrid);
    private ColourLegend colLegend = new ColourLegend();

    /**
     * This constructor adds all the respective Panels for the grid, settings, and
     * legend into one main panel. The locations of each panel is hard-coded using
     * setBounds().
     */
    public MainPanel() {
	this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	this.setBackground(Color.LIGHT_GRAY);
	this.setLayout(null);

	// Settings to change settings of grid/algorithms
	settings.setBounds(WIDTH - SettingsPanel.WIDTH - SPACING, HEIGHT - SettingsPanel.HEIGHT - SPACING,
		SettingsPanel.WIDTH, SettingsPanel.HEIGHT);
	this.add(settings);

	// Legend of what colours represent
	colLegend.setBounds(WIDTH - ColourLegend.WIDTH - SPACING, SPACING, ColourLegend.WIDTH, ColourLegend.HEIGHT);
	this.add(colLegend);

	// Grid for obstacle placement
	mainGrid.setBounds(SPACING, SPACING, GridPanel.MAP_SIZE_WIDTH, GridPanel.MAP_SIZE_HEIGHT);
	this.add(mainGrid);
    }

}
