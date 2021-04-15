package main;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import sections.board.GridPanel;
import sections.legend.ColourLegend;
import sections.settings.SettingsPanel;

public class MainWindow extends JFrame implements ActionListener {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Fields
    private GridPanel freehandGrid = new GridPanel();
    private SettingsPanel settings = new SettingsPanel(freehandGrid);
    private ColourLegend colLegend = new ColourLegend();

    public MainWindow() {
	// Main Window properties
	this.setTitle("Pathfinding Algorithms");
	this.setIconImage(PathfindingMain.ICON);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	this.setSize(800, 600); // MIGHT NEED TO ADJUST SIZE TO FIT NECESSARY ALGORITHMS
	this.getContentPane().setBackground(PathfindingMain.BACKGROUND_COLOUR);
	this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

	// Settings to change settings of grid/algorithms
	this.add(settings);

	// Legend of what colours represent
	this.add(colLegend);

	// Grid for obstacle placement
	this.add(freehandGrid);
	

	this.setLocationRelativeTo(null);
	this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	

    }

}
