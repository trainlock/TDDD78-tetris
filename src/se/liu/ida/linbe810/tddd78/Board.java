package se.liu.ida.linbe810.tddd78;

import java.util.ArrayList;
import java.util.Random;

/**
 * Creates a gameboard and fills it up with empty spaces.
 */
public class Board
{
    private SquareType[][] squares;
    private int height, width;
    private Poly falling;
    private ArrayList<BoardListener> boardListener;

    public Board(int height, int width) {
        Random rand = new Random();

        this.height = height;
        this.width = width;
        squares = new SquareType[height][width];
        boardListener = new ArrayList<BoardListener>();

        falling = TetrominoMaker.getPoly(rand.nextInt(TetrominoMaker.getNumberOfTypes()));
        final int fallingX = 0;
        final int fallingY = 0;

        for (int row = 0; row < height ; row++) {
            for (int column = 0; column < width ; column++) {
                if (row == 0 || column == 0 ||
                    column == width -1 || row == height -1) {
                    squares[row][column] = SquareType.OUTSIDE;
                }
                else{
                    squares[row][column] = SquareType.EMPTY ;
                }
            }
        }
        notifyListeners();
    }

    public void randomBoard() {
        Random rand = new Random();
        SquareType[] randSquare = SquareType.values();
        int squareLength = randSquare.length - 1;
        for (int column = 1; column < height -1; column++) {
            for (int row = 1; row < width -1; row++) {
                setSquare(column, row, randSquare[rand.nextInt(squareLength)]);
            }
        }
    }

    public void addBoardListener(BoardListener bl) {
        boardListener.add(bl);
    }

    private void notifyListeners() {
        for (BoardListener bl : boardListener) {
            bl.boardChanged();
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
        // Kraschar om den inte har någon storlek.
        return squares[0].length;
    }

    /**
    public static void main(String[] args) {
	Board randomBoard = new Board(10, 10);
        System.out.println(randomBoard);
    }

    public SquareType getType() {
        SquareType[] randSquare = SquareType.values();
        int squareLength = randSquare.length -1;
        return randSquare[rand.nextInt(squareLength)];
    }
     */
}
