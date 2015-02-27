package se.liu.ida.linbe810.tddd78;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Creates a graphic board of the tetris.
 */
public class TetrisFrame extends JFrame
{
    public static final int TIME_INTERVALS = 500;
    /**
     * The interval between the boards updates.
     */
    private JFrame frame;
    private Board gameBoard;

    public TetrisFrame(final Board gameBoard, final String title) throws HeadlessException {
	super(title);
	this.gameBoard = gameBoard;

	this.frame = new JFrame("Menybar");
	createGUI();
	createMenus();

	frame.pack();
	frame.setVisible(true);

	final Action doOneStep = new AbstractAction() {
	    @Override public void actionPerformed(final ActionEvent e) {
  		gameBoard.randomBoard();
		repaint();
	    }
 	};


	final Timer clockTimer = new Timer(TIME_INTERVALS, doOneStep);
	clockTimer.setCoalesce(true);
	clockTimer.start();

	//clockTimer.stop();
    }

    public void createGUI() {
	TetrisComponent comp = new TetrisComponent(gameBoard);

	comp.setSize(getPreferredSize());

	frame.setLayout(new BorderLayout());
	frame.add(comp, BorderLayout.CENTER);
    }

    private void createMenus() {
	// Inget händer när man trycker på knapparna.
	// Fönstret stängs ej ner!

	JMenu newGameButton = new JMenu("New Game");
	//JMenuItem newGameButton = new JMenuItem("New Game", 'N');
	JMenu quitButton = new JMenu("Quit");
	//JMenuItem quitButton = new JMenuItem("Quit", 'Q');

	newGameButton.setAction(new AbstractAction() {
	    @Override public void actionPerformed(ActionEvent e) {
		Object[] options = {"Yes, wouldn't have it any other way", "No thanks, not my game today"};
		int optionChosen = JOptionPane.showOptionDialog(frame.getParent(),
								"Would you like to start a new game?",
								"Are you sure?",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null,
								options,
								options[2]);
		if (optionChosen == JOptionPane.YES_OPTION); {
		    System.exit(0);
		}
	    }
	});
	quitButton.setAction(new AbstractAction() {
	    @Override public void actionPerformed(ActionEvent e) {
		Object[] options = {"Yes, unfortunately", "No, I'm on a roll!"};
		int optionChosen = JOptionPane.showOptionDialog(frame.getParent(),
								"Are you really sure you want to do that?",
								"No!! You can't do this! Why?!",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null,
								options,
								options[2]);
		if (optionChosen == JOptionPane.YES_OPTION); {
		    System.exit(0);
		}
	    }
	});

	newGameButton.setText("New Game");
	quitButton.setText("Quit");

	final JMenuBar bar = new JMenuBar();
	bar.add(newGameButton);
	bar.add(Box.createHorizontalGlue());
	bar.add(quitButton);
	frame.setJMenuBar(bar);
    }

/**
 *
 	int rows = gameBoard.getWidth();
  	int columns = gameBoard.getHeight();

  	Ska ändra här för att måla ut board
 	textBoard = new JTextArea(rows, columns);
 	textBoard.setText(BoardToTextConverter.convertToText(gameBoard));
 	Font font = new Font("monospaced", Font.BOLD, FONT_SIZE);
 	textBoard.setFont(font);

 	frame.setLayout(new BorderLayout());
 	frame.add(textBoard, BorderLayout.CENTER);

    public JTextArea getTextBoard(){
	return textBoard;
    }

    public void setText(String text) {
	textBoard.setText(text);
    }

     public void updateText(Board board, JTextArea text) {
 	text.setText(BoardToTextConverter.convertToText(board));
     }

 	Ska ändra här så att den tar repaint()
 	updateText(gameBoard, textBoard);
*/
}
