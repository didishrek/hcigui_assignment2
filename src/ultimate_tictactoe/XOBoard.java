package ultimate_tictactoe;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;

/**
 * Created by Adrien Schricke on 01/11/2016.
 */
public class XOBoard extends Pane {
    private XOUltimateBoard xoUltimateBoard;
    private GameLogic gameLogic;
    private int[][] board;
    private XOPiece[][] renders;
    private Rectangle back;
    private Line h1, h2, v1, v2;
    private double cell_width, cell_height;
    private Translate ch_one, ch_two, cw_one, cw_two;
    private final int EMPTY = 0;
    private final int XPIECE = 1;
    private final int OPIECE = 2;
    private int winner;

    public XOBoard(XOUltimateBoard xoub, GameLogic gl) {
        gameLogic = gl;
        xoUltimateBoard = xoub;
        board = new int[3][3];
        renders = new XOPiece[3][3];
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
                renders[i][j] = null;
            }
        back = new Rectangle();
        back.setFill(Color.BLACK);
        h1 = new Line(); h2 = new Line();
        v1 = new Line(); v2 = new Line();
        h1.setStroke(Color.WHITE); h2.setStroke(Color.WHITE);
        v1.setStroke(Color.WHITE); v2.setStroke(Color.WHITE);
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
        getChildren().addAll(back, h1, h2, v1, v2);
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
        cell_width = width / 3.0;
        cell_height = height / 3.0;
        back.setWidth(width); back.setHeight(height);
        ch_one.setY(cell_height); ch_two.setY(2 * cell_height);
        h1.setEndX(width); h2.setEndX(width);
        cw_one.setX(cell_width); cw_two.setX(2 * cell_width);
        v1.setEndY(height); v2.setEndY(height);
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i][j] != 0) {
                    renders[i][j].relocate(i * cell_width, j * cell_height);
                    renders[i][j].resize(cell_width, cell_height);
                }
            }
        }
    }

    public void resetGame() {
        winner = 0;
        removeXO();
        back.setFill(Color.BLACK);
        h1.setStroke(Color.WHITE); h2.setStroke(Color.WHITE);
        v1.setStroke(Color.WHITE); v2.setStroke(Color.WHITE);
    }

    public void winnerscolor(){
        removeXO();
        Paint color;
        if (winner == 1)
            color = Color.RED;
        else if( winner == 2)
            color = Color.LIME;
        else
            color = Color.BLUE;
        back.setFill(color);
        h1.setStroke(color); h2.setStroke(color);
        v1.setStroke(color); v2.setStroke(color);
    }

    public void removeXO(){
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                board[i][j] = 0;
                getChildren().remove(renders[i][j]);
                renders[i][j] = null;
            }
        }
    }

    public void placePiece(final double x, final double y) {
        int indexx = ((int) (x / cell_width)) % 3;
        int indexy = ((int) (y / cell_height)) % 3;


        if (winner != 0){
            //Prevents play after winning
            return;
        }
        gameLogic.setLastXY(indexx, indexy);
        if(board[indexx][indexy] == EMPTY && xoUltimateBoard.getCurrent_player() == XPIECE) {
            placeThatPiece(indexx, indexy, XPIECE);
            xoUltimateBoard.setCurrent_player(OPIECE);
        }
        else if(board[indexx][indexy] == EMPTY && xoUltimateBoard.getCurrent_player() == OPIECE) {
            placeThatPiece(indexx, indexy, OPIECE);
            xoUltimateBoard.setCurrent_player(XPIECE);
        }
        winner = gameLogic.checkWinner(this.board);
        if (winner != 0) {
            gameLogic.setEverywhere(true);
            winnerscolor();
        }
    }

    private void placeThatPiece(int indexx, int indexy, int piece) {
        board[indexx][indexy] = piece;
        renders[indexx][indexy] = new XOPiece(piece);
        renders[indexx][indexy].resize(cell_width, cell_height);
        renders[indexx][indexy].relocate(indexx * cell_width, indexy * cell_height);
        getChildren().add(renders[indexx][indexy]);
    }

    public int getWinner() {
        return winner;
    }

}
