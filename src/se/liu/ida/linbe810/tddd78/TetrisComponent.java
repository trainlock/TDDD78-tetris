package se.liu.ida.linbe810.tddd78;

import java.awt.*;
import java.awt.Color;
import java.util.*;
import javax.swing.*;

public class TetrisComponent extends JComponent
{
    private static Board gameBoard;
    private EnumMap<SquareType, Color> colourMap;
    private static int SPACE = 2;
    private static int TEXT_SIZE = 12;
    private static int SQUARE_SIZE = 30;

    public TetrisComponent(Board gameBoard) {
	this.gameBoard = gameBoard;
        this.colourMap = new EnumMap<SquareType, Color>(SquareType.class);
        fillColourMap();
    }

    public Dimension getPreferredSize() {
	int width = SQUARE_SIZE * gameBoard.getWidth();
	int height = SQUARE_SIZE * gameBoard.getHeight();
        System.out.println("Widht: " + width + " Height :" + height);
        return new Dimension(width, height);
    }

    public void fillColourMap() {
        // I, O, T, S, Z, J, L, EMPTY, OUTSIDE

        colourMap.put(SquareType.I, Color.GREEN);
        colourMap.put(SquareType.O, Color.ORANGE);
        colourMap.put(SquareType.T, Color.YELLOW);
        colourMap.put(SquareType.S, Color.CYAN);
        colourMap.put(SquareType.Z, Color.PINK);
        colourMap.put(SquareType.J, Color.RED);
        colourMap.put(SquareType.L, Color.BLUE);
        colourMap.put(SquareType.EMPTY, Color.LIGHT_GRAY);
        colourMap.put(SquareType.OUTSIDE, Color.GRAY);
    }

    @Override
    public void paintComponent(final Graphics g) {
	super.paintComponent(g);
        fillBoardColour(gameBoard, g);
    }

    public void fillBoardColour(Board gameBoard, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int height = gameBoard.getHeight();
        // height == row
       	int width = gameBoard.getWidth();
        // width == column
       	Poly poly = gameBoard.getFalling();
       	int curX = gameBoard.getFallingX();
       	int curY = gameBoard.getFallingY();

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {

                SquareType currentSquare = gameBoard.getSquare(row, column);

                if (poly != null) {
                    int polyY = posConverterY(curY, row);
                    int polyX = posConverterX(curX, column);

                    if (polyX >= 0 && polyX < poly.getWidth()-1
                        && polyY >= 0 && polyY < poly.getHeight()-1
                        && currentSquare != SquareType.EMPTY) {
                        currentSquare = poly.getPolyShape()[polyY][polyX];
                    }
                }
                g2d.setColor(getSqColourMap(currentSquare));
                g2d.drawRect(column * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                g2d.fillRect(column * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    public static int posConverterX(int posX, int w) {
	int polyCordX = w - posX;
	return polyCordX;
    }

    public static int posConverterY(int curY, int h) {
	int polyCordY = h - curY;
	return polyCordY;
    }

    public Color getSqColourMap(SquareType squareType) {
        return colourMap.get(squareType);
    }
}