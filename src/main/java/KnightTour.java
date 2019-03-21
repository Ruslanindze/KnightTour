import java.util.ArrayList;

public class KnightTour {

    public static void main(String[] args){
        int sizeBoard = 8;
        int maxNumbMoves = sizeBoard * sizeBoard;
        int countSuccessfulTours = 0;
        int numbMove = 0;
        // -------------------------------------

        for(int i = 0 ; i < sizeBoard; i++){
            for(int j = 0; j < sizeBoard; j++) {
                ChessBoard chessBoard = new ChessBoard();
                Knight knightA = new Knight(chessBoard, i, j);

                // Run the knight tour and monitor count moves
                numbMove = knightA.startTour();
                // Display statistics
                // chessBoard.printChessBoard(numbMove);

                if (numbMove == maxNumbMoves){
                    ++countSuccessfulTours;
                    knightA.setNumbMove(0);
                }
            }
        }

        System.out.println("Number of successful tours: " + countSuccessfulTours);
    }
}