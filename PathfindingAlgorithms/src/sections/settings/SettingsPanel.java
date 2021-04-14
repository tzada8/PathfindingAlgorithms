package sections.settings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSlider;

import customswing.CustomButton;
import customswing.CustomCheckBox;
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
    private JSlider speedOfSolutionSlider = new JSlider();

    public SettingsPanel(GridPanel freehandGrid) {
	this.setPreferredSize(new Dimension(200, 200));
	this.setBackground(Color.ORANGE);
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
	this.add(speedOfSolutionSlider);

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

    @Override
    public void actionPerformed(ActionEvent e) {
	// Reset board to all WHITE
	if (e.getSource() == resetButton) {
	    freehandGrid.resetBoard();
	}

	if (e.getSource() == algorithmsComboBox) {
	    System.out.println(getAlgorithm());
	    System.out.println(algorithmsComboBox.getSelectedIndex());
	}

	if (e.getSource() == obstaclesComboBox) {
	    System.out.println(getObstacle());
	    System.out.println(obstaclesComboBox.getSelectedIndex());
	}

	if (e.getSource() == startStopButton) {
	    String currentText = startStopButton.getText();
	    if (currentText.equals("Start")) {
		System.out.println("Start Program");
		startStopButton.setText("Stop");
	    } else {
		System.out.println("Stop Program");
		startStopButton.setText("Start");
	    }
	}
	
	if (e.getSource() == showStepsCheckBox) {
	    System.out.println("Show the steps");
	}
    }
}