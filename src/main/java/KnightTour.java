import java.util.ArrayList;

public class KnightTour {

    public static void main(String[] args){
        ChessBoard chessBoard = new ChessBoard();
        Knight knightA = new Knight(chessBoard, 0, 0);
        // -------------------------------------
        // Run the knight tour and monitor count moves
        int numbMove = knightA.startTour();
        // Display statistics
        chessBoard.printChessBoard(numbMove);
    }
}