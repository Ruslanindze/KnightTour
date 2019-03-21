import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ChessBoard {
    private int sizeBoard = 8;
    private int[][] chessboard = {
            {2, 3, 4, 4, 4, 4, 3, 2},
            {3, 4, 6, 6, 6, 6, 4, 3},
            {4, 6, 8, 8, 8, 8, 6, 4},
            {4, 6, 8, 8, 8, 8, 6, 4},
            {4, 6, 8, 8, 8, 8, 6, 4},
            {4, 6, 8, 8, 8, 8, 6, 4},
            {3, 4, 6, 6, 6, 6, 4, 3},
            {2, 3, 4, 4, 4, 4, 3, 2}};
    //---------------------------------

    /**
     * The getter returns the size of a chessboard.
     * @return int sizeBoard
     */
    public int getSizeBoard() {
        return sizeBoard;
    }

    /**
     * The getter returns the chessboard with the cost of each square.
     * @return int[][] chessboard
     */
    public int[][] getChessboard() {
        return chessboard;
    }

    /**
     * The setter sets chessboard.
     * @param chessboard - chessboard
     */
    public void setChessboard(int[][] chessboard) {
        this.chessboard = chessboard;
    }

    //---------------------------------

    /**
     * The method displays the current chessboard with a price for each square.
     * @param numbMove - the number of moves made by the knight
     */
    public void printChessBoard(int numbMove){
        String sOut = "";
        System.out.println("Here is a chessboard marked the moves of a chess figure (number - number of possible moves)");
        for(int i = 0; i < sizeBoard; i++){
            for(int j = 0 ; j < sizeBoard; j++){
                if (chessboard[i][j] < 10)
                    sOut += chessboard[i][j] + "  |  ";
                else
                    sOut += chessboard[i][j] + " |  ";
            }

            System.out.print(sOut + '\n');

            for(int z = 0; z < sOut.length()/2-1; z++)
                System.out.print("--");
            System.out.println();

            sOut = "";
        }
        System.out.println("All the moves made: " + numbMove);
    }
}