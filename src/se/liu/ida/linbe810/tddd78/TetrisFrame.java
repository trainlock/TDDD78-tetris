package se.liu.ida.linbe810.tddd78;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Collections;

/**
 * Creates a graphic board of the tetris.
 */
public class TetrisFrame extends JFrame
{
    private static final int TIME_INTERVALS = 400;
    private Board gameBoard;
    private JComponent component;
    private JLabel highscoreLabel;
    private boolean isNewGame;


    public TetrisFrame(final Board gameBoard, final String title) throws HeadlessException {
	super(title);
	this.gameBoard = gameBoard;
	this.isNewGame = false;
	this.highscoreLabel = new JLabel("Score: " + gameBoard.getHighScore());

	component = new TetrisComponent(gameBoard);
	this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	createGUI();
	createMenus();

	this.pack();
	this.setVisible(true);

	final Action doOneStep = new AbstractAction() {
	    @Override public void actionPerformed(final ActionEvent e) {
		tick();
	    }
	};

	final Timer clockTimer = new Timer(TIME_INTERVALS, doOneStep);
	clockTimer.setCoalesce(true);
	clockTimer.start();
    }

    public void createGUI() {
	this.setLayout(new BorderLayout());
	this.add(component, BorderLayout.CENTER);
    }

    private void createMenus() {
	JButton newGameButton = new JButton("New Game");
	JButton quitButton = new JButton("Quit");

	newGameButton.setAction(new AbstractAction() {
	    @Override public void actionPerformed(ActionEvent e) {
		Object[] options = {"Yes, wouldn't have it any other way", "No thanks, not my game today"};
		int optionChosen = JOptionPane.showOptionDialog(null,
								"Would you like to start a new game?",
								"Are you sure?",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null,
								options,
								options[1]);
		if (optionChosen == JOptionPane.YES_OPTION) {
		    System.exit(0);
		}
	    }
	});
	quitButton.setAction(new AbstractAction() {
	    @Override public void actionPerformed(ActionEvent e) {
		Object[] options = {"Yes, unfortunately", "No, I'm on a roll!"};
		int optionChosen = JOptionPane.showOptionDialog(null,
								"Are you really sure you want to do that?",
								"No!! You can't do this! Why?!",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null,
								options,
								options[1]);
		if (optionChosen == JOptionPane.YES_OPTION) {
		    System.exit(0);
		}
	    }
	});

	newGameButton.setText("New Game");
	quitButton.setText("Quit");

	final JMenuBar menuBar = new JMenuBar();
	menuBar.add(newGameButton);
	menuBar.add(Box.createHorizontalGlue());
	menuBar.add(highscoreLabel);
	menuBar.add(Box.createHorizontalGlue());
	menuBar.add(quitButton);
	this.setJMenuBar(menuBar);
    }

    private void showGameOver() {
	if (!isNewGame) {
	    isNewGame = true;

	    final String name = JOptionPane.showInputDialog(this, "You have beaten the game! \n Enter your name: ",
							    "Please don't write here");

	    final Highscore highscore = new Highscore(gameBoard.getHighScore(), name);
	    HighscoreList.getInstance().addHighscores(highscore);
	    Collections.sort(HighscoreList.getInstance().getHighscores(), new ScoreComparator());
	    Collections.reverse(HighscoreList.getInstance().getHighscores());
	    showHighscore();

	    Object[] options = { "New Game", "Quit" };
	    int optionChosen = JOptionPane.showOptionDialog(this, "Would you like to start a new game or quit?", null,
							    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
							    options, options[0]);
	    if (optionChosen == JOptionPane.YES_OPTION) {
		newGame();
	    } else if (optionChosen == JOptionPane.NO_OPTION) {
		System.exit(0);
	    }
	}
    }

    public void showHighscore(){
	StringBuilder builder = new StringBuilder();

	for (Highscore highscore1 : HighscoreList.getInstance().getHighscores()) {
	    builder.append(highscore1);
	    builder.append('\n');
	}
	String text = builder.toString();
	JOptionPane.showMessageDialog(this, text);
    }

    public void newGame() {
	this.dispose();

	Board board = new Board(gameBoard.getHeight(), gameBoard.getWidth());
	board.setIsGameOver(false);
	TetrisFrame tetrisFrame= new TetrisFrame(board, "MyBoard");
	tetrisFrame.setVisible(true);
	board.addBoardListener(new TetrisComponent(board));
    }

    public void tick() {
	if (gameBoard.gameOver()) {
	    showGameOver();
	}
	else {
	    gameBoard.fall();
	    highscoreLabel.setText("Score: " + gameBoard.getHighScore());
	}
    }
}
