package Player;

import javafx.application.Application;
import javafx.stage.Stage;

public class RealPlayer extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        PlayerMenu playerMenu = new PlayerMenu();
        playerMenu.playerWindowMenu(primaryStage);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
