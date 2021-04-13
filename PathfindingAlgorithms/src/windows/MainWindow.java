package windows;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import board.GridPanel;
import customswing.CustomButton;
import main.PathfindingMain;

public class MainWindow extends JFrame implements ActionListener {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Fields
    private SettingsWindow settings;
    private GridPanel freehandGrid = new GridPanel();
    private CustomButton settingsButton = new CustomButton("Settings");
    private CustomButton startStopButton = new CustomButton("Start");

    public MainWindow() {
	// Main Window properties
	this.setTitle("Pathfinding Algorithms");
	this.setIconImage(PathfindingMain.ICON);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	this.setSize(800, 600); // MIGHT NEED TO ADJUST SIZE TO FIT NECESSARY ALGORITHMS
	this.getContentPane().setBackground(PathfindingMain.BACKGROUND_COLOUR);
	this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

	// Grid for freehand drawing
	this.add(freehandGrid);

	// Start/Stop button on main window
	startStopButton.addActionListener(this);
	this.add(startStopButton);

	// Settings button on main window
	settingsButton.addActionListener(this);
	this.add(settingsButton);

	this.setLocationRelativeTo(null);
	this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == settingsButton) {
	    settings = new SettingsWindow();
	    System.out.println(settings.getAlgorithmHeight());
	} else if (e.getSource() == startStopButton) {
	    System.out.println("Start Program");
	}

    }

}
