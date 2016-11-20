package ultimate_tictactoe;

import javafx.scene.layout.Pane;

/**
 * Created by Adrien Schricke on 01/11/2016.
 */
public class XOUltimateBoard extends Pane {
    private GameLogic gameLogic;
    private XOBoard[][] ultimateBoard;
    private double cell_width, cell_height;
    private int current_player;

    public XOUltimateBoard(GameLogic gl) {
        gameLogic = gl;
        ultimateBoard = new XOBoard[3][3];
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++) {
                ultimateBoard[i][j] = new XOBoard(this, gameLogic);
                getChildren().add(ultimateBoard[i][j]);
            }
        current_player = 1;
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

    public void resetGame() {
        current_player = 1;
        gameLogic.resetGame();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                ultimateBoard[i][j].resetGame();
            }
        }
    }

    public void placePiece(final double x, final double y) {
        int indexx = (int) (x / cell_width);
        int indexy = (int) (y / cell_height);


        if (gameLogic.isAuthorizedBoard(this.ultimateBoard, indexx, indexy)){
            ultimateBoard[indexx][indexy].placePiece(x, y);
            gameLogic.countPoint(ultimateBoard);
        }
    }

    public int getCurrent_player() {
        return current_player;
    }

    public void setCurrent_player(int current_player) {
        this.current_player = current_player;
    }

}