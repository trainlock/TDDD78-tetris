package se.liu.ida.linbe810.tddd78;

/**
 * Creates a board and tests it.
 */
public final class BoardTest
{
    private BoardTest() {}

    public static void main(String[] args) {
	//for (int count = 0; count < 10; count++) {
	Board board = new Board(20, 10);
	//board.randomBoard();
	//System.out.println(BoardToTextConverter.convertToText(board));

	new TetrisFrame(board, "MyBoard");
    }
}

