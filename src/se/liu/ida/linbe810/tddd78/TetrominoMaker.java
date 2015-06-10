package se.liu.ida.linbe810.tddd78;

/**
 * Creates a double list that represent a tetrisblock.
 */
public final class TetrominoMaker
{
    private TetrominoMaker() {}

    public static int getNumberOfTypes() {
	return (SquareType.values().length -2);
    }

    public static Poly getPoly(int n) {
	Poly poly;

	switch (n) {
	    case 0: // I_BLOCK
		poly = createPolyI();
		break;
	    case 1: // O_BLOCK
		poly = createPolyO();
		break;
	    case 2: // T_BLOCK
		poly = createPolyT();
		break;
	    case 3: // S_BLOCK
		poly = createPolyS();
		break;
	    case 4: // Z_BLOCK
		poly = createPolyZ();
		break;
	    case 5: // J_BLOCK
		poly = createPolyJ();
		break;
	    case 6: // L_BLOCK
	    default:
		poly = createPolyL();
		break;
	}
	return poly;
    }

    public static void createEmptyBackground(SquareType[][] square, int squareSize) {
	for (int height = 0; height < squareSize; height++) {
	    for (int width = 0; width < squareSize; width++) {
		square[height][width] = SquareType.EMPTY;
	    }
	}
    }

    public static Poly createPolyI() {
	int size = 4;
	SquareType[][] squareI = new SquareType[size][size];
	createEmptyBackground(squareI, size);
	squareI[0][1] = SquareType.I_BLOCK;
	squareI[1][1] = SquareType.I_BLOCK;
	squareI[2][1] = SquareType.I_BLOCK;
	squareI[3][1] = SquareType.I_BLOCK;
	return new Poly(squareI);
    }

    public static Poly createPolyO() {
	int size = 2;
	SquareType[][] squareO = new SquareType[size][size];
	squareO[0][0] = SquareType.O_BLOCK;
	squareO[0][1] = SquareType.O_BLOCK;
	squareO[1][0] = SquareType.O_BLOCK;
	squareO[1][1] = SquareType.O_BLOCK;
	return new Poly(squareO);
    }

    public static Poly createPolyT() {
	int size = 3;
	SquareType[][] squareT = new SquareType[size][size];
	createEmptyBackground(squareT, size);
	squareT[0][1] = SquareType.T_BLOCK;
	squareT[1][0] = SquareType.T_BLOCK;
	squareT[1][1] = SquareType.T_BLOCK;
	squareT[1][2] = SquareType.T_BLOCK;
	return new Poly(squareT);
    }

    public static Poly createPolyS() {
	int size = 3;
	SquareType[][] squareS = new SquareType[size][size];
	createEmptyBackground(squareS, size);
	squareS[0][1] = SquareType.S_BLOCK;
	squareS[0][2] = SquareType.S_BLOCK;
	squareS[1][0] = SquareType.S_BLOCK;
	squareS[1][1] = SquareType.S_BLOCK;
	return new Poly(squareS);
    }

    public static Poly createPolyZ() {
	int size = 3;
	SquareType[][] squareZ = new SquareType[size][size];
	createEmptyBackground(squareZ, size);
	squareZ[0][0] = SquareType.Z_BLOCK;
	squareZ[0][1] = SquareType.Z_BLOCK;
	squareZ[1][1] = SquareType.Z_BLOCK;
	squareZ[1][2] = SquareType.Z_BLOCK;
	return new Poly(squareZ);
    }

    public static Poly createPolyJ() {
	int size = 3;
	SquareType[][] squareJ = new SquareType[size][size];
	createEmptyBackground(squareJ, size);
	squareJ[0][0] = SquareType.J_BLOCK;
	squareJ[1][0] = SquareType.J_BLOCK;
	squareJ[1][1] = SquareType.J_BLOCK;
	squareJ[1][2] = SquareType.J_BLOCK;
	return new Poly(squareJ);
    }

    public static Poly createPolyL() {
	int size = 3;
	SquareType[][] squareL = new SquareType[size][size];
	createEmptyBackground(squareL, size);
	squareL[0][2] = SquareType.L_BLOCK;
	squareL[1][0] = SquareType.L_BLOCK;
	squareL[1][1] = SquareType.L_BLOCK;
	squareL[1][2] = SquareType.L_BLOCK;
	return new Poly(squareL);
    }

}
