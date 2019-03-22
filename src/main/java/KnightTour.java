public class KnightTour {

    public static void main(String[] args) {
        final int HEIGHT_OF_BOARD = 8;
        final int WIDTH_OF_BOARD = 8;

        int maxNumbMoves = HEIGHT_OF_BOARD * WIDTH_OF_BOARD;
        int countSuccessfulTours = 0;
        int numberMove;

        for (int i = 0; i < HEIGHT_OF_BOARD; i++) {
            for (int j = 0; j < WIDTH_OF_BOARD; j++) {
                ChessBoard chessBoard = new ChessBoard();
                Knight knightA = new Knight(chessBoard, i, j);

                // Run the knight tour and monitor count moves
                numberMove = knightA.startTour();

                if (numberMove == maxNumbMoves) {
                    ++countSuccessfulTours;
                } else {
                    System.out.println("It was a bad tour! I started my journey from the square " +
                            "with coordinates (" + knightA.getLocationByAxisX() + " ; "
                            + knightA.getLocationByAxisY() + ")");
                    // Display statistics.
                    chessBoard.printChessBoard(numberMove);
                }
            }
        }

        System.out.println("\nNumber of successful tours: " + countSuccessfulTours);
    }
}