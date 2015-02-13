package se.liu.ida.linbe810.tddd78;

public final class BoardTest
{

    private BoardTest() {}

    public static void main(String[] args) {
	//for (int count = 0; count < 10; count++) {
	Board board = new Board(10, 10);
	//board.randomBoard();
	//System.out.println(BoardToTextConverter.convertToText(board));
	new TetrisFrame(board, "MyBoard");
    }
}
