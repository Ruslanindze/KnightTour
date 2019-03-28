/**
 * The class to start of the Knight tour.
 */
public class KnightTour {

    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();
        int maxNumbMoves = chessBoard.HEIGHT_OF_BOARD * chessBoard.WIDTH_OF_BOARD;
        int countSuccessfulTours = 0;
        int numberOfMovesMade;

        for (int i = 0; i < chessBoard.HEIGHT_OF_BOARD; i++) {
            for (int j = 0; j < chessBoard.WIDTH_OF_BOARD; j++) {
                chessBoard = new ChessBoard();
                Knight knightA = new Knight(chessBoard, i, j);

                System.out.println("Knight by name knightA begins tour with coordinates (" +
                        knightA.getLocationByAxisX() + "," + knightA.getLocationByAxisY() + ").");

                // Run the knight tour and monitor count moves.
                numberOfMovesMade = knightA.startTour();

                if (numberOfMovesMade == maxNumbMoves) {
                    System.out.println("It was a very good tour, congratulations! ");
                    ++countSuccessfulTours;
                } else {
                    System.out.println("It was a bad tour! I finished my journey in square " +
                            "with coordinates (" + knightA.getLocationByAxisX() + " ; "
                            + knightA.getLocationByAxisY() + ")");
                    // Display current version of the chessboard.
                    chessBoard.printChessBoard();
                    System.out.println("\nAll the moves made: " + numberOfMovesMade + "\n");
                }
            }
        }

        System.out.println("\nNumber of successful tours: " + countSuccessfulTours);
    }
}