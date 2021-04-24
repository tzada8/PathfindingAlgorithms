package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

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
    public static final int WIDTH = 850;
    public static final int HEIGHT = 600;
    public static final int FROM_OUTSIDE = 10;

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
	this.setPreferredSize(new Dimension(850, 600));
	this.setBackground(Color.LIGHT_GRAY);
//	this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	this.setLayout(null);

	// Settings to change settings of grid/algorithms
	settings.setBounds(FROM_OUTSIDE, FROM_OUTSIDE, SettingsPanel.WIDTH, SettingsPanel.HEIGHT);
	this.add(settings);

	// Legend of what colours represent
	colLegend.setBounds(FROM_OUTSIDE, HEIGHT - ColourLegend.HEIGHT - FROM_OUTSIDE, ColourLegend.WIDTH,
		ColourLegend.HEIGHT);
	this.add(colLegend);

	// Grid for obstacle placement
	mainGrid.setBounds((WIDTH - GridPanel.MAP_SIZE_WIDTH / 2) / 2, (HEIGHT - GridPanel.MAP_SIZE_HEIGHT) / 2,
		GridPanel.MAP_SIZE_WIDTH, GridPanel.MAP_SIZE_HEIGHT);
	this.add(mainGrid);
    }

}
