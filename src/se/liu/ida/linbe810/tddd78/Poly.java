package se.liu.ida.linbe810.tddd78;

/**
 * The Tetromino-object with its methodes used to modify it.
 */
public class Poly
{
    private SquareType[][] polyShape;
    private int height, width;

    public Poly(final SquareType[][] polyShape) {
	this.polyShape = polyShape;
        height = polyShape.length;
        width = polyShape[0].length;
    }

    public SquareType[][] getPolyShape() {
        return polyShape;
    }

    public SquareType polyShape(int height, int width) {
        return polyShape[height][width];
    }

    public int getHeight() {
        return polyShape.length;
    }

    public int getWidth() {
        return polyShape[0].length;
    }

    public Poly rotateRight() {
        SquareType[][] newSquare = new SquareType[height][width];

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++){
                newSquare[column][height-1-row] = this.polyShape[row][column];
            }
        }

        return new Poly(newSquare);
    }
}
