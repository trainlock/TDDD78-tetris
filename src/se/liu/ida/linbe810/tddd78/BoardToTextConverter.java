package se.liu.ida.linbe810.tddd78;

public final class BoardToTextConverter
{
    private BoardToTextConverter() {}

    public static String convertToText(Board gameBoard) {
	StringBuilder builder = new StringBuilder();
	int height = gameBoard.getHeight();
	int width = gameBoard.getWidth();
	Poly poly = gameBoard.getFalling();
	int curX = gameBoard.getFallingX();
	int curY = gameBoard.getFallingY();
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
			squareType(gameBoard.getSquare(h, w), builder);
		    }
		} else {
		    squareType(gameBoard.getSquare(h, w), builder);
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

    public static void squareType(SquareType squareType, StringBuilder builder) {
	switch (squareType) {
	    case EMPTY:
		builder.append(" ");
		break;
	    case I:
		builder.append("I");
		break;
	    case J:
		builder.append("J");
		break;
	    case L:
		builder.append("L");
		break;
	    case O:
		builder.append("O");
		break;
	    case S:
		builder.append("S");
		break;
	    case T:
		builder.append("T");
		break;
	    case Z:
		builder.append("Z");
		break;
	    case OUTSIDE:
		builder.append("§");
		break;
	}
	return builder;
    }
}