package se.liu.ida.linbe810.tddd78;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Creates a gameboard and fills it up with empty spaces.
 */
public class Board
{
    private SquareType[][] squares;
    private Poly fallingPoly;

    private List<Integer> polyToRemove;
    private List<BoardListener> boardListeners;

    private int height, width;
    private int fallingPosX, fallingPosY;
    private int nrOfFullLines;
    private int highScore;

    private boolean isFalling;
    private boolean isGameOver;
    private boolean isFullRow;

    private final static int SQUARE_SIZE = 30;
    private final static int ONE_ROW_SCORE = 100;
    private final static int TWO_ROWS_SCORE = 300;
    private final static int THREE_ROWS_SCORE = 500;
    private final static int FOUR_ROWS_SCORE = 800;

    public Board(int height, int width) {
        Random rand = new Random();

        this.height = height;
        this.width = width;
        this.squares = new SquareType[height][width];
        this.polyToRemove = new ArrayList<Integer>();

        this.isGameOver = false;
        this.isFullRow = false;
        this.isFalling = true;
        // Ändra här vid testning!
        this.fallingPoly = TetrominoMaker.getPoly(rand.nextInt(TetrominoMaker.getNumberOfTypes()));
        this.fallingPosX = width/2 - 1;
        this.fallingPosY = 1;

        this.boardListeners = new ArrayList<BoardListener>();

        for (int row = 0; row < height ; row++) {
            for (int column = 0; column < width ; column++) {
                if (row == 0 || column == 0 ||
                    column == width -1 || row == height -1) {
                    squares[row][column] = SquareType.OUTSIDE;
                }
                else{
                    squares[row][column] = SquareType.EMPTY;
                }
            }
        }
        this.notifyListeners();
    }

    public void addBoardListener(BoardListener bl) {
        boardListeners.add(bl);
    }

    private void notifyListeners() {
        for (BoardListener bl : boardListeners) {
            bl.boardChanged();
        }
    }

