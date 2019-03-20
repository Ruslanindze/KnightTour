import java.util.ArrayList;
import java.util.Arrays;

public class KnightTour {
    public static int startTour(Knight knightAlpha, ChessBoard chessBoard){
        boolean possMove = true; // By default move is possible
        int randomIdx;
        int moveNumber;

        while(possMove){
            possMove = false;

            ArrayList<Integer> listPosMov = knightAlpha.PossibleMoves(chessBoard);

            randomIdx = knightAlpha.knightChooseMove(listPosMov);

            if (randomIdx > -1){
                moveNumber = listPosMov.get(randomIdx);
                chessBoard = knightAlpha.toMove(chessBoard, moveNumber);
                possMove = true;
            }
        }

        return knightAlpha.getNumbMove();
    }


    public static void main(String[] args){
        Knight kAlpha = new Knight();
        ChessBoard chessBoard = new ChessBoard();
        // -------------------------------------
        // Setting the location by default
        kAlpha.knightChooseLocation();
        // Run the knight tour and monitor count moves
        int numbMove = startTour(kAlpha, chessBoard);
        // Display statistics
        chessBoard.printChessBoard(numbMove);
    }
}
