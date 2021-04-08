package windows;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import main.PathfindingMain;
import settingsSections.ChooseAlgorithms;
import settingsSections.ChooseObstacle;
import settingsSections.SizesAndLocation;

public class SettingsWindow extends JFrame implements ActionListener {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Fields
    private JButton finish; // submit all settings
    private SizesAndLocation sizesAndLocation = new SizesAndLocation(); // sizes and coordinate placements
    private ChooseObstacle chooseObstacle = new ChooseObstacle();
    private ChooseAlgorithms chooseAlgorithms = new ChooseAlgorithms();

    public SettingsWindow() {
	// Settings Window properties
	this.setTitle("Settings");
	this.setIconImage(new ImageIcon("images/pathfinding_icon.png").getImage());
	this.setResizable(false);
	this.getContentPane().setBackground(PathfindingMain.BACKGROUND_COLOUR);
	this.setLayout(new FlowLayout());

	// Finish button to submit settings
	finish = new JButton("Finish");
	finish.setFont(new Font(PathfindingMain.GAME_FONT, Font.PLAIN, 16));
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

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == finish) {
	    System.out.println("Hello " + sizesAndLocation.getWidthField());
	    System.out.println("Hello " + sizesAndLocation.getHeightField());
	    
	    System.out.println(chooseObstacle.getSelectedObstacle());
	    
	    this.dispose();
	}
    }
}
