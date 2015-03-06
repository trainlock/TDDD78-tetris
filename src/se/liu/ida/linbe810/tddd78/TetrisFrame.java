package se.liu.ida.linbe810.tddd78;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Creates a graphic board of the tetris.
 */
public class TetrisFrame extends JFrame implements MouseListener
{
    public static final int TIME_INTERVALS = 500;
    /**
     * The interval between the boards updates.
     */
    private JFrame frame;
    private Board gameBoard;
    private JComponent component = null;

    public TetrisFrame(final Board gameBoard, final String title) throws HeadlessException {
	super(title);
	this.gameBoard = gameBoard;

	this.frame = new JFrame("Menybar");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	createGUI();
	createMenus();

	frame.pack();
	frame.setVisible(true);

	final Action doOneStep = new AbstractAction() {
	    @Override public void actionPerformed(final ActionEvent e) {
  		gameBoard.randomBoard();
		// Kalla komponent som sedan får rita om sig själv eller något sådant
		component.repaint();
	    }
 	};


	final Timer clockTimer = new Timer(TIME_INTERVALS, doOneStep);
	clockTimer.setCoalesce(true);
	clockTimer.start();

	//clockTimer.stop();
    }

    public void createGUI() {
	component = new TetrisComponent(gameBoard);

	component.setSize(getPreferredSize());
	frame.addMouseListener(this);

	frame.setLayout(new BorderLayout());
	frame.add(component, BorderLayout.CENTER);
    }

    private void createMenus() {
	JButton newGameButton = new JButton("New Game");
	JButton quitButton = new JButton("Quit");

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
								options[1]);
		if (optionChosen == JOptionPane.YES_OPTION);
		System.exit(0);
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
								options[1]);
		if (optionChosen == JOptionPane.YES_OPTION);
		System.exit(0);
	    }
	});

	newGameButton.setText("New Game");
	quitButton.setText("Quit");

	final JMenuBar menuBar = new JMenuBar();
	menuBar.add(newGameButton);
	menuBar.add(Box.createHorizontalGlue());
	menuBar.add(quitButton);
	frame.setJMenuBar(menuBar);
    }

    @Override public void mouseClicked(final MouseEvent e) {

    }

    @Override public void mousePressed(final MouseEvent e) {

    }

    @Override public void mouseReleased(final MouseEvent e) {

    }

    @Override public void mouseEntered(final MouseEvent e) {

    }

    @Override public void mouseExited(final MouseEvent e) {

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
