package se.liu.ida.linbe810.tddd78;

public class Poly
{
    private SquareType[][] polyShape;

    public Poly(final SquareType[][] polyShape) {
	this.polyShape = polyShape;
    }

    public SquareType[][] getPolyShape() {
        return polyShape;
    }

    public int getHeight() {
        return polyShape.length;
    }

    public int getWidth() {
        return polyShape[0].length;
    }
}
