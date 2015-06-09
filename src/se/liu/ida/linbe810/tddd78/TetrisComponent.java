package se.liu.ida.linbe810.tddd78;

import java.awt.event.ActionEvent;
import java.awt.*;
import java.util.*;
import javax.swing.*;

/**A class for handling the graphical component*/
public class TetrisComponent extends JComponent implements BoardListener
{
    private Board gameBoard;
    private Map<SquareType, Color> colourMap;
    private int squareSize;

    public TetrisComponent(Board gameBoard) {
	this.gameBoard = gameBoard;
        this.colourMap = new EnumMap<SquareType, Color>(SquareType.class);
        this.squareSize = gameBoard.getSquareSize();
        gameBoard.addBoardListener(this);
        this.getKeyBindings();
        fillColourMap();
    }

    @Override
    public Dimension getPreferredSize() {
        super.getPreferredSize();
	int width = squareSize * gameBoard.getWidth();
	int height = squareSize * gameBoard.getHeight();
        return new Dimension(width, height);
    }

    public void fillColourMap() {
        // I_BLOCK, O_BLOCK, T_BLOCK, S_BLOCK, Z_BLOCK, J_BLOCK, L_BLOCK, EMPTY, OUTSIDE
        colourMap.put(SquareType.I_BLOCK, Color.GREEN);
        colourMap.put(SquareType.O_BLOCK, Color.ORANGE);
        colourMap.put(SquareType.T_BLOCK, Color.YELLOW);
        colourMap.put(SquareType.S_BLOCK, Color.CYAN);
        colourMap.put(SquareType.Z_BLOCK, Color.PINK);
        colourMap.put(SquareType.J_BLOCK, Color.RED);
        colourMap.put(SquareType.L_BLOCK, Color.BLUE);
        colourMap.put(SquareType.EMPTY, Color.LIGHT_GRAY);
        colourMap.put(SquareType.OUTSIDE, Color.GRAY);
    }

    @Override
    public void paintComponent(final Graphics g) {
	super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;
        gameBoard.draw(g2d, colourMap);
    }

    @Override public void boardChanged() {
        this.repaint();
    }

    // Kommer inte in i action. Därför fungerar det ej!
    private final Action exitAction = new AbstractAction() {
        @Override public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };

    private final Action moveRight = new AbstractAction() {
        @Override public void actionPerformed(ActionEvent e) {
            gameBoard.moveRight();
        }
    };

    private final Action moveLeft = new AbstractAction() {
        @Override public void actionPerformed(ActionEvent e) {
            gameBoard.moveLeft();
        }
    };

    private final Action moveDown = new AbstractAction() {
        @Override public void actionPerformed(ActionEvent e) {
            gameBoard.moveDown();
        }
    };

    private final Action rotatePoly = new AbstractAction() {
        @Override public void actionPerformed(ActionEvent e) {
            gameBoard.rotatePoly();
        }
    };

    public void getKeyBindings(){
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("Q"), "exitAction");
	this.getActionMap().put("exitAction", exitAction);

        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
        this.getActionMap().put("moveRight", moveRight);

        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        this.getActionMap().put("moveLeft", moveLeft);

        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
        this.getActionMap().put("moveDown", moveDown);

        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "rotatePoly");
        this.getActionMap().put("rotatePoly", rotatePoly);
    }
}
