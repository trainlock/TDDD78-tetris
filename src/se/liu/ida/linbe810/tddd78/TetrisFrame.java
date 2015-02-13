package se.liu.ida.linbe810.tddd78;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TetrisFrame extends JFrame
{
    public static final int TIME_INTERVALS = 500;
    private JTextArea textBoard;
    private Board gameBoard;
    public static final int FONT_SIZE = 24;

    public TetrisFrame(final Board gameBoard, final String title) throws HeadlessException {
	super(title);
	this.gameBoard = gameBoard;
	int rows = gameBoard.getWidth();
	int columns = gameBoard.getHeight();

	textBoard = new JTextArea(rows, columns);
	textBoard.setText(BoardToTextConverter.convertToText(gameBoard));
	Font font = new Font("monospaced", Font.BOLD, FONT_SIZE);
	textBoard.setFont(font);

	this.setLayout(new BorderLayout());
	this.add(textBoard, BorderLayout.CENTER);
	this.pack();
	this.setVisible(true);

	final Timer clockTimer = new Timer(TIME_INTERVALS, doOneStep);
	clockTimer.setCoalesce(true);
	clockTimer.start();

	//clockTimer.stop();
    }

    public JTextArea getTextBoard(){
	return textBoard;
    }

    public void setText(String text) {
	textBoard.setText(text);
    }

    final Action doOneStep = new AbstractAction() {
   	    @Override public void actionPerformed(final ActionEvent e) {
     		gameBoard.randomBoard();
   		updateText(gameBoard, textBoard);
   		}
    	};

    public void updateText(Board board, JTextArea text) {
	text.setText(BoardToTextConverter.convertToText(board));
    }
}
