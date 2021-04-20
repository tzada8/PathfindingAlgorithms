package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import sections.board.GridPanel;
import sections.legend.ColourLegend;
import sections.settings.SettingsPanel;

public class MainPanel extends JPanel {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Fields
    private GridPanel mainGrid = new GridPanel();
    private SettingsPanel settings = new SettingsPanel(mainGrid);
    private ColourLegend colLegend = new ColourLegend();

    // Main panel that is called in frame, and has all according frames
    public MainPanel() {
	this.setPreferredSize(new Dimension(850, 600));
	this.setBackground(Color.LIGHT_GRAY);
	this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
//	this.setLayout(null);

	// Settings to change settings of grid/algorithms
//	settings.setBounds(100, 100, 100, 100);
	this.add(settings);

	// Legend of what colours represent
	this.add(colLegend);

	// Grid for obstacle placement
	this.add(mainGrid);
    }

}
