package windows;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import board.GridPanel;
import customswing.CustomButton;
import main.PathfindingMain;

public class FreehandWindow extends JFrame implements ActionListener {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Constants
    private static final int FREEHAND_WINDOW_WIDTH = GridPanel.MAP_SIZE + 40;
    private static final int FREEHAND_WINDOW_HEIGHT = GridPanel.MAP_SIZE + 105;

    // Fields
    private GridPanel freehandGrid = new GridPanel(Color.orange);
    private CustomButton resetButton = new CustomButton("Reset");
    private CustomButton confirmButton = new CustomButton("Confirm");

    public FreehandWindow() {
	// Main Window properties
	this.setTitle("Freehand Obstacles");
	this.setIconImage(PathfindingMain.ICON);
	this.setSize(FREEHAND_WINDOW_WIDTH, FREEHAND_WINDOW_HEIGHT);
	this.setResizable(false);
	this.getContentPane().setBackground(PathfindingMain.BACKGROUND_COLOUR);
	this.setLocation(500, 50);
	this.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 12));

	this.add(freehandGrid);

	resetButton.addActionListener(this);
	this.add(resetButton);

	confirmButton.addActionListener(this);
	this.add(confirmButton);

	this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
	// Reset board to all WHITE
	if (e.getSource() == resetButton) {
	    freehandGrid.resetBoard();
	}

    }
}
