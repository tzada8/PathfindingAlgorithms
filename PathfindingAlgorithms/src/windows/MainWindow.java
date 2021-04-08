package windows;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import algorithms.AlgorithmPanel;
import main.PathfindingMain;

public class MainWindow extends JFrame implements ActionListener {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Fields
    private SettingsWindow settings;
    private JButton settingsButton = new JButton("Settings");
    private JButton startStopButton = new JButton("Start");

    public MainWindow() {
	// Main Window properties
	this.setTitle("Pathfinding Algorithms");
	this.setIconImage(new ImageIcon("images/pathfinding_icon.png").getImage());
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	this.setSize(500, 500); // MIGHT NEED TO ADJUST SIZE TO FIT NECESSARY ALGORITHMS
	this.getContentPane().setBackground(PathfindingMain.BACKGROUND_COLOUR);
	this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

	// All algorithms on main window
	this.add(new AlgorithmPanel(Color.red));
	this.add(new AlgorithmPanel(Color.blue));
	this.add(new AlgorithmPanel(Color.green));

	// Start/Stop button on main window
	startStopButton.setBounds(100, 160, 200, 40);
	startStopButton.setFont(new Font(PathfindingMain.GAME_FONT, Font.PLAIN, 16));
	startStopButton.setFocusable(false);
	startStopButton.addActionListener(this);
	this.add(startStopButton);

	// Settings button on main window
	settingsButton.setBounds(100, 160, 200, 40);
	settingsButton.setFont(new Font(PathfindingMain.GAME_FONT, Font.PLAIN, 16));
	settingsButton.setFocusable(false);
	settingsButton.addActionListener(this);
	this.add(settingsButton);

	this.setLocationRelativeTo(null);
	this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == settingsButton) {
	    settings = new SettingsWindow();
	    System.out.println(settings.getAlgorithmHeight());
	} else if (e.getSource() == startStopButton) {
	    System.out.println("Start Program");
	}

    }

}
