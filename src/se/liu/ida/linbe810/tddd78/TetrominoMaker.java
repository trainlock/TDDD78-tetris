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

	Poly poly = null;

	switch (SquareType.values()[n]) {
	    case I:
		poly = createPolyI();
		break;
	    case O:
		poly = createPolyO();
		break;
	    case T:
		poly = createPolyT();
		break;
	    case S:
		poly = createPolyS();
		break;
	    case Z:
		poly = createPolyZ();
		break;
	    case J:
		poly = createPolyJ();
		break;
	    case L:
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
	squareI[1][0] = SquareType.I;
	squareI[1][1] = SquareType.I;
	squareI[1][2] = SquareType.I;
	squareI[1][3] = SquareType.I;
	return new Poly(squareI);
    }

    public static Poly createPolyO() {
	int size = 2;
	SquareType[][] squareO = new SquareType[size][size];
	squareO[0][0] = SquareType.O;
	squareO[0][1] = SquareType.O;
	squareO[1][0] = SquareType.O;
	squareO[1][1] = SquareType.O;
	return new Poly(squareO);
    }

    public static Poly createPolyT() {
	int size = 3;
	SquareType[][] squareT = new SquareType[size][size];
	createEmptyBackground(squareT, size);
	squareT[0][1] = SquareType.T;
	squareT[1][0] = SquareType.T;
	squareT[1][1] = SquareType.T;
	squareT[1][2] = SquareType.T;
	return new Poly(squareT);
    }

    public static Poly createPolyS() {
	int size = 3;
	SquareType[][] squareS = new SquareType[size][size];
	createEmptyBackground(squareS, size);
	squareS[0][1] = SquareType.S;
	squareS[0][2] = SquareType.S;
	squareS[1][0] = SquareType.S;
	squareS[1][1] = SquareType.S;
	return new Poly(squareS);
    }

    public static Poly createPolyZ() {
	int size = 3;
	SquareType[][] squareZ = new SquareType[size][size];
	createEmptyBackground(squareZ, size);
	squareZ[0][0] = SquareType.Z;
	squareZ[0][1] = SquareType.Z;
	squareZ[1][1] = SquareType.Z;
	squareZ[1][2] = SquareType.Z;
	return new Poly(squareZ);
    }

    public static Poly createPolyJ() {
	int size = 3;
	SquareType[][] squareJ = new SquareType[size][size];
	createEmptyBackground(squareJ, size);
	squareJ[0][0] = SquareType.J;
	squareJ[1][0] = SquareType.J;
	squareJ[1][1] = SquareType.J;
	squareJ[1][2] = SquareType.J;
	return new Poly(squareJ);
    }

    public static Poly createPolyL() {
	int size = 3;
	SquareType[][] squareL = new SquareType[size][size];
	createEmptyBackground(squareL, size);
	squareL[0][2] = SquareType.L;
	squareL[1][0] = SquareType.L;
	squareL[1][1] = SquareType.L;
	squareL[1][2] = SquareType.L;
	return new Poly(squareL);
    }

}
