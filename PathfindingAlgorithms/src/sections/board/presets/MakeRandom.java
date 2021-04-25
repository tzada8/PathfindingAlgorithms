package sections.board.presets;

import java.util.Random;

import sections.board.GridPanel;
import sections.board.Node;

/**
 * The following MakeRandom class goes through the entire grid and makes a
 * barrier (BLACK) at random locations on the grid. This class generates a
 * different set of obstacles each time.
 * 
 * @author Troy Zada
 *
 */

public class MakeRandom {

    /**
     * This constructor creates a random set of obstacles every time. This involves
     * turning randomly generated locations into barriers from an available tile.
     * 
     * @param map - The grid that will be turned into a Random grid.
     */
    public MakeRandom(Node[][] map) {
	Random random = new Random();

	// Randomly get a true/false for each Node, rendering all 'true' Nodes
	for (int r = 0; r < GridPanel.ROWS; r++) {
	    for (int c = 0; c < GridPanel.COLUMNS; c++) {
		if (random.nextBoolean()) {
		    map[r][c].makeBarrier();
		}
	    }
	}
	placeStartAndEndPoint(map, random);
    }

    /**
     * Randomly places start and end point on grid. If random location ends up being
     * exact same spot, then try placing again through recursion.
     * 
     * @param map - The grid that will be turned into a Random grid.
     */
    private void placeStartAndEndPoint(Node[][] map, Random random) {
	int startRow = random.nextInt(GridPanel.ROWS);
	int startCol = random.nextInt(GridPanel.COLUMNS);
	int endRow = random.nextInt(GridPanel.ROWS);
	int endCol = random.nextInt(GridPanel.COLUMNS);

	// If random rows and columns end up being exact same spot, then try placing
	// again; else can just place it since different spots
	if (startRow == endRow && startCol == endCol) {
	    placeStartAndEndPoint(map, random);
	} else {
	    map[startRow][startCol].makeStart();
	    map[endRow][endCol].makeEnd();
	}
    }
}
