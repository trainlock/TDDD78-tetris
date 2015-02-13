package se.liu.ida.linbe810.tddd78;

import java.util.Random;

public class Board
{
    private SquareType[][] squares;
    private int height, width;
    private Poly falling;
    private int fallingX, fallingY;

    public Board(int height, int width) {
        Random rand = new Random();

        int border = 1;
        this.height = height;
        this.width = width;
        squares = new SquareType[height + border][width + border];

        falling = TetrominoMaker.getPoly(rand.nextInt(TetrominoMaker.getNumberOfTypes()));
        fallingX = 0;
        fallingY = 0;

        for (int column = 0; column < height + border; column++) {
            for (int row = 0; row < width + border; row++) {
                if (column == 0 || row == 0 ||
                    row == width + border - 1 || column == height + border -1) {
                    squares[column][row] = SquareType.OUTSIDE;
                }
                else{
                    squares[column][row] = SquareType.EMPTY ;
                }
            }
        }
    }

    public void randomBoard() {
        Random rand = new Random();
        SquareType[] randSquare = SquareType.values();
        int squareLength = randSquare.length - 1;
        for (int column = 1; column < height; column++) {
            for (int row = 1; row < width; row++) {
                setSquare(column, row, randSquare[rand.nextInt(squareLength)]);
            }
        }
    }

    public void setSquare(int h, int w, SquareType value) {
        squares[h][w] = value;
    }

    public SquareType getSquare(int h, int w) {
        SquareType rowCol = squares[h][w];
        return rowCol;
    }

    public Poly getFalling() {
        return falling;
    }

    public int getFallingX() {
        return 3;
    }

    public int getFallingY() {
        return 1;
    }

    public int getHeight() {
        return squares.length;
    }

    public int getWidth() {
        return squares[0].length;
    }

    public static void main(String[] args) {
	Board randomBoard = new Board(10, 10);
        System.out.println(randomBoard);
    }

    /**
    public SquareType getType() {
        SquareType[] randSquare = SquareType.values();
        int squareLength = randSquare.length -1;
        // Bättre att göra en while eller så för att få bort OUTSIDE.
        return randSquare[rand.nextInt(squareLength)];
    }
     */
}
