package main;

import javax.swing.JFrame;

public class MainWindow extends JFrame {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    public MainWindow() {
	// Main Window properties
	this.setTitle("Pathfinding Algorithms");
	this.setIconImage(PathfindingMain.ICON);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	this.getContentPane().setBackground(PathfindingMain.BACKGROUND_COLOUR);

	// Frame with all main content (grid, settings, and legend)
	this.add(new MainFrame());

	this.pack();
	this.setLocationRelativeTo(null);
	this.setVisible(true);
    }
}
