package se.liu.ida.linbe810.tddd78;

/**
 * Creates a board and tests it.
 */
public final class  BoardTest
{
    private BoardTest() {}

    public static void main(String[] args) {
        final int width = 10, height = 21;
        Board board = new Board(height, width);
        TetrisFrame tetrisFrame = new TetrisFrame(board, "MyBoard");
        tetrisFrame.setVisible(true);
    }
}

