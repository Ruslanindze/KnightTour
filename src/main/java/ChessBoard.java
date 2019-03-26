public class ChessBoard {
    public final int HEIGHT_OF_BOARD = 8;
    public final int WIDTH_OF_BOARD = 8;
    private int[][] theBoard;

    /**
     * It's default constructor, initializes of the property theBoard.
     */
    public ChessBoard() {
        int[][] theBoard = {
                {2, 3, 4, 4, 4, 4, 3, 2},
                {3, 4, 6, 6, 6, 6, 4, 3},
                {4, 6, 8, 8, 8, 8, 6, 4},
                {4, 6, 8, 8, 8, 8, 6, 4},
                {4, 6, 8, 8, 8, 8, 6, 4},
                {4, 6, 8, 8, 8, 8, 6, 4},
                {3, 4, 6, 6, 6, 6, 4, 3},
                {2, 3, 4, 4, 4, 4, 3, 2}};

        this.theBoard = theBoard;
    }

    /**
     * This is a getter for the property getValueSquare.
     *
     * @param axisY - the coordinate by axis "Y"
     * @param axisX - the coordinate by axis "X"
     * @return Integer value of the square
     */
    public int getValueSquare(int axisY, int axisX) {
        return theBoard[axisY][axisX];
    }

    /**
     * The setter sets the value for the square
     *
     * @param axisY - the coordinate by axis "Y"
     * @param axisX - the coordinate by axis "X"
     * @param value - the value for square
     */
    public void setValueSquare(int axisY, int axisX, int value) {
        theBoard[axisY][axisX] = value;
    }

    /**
     * The method displays the current chessboard with a price for each square.
     *
     * @param numberOfMovesMade - the number of moves made by the knight
     */
    public void printChessBoard(int numberOfMovesMade) {
        System.out.print("   ");
        for (int i = 0; i < HEIGHT_OF_BOARD; i++) System.out.printf("% 3d", i);
        System.out.print("\n  ***********");
        for (int i = 0; i < HEIGHT_OF_BOARD; i++) System.out.printf("%s", "**");
        System.out.println();

        for (int i = 0; i < WIDTH_OF_BOARD; i++) {
            System.out.print(i + " *");
            for (int j = 0; j < HEIGHT_OF_BOARD; j++) {
                System.out.printf("% 3d", theBoard[i][j]);
            }
            System.out.println(" *");
        }

        System.out.print("  ***********");
        for (int i = 0; i < HEIGHT_OF_BOARD; i++) System.out.printf("%s", "**");
    }
}