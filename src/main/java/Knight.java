import java.security.SecureRandom;
import java.util.ArrayList;

public class Knight {
    private final int MARKED_SQUARE = -1;
    private final int ERROR_RETURN = -1;
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
    private SecureRandom random = new SecureRandom();
    // The variable stores number of moves made
    private int numberOfMovesMade = 0;

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
     * @param axisY      - coordinate by axis Y
     * @param axisX      - coordinate by axis X
     */
    public Knight(ChessBoard chessBoard, int axisY, int axisX) {
        this.chessBoard = chessBoard;

        // If is incorrect data then to set location by default (center, center)
        if ((axisY < 0) || (axisY >= chessBoard.getHeightTheBoard()) ||
                (axisX < 0) || (axisX >= chessBoard.getWidthTheBoard())) {
            locationByAxisY = chessBoard.getHeightTheBoard() / 2;
            locationByAxisX = chessBoard.getWidthTheBoard() / 2;
        } else {
            locationByAxisY = axisY;
            locationByAxisX = axisX;
        }

        ++numberOfMovesMade;
        chessBoard.setValueSquare(locationByAxisY, locationByAxisX, MARKED_SQUARE);

        reduceAccessibilityNumb();
    }

    /**
     * This is a getter for the property locationByAxisY.
     *
     * @return Coordinate location the Knight by axis "Y"
     */
    public int getLocationByAxisY() {
        return locationByAxisY;
    }

    /**
     * This is a getter for the property locationByAxisX.
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
        this.numberOfMovesMade = numbMove;
    }

    /**
     * This is a getter for the property numberOfMovesMade.
     *
     * @return Integer number of moves.
     */
    public int getNumbMove() {
        return numberOfMovesMade;
    }

    /**
     * The method returns possibles of moves for a Knight from current location.
     *
     * @return Integer list of possible moves
     */
    private ArrayList<Integer> getPossibleMoves() {
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
     * The method returns possibles of moves for a Knight from current location (overloaded version).
     *
     * @param axisY - coordinate by axis Y
     * @param axisX - coordinate by axis X
     * @return
     */
    private ArrayList<Integer> getPossibleMoves(int axisY, int axisX) {
        ArrayList<Integer> possibleMoves = new ArrayList<Integer>();

        for (int i = 0; i < availableMoves.length; i++) {
            int tempAxisY = axisY + availableMoves[i][1];
            int tempAxisX = axisX + availableMoves[i][0];

            if (((tempAxisY > -1) && (tempAxisY < chessBoard.getHeightTheBoard())) &&
                    ((tempAxisX > -1) && (tempAxisX < chessBoard.getWidthTheBoard())) &&
                    (chessBoard.getValueSquare(tempAxisY, tempAxisX) > -1)) {
                possibleMoves.add(i);
            }
        }

        return possibleMoves;
    }

    /**
     * The method chooses most preferred the move.
     *
     * @param possibleMoves - possible of moves for Knight
     * @return Preferred move number
     */
    private int choosePreferableMove(ArrayList<Integer> possibleMoves) {
        int chooseMoveNumb = ERROR_RETURN;
        int valuePreferredOfSquare = availableMoves.length;

        for (int currMoveNumb : possibleMoves) {
            int tempAxisY = locationByAxisY + availableMoves[currMoveNumb][1];
            int tempAxisX = locationByAxisX + availableMoves[currMoveNumb][0];

            valuePreferredOfSquare = Math.min(
                    valuePreferredOfSquare,
                    chessBoard.getValueSquare(tempAxisY, tempAxisX));
        }

        ArrayList<Integer> listCorrectChoices = new ArrayList<Integer>();

        for (int currMoveNumb : possibleMoves) {
            int tempAxisY = locationByAxisY + availableMoves[currMoveNumb][1];
            int tempAxisX = locationByAxisX + availableMoves[currMoveNumb][0];

            if (chessBoard.getValueSquare(tempAxisY, tempAxisX) == valuePreferredOfSquare) {
                listCorrectChoices.add(currMoveNumb);
            }
        }

        valuePreferredOfSquare = availableMoves.length;
        if (!listCorrectChoices.isEmpty()) {
            for (int currentMoveNumb : listCorrectChoices) {
                int currentAxisY = locationByAxisY + availableMoves[currentMoveNumb][1];
                int currentAxisX = locationByAxisX + availableMoves[currentMoveNumb][0];
                ArrayList<Integer> futurePossibleMoves = getPossibleMoves(currentAxisY, currentAxisX);

                if (!futurePossibleMoves.isEmpty()) {
                    for (int futureMoveNumb : futurePossibleMoves) {
                        int futureAxisY = currentAxisY + availableMoves[futureMoveNumb][1];
                        int futureAxisX = currentAxisX + availableMoves[futureMoveNumb][0];

                        if (chessBoard.getValueSquare(futureAxisY, futureAxisX) <= valuePreferredOfSquare) {
                            valuePreferredOfSquare = chessBoard.getValueSquare(futureAxisY, futureAxisX);
                            chooseMoveNumb = currentMoveNumb;
                        }
                    }
                } else {
                    chooseMoveNumb = currentMoveNumb;
                }
            }
        }

        return chooseMoveNumb;
    }

    /**
     * The method reduces the price of squares depending on the knight's move
     */
    private void reduceAccessibilityNumb() {
        ArrayList<Integer> possibleMoves = getPossibleMoves();

        for (int currentMoveNumb : possibleMoves) {
            int tempAxisY = locationByAxisY + availableMoves[currentMoveNumb][1];
            int tempAxisX = locationByAxisX + availableMoves[currentMoveNumb][0];

            chessBoard.setValueSquare(tempAxisY, tempAxisX,
                    chessBoard.getValueSquare(tempAxisY, tempAxisX) - 1);
        }
    }

    /**
     * The method moves the horse given the rules
     *
     * @param moveNumber - for to select the direction of travel
     */
    private void toMove(int moveNumber) {
        locationByAxisY += availableMoves[moveNumber][1];
        locationByAxisX += availableMoves[moveNumber][0];

        ++numberOfMovesMade;
        chessBoard.setValueSquare(locationByAxisY, locationByAxisX, MARKED_SQUARE);

        reduceAccessibilityNumb();
    }

    /**
     * A Knight begins own tour on a chessboard and returns number of moves made
     *
     * @return int getNumbMove
     */
    public int startTour() {
        while (!getPossibleMoves().isEmpty()) {
            ArrayList<Integer> listPosMov = getPossibleMoves();
            int moveNumber = choosePreferableMove(listPosMov);

            if (moveNumber != ERROR_RETURN) {
                toMove(moveNumber);
            }
        }
        return getNumbMove();
    }
}