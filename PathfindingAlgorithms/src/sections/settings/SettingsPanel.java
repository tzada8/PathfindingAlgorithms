package sections.settings;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import algorithms.AStar;
import algorithms.BreathFirstSearch;
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
    private static final String[] START_STOP_BUTTON_TEXT = { "Start", "Stop" };

    // Fields
    private JComboBox<String> algorithmsComboBox;
    private JComboBox<String> obstaclesComboBox;
    private CustomButton clearBoardButton = new CustomButton("Clear Board");
    private CustomButton resetPathfindingButton = new CustomButton("Reset Pathfinding");
    private CustomButton startStopButton = new CustomButton(START_STOP_BUTTON_TEXT[0]);
    private CustomCheckBox showStepsCheckBox = new CustomCheckBox("Show Steps");
    private GridPanel mainGrid;

    // Creates panel for all settings/form options and applies them to the board
    public SettingsPanel(GridPanel mainGrid) {
	this.setPreferredSize(new Dimension(200, 200));
	this.setBackground(PathfindingMain.COMPONENT_COLOUR);
	this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	this.mainGrid = mainGrid;

	// Adding button to clear entire board
	clearBoardButton.addActionListener(this);
	this.add(clearBoardButton);

	// Adding button to reset pathfinding (REDs, GREENs, and MAGENTAs)
	resetPathfindingButton.addActionListener(this);
	this.add(resetPathfindingButton);

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

	// If reset button is clicked, then clear board entirely (only works on
	// "Freehand")
	if (e.getSource() == clearBoardButton) {
	    mainGrid.makeFreehand();
	}

	if (e.getSource() == resetPathfindingButton) {
	    mainGrid.resetPathfinding();
	}

	// If new obstacle in chosen from ComboBox, then update grid
	if (e.getSource() == obstaclesComboBox) {
	    String currentObstacle = this.getObstacle();
	    // Let Clear Board button be clickable only for freehand option
	    if (currentObstacle == OBSTACLES[0]) {
		clearBoardButton.setEnabled(true);
	    } else {
		clearBoardButton.setEnabled(false);
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
	    // If solving, then solve use last chosen algorithm and CheckBox value
	    if (startStopButton.getText().equals(START_STOP_BUTTON_TEXT[0])) {
		// If grid doesn't have both a start and end point, then give error message box
		if (!mainGrid.hasStartAndEndPoint()) {
		    JOptionPane.showMessageDialog(null, "Missing both a Start and End point.",
			    "Error in Board Creation", JOptionPane.ERROR_MESSAGE);
		} else { // Else all is good and can continue solving board
		    // Disable all options while board is being solved except this button
		    enableOrDisableOptions(false);

		    String currentAlgorithm = this.getAlgorithm();
		    boolean showSteps = this.shouldShowSteps();
		    if (currentAlgorithm == ALGORITHMS[0]) {
			new BreathFirstSearch(mainGrid, mainGrid.getStartNode(), showSteps);
		    } else if (currentAlgorithm == ALGORITHMS[1]) {
			System.out.println("Solve using DFS");
		    } else if (currentAlgorithm == ALGORITHMS[2]) {
			new AStar(mainGrid, mainGrid.getStartNode(), showSteps);
		    } else if (currentAlgorithm == ALGORITHMS[3]) {
			new Dijkstra(showSteps);
		    }
		}
	    } else { // Else stopping, so immediately stop solving
		// Enables all options again since board stops being solved
		enableOrDisableOptions(true);

		System.out.println("Stop Program");
	    }
	}
    }

    // Make all options unclickable/clickable depending on START / STOP
    private void enableOrDisableOptions(boolean type) {
	// Make options unclickable / clickable while solution being solved
	algorithmsComboBox.setEnabled(type);
	obstaclesComboBox.setEnabled(type);
	if (this.getObstacle() == OBSTACLES[0]) {
	    clearBoardButton.setEnabled(type);
	}
	resetPathfindingButton.setEnabled(type);
	showStepsCheckBox.setEnabled(type);

	// Make board unclickable / clickable depending on current obstacle
	if (this.getObstacle() == OBSTACLES[0] && type) {
	    mainGrid.makeClickable();
	} else {
	    mainGrid.makeUnclickable();
	}

	// Readjust button text depending on what was just pressed
	if (type) {
	    startStopButton.setText(START_STOP_BUTTON_TEXT[0]);
	} else {
	    startStopButton.setText(START_STOP_BUTTON_TEXT[1]);
	}
    }
}