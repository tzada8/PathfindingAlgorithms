package main;

import javax.swing.JFrame;

/**
 * 
 * The following MainWindow class extends JFrame and creates the actual window
 * which can be viewed.
 * 
 * @author Troy Zada
 *
 */

public class MainWindow extends JFrame {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    /**
     * This constructor adds the MainPanel onto the frame such that all the context
     * the main panel has will be displayed in the frame.
     */
    public MainWindow() {
	// Main Window properties
	this.setTitle("Pathfinding Algorithms");
	this.setIconImage(PathfindingMain.ICON);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	this.getContentPane().setBackground(PathfindingMain.BACKGROUND_COLOUR);

	// Panel with all main content (grid, settings, and legend)
	this.add(new MainPanel());

	this.pack();
	this.setLocationRelativeTo(null);
	this.setVisible(true);
    }
}
