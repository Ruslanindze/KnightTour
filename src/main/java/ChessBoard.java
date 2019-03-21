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

    public int getSizeBoard() {
        return sizeBoard;
    }

    public int[][] getChessboard() {
        return chessboard;
    }

    public void setChessboard(int[][] chessboard) {
        this.chessboard = chessboard;
    }

    //---------------------------------
    public void printChessBoard(int numbMove){
        String sOut = "";
        System.out.println("Here is a chessboard marked the moves of a chess figure (number - number of the move)");
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