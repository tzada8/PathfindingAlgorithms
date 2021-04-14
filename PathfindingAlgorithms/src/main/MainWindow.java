package main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import customswing.CustomButton;
import sections.board.GridPanel;
import sections.settings.SettingsPanel;

public class MainWindow extends JFrame implements ActionListener {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Fields
    private GridPanel freehandGrid = new GridPanel();
    private SettingsPanel settings = new SettingsPanel(freehandGrid);

    public MainWindow() {
	// Main Window properties
	this.setTitle("Pathfinding Algorithms");
	this.setIconImage(PathfindingMain.ICON);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	this.setSize(800, 600); // MIGHT NEED TO ADJUST SIZE TO FIT NECESSARY ALGORITHMS
	this.getContentPane().setBackground(PathfindingMain.BACKGROUND_COLOUR);
	this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

	this.add(settings);

	// Grid for freehand drawing
	this.add(freehandGrid);

	this.setLocationRelativeTo(null);
	this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	

    }

}
