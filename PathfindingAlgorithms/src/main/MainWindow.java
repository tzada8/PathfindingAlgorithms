package main;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainWindow extends JFrame {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    public MainWindow() {
	EventQueue.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		try {
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException ex) {
		} catch (InstantiationException ex) {
		} catch (IllegalAccessException ex) {
		} catch (UnsupportedLookAndFeelException ex) {
		}

		// Main Window properties
		JFrame frame = new JFrame();
		frame.setTitle("Pathfinding Algorithms");
		frame.setIconImage(PathfindingMain.ICON);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setBackground(PathfindingMain.BACKGROUND_COLOUR);

		// Panel with all main content (grid, settings, and legend)
		frame.add(new MainPanel());

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	    }
	});
    }
}
