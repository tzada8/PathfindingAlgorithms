package sections.settings;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import algorithms.AStar;
import algorithms.BreathFirstSearch;
import algorithms.DepthFirstSearch;
import algorithms.Dijkstra;
import customswing.CustomButton;
import customswing.CustomCheckBox;
import customswing.CustomLabel;
import main.MainPanel;
import sections.board.GridPanel;
import sections.board.Node;

/**
 * 
 * The following SettingsPanel class extends JPanel and acts as a container for
 * all the settings that can change options of the grid. The options include
 * choosing an algorithm, choosing an obstacle, a clear button, a reset
 * pathfinding button, a start button, and a show steps button.
 * 
 * @author Troy Zada
 *
 */

public class SettingsPanel extends JPanel implements ActionListener {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Constants
    public static final int HEIGHT = 175;
    private static final String[] ALGORITHMS = { "Breath First Search", "Depth First Search", "A*", "Dijkstra" };
    private static final String[] OBSTACLES = { "Freehand", "Preset 1", "Preset 2", "Preset 3", "Random" };

    // Fields
    private JComboBox<String> algorithmsComboBox;
    private JComboBox<String> obstaclesComboBox;
    private CustomButton clearBoardButton = new CustomButton("Clear Board");
    private CustomButton resetPathfindingButton = new CustomButton("Reset Pathfinding");
    private CustomButton startButton = new CustomButton("Start");
    private CustomCheckBox showStepsCheckBox = new CustomCheckBox("Show Steps");
    private GridPanel mainGrid;

    /**
     * This constructor creates the settings panel that allows options to be changed
     * to try out different algorithms / obstacles.
     * 
     * @param mainGrid - All setting options are applied to change the grid.
     */
    public SettingsPanel(GridPanel mainGrid) {
	this.setBackground(MainPanel.COMPONENT_COLOUR);
	this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
	this.mainGrid = mainGrid;

	// Start button to run solution
	startButton.addActionListener(this);
	this.add(startButton);

	// Button to clear entire board
	clearBoardButton.addActionListener(this);
	this.add(clearBoardButton);

	// Button to reset pathfinding (REDs, GREENs, and MAGENTAs)
	resetPathfindingButton.addActionListener(this);
	this.add(resetPathfindingButton);

	// CheckBox where the user can choose to see steps or not
	showStepsCheckBox.addActionListener(this);
	this.add(showStepsCheckBox);

	// ComboBox to choose between Algorithms
	algorithmsComboBox = new JComboBox<String>(ALGORITHMS);
	algorithmsComboBox.setForeground(Color.BLACK);
	algorithmsComboBox.setBackground(Color.WHITE);
	algorithmsComboBox.setFocusable(false);
	algorithmsComboBox.setFont(CustomLabel.SMALL_TEXT);
	algorithmsComboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
	algorithmsComboBox.addActionListener(this);
	this.add(algorithmsComboBox);

	// ComboBox to choose between Obstacles
	obstaclesComboBox = new JComboBox<String>(OBSTACLES);
	obstaclesComboBox.setForeground(Color.BLACK);
	obstaclesComboBox.setBackground(Color.WHITE);
	obstaclesComboBox.setFocusable(false);
	obstaclesComboBox.setFont(CustomLabel.SMALL_TEXT);
	obstaclesComboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
	obstaclesComboBox.addActionListener(this);
	this.add(obstaclesComboBox);
    }

    /**
     * Getter method to get the current algorithm.
     * 
     * @return - A text representation of the currently chosen algorithm.
     */
    public String getAlgorithm() {
	return (String) algorithmsComboBox.getSelectedItem();
    }

    /**
     * Getter method to get the current obstacle.
     * 
     * @return - A text representation of the currently chosen obstacle.
     */
    public String getObstacle() {
	return (String) obstaclesComboBox.getSelectedItem();
    }

    /**
     * Getter method to get the current state of the checkbox.
     * 
     * @return - Boolean value if checkbox is checked / unchecked.
     */
    public boolean shouldShowSteps() {
	return showStepsCheckBox.isSelected();
    }

    /**
     * When one of the options are clicked/changed, then perform the corresponding
     * events.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
	// "Clear Board" button, to entirely clear board (only works on Freehand)
	if (e.getSource() == clearBoardButton) {
	    mainGrid.makeFreehand();
	}

	// "ResetPathfinding" button, to reset any pathfinding Nodes
	if (e.getSource() == resetPathfindingButton) {
	    mainGrid.resetPathfinding();
	}

	// "Obstacles" combobox, to change the obstacle of the grid
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
	    }
	}

	// "Algorithms" combobox, to reset pathfinding if a new algorithm is chosen
	if (e.getSource() == algorithmsComboBox) {
	    String currentAlgorithm = this.getAlgorithm();
	    if (currentAlgorithm == ALGORITHMS[0]) {
		mainGrid.resetPathfinding();
	    } else if (currentAlgorithm == ALGORITHMS[1]) {
		mainGrid.resetPathfinding();
	    } else if (currentAlgorithm == ALGORITHMS[2]) {
		mainGrid.resetPathfinding();
	    } else if (currentAlgorithm == ALGORITHMS[3]) {
		mainGrid.resetPathfinding();
	    }
	}

	// "Start" button, to solve current board with last chosen algorithm and if user
	// wants to see steps or not
	if (e.getSource() == startButton) {
	    // If grid doesn't have both a start and end point, then give error message box
	    if (!mainGrid.hasStartAndEndPoint()) {
		JOptionPane.showMessageDialog(null, "Missing both a Start and End point.", "Error in Board Creation",
			JOptionPane.ERROR_MESSAGE);
	    } else {
		// Disable all options while board is being solved
		enableOrDisableOptions(false);
		mainGrid.resetPathfinding();

		// Get current options, and send it to selected algorithm
		String currentAlgorithm = this.getAlgorithm();
		Node startNode = mainGrid.getStartNode();
		boolean showSteps = this.shouldShowSteps();
		if (currentAlgorithm == ALGORITHMS[0]) {
		    new BreathFirstSearch(this, mainGrid, startNode, showSteps);
		} else if (currentAlgorithm == ALGORITHMS[1]) {
		    new DepthFirstSearch(this, mainGrid, startNode, showSteps);
		} else if (currentAlgorithm == ALGORITHMS[2]) {
		    new AStar(this, mainGrid, startNode, showSteps);
		} else if (currentAlgorithm == ALGORITHMS[3]) {
		    new Dijkstra(this, mainGrid, startNode, showSteps);
		}
	    }
	}
    }

    /**
     * Make all options either unclickable or clickable. If algorithm is in the
     * middle of being solved, then all options will be unclickable. If finished
     * solving, then options will be clickable.
     * 
     * @param type - Boolean to enable / disable all options.
     */
    public void enableOrDisableOptions(boolean type) {
	algorithmsComboBox.setEnabled(type);
	obstaclesComboBox.setEnabled(type);
	if (this.getObstacle() == OBSTACLES[0]) {
	    clearBoardButton.setEnabled(type);
	}
	resetPathfindingButton.setEnabled(type);
	startButton.setEnabled(type);
	showStepsCheckBox.setEnabled(type);

	// Make board itself be unclickable / clickable depending on current obstacle
	if (this.getObstacle() == OBSTACLES[0] && type) {
	    mainGrid.makeClickable();
	} else {
	    mainGrid.makeUnclickable();
	}
    }
}