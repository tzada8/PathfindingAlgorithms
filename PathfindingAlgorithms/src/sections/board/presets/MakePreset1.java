package sections.board.presets;

import sections.board.Node;

public class MakePreset1 {

    public MakePreset1(Node[][] map) {
	// Make start and end points
	map[1][0].makeStart();
	map[29][30].makeEnd();

	// Top & Bottom barriers of maze
	for (int c = 0; c < 31; c++) {
	    map[0][c].makeBarrier();
	    map[30][c].makeBarrier();
	}

	// Left & Right barriers of maze
	for (int r = 0; r < 31; r++) {
	    if (map[r][0].isAvailable()) {
		map[r][0].makeBarrier();
	    }
	    if (map[r][30].isAvailable()) {
		map[r][30].makeBarrier();
	    }
	}

	// All other hard-coded barriers of maze
	for (int r = 1; r <= 4; r++) {
	    map[r][4].makeBarrier();
	}

	map[2][2].makeBarrier();
	map[2][3].makeBarrier();
	for (int r = 3; r <= 6; r++) {
	    map[r][2].makeBarrier();
	}

	map[2][6].makeBarrier();
	map[3][6].makeBarrier();
	for (int c = 6; c <= 12; c++) {
	    map[4][c].makeBarrier();
	}

	map[2][8].makeBarrier();
	map[2][9].makeBarrier();
	for (int r = 1; r <= 3; r++) {
	    map[r][10].makeBarrier();
	}

	map[5][8].makeBarrier();
	for (int c = 6; c <= 8; c++) {
	    map[6][c].makeBarrier();
	}

	map[1][14].makeBarrier();
	for (int c = 12; c <= 20; c++) {
	    map[2][c].makeBarrier();
	}

	map[2][23].makeBarrier();
	map[2][24].makeBarrier();
	for (int r = 1; r <= 4; r++) {
	    map[r][22].makeBarrier();
	}

	for (int c = 26; c <= 28; c++) {
	    map[2][c].makeBarrier();
	}
	map[3][26].makeBarrier();
	for (int c = 24; c <= 28; c++) {
	    map[4][c].makeBarrier();
	}
	map[5][28].makeBarrier();
	map[6][28].makeBarrier();
	map[6][29].makeBarrier();

	for (int r = 5; r <= 10; r++) {
	    map[r][24].makeBarrier();
	}
	map[6][23].makeBarrier();
	map[6][22].makeBarrier();
	map[7][22].makeBarrier();
	map[8][22].makeBarrier();
	map[8][21].makeBarrier();
	map[8][20].makeBarrier();
	map[7][20].makeBarrier();
	map[6][20].makeBarrier();

	for (int c = 16; c <= 21; c++) {
	    map[4][c].makeBarrier();
	}
	for (int r = 5; r <= 10; r++) {
	    map[r][16].makeBarrier();
	}

	map[10][15].makeBarrier();
	for (int r = 5; r <= 12; r++) {
	    map[r][14].makeBarrier();
	}

	map[8][1].makeBarrier();
	map[8][2].makeBarrier();

	map[12][1].makeBarrier();
	for (int r = 10; r <= 16; r++) {
	    map[r][2].makeBarrier();
	}
	for (int c = 3; c <= 6; c++) {
	    map[12][c].makeBarrier();
	}

	for (int r = 6; r <= 10; r++) {
	    map[r][4].makeBarrier();
	}
	for (int c = 5; c <= 10; c++) {
	    map[10][c].makeBarrier();
	}
	map[9][8].makeBarrier();
	for (int c = 6; c <= 8; c++) {
	    map[8][c].makeBarrier();
	}

	for (int r = 6; r <= 9; r++) {
	    map[r][10].makeBarrier();
	}
	for (int c = 11; c <= 13; c++) {
	    map[6][c].makeBarrier();
	}
	map[7][12].makeBarrier();
	map[8][12].makeBarrier();

	for (int c = 8; c <= 13; c++) {
	    map[12][c].makeBarrier();
	}
	map[10][12].makeBarrier();
	map[11][12].makeBarrier();
	map[13][8].makeBarrier();
	map[14][8].makeBarrier();
	map[13][10].makeBarrier();
	map[14][10].makeBarrier();

	map[6][17].makeBarrier();
	for (int r = 6; r <= 12; r++) {
	    map[r][18].makeBarrier();
	}
	map[10][19].makeBarrier();
	for (int r = 10; r <= 12; r++) {
	    map[r][20].makeBarrier();
	}

	map[12][17].makeBarrier();
	for (int r = 12; r <= 18; r++) {
	    map[r][16].makeBarrier();
	}
	for (int c = 12; c <= 15; c++) {
	    map[14][c].makeBarrier();
	}
	map[18][17].makeBarrier();
	for (int r = 14; r <= 18; r++) {
	    map[r][18].makeBarrier();
	}

	map[10][23].makeBarrier();
	map[10][22].makeBarrier();
	map[11][22].makeBarrier();
	for (int c = 22; c <= 25; c++) {
	    map[12][c].makeBarrier();
	}
	for (int r = 6; r <= 16; r++) {
	    map[r][26].makeBarrier();
	}
	map[12][27].makeBarrier();
	for (int r = 8; r <= 12; r++) {
	    map[r][28].makeBarrier();
	}
	for (int c = 20; c <= 25; c++) {
	    map[14][c].makeBarrier();
	}
	for (int c = 20; c <= 25; c++) {
	    map[16][c].makeBarrier();
	}

	map[14][29].makeBarrier();
	for (int r = 14; r <= 20; r++) {
	    map[r][28].makeBarrier();
	}
	for (int c = 24; c <= 27; c++) {
	    map[18][c].makeBarrier();
	}

	for (int r = 18; r <= 20; r++) {
	    map[r][2].makeBarrier();
	}
	map[20][3].makeBarrier();
	for (int r = 20; r <= 24; r++) {
	    map[r][4].makeBarrier();
	}
	map[24][1].makeBarrier();
	for (int r = 22; r <= 24; r++) {
	    map[r][2].makeBarrier();
	}
	map[22][3].makeBarrier();

	for (int c = 1; c <= 4; c++) {
	    map[26][c].makeBarrier();
	}

	map[29][2].makeBarrier();
	for (int c = 2; c <= 6; c++) {
	    map[28][c].makeBarrier();
	}
	map[27][6].makeBarrier();
	map[26][6].makeBarrier();

	map[29][8].makeBarrier();
	for (int c = 8; c <= 10; c++) {
	    map[28][c].makeBarrier();
	}

	for (int r = 14; r <= 18; r++) {
	    map[r][4].makeBarrier();
	}
	map[18][5].makeBarrier();
	for (int r = 18; r <= 24; r++) {
	    map[r][6].makeBarrier();
	}
	map[24][7].makeBarrier();
	for (int r = 22; r <= 26; r++) {
	    map[r][8].makeBarrier();
	}
	for (int c = 9; c <= 11; c++) {
	    map[26][c].makeBarrier();
	}

	for (int r = 24; r <= 29; r++) {
	    map[r][12].makeBarrier();
	}
	map[24][13].makeBarrier();
	map[24][14].makeBarrier();
	map[26][13].makeBarrier();
	map[26][14].makeBarrier();
	map[24][11].makeBarrier();
	for (int r = 18; r <= 24; r++) {
	    map[r][10].makeBarrier();
	}
	map[18][8].makeBarrier();
	map[18][9].makeBarrier();
	map[20][8].makeBarrier();
	map[20][9].makeBarrier();

	map[14][6].makeBarrier();
	map[15][6].makeBarrier();
	for (int c = 6; c <= 12; c++) {
	    map[16][c].makeBarrier();
	}
	map[17][12].makeBarrier();
	map[18][12].makeBarrier();
	map[18][13].makeBarrier();
	for (int r = 16; r <= 19; r++) {
	    map[r][14].makeBarrier();
	}
	for (int c = 12; c <= 22; c++) {
	    map[20][c].makeBarrier();
	}
	map[18][20].makeBarrier();
	map[19][20].makeBarrier();
	map[18][22].makeBarrier();
	map[19][22].makeBarrier();

	for (int c = 12; c <= 18; c++) {
	    map[22][c].makeBarrier();
	}
	for (int r = 23; r <= 26; r++) {
	    map[r][16].makeBarrier();
	}
	map[26][17].makeBarrier();
	map[26][18].makeBarrier();
	map[27][18].makeBarrier();
	for (int c = 16; c <= 26; c++) {
	    map[28][c].makeBarrier();
	}

	map[27][24].makeBarrier();
	for (int c = 24; c <= 27; c++) {
	    map[26][c].makeBarrier();
	}
	for (int r = 24; r <= 28; r++) {
	    map[r][28].makeBarrier();
	}

	for (int c = 18; c <= 20; c++) {
	    map[24][c].makeBarrier();
	}
	map[23][20].makeBarrier();
	for (int c = 20; c <= 29; c++) {
	    map[22][c].makeBarrier();
	}
	map[21][22].makeBarrier();
	map[21][24].makeBarrier();
	for (int c = 24; c <= 26; c++) {
	    map[20][c].makeBarrier();
	}
	map[23][26].makeBarrier();
	map[24][26].makeBarrier();

	map[23][24].makeBarrier();
	for (int c = 22; c <= 24; c++) {
	    map[24][c].makeBarrier();
	}
	map[25][22].makeBarrier();
	for (int c = 20; c <= 22; c++) {
	    map[26][c].makeBarrier();
	}
	map[27][20].makeBarrier();

	map[28][14].makeBarrier();
	map[29][14].makeBarrier();

	map[4][14].makeBarrier();
    }

}
