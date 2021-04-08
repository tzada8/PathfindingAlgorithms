package settingsSections;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ChooseObstacle extends JPanel implements ActionListener {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    private JRadioButton freehandButton = new JRadioButton("Freehand");
    private JRadioButton preset1Button = new JRadioButton("Preset 1");
    private JRadioButton preset2Button = new JRadioButton("Preset 2");
    private JRadioButton preset3Button = new JRadioButton("Preset 3");
    private JRadioButton randomButton = new JRadioButton("Random");
    private String selectedObstacle;

    // Panel with a section to choose which type of obstacles will be used
    public ChooseObstacle() {
	this.setBackground(null);

	// Label for this frame section
	this.add(new SectionHeader("Choose Type of Obstacle:").getHeader());

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
