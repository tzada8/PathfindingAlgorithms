package algorithms;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class GridPanel extends JPanel {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Basic Panel that holds all content for a given algorithm
    public GridPanel(Color background) {
	this.setPreferredSize(new Dimension(100, 100));
	this.setBackground(background);
	this.setLayout(new FlowLayout());
    }

}
