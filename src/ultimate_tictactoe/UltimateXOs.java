package ultimate_tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by Adrien Schricke on 01/11/2016.
 */
public class UltimateXOs extends Application {
    private static String WINDOW_NAME = "TicTacToe Ultimate";
    private StackPane sp_mainlayout;
    private CustomControl cc_custom;

    public void init() {
        sp_mainlayout = new StackPane();
        cc_custom = new CustomControl();
        sp_mainlayout.getChildren().add(cc_custom);
    }

    public void start(Stage stage){
        stage.setTitle(WINDOW_NAME);
        stage.setScene(new Scene(sp_mainlayout, 400, 300));
        stage.show();

    }

    public void stop(){

    }

    public static void main(String[] args){
        launch(args);
    }
}
