package Player;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ExceptionProcessing extends BorderPane {

private Button button;

public ExceptionProcessing(String TextForErrorMessage) {
    this.button = new Button (TextForErrorMessage);
}

public ExceptionProcessing() {
    this.button = new Button ("Похоже, нет нужного файла!");
}

    public void exceptionWindowShow (Stage primaryStage) {

        System.out.println("Error message window ");

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(12.0);
        dropShadow.setOffsetX(12.0);
        dropShadow.setOffsetY(12.0);
        dropShadow.setColor(Color.color(0.2, 0.7, 0.9));
        button.setStyle("-fx-font-size: 20;");
        button.setEffect(dropShadow);
        getChildren().add(button);

        button.setOnAction(e ->
                {
                    PlayerMenu playerSceneMenu = new PlayerMenu();
                    try {
                        playerSceneMenu.playerWindowMenu(primaryStage);
                    } catch (Exception ignored) {
                    }
                }
        );

        BorderPane root = new BorderPane();
        root.setCenter(button);

        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Error message");
        primaryStage.show();


    }

}
