package sections.board.presets;

import sections.board.Node;

/**
 * The following MakePreset3 class goes through the entire grid and makes a
 * barrier (BLACK) at each of the specific locations.
 * 
 * @author Troy Zada
 *
 */

public class MakePreset3 {

    /**
     * This constructor creates the third preset option of the board. This involves
     * turning all the specific locations into barriers from an available tile.
     * 
     * @param map - The grid that will be turned into Preset3.
     */
    public MakePreset3(Node[][] map) {
	// Make start and end points
	map[23][0].makeStart();
	map[7][30].makeEnd();

	// Top & Bottom barriers of maze
	for (int c = 0; c <= 30; c++) {
	    map[0][c].makeBarrier();
	    map[30][c].makeBarrier();
	}

	// Left & Right barriers of maze
	for (int r = 0; r <= 30; r++) {
	    if (map[r][0].isAvailable()) {
		map[r][0].makeBarrier();
	    }
	    if (map[r][30].isAvailable()) {
		map[r][30].makeBarrier();
	    }
	}

	// All other hard-coded barriers of maze
	for (int r = 4; r <= 5; r++) {
	    for (int c = 4; c <= 12; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 3; r <= 4; r++) {
	    for (int c = 17; c <= 24; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 5; r <= 11; r++) {
	    for (int c = 17; c <= 18; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 7; r <= 11; r++) {
	    for (int c = 23; c <= 24; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 9; r <= 10; r++) {
	    for (int c = 1; c <= 13; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 11; r <= 16; r++) {
	    for (int c = 12; c <= 13; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 13; r <= 17; r++) {
	    for (int c = 7; c <= 8; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 18; r <= 21; r++) {
	    for (int c = 6; c <= 8; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 22; r <= 24; r++) {
	    for (int c = 5; c <= 7; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 25; r <= 27; r++) {
	    for (int c = 5; c <= 6; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 23; r <= 25; r++) {
	    for (int c = 10; c <= 11; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 20; r <= 21; r++) {
	    for (int c = 13; c <= 15; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 26; r <= 27; r++) {
	    for (int c = 13; c <= 16; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 22; r <= 23; r++) {
	    for (int c = 18; c <= 26; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 24; r <= 27; r++) {
	    for (int c = 23; c <= 24; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 14; r <= 19; r++) {
	    for (int c = 20; c <= 21; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 16; r <= 17; r++) {
	    for (int c = 27; c <= 29; c++) {
		map[r][c].makeBarrier();
	    }
	}
    }

}
