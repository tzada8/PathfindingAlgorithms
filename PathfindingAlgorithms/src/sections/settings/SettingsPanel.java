package sections.settings;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

import customswing.CustomButton;
import customswing.CustomCheckBox;
import main.PathfindingMain;
import sections.board.GridPanel;

public class SettingsPanel extends JPanel implements ActionListener {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Constants
    private static final String[] ALGORITHMS = { "Breath First Search", "Depth First Search", "A*", "Dijkstra" };
    private static final String[] OBSTACLES = { "Freehand", "Preset 1", "Preset 2", "Preset 3", "Random" };

    // TEMP VAR
    private GridPanel freehandGrid;

    // Fields
    private JComboBox<String> algorithmsComboBox;
    private JComboBox<String> obstaclesComboBox;
    private CustomButton resetButton = new CustomButton("Reset");
    private CustomButton startStopButton = new CustomButton("Start");
    private CustomCheckBox showStepsCheckBox = new CustomCheckBox("Show Steps");
    private JSlider solutionSpeedSlider = new JSlider();

    public SettingsPanel(GridPanel freehandGrid) {
	this.setPreferredSize(new Dimension(200, 200));
	this.setBackground(PathfindingMain.COMPONENT_COLOUR);
	this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	this.freehandGrid = freehandGrid;

	// Adding button to reset entire board
	resetButton.addActionListener(this);
	this.add(resetButton);

	// Adding Start/Stop button to keep running /stop running solution
	startStopButton.addActionListener(this);
	this.add(startStopButton);

	// Adding a CheckBox where the user can choose to see steps or not
	showStepsCheckBox.addActionListener(this);
	this.add(showStepsCheckBox);

	// Adding a Slider where the user can choose what speed solution occurs at
	this.add(solutionSpeedSlider);

	// Adding ComboBox to choose between Algorithms
	algorithmsComboBox = new JComboBox<String>(ALGORITHMS);
	algorithmsComboBox.addActionListener(this);
	this.add(algorithmsComboBox);

	// Adding ComboBox to choose between Obstacles
	obstaclesComboBox = new JComboBox<String>(OBSTACLES);
	obstaclesComboBox.addActionListener(this);
	this.add(obstaclesComboBox);
    }

    // Gets the currently selected algorithm
    public String getAlgorithm() {
	return (String) algorithmsComboBox.getSelectedItem();
    }

    // Gets the currently selected obstacle
    public String getObstacle() {
	return (String) obstaclesComboBox.getSelectedItem();
    }

//  private CustomButton resetButton = new CustomButton("Reset");
//  private CustomButton startStopButton = new CustomButton("Start");
    // Gets

    // Gets a true or false if the show steps checkbox is chosen
    public boolean shouldShowSteps() {
	return showStepsCheckBox.isSelected();
    }

    // Gets current value of slider
    public int getSliderValue() {
	return solutionSpeedSlider.getValue();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	// Reset board to all WHITE
	if (e.getSource() == resetButton) {

	    // GIVE RESET BUTTON 2 DIFFERENT FUNCTIONALITIES:
	    // - IF ON "FREEHAND", THEN "RESET" WILL RESET BOARD ENTIRELY
	    // - IF ON "ANYTHING ELSE", THEN "RESET" WILL RESET RED/GREEN/PURPLES

	    System.out.println("Resetting board...");
	    freehandGrid.makeFreehand();
	    System.out.println("Done resetting.");
	}

	if (e.getSource() == algorithmsComboBox) {
	    String currentAlgorithm = this.getAlgorithm();
	    if (currentAlgorithm == "Breath First Search") {
		System.out.println("Solve using BFS");
	    } else if (currentAlgorithm == "Depth First Search") {
		System.out.println("Solve using DFS");
	    } else if (currentAlgorithm == "A*") {
		System.out.println("Solve using A*");
	    } else if (currentAlgorithm == "Dijkstra") {
		System.out.println("Solve using Dijkstra");
	    }
	}

	if (e.getSource() == obstaclesComboBox) {
	    String currentObstacle = this.getObstacle();
	    if (currentObstacle == "Freehand") {
		freehandGrid.makeFreehand();
	    } else if (currentObstacle == "Preset 1") {
		freehandGrid.makePreset1();
		System.out.println("Loading Preset 1 board...");
	    } else if (currentObstacle == "Preset 2") {
		freehandGrid.makePreset2();
		System.out.println("Loading Preset 2 board...");
	    } else if (currentObstacle == "Preset 3") {
		freehandGrid.makePreset3();
		System.out.println("Loading Preset 3 board...");
	    } else if (currentObstacle == "Random") {
		System.out.println("Loading Random board from API...");
	    }
	}

	if (e.getSource() == startStopButton) {
	    if (startStopButton.getText().equals("Start")) {
		System.out.println("Start Program");
		startStopButton.setText("Stop");
	    } else {
		System.out.println("Stop Program");
		startStopButton.setText("Start");
	    }
	}

	if (e.getSource() == showStepsCheckBox) {
	    System.out.println(this.shouldShowSteps());
	    if (this.shouldShowSteps()) {
		System.out.println("Show the steps");
	    } else {
		System.out.println("Don't show");
	    }
	}
//	this.getParent().repaint();
    }
}