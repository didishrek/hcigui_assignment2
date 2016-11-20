package ultimate_tictactoe;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Created by Adrien Schricke on 01/11/2016.
 */
public class XOUltimateBoard extends Pane {
    private GameLogic gameLogic;
    private XOBoard[][] ultimateBoard;
    private double cell_width, cell_height;
    private int current_player;
    private Label winner_message;

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
            winner = 0;
            if (winner != 0){
                winner_message = new Label("Player " + winner + " wins !");
                winner_message.setAlignment(Pos.CENTER);
                System.out.println("Player " + winner + " wins !");
                winner_message.setTextFill(Color.WHITE);
                getChildren().add(winner_message);
            } else{
                winner_message = new Label("You are loosers !!!");
                System.out.println("You are loosers !!!");
                winner_message.setAlignment(Pos.CENTER);
                winner_message.setTextFill(Color.WHITE);
                getChildren().add(winner_message);
            }
        }
    }

    public int getCurrent_player() {
        return current_player;
    }

    public void setCurrent_player(int current_player) {
        this.current_player = current_player;
    }

}