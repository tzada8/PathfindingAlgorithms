package windows;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;

import algorithms.GridPanel;
import main.PathfindingMain;

public class FreehandWindow extends JFrame {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;
    
    // Fields
    private GridPanel freehandGrid = new GridPanel(Color.orange);

    public FreehandWindow() {
	// Main Window properties
	this.setTitle("Freehand Obstacles");
	this.setIconImage(PathfindingMain.ICON);
	this.setResizable(false);
	this.getContentPane().setBackground(PathfindingMain.BACKGROUND_COLOUR);
	
	this.add(freehandGrid);
	
	this.pack();
	this.setLocationRelativeTo(null);
	this.setVisible(true);
    }
}
