package se.liu.ida.linbe810.tddd78;

/**
 * Converts the gameboard into a string and adds a random polynom.
 */
public final class BoardToTextConverter
{
    private BoardToTextConverter() {}

    public static String convertToText(Board gameBoard) {
	StringBuilder builder = new StringBuilder();

	int height = gameBoard.getHeight();
	int width = gameBoard.getWidth();
	Poly poly = gameBoard.getFallingPoly();
	int curX = 2;
	int curY = 1;
	int maxX = poly.getWidth();
	int maxY = poly.getHeight();

	for (int h = 0; h < height; h++) {
	    int polyY = posConverterY(curY, h);
	    for (int w = 0; w < width; w++) {
		int polyX = posConverterX(curX, w);
		if (polyX >= 0 && polyX < maxX && polyY >= 0 && polyY < maxY) {
		    if (poly.getPolyShape()[polyY][polyX] != SquareType.EMPTY) {
			squareType(poly.getPolyShape()[polyY][polyX], builder);
		    } else {
			squareType(gameBoard.getSquareType(h, w), builder);
		    }
		} else {
		    squareType(gameBoard.getSquareType(h, w), builder);
		}
		builder.append(' ');
	    }
	    builder.append('\n');
	}
	String text = builder.toString();
	return text;
    }

    public static int posConverterX(int posX, int w) {
	int polyCordX = w - posX;
	return polyCordX;
    }

    public static int posConverterY(int curY, int h) {
	int polyCordY = h - curY;
	return polyCordY;
    }

    public static StringBuilder squareType(SquareType squareType, StringBuilder builder) {
	switch (squareType) {
	    case EMPTY:
		builder.append(" ");
		break;
	    case I_BLOCK:
		builder.append("I_BLOCK");
		break;
	    case J_BLOCK:
		builder.append("J_BLOCK");
		break;
	    case L_BLOCK:
		builder.append("L_BLOCK");
		break;
	    case O_BLOCK:
		builder.append("O_BLOCK");
		break;
	    case S_BLOCK:
		builder.append("S_BLOCK");
		break;
	    case T_BLOCK:
		builder.append("T_BLOCK");
		break;
	    case Z_BLOCK:
		builder.append("Z_BLOCK");
		break;
	    case OUTSIDE:
		builder.append("§");
		break;
	}
	return builder;
    }
}