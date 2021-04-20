package sections.settings;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import algorithms.AStar;
import algorithms.Dijkstra;
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
    private static final String[] RESET_BUTTON_TEXT = { "Clear Board", "Reset Pathfinding" };
    private static final String[] START_STOP_BUTTON_TEXT = { "Start", "Stop" };

    // Fields
    private JComboBox<String> algorithmsComboBox;
    private JComboBox<String> obstaclesComboBox;
    private CustomButton resetButton = new CustomButton(RESET_BUTTON_TEXT[0]);
    private CustomButton startStopButton = new CustomButton(START_STOP_BUTTON_TEXT[0]);
    private CustomCheckBox showStepsCheckBox = new CustomCheckBox("Show Steps");
    private GridPanel mainGrid;

    // Creates panel for all settings/form options and applies them to the board
    public SettingsPanel(GridPanel mainGrid) {
	this.setPreferredSize(new Dimension(200, 200));
	this.setBackground(PathfindingMain.COMPONENT_COLOUR);
	this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	this.mainGrid = mainGrid;

	// Adding button to reset entire board
	resetButton.addActionListener(this);
	this.add(resetButton);

	// Adding Start/Stop button to keep running /stop running solution
	startStopButton.addActionListener(this);
	this.add(startStopButton);

	// Adding a CheckBox where the user can choose to see steps or not
	showStepsCheckBox.addActionListener(this);
	this.add(showStepsCheckBox);

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

    @Override
    public void actionPerformed(ActionEvent e) {

	// If reset button is clicked, then reset board accordingly
	if (e.getSource() == resetButton) {
	    // If on "Freehand", then button will clear board entirely
	    if (this.getObstacle() == "Freehand") {
		mainGrid.makeFreehand();
	    } else { // If on anything else, then button will reset pathfinding
		mainGrid.resetPathfinding();
	    }
	}

	// If new obstacle in chosen from ComboBox, then update grid and text
	// for resetButton
	if (e.getSource() == obstaclesComboBox) {
	    String currentObstacle = this.getObstacle();
	    System.out.println(currentObstacle);
	    // Update button text depending on current obstacle
	    if (currentObstacle == OBSTACLES[0]) {
		resetButton.setText(RESET_BUTTON_TEXT[0]);
	    } else {
		resetButton.setText(RESET_BUTTON_TEXT[1]);
	    }

	    // Update board depending on current obstacle
	    if (currentObstacle == OBSTACLES[0]) {
		mainGrid.makeFreehand();
	    } else if (currentObstacle == OBSTACLES[1]) {
		mainGrid.makePreset1();
	    } else if (currentObstacle == OBSTACLES[2]) {
		mainGrid.makePreset2();
	    } else if (currentObstacle == OBSTACLES[3]) {
		mainGrid.makePreset3();
	    } else if (currentObstacle == OBSTACLES[4]) {
		mainGrid.makeRandom();
		System.out.println("Loading Random board from API...");
	    }
	}

	if (e.getSource() == startStopButton) {
	    if (startStopButton.getText().equals(START_STOP_BUTTON_TEXT[0])) {
		System.out.println("Start Program");
		// Once user chooses to start program, get the algorithm they last chose
		// and solve board with that algorithm
		String currentAlgorithm = this.getAlgorithm();
		boolean showSteps = this.shouldShowSteps();
		if (currentAlgorithm == ALGORITHMS[0]) {
//		    BreathFirstSearch bfsTree = new BreathFirstSearch();
		} else if (currentAlgorithm == ALGORITHMS[1]) {
		    System.out.println("Solve using DFS");
		} else if (currentAlgorithm == ALGORITHMS[2]) {
		    mainGrid.solveBoard(new AStar(), showSteps);
		} else if (currentAlgorithm == ALGORITHMS[3]) {
		    mainGrid.solveBoard(new Dijkstra(), showSteps);
		}

		System.out.println("Solving with " + this.getAlgorithm());
		System.out.println("Should we show steps? " + this.shouldShowSteps());

		startStopButton.setText(START_STOP_BUTTON_TEXT[1]);
	    } else {
		System.out.println("Stop Program");
		startStopButton.setText(START_STOP_BUTTON_TEXT[0]);
	    }
	}
    }
}