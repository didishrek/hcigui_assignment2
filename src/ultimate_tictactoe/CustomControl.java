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


    public CustomControl() {
        setSkin(new CustomControlSkin(this));
        xoultimateboard = new XOUltimateBoard();
        getChildren().add(xoultimateboard);
        // add a mouse clicked listener that will try to place a piece
//        setOnMouseClicked(new EventHandler<MouseEvent>() {
//            // overridden handle method
//            @Override
//            public void handle(MouseEvent event) { xoultimateboard.placePiece(event.getX(), event.getY());
//
//            }
//        });
        setOnKeyPressed(new EventHandler<KeyEvent>() {
            // overridden handle method
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
