import java.security.SecureRandom;
import java.util.ArrayList;

public class Knight {
    private ChessBoard chessBoard;
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
    private int locationByAxisY;
    private int locationByAxisX;

    // It's private a object-random
    private SecureRandom knightRand = new SecureRandom();
    // The variable stores number of moves made
    private int numbMove = 0;


    /**
     * Constructor without any parameters unsupported on this version.
     */
    public Knight() {
        throw new UnsupportedOperationException("Can't create Knight without chess board.");
    }

    /**
     * Constructor with only one parameter - a chessboard and location by default (0, 0).
     *
     * @param chessBoard - the chessboard where knight is
     */
    public Knight(ChessBoard chessBoard) {
        this(chessBoard, 0, 0);
    }

    /**
     * Constructor with initializes of chessboard and location.
     *
     * @param chessBoard - the chessboard where knight is
     * @param y          - coordinate by axis Y
     * @param x          - coordinate by axis X
     */
    public Knight(ChessBoard chessBoard, int y, int x) {
        this.chessBoard = chessBoard;
        setLocation(y, x);
    }

    /**
     * This getter is.
     *
     * @return Coordinate location the Knight by axis "Y"
     */
    public int getLocationByAxisY() {
        return locationByAxisY;
    }

    /**
     * This getter is.
     *
     * @return Coordinate location the Knight by axis "X"
     */
    public int getLocationByAxisX() {
        return locationByAxisX;
    }

    /**
     * The setter sets number moves made by the Knight.
     *
     * @param numbMove - number move
     */
    public void setNumbMove(int numbMove) {
        this.numbMove = numbMove;
    }

    /**
     * This getter is.
     *
     * @return Integer number of moves.
     */
    public int getNumbMove() {
        return numbMove;
    }


    /**
     * The setter sets location of a Knight.
     *
     * @param locationByAxisY - the location by axis "Y"
     * @param locationByAxisX - the location by axis "X"
     */
    public void setLocation(int locationByAxisY, int locationByAxisX) {
        // If is incorrect data then to set location by default (3, 3)
        if ((locationByAxisY < 0) || (locationByAxisY >= chessBoard.getHeightTheBoard()))
            locationByAxisY = chessBoard.getHeightTheBoard() / 2;
        if ((locationByAxisX < 0) || (locationByAxisX >= chessBoard.getWidthTheBoard()))
            locationByAxisX = chessBoard.getWidthTheBoard() / 2;

        this.locationByAxisY = locationByAxisY;
        this.locationByAxisX = locationByAxisX;

        ++numbMove;
        reduceAcessibilityNumb();
    }


    /**
     * The method returns possibles of moves for a Knight from current location.
     *
     * @return Integer list of possible moves
     */
    public ArrayList<Integer> getPossibleMoves() {
        ArrayList<Integer> possibleMoves = new ArrayList<Integer>();

        for (int i = 0; i < availableMoves.length; i++) {
            int tempAxisY = locationByAxisY + availableMoves[i][1];
            int tempAxisX = locationByAxisX + availableMoves[i][0];

            if (((tempAxisY > -1) && (tempAxisY < chessBoard.getHeightTheBoard())) &&
                    ((tempAxisX > -1) && (tempAxisX < chessBoard.getWidthTheBoard())) &&
                    (chessBoard.getValueSquare(tempAxisY, tempAxisX) > -1)) {
                possibleMoves.add(i);
            }
        }

        return possibleMoves;
    }

    /**
     * The method random chooses and returns moves from possible of moves.
     *
     * @param possibleMoves - possible of moves for Knight
     * @return Integer random number
     */
    public int randomChooseMove(ArrayList<Integer> possibleMoves) {
        if (!possibleMoves.isEmpty()) {
            return knightRand.nextInt(possibleMoves.size());
        }

        return -1;
    }

    /**
     * The method chooses most preferred the move.
     *
     * @param possibleMoves - possible of moves for Knight
     * @return Preferred move number
     */
    public int choosePreferrableMove(ArrayList<Integer> possibleMoves) {
        int chooseMoveNumb = -1;
        int valuePrefferedofSquare = availableMoves.length;

        for (int i = 0; i < possibleMoves.size(); i++) {
            int currMoveNumb = possibleMoves.get(i);
            int tempAxisY = locationByAxisY + availableMoves[currMoveNumb][1];
            int tempAxisX = locationByAxisX + availableMoves[currMoveNumb][0];

            if (chessBoard.getValueSquare(tempAxisY, tempAxisX) < valuePrefferedofSquare) {
                valuePrefferedofSquare = chessBoard.getValueSquare(tempAxisY, tempAxisX);
            }
        }

        ArrayList<Integer> listCorrectChoices = new ArrayList<Integer>();

        for (int i = 0; i < possibleMoves.size(); i++) {
            int currMoveNumb = possibleMoves.get(i);
            int tempAxisY = locationByAxisY + availableMoves[currMoveNumb][1];
            int tempAxisX = locationByAxisX + availableMoves[currMoveNumb][0];

            if (chessBoard.getValueSquare(tempAxisY, tempAxisX) == valuePrefferedofSquare) {
                listCorrectChoices.add(currMoveNumb);
            }
        }

        int randomIndex = randomChooseMove(listCorrectChoices);
        if (randomIndex > -1) {
            chooseMoveNumb = listCorrectChoices.get(randomIndex);
        }

        return chooseMoveNumb;
    }

    /**
     * The method reduces the price of squares depending on the knight's move
     */
    public void reduceAcessibilityNumb() {
        chessBoard.setValueSquare(locationByAxisY, locationByAxisX, -1);
        ArrayList<Integer> possibleMoves = getPossibleMoves();

        for (int i = 0; i < possibleMoves.size(); i++) {
            int currentMoveNumb = possibleMoves.get(i);
            int tempAxisY = locationByAxisY + availableMoves[currentMoveNumb][1];
            int tempAxisX = locationByAxisX + availableMoves[currentMoveNumb][0];

            chessBoard.setValueSquare(locationByAxisY, locationByAxisX,
                    chessBoard.getValueSquare(tempAxisY, tempAxisX) - 1);
        }
    }

    /**
     * The method moves the horse given the rules
     *
     * @param moveNumber - for to select the direction of travel
     */
    public void toMove(int moveNumber) {
        locationByAxisY += availableMoves[moveNumber][1];
        locationByAxisX += availableMoves[moveNumber][0];

        ++numbMove;
        reduceAcessibilityNumb();
    }

    /**
     * A Knight begins own tour on a chessboard and returns number of moves made
     *
     * @return int getNumbMove
     */
    public int startTour() {
        boolean possMove = true; // By default move is possible
        int maxNumberOfMoves = chessBoard.getMaxNumberOfMoves();
        int moveNumber;

        while (possMove) {
            possMove = false;

            ArrayList<Integer> listPosMov = getPossibleMoves();
            moveNumber = choosePreferrableMove(listPosMov);

            if (moveNumber > -1) {
                toMove(moveNumber);
                if ((numbMove == maxNumberOfMoves)) {
                    break;
                }
                possMove = true;
            }
        }

        return getNumbMove();
    }
}