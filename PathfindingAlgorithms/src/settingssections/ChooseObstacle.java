package settingssections;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import customswing.CustomLabel;
import customswing.CustomRadioButton;

public class ChooseObstacle extends JPanel implements ActionListener {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    private CustomRadioButton freehandButton = new CustomRadioButton("Freehand");
    private CustomRadioButton preset1Button = new CustomRadioButton("Preset 1");
    private CustomRadioButton preset2Button = new CustomRadioButton("Preset 2");
    private CustomRadioButton preset3Button = new CustomRadioButton("Preset 3");
    private CustomRadioButton randomButton = new CustomRadioButton("Random");
    private String selectedObstacle;

    // Panel with a section to choose which type of obstacles will be used
    public ChooseObstacle() {
	this.setBackground(null);

	// Label for this frame section
	this.add(new CustomLabel("Choose Type of Obstacle:", CustomLabel.HEADER_FONT_SIZE));

	ButtonGroup group = new ButtonGroup();
	group.add(freehandButton);
	group.add(preset1Button);
	group.add(preset2Button);
	group.add(preset3Button);
	group.add(randomButton);

	freehandButton.addActionListener(this);
	preset1Button.addActionListener(this);
	preset2Button.addActionListener(this);
	preset3Button.addActionListener(this);
	randomButton.addActionListener(this);

	this.add(freehandButton);
	this.add(preset1Button);
	this.add(preset2Button);
	this.add(preset3Button);
	this.add(randomButton);
    }

    // Instance method that returns which of the radio buttons was selected
    public String getSelectedObstacle() {
	return selectedObstacle;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == freehandButton) {
	    selectedObstacle = "freehand";
	} else if (e.getSource() == preset1Button) {
	    selectedObstacle = "preset1";
	} else if (e.getSource() == preset2Button) {
	    selectedObstacle = "preset2";
	} else if (e.getSource() == preset3Button) {
	    selectedObstacle = "preset3";
	} else if (e.getSource() == randomButton) {
	    selectedObstacle = "random";
	}

    }

}
