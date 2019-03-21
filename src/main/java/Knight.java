import java.security.SecureRandom;
import java.util.ArrayList;

public class Knight {
    private ChessBoard chessBoard;
    private int[][] chess_board;
    private int sizeBoard;
    private int maxNumbMoves;
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

    /**
     * Constructor without any parameters unsupported on this version.
     */
    public Knight() {
        throw new UnsupportedOperationException("Can't create Knight without chess board.");
    }

    /**
     * Constructor with only one parameter - a chessboard and location by default (0, 0).
     * @param chessBoard - the chessboard where knight is
     */
    public Knight(ChessBoard chessBoard){
        this(chessBoard, 0, 0);
    }

    /**
     * Constructor wich initializes of chessboard and location.
     * @param chessBoard - the chessboard where knight is
     * @param y - current row
     * @param x - current column
     */
    public Knight(ChessBoard chessBoard, int y, int x) {
        this.chessBoard = chessBoard;
        chess_board = chessBoard.getChessboard();
        sizeBoard = chess_board.length;
        maxNumbMoves = sizeBoard * sizeBoard;
        setLocation(y, x);
    }

    // ----------------------------------------------------

    /**
     * The getter returns the size of a chessboard.
     * @return int sizeBoard
     */
    public int getSizeBoard() {
        return sizeBoard;
    }

    /**
     * The setter sets number moves made by the Knight.
     * @param numbMove - number moves
     */
    public void setNumbMove(int numbMove) {
        this.numbMove = numbMove;
    }

    /**
     * The getter returns the number of moves.
     * @return int numbMove
     */
    public int getNumbMove() {
        return numbMove;
    }


    /**
     * The setter sets location of a Knight.
     * @param currRow - current row
     * @param currCol - curren column
     */
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

    /**
     * The method returns possibles of moves for a Knight from current location.
     * @return ArrayList<Integer> getPossibleMoves
     */
    public ArrayList<Integer> getPossibleMoves(){
        int tempRow = currRow;
        int tempCol = currColumn;
        ArrayList<Integer> possibleMoves = new ArrayList<Integer>();

        for(int i = 0; i < availableMoves.length; i++){
            tempCol += availableMoves[i][0];
            tempRow += availableMoves[i][1];

            if(((tempRow > -1)&&(tempCol > -1)) && ((tempRow < sizeBoard)&&(tempCol < sizeBoard))){
                if (chess_board[tempRow][tempCol] > -1){
                    possibleMoves.add(i);
                }
            }

            tempCol = currColumn;
            tempRow = currRow;
        }

        return possibleMoves;
    }

    /**
     * The method random chooses and returns moves from possible of moves.
     * @param possibleMoves - possible of moves for Knight
     * @return int idx
     */
    public int randomChooseMove(ArrayList<Integer> possibleMoves) {
        int size = possibleMoves.size();
        int idx = -1; // If the negative outcome then return -1

        if (size > 0) idx = KnightRand.nextInt(size);

        return idx;
    }

    /**
     * The method chooses and returns move with least price.
     * @param possibleMoves - possible of moves for Knight
     * @return int chooseMoveNumb
     */
    public int chooseLeastMove(ArrayList<Integer> possibleMoves){
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

    /**
     * The method reduces the price of squares depending on the knight's move
     */
    public void reduceAcessibilityNumb(){
        int tempRow = currRow;
        int tempCol = currColumn;
        int currentMoveNumb;

        chess_board[currRow][currColumn] = -1;

        ArrayList<Integer> possibleMoves = getPossibleMoves();

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

    /**
     * The method moves the horse given the rules
     * @param moveNumber - for to select the direction of travel
     */
    public void toMove(int moveNumber) {
        currRow += availableMoves[moveNumber][1];
        currColumn += availableMoves[moveNumber][0];

        ++numbMove;
        reduceAcessibilityNumb();
    }

    /**
     * A Knight begins own tour on a chessboard and returns number of moves made
     * @return int getNumbMove
     */
    public int startTour(){
        boolean possMove = true; // By default move is possible
        int randomIdx;
        int moveNumber;

        while(possMove){
            possMove = false;

            ArrayList<Integer> listPosMov = getPossibleMoves();
            moveNumber = chooseLeastMove(listPosMov);

            if (moveNumber > -1){
                toMove(moveNumber);

                if ((numbMove == 64)){
                    break;
                }

                possMove = true;
            }
        }

        return getNumbMove();
    }
}