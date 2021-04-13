package windows;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JFrame;

import customswing.CustomButton;
import main.PathfindingMain;
import settingssections.ChooseAlgorithms;
import settingssections.ChooseObstacle;
import settingssections.SizesAndLocation;

public class SettingsWindow extends JFrame implements ActionListener {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Fields
    private CustomButton finish; // submit all settings
    private SizesAndLocation sizesAndLocation = new SizesAndLocation(); // sizes and coordinate placements
    private ChooseObstacle chooseObstacle = new ChooseObstacle();
    private ChooseAlgorithms chooseAlgorithms = new ChooseAlgorithms();

    public SettingsWindow() {
	// Settings Window properties
	this.setTitle("Settings");
	this.setIconImage(PathfindingMain.ICON);
	this.setResizable(false);
	this.setPreferredSize(new Dimension(500, 500));
	this.getContentPane().setBackground(PathfindingMain.BACKGROUND_COLOUR);
	this.setLayout(new GridLayout(2, 2, 10, 10));

	// Finish button to submit settings
	finish = new CustomButton("Finish");
	finish.addActionListener(this);

	// Adding all elements
	this.add(chooseAlgorithms);
	this.add(chooseObstacle);
	this.add(sizesAndLocation);
	this.add(finish);

	this.pack();
	this.setLocationRelativeTo(null);
	this.setVisible(true);

    }

    /*********** SIZES AND LOCATIONS ***********/
    // Instance method that returns the algorithm height
    public String getAlgorithmHeight() {
	return sizesAndLocation.getHeightField();
    }

    // Instance method that returns the algorithm width
    public String getAlgorithmWidth() {
	return sizesAndLocation.getWidthField();
    }

    // Instance method that returns the algorithm starting position
    public String getAlgorithmStart() {
	return sizesAndLocation.getStartPosField();
    }

    // Instance method that returns the value the user set for the END POINT field
    public String getAlgorithmEnd() {
	return sizesAndLocation.getEndPosField();
    }

    /*********** OBSTACLE ***********/
    // Instance method that returns the chooseObstacle Object
    public String getAlgorithmObstacle() {
	return chooseObstacle.getSelectedObstacle();
    }

    /*********** ALGORITHMS ***********/
    // Instance method that returns the chooseAlgorithms Object
    public boolean[] getAlgorithms() {
	return chooseAlgorithms.getSelectedAlgorithms();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == finish) {
	    // If "Freehand" option is chosen, open a board for user to draw on
	    if (chooseObstacle.getSelectedObstacle().equals("freehand")) {
		new FreehandWindow();
	    }
	    
	    // Screen size and Start/End Points
	    System.out.println("Width: " + sizesAndLocation.getWidthField());
	    System.out.println("Height: " + sizesAndLocation.getHeightField());
	    System.out.println("Start Point: " + sizesAndLocation.getStartPosField());
	    System.out.println("End Point: " + sizesAndLocation.getEndPosField());

	    // Type of Obstacle
	    System.out.println(chooseObstacle.getSelectedObstacle());

	    // Types of Algorithms to compare
	    System.out.println(Arrays.toString(chooseAlgorithms.getSelectedAlgorithms()));

	    this.dispose();
	}
    }
}
