package ultimate_tictactoe;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;

/**
 * Created by Adrien Schricke on 01/11/2016.
 */
public class XOUltimateBoard extends Pane {
    // private fields of the class
    private GameLogic gameLogic;
    private int[][] bigBoard;
    private XOBoard[][] ultimateBoard;
    private double cell_width, cell_height; // width and height of a cell
    private int current_player; // who is the current player
    // constants for the class
    private final int EMPTY = 0;
    private final int XPIECE = 1;
    private final int OPIECE = 2;

    public XOUltimateBoard(GameLogic gl) {
        gameLogic = gl;
        bigBoard = new int[3][3];
        ultimateBoard = new XOBoard[3][3];
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++) {
                bigBoard[i][j] = EMPTY;
                ultimateBoard[i][j] = new XOBoard(this, gameLogic);
                getChildren().add(ultimateBoard[i][j]);
            }
        current_player = XPIECE;
    }
    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
        cell_width = width / 3.0;
        cell_height = height / 3.0;

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                ultimateBoard[i][j].relocate(i * cell_width, j * cell_height);
                ultimateBoard[i][j].resize(cell_width, cell_height);
            }
        }
    }
    // public method for resetting the game
    public void resetGame() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                bigBoard[i][j] = 0;
                getChildren().remove(ultimateBoard[i][j]);
                ultimateBoard[i][j] = null;
            }
        }
    }

//
//    // public method that tries to place a piece
    public void placePiece(final double x, final double y) {
        // translate the x, y coordinates into cell indexes
        int indexx = (int) (x / cell_width);
        int indexy = (int) (y / cell_height);

        ultimateBoard[indexx][indexy].resize(cell_width, cell_height);
        ultimateBoard[indexx][indexy].relocate(indexx * cell_width, indexy *
                cell_height);
        ultimateBoard[indexx][indexy].placePiece(x, y);
    }

    public int getCurrent_player() {
        return current_player;
    }

    public void setCurrent_player(int current_player) {
        this.current_player = current_player;
    }
}