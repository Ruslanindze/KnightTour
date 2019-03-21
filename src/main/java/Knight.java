import java.security.SecureRandom;
import java.util.ArrayList;

public class Knight {
    private ChessBoard chessBoard;
    private int sizeBoard;
    private int[][] chess_board;
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

    public Knight() {
        throw new UnsupportedOperationException("Can't create Knight without chess board.");
    }

    public Knight(ChessBoard chessBoard){
        this(chessBoard, 0, 0);
    }

    public Knight(ChessBoard chessBoard, int y, int x) {
        this.chessBoard = chessBoard;
        chess_board = chessBoard.getChessboard();
        sizeBoard = chess_board.length;

        setLocation(y, x);
    }

    // ----------------------------------------------------

    public int getNumbMove() {
        return numbMove;
    }

    public void setLocation(int currRow, int currCol) {
        // If is incorrect data then to set location by default (3, 3)
        if ((currRow < 0) || (currRow >= sizeBoard))
            currRow = sizeBoard/2;
        if ((currCol < 0) || (currCol >= sizeBoard))
            currCol = sizeBoard/2;

        this.currRow = currRow;
        this.currColumn = currCol;

        ++numbMove;
        reduceAcessibilityNumb();
    }
    //-------------------------------------------------------------
    // This function searches possible moves
    public ArrayList<Integer> getPossibleMoves(ChessBoard chessBoard){
        int pRow = currRow;
        int pCol = currColumn;
        ArrayList<Integer> possibleMoves = new ArrayList<Integer>();

        for(int i = 0; i < availableMoves.length; i++){
            pCol += availableMoves[i][0];
            pRow += availableMoves[i][1];

            if(((pRow > -1)&&(pCol > -1)) && ((pRow < sizeBoard)&&(pCol < sizeBoard))){
                if (chess_board[pRow][pCol] > 0){
                    possibleMoves.add(i);
                }
            }

            pCol = currColumn;
            pRow = currRow;
        }

        return possibleMoves;
    }

    public int randomChooseMove(ArrayList<Integer> possibleMoves) {
        int size = possibleMoves.size();
        int idx = -1; // If the negative outcome then return -1

        if (size > 0) idx = KnightRand.nextInt(size);

        return idx;
    }

    public int chooseLeastMove(ChessBoard chessBoard, ArrayList<Integer> possibleMoves){
        int chooseMoveNumb = -1;
        int leastSquare = availableMoves.length;
        int currMoveNumb;
        int tempIdx;
        int pROw = currRow;
        int pCol = currColumn;
        int [][] chessboard = chessBoard.getChessboard();

        for(int i = 0; i < possibleMoves.size(); i++){
            currMoveNumb = possibleMoves.get(i);
            pROw += availableMoves[currMoveNumb][1];
            pCol += availableMoves[currMoveNumb][0];

            if (chessboard[pROw][pCol] < leastSquare){
                leastSquare = chessboard[pROw][pCol];
            }

            pROw = currRow;
            pCol = currColumn;
        }

        ArrayList<Integer> listCorrectChoices = new ArrayList<Integer>();

        for(int i = 0; i < possibleMoves.size(); i++){
            currMoveNumb = possibleMoves.get(i);
            pROw += availableMoves[currMoveNumb][1];
            pCol += availableMoves[currMoveNumb][0];

            if (chessboard[pROw][pCol] == leastSquare){
                listCorrectChoices.add(currMoveNumb);
            }

            pROw = currRow;
            pCol = currColumn;
        }


        tempIdx = randomChooseMove(listCorrectChoices);

        if (tempIdx > -1){
            chooseMoveNumb = listCorrectChoices.get(tempIdx);
        }

        return chooseMoveNumb;
    }


    public void reduceAcessibilityNumb(){
        int tempRow = currRow;
        int tempCol = currColumn;
        int currentMoveNumb;

        chess_board[currRow][currColumn] = 0;

        ArrayList<Integer> possibleMoves = getPossibleMoves(chessBoard);

        for(int i = 0; i < possibleMoves.size(); i++){
            currentMoveNumb = possibleMoves.get(i);
            tempRow += availableMoves[currentMoveNumb][1];
            tempCol += availableMoves[currentMoveNumb][0];

            chess_board[tempRow][tempCol] -= 1;

            tempRow = currRow;
            tempCol = currColumn;
        }

        chessBoard.setChessboard(chess_board);
    }

    // This function marks square where will the horse be and move and reduce the accessibility numbers
    public void toMove(int moveNumber) {
        currRow += availableMoves[moveNumber][1];
        currColumn += availableMoves[moveNumber][0];

        reduceAcessibilityNumb();
        ++numbMove;
    }

    public int startTour(){
        boolean possMove = true; // By default move is possible
        int randomIdx;
        int moveNumber;

        while(possMove){
            possMove = false;

            ArrayList<Integer> listPosMov = getPossibleMoves(chessBoard);
            moveNumber = chooseLeastMove(chessBoard, listPosMov);

            if (moveNumber > -1){
                toMove(moveNumber);

                if (numbMove == 2){
                    System.out.println("Tut: " + currRow + " " + currColumn);
                    System.out.println("Tut2: " + getPossibleMoves(chessBoard));
                    break;
                }

                possMove = true;
            }
        }

        return getNumbMove();
    }
}