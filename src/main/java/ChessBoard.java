public class ChessBoard {
    private int heightTheBoard = 8;
    private int widthTheBoard = 8;
    private int[][] theBoard = {
            {2, 3, 4, 4, 4, 4, 3, 2},
            {3, 4, 6, 6, 6, 6, 4, 3},
            {4, 6, 8, 8, 8, 8, 6, 4},
            {4, 6, 8, 8, 8, 8, 6, 4},
            {4, 6, 8, 8, 8, 8, 6, 4},
            {4, 6, 8, 8, 8, 8, 6, 4},
            {3, 4, 6, 6, 6, 6, 4, 3},
            {2, 3, 4, 4, 4, 4, 3, 2}};

    /**
     * This getter is.
     *
     * @return Integer number of height the chessboard
     */
    public int getHeightTheBoard() {
        return heightTheBoard;
    }

    /**
     * The setter sets of height the chessboard
     *
     * @param heightTheBoard - height the chessboard
     */
    public void setHeightTheBoard(int heightTheBoard) {
        this.heightTheBoard = heightTheBoard;
    }

    /**
     * This getter is.
     *
     * @return Integer number of width the chessboard
     */
    public int getWidthTheBoard() {
        return widthTheBoard;
    }


    /**
     * The setter sets of width the chessboard
     *
     * @param widthTheBoard - width the chessboard
     */
    public void setWidthTheBoard(int widthTheBoard) {
        this.widthTheBoard = widthTheBoard;
    }

    /**
     * This getter is.
     *
     * @return Two-dimensional integer array with the values of each square of a chessboard
     */
    public int[][] getTheBoard() {
        return theBoard;
    }

    /**
     * The setter sets the chessboard
     *
     * @param theBoard - array with the values of each square
     */
    public void setTheBoard(int[][] theBoard) {
        this.theBoard = theBoard;
    }

    /**
     * This getter is.
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
     * @param numberOfMoves - the number of moves made by the knight
     */
    public void printChessBoard(int numberOfMoves) {
        System.out.print("   ");
        for (int i = 0; i < heightTheBoard; i++) System.out.printf("% 3d", i);
        System.out.print("\n  ***********");
        for (int i = 0; i < heightTheBoard; i++) System.out.printf("%s", "**");
        System.out.println();

        for (int i = 0; i < widthTheBoard; i++) {
            System.out.print(i + " *");
            for (int j = 0; j < heightTheBoard; j++) {
                System.out.printf("% 3d", theBoard[i][j]);
            }
            System.out.println(" *");
        }

        System.out.print("  ***********");
        for (int i = 0; i < heightTheBoard; i++) System.out.printf("%s", "**");

        System.out.println("\nAll the moves made: " + numberOfMoves + "\n");
    }
}