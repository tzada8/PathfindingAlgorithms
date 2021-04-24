package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * 
 * The following PathfindingMain class includes the main method to render all
 * content as well as constants for visual updates (e.g. colour, font sizes,
 * etc.).
 * 
 * @author Troy Zada
 *
 */

public class PathfindingMain {

    /* CONSTANTS */
    // Icon
    public static final Image ICON = new ImageIcon("images/pathfinding_icon.png").getImage();
    // Colours
    public static final Color BACKGROUND_COLOUR = Color.LIGHT_GRAY;
    public static final Color COMPONENT_COLOUR = new Color(140, 140, 140);
    // Fonts
    public static final String GAME_FONT = "Helvetica";
    public static final Font MAIN_TEXT = new Font(GAME_FONT, Font.PLAIN, 16);

    public static void main(String[] args) {
	new MainWindow();
    }

}
