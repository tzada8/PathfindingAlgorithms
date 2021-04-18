package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

import sections.board.GridPanel;
import sections.board.Node;
import sections.legend.ColourLegend;
import sections.settings.SettingsPanel;

public class MainFrame extends JPanel {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Fields
    private GridPanel mainGrid = new GridPanel();
    private SettingsPanel settings = new SettingsPanel(mainGrid);
    private ColourLegend colLegend = new ColourLegend();
//    private boolean firstTimeRendering = true;

    public MainFrame() {
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

//    @Override
//    protected void paintComponent(Graphics g) {
//	super.paintComponent(g);
//
//	String currentObstacle = settings.getObstacle();
//	System.out.println(currentObstacle);
//
//	// If first ever time starting program, set currentObstacle to default
//	if (firstTimeRendering) {
//	    currentObstacle = null;
//	    firstTimeRendering = false;
//	}
//
//	if (currentObstacle == "Freehand") {
//	    mainGrid.makeFreehand();
//	} else if (currentObstacle == "Preset 1") {
//	    mainGrid.makePreset1();
//	    System.out.println("Loading Preset 1 board...");
//	} else if (currentObstacle == "Preset 2") {
//	    mainGrid.makePreset2();
//	    System.out.println("Loading Preset 2 board...");
//	} else if (currentObstacle == "Preset 3") {
//	    mainGrid.makePreset2();
//	    System.out.println("Loading Preset 3 board...");
//	} else if (currentObstacle == "Random") {
//	    System.out.println("Loading Random board from API...");
//	}
//
//    }

}