    public void draw(Graphics2D g2d, Map<SquareType, Color> squareColor) {
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {

                SquareType square = this.getSquareType(row, column);
                int polyY = row - fallingPosY;
                int polyX = column - fallingPosX;

                if (polyX >= 0 && polyX < fallingPoly.getWidth()
                    && polyY >= 0 && polyY < fallingPoly.getHeight()) {

                    if (fallingPoly.getPolyShape()[polyY][polyX].equals(SquareType.EMPTY) ||
                        square.equals(SquareType.OUTSIDE)) {
                        paintSquare(g2d, squareColor, square, column, row);
                        }
                    else {
                        paintSquare(g2d, squareColor, fallingPoly.getPolyShape()[polyY][polyX], column, row);
                        }
                    }
                else {
                    paintSquare(g2d, squareColor, square, column, row);
                }
            }
        }
        this.notifyListeners();
    }

    public void paintSquare(Graphics2D g2d, Map<SquareType, Color> squareColor, SquareType currentSquare, int column, int row) {
        g2d.setColor(getSqColour(currentSquare, squareColor));
        g2d.drawRect(column * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        g2d.fillRect(column * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE - 1, SQUARE_SIZE - 1);
        this.notifyListeners();
    }

    public void fall() {
        this.isFalling = true;
        for (int h = 0; h < fallingPoly.getHeight(); h++) {
            for (int w = 0; w < fallingPoly.getWidth(); w++) {
                if (fallingPoly.polyShape(h, w) != SquareType.EMPTY &&
                    this.getSquareType((fallingPosY + h + 1), (fallingPosX + w)) != SquareType.EMPTY) {
                    isFalling = false;
                    polyToBoard();
                    checkRows();
                    removeRow();
                    break;
                }
            }
        }
        if (isFalling) {
            fallingPosY++;
        }
    }

    public void polyToBoard() {
        for (int h = 0; h < fallingPoly.getHeight() ; h++) {
            for (int w = 0; w < fallingPoly.getWidth() ; w++) {
                if (fallingPoly.polyShape(h, w) != SquareType.EMPTY &&
                    this.getSquareType((fallingPosY + h), (fallingPosX + w)) == SquareType.EMPTY) {
                    squares[(fallingPosY + h)][(fallingPosX + w)] = fallingPoly.polyShape(h, w);
                }
            }
        }
        if (!isFalling) {
            generateNewPoly();
        }
        this.notifyListeners();
    }

    public void generateNewPoly() {
        Random rand = new Random();
        this.fallingPoly = TetrominoMaker.getPoly(rand.nextInt(TetrominoMaker.getNumberOfTypes()));
        this.fallingPosX = width/2 - 1; //fallingPoly.getWidth()/2);
        this.fallingPosY = 1;
    }

    public void checkRows(){
        for (int h = 1; h < height-1; h++) {
            for (int w = 1; w < width-1; w++) {
                if (this.getSquareType(h, w) == SquareType.EMPTY) {
                    isFullRow = false;
                    break;

                }
            }
            if (isFullRow){
                nrOfFullLines++;
                polyToRemove.add(h);
            }
            isFullRow = true;
        }
    }

    public void removeRow() {
        for (Integer nr : polyToRemove) {
            moveRows(nr);
        }
        polyToRemove.clear();
        highscoreCounter();
        this.notifyListeners();
    }

    public void moveRows(int row) {
        // Tar bort raderna helt så att längden på brädet krymper!!!
        for (int h = row; h > 1; h--) {
            System.arraycopy(squares[h - 1], 1, squares[h], 1, width - 1 - 1);
        }
    }

    public boolean gameOver() {
        for (int h = 0; h < fallingPoly.getHeight(); h++) {
            for (int w = 0; w < fallingPoly.getWidth(); w++) {
                if (fallingPoly.polyShape(h, w) != SquareType.EMPTY &&
                    this.getSquareType((fallingPosY + h), (fallingPosX + w)) != SquareType.EMPTY) {
                    this.isGameOver = true;
                }
            }
        }
        return isGameOver;
    }

    public void setIsGameOver(boolean gameOver) {
        this.isGameOver = gameOver;
    }

    public void highscoreCounter() {
        switch (nrOfFullLines) {
            case 1:
                highScore += ONE_ROW_SCORE;
                break;
            case 2:
                highScore += TWO_ROWS_SCORE;
                break;
            case 3:
                highScore += THREE_ROWS_SCORE;
                break;
            case 4:
                highScore += FOUR_ROWS_SCORE;
                break;
        }
        nrOfFullLines = 0;
    }

    public int getHighScore() {
        return highScore;
    }

    public void moveRight(){
        boolean isMovingRight = true;
        for (int h = 0; h < fallingPoly.getHeight(); h++) {
            for (int w = 0; w < fallingPoly.getWidth(); w++) {
                if (fallingPoly.polyShape(h, w) != SquareType.EMPTY &&
                    this.getSquareType((fallingPosY + h), (fallingPosX + w + 1)) != SquareType.EMPTY) {
                    isMovingRight = false;
                    break;
                }
            }
        }
        if (isMovingRight && !gameOver()){
            fallingPosX++;
        }
    }

    public void moveLeft(){
        boolean isMovingLeft = true;
        for (int h = 0; h < fallingPoly.getHeight(); h++) {
            for (int w = 0; w < fallingPoly.getWidth(); w++) {
                if (fallingPoly.polyShape(h, w) != SquareType.EMPTY &&
                    this.getSquareType((fallingPosY + h), (fallingPosX + w - 1)) != SquareType.EMPTY) {
                    isMovingLeft = false;
                    break;
                }
            }
        }
        if (isMovingLeft && !gameOver()){
            fallingPosX--;
        }
    }

    public void moveDown(){
        if (!gameOver()) {
            fall();
        }
    }

    public void rotatePoly(){
        if(canRotate() && !gameOver()) {
            fallingPoly = fallingPoly.rotateRight();
        }
    }

    public boolean canRotate() {
        if(isPolyOnBoard()) {
            for (int h = 0; h < fallingPoly.rotateRight().getHeight(); h++) {
                for (int w = 0; w < fallingPoly.rotateRight().getWidth(); w++) {
                    if (fallingPoly.rotateRight().polyShape(h, w) != SquareType.EMPTY &&
                        this.getSquareType((fallingPosY + h), (fallingPosX + w)) != SquareType.EMPTY) {
                            return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean isPolyOnBoard() {
        return !(fallingPosX < 1 || fallingPosX > width || fallingPosY > height);
    }

    public SquareType getSquareType(int h, int w) {
        SquareType rowCol = squares[h][w];
        return rowCol;
    }

    public Poly getFallingPoly() {
        return fallingPoly;
    }

    public Color getSqColour(SquareType squareType, Map<SquareType, Color> colourMap) {
        return colourMap.get(squareType);
    }

    public static int getSquareSize() {
        return SQUARE_SIZE;
    }

    public int getHeight() {
        return squares.length;
    }

    public int getWidth() {
        // Kraschar om den inte har någon storlek.
        return squares[0].length;
    }
}