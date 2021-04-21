package sections.board.presets;

import sections.board.Node;

public class MakePreset1 {

    public MakePreset1(Node[][] map) {
	// Make start and end points
	map[0][7].makeStart();
	map[30][23].makeEnd();

	// Top & Bottom barriers of maze
	for (int c = 0; c <= 30; c++) {
	    if (map[0][c].isAvailable()) {
		map[0][c].makeBarrier();
	    }
	    if (map[30][c].isAvailable()) {
		map[30][c].makeBarrier();
	    }
	}

	// Left & Right barriers of maze
	for (int r = 0; r <= 30; r++) {
	    map[r][0].makeBarrier();
	    map[r][30].makeBarrier();
	}

	// All other hard-coded barriers of maze
	for (int r = 4; r <= 5; r++) {
	    for (int c = 2; c <= 21; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 3; r <= 5; r++) {
	    for (int c = 24; c <= 27; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 8; r <= 9; r++) {
	    for (int c = 1; c <= 16; c++) {
		map[r][c].makeBarrier();
	    }
	    for (int c = 20; c <= 29; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 12; r <= 13; r++) {
	    for (int c = 5; c <= 27; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 14; r <= 15; r++) {
	    for (int c = 24; c <= 25; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 16; r <= 17; r++) {
	    for (int c = 24; c <= 29; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 16; r <= 21; r++) {
	    for (int c = 1; c <= 6; c++) {
		map[r][c].makeBarrier();
	    }
	    for (int c = 16; c <= 21; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 18; r <= 19; r++) {
	    for (int c = 7; c <= 11; c++) {
		map[r][c].makeBarrier();
	    }
	    for (int c = 14; c <= 15; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 24; r <= 27; r++) {
	    for (int c = 3; c <= 5; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 20; r <= 27; r++) {
	    for (int c = 26; c <= 27; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 22; r <= 25; r++) {
	    for (int c = 24; c <= 25; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 24; r <= 25; r++) {
	    for (int c = 8; c <= 23; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 26; r <= 27; r++) {
	    for (int c = 11; c <= 12; c++) {
		map[r][c].makeBarrier();
	    }
	}

	for (int r = 28; r <= 29; r++) {
	    for (int c = 16; c <= 17; c++) {
		map[r][c].makeBarrier();
	    }
	}

	map[26][21].makeBarrier();
	map[26][22].makeBarrier();

    }

}
