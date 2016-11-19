package ultimate_tictactoe;

import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * Created by 42900 on 12/11/2016.
 */
public class CustomControl extends Control {
    private XOUltimateBoard xoultimateboard;
    private GameLogic gameLogic;


    public CustomControl() {
        setSkin(new CustomControlSkin(this));
        gameLogic = new GameLogic();
        xoultimateboard = new XOUltimateBoard(gameLogic);
        getChildren().add(xoultimateboard);
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) { xoultimateboard.placePiece(event.getX(), event.getY());

            }
        });
        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.SPACE)
                    xoultimateboard.resetGame();
            }
        });
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
        xoultimateboard.resize(width, height);
    }

}
