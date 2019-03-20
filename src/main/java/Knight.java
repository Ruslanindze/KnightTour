import java.security.SecureRandom;
import java.util.ArrayList;

public class Knight {
    // The knight knows size of the chessboard
    private int sizeBoard = 8;
    // A variable availableMoves stores variants moving ([x][y])
    private int[][] availableMoves = {
            {2, -1},
            {1, -2},
            {-1, -2},
            {-2, -1},
            {-2, 1},
            {-1, 2},
            {1, 2},
            {2, 1}
    };
    // These variables store location the knight
    private int currRow, currColumn;
    // It's private a object-random
    private SecureRandom KnightRand = new SecureRandom();
    // The variable stores number of moves made
    private int numbMove = 0;
    // ----------------------------------------------------

    public int[][] getAvailableMoves() {
        return availableMoves;
    }

    public int getCurrRow() {
        return currRow;
    }

    public int getCurrColumn() {
        return currColumn;
    }

    public int getNumbMove() {
        return numbMove;
    }
    //-------------------------------------------------------------

    public void setLocation(int currRow, int currCol) {
        // If is incorrect data then to set location by default (3, 3)
        if ((currRow < 0) || (currRow >= sizeBoard))
            currRow = 3;
        if ((currCol < 0) || (currCol >= sizeBoard))
            currCol = 3;

        this.currRow = currRow;
        this.currColumn = currCol;
    }

    // This function marks square where will the horse be and move
    public ChessBoard toMove(ChessBoard chessBoard, int moveNumber) {
        currRow += availableMoves[moveNumber][1];
        currColumn += availableMoves[moveNumber][0];

        int[][] currChessBoard = chessBoard.getChessboard();
        currChessBoard[currRow][currColumn] = ++numbMove;
        chessBoard.setChessboard(currChessBoard);

        return chessBoard;
    }

    public void knightChooseLocation() {
        currColumn = KnightRand.nextInt(sizeBoard);
        currRow = KnightRand.nextInt(sizeBoard);
    }

    public int knightChooseMove(ArrayList<Integer> possibleMoves) {
        int size = possibleMoves.size();
        int idx = -1; // If the negative outcome then return -1

        if (size > 0) idx = KnightRand.nextInt(size);

        return idx;
    }

    // This function searches possible moves
    public ArrayList<Integer> PossibleMoves(ChessBoard chessBoard){
        int pRow = currRow;
        int pCol = currColumn;
        ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
        int[][] chessboard = chessBoard.getChessboard();

        for(int i = 0; i < availableMoves.length; i++){
            pCol += availableMoves[i][0];
            pRow += availableMoves[i][1];

            if(((pRow > -1)&&(pCol > -1)) && ((pRow < sizeBoard)&&(pCol < sizeBoard))){
                if (chessboard[pRow][pCol] == 0){
                    possibleMoves.add(i);
                }
            }

            pCol = currColumn;
            pRow = currRow;
        }

        return possibleMoves;
    }
}
