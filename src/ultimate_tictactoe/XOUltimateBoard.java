package ultimate_tictactoe;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.transform.Translate;

/**
 * Created by Adrien Schricke on 01/11/2016.
 */
public class XOUltimateBoard extends Pane {
    private GameLogic gameLogic;
    private XOBoard[][] ultimateBoard;
    private double cell_width, cell_height;
    private int current_player;
    private Label winner_message;

    private Line h1, h2, v1, v2;
    private Translate ch_one, ch_two, cw_one, cw_two;

    public XOUltimateBoard(GameLogic gl) {
        gameLogic = gl;
        ultimateBoard = new XOBoard[3][3];
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++) {
                ultimateBoard[i][j] = new XOBoard(this, gameLogic);
                getChildren().add(ultimateBoard[i][j]);
            }
        current_player = 1;
        h1 = new Line(); h2 = new Line();
        v1 = new Line(); v2 = new Line();
        h1.setStroke(Color.BLUE); h2.setStroke(Color.BLUE);
        v1.setStroke(Color.BLUE); v2.setStroke(Color.BLUE);
        h1.setStartX(0); h1.setStartY(0); h1.setEndY(0);
        h2.setStartX(0); h2.setStartY(0); h2.setEndY(0);
        v1.setStartX(0); v1.setStartY(0); v1.setEndX(0);
        v2.setStartX(0); v2.setStartY(0); v2.setEndX(0);
        ch_one = new Translate(0, 0);
        ch_two = new Translate(0, 0);
        h1.getTransforms().add(ch_one);
        h2.getTransforms().add(ch_two);
        cw_one = new Translate(0, 0);
        cw_two = new Translate(0, 0);
        v1.getTransforms().add(cw_one);
        v2.getTransforms().add(cw_two);
        getChildren().addAll(h1, h2, v1, v2);
    }
    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
        cell_width = width / 3.0;
        cell_height = height / 3.0;

        ch_one.setY(cell_height); ch_two.setY(2 * cell_height);
        h1.setEndX(width); h2.setEndX(width);
        cw_one.setX(cell_width); cw_two.setX(2 * cell_width);
        v1.setEndY(height); v2.setEndY(height);

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
        getChildren().remove(winner_message);
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

        if (gameLogic.isFinished()){
            int winner = gameLogic.getUltimateWinner();
            if (winner != 0){
                displayMessage("Player " + winner + " wins !");
            } else{
                displayMessage("You are losers!");
            }
            return;
        }
    }

    public int getCurrent_player() {
        return current_player;
    }

    public void setCurrent_player(int current_player) {
        this.current_player = current_player;
    }

    public void displayMessage(String msg){
        winner_message = new Label(msg);
        winner_message.setTextFill(Color.WHITE);
        getChildren().add(winner_message);
    }
}