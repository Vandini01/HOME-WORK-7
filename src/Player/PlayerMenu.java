package Player;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class PlayerMenu extends BorderPane {

private Label pricePlayar1Label;
private Label pricePlayar2Label;
private Label pricePlayar3Label;
private Label pricePlayar4Label;
private Label pricePlayar5Label;
private Label pricePlayar6Label;

private Button buttonPlayer1;
private Button buttonPlayer2;
private Button buttonPlayer3;
private Button buttonPlayer4;
private Button buttonPlayer5;
private Button buttonPlayer6;

Player player1;
Player player2;
Player player3;
Player player4;
Player player5;
Player player6;

String price;
private Button buttonErrorMessage;
private Label pictureMusicPlayer;

PlayerMenu() {
    this.player1 = new Player1();
    this.player2 = new Player2();
    this.player3 = new Player3();
    this.player4 = new Player4();
    this.player5 = new Player5();
    this.player6 = new Player6();

    this.pricePlayar1Label = new Label (player1.getPrice());
    this.pricePlayar2Label = new Label (player2.getPrice());
    this.pricePlayar3Label = new Label (player3.getPrice());
    this.pricePlayar4Label = new Label (player4.getPrice());
    this.pricePlayar5Label = new Label (player5.getPrice());
    this.pricePlayar6Label = new Label (player6.getPrice());

    this.buttonPlayer1 = new Button("Player 1");
    this.buttonPlayer2 = new Button("Player 2");
    this.buttonPlayer3 = new Button("Player 3");
    this.buttonPlayer4 = new Button("Player 4");
    this.buttonPlayer5 = new Button("Player 5");
    this.buttonPlayer6 = new Button("Player 6");

    this.buttonErrorMessage = new Button(" Error ");   //  Эта кнопка нужна чтобы протестировать этот режим, если вдруг никаких ошибок не обнаружится
    this.price = "     Price :  ";
    this.pictureMusicPlayer = new Label ();
}


    public void playerWindowMenu(Stage primaryStage) throws IOException {

        System.out.println("Player Menu!");

        BorderPane root = new BorderPane();

        DropShadow dropShadow1 = new DropShadow();
        dropShadow1.setRadius(5.0);
        dropShadow1.setOffsetX(3.0);
        dropShadow1.setOffsetY(3.0);
        dropShadow1.setColor(Color.color(0.4, 0.5, 0.5));
        buttonPlayer1.setEffect(dropShadow1);
        buttonPlayer2.setEffect(dropShadow1);
        buttonPlayer3.setEffect(dropShadow1);
        buttonPlayer4.setEffect(dropShadow1);
        buttonPlayer5.setEffect(dropShadow1);
        buttonPlayer6.setEffect(dropShadow1);
        buttonErrorMessage.setEffect(dropShadow1);

        String setStyleForPlayers = "-fx-background-color: \n" +
                "        rgba(0,0,0,0.08),\n" +
                "        linear-gradient(#5a61af, #51536d),\n" +
                "        linear-gradient(#e4fbff 0%,#cee6fb 10%, #a5d3fb 50%, #88c6fb 51%, #d5faff 100%);\n" +
                "    -fx-background-insets: 0 0 -1 0,0,1;\n" +
                "    -fx-background-radius: 5,5,4;\n" +
                "    -fx-padding: 3 30 3 30;\n" +
                "    -fx-text-fill: #242d35;\n" +
                "    -fx-font-size: 14px;";

        buttonPlayer1.setStyle(setStyleForPlayers);
        buttonPlayer2.setStyle(setStyleForPlayers);
        buttonPlayer3.setStyle(setStyleForPlayers);
        buttonPlayer4.setStyle(setStyleForPlayers);
        buttonPlayer5.setStyle(setStyleForPlayers);
        buttonPlayer6.setStyle(setStyleForPlayers);

        String setStyleForErrorButton = "-fx-background-color: \n" +
                "        #c3c4c4,\n" +
                "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-background-insets: 0,1,1;\n" +
                "    -fx-text-fill: black;\n" +
                "    -fx-font-size: 14px;" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );";

        buttonErrorMessage.setStyle(setStyleForErrorButton);


        buttonPlayer1.setOnAction(e -> {
                    try {
                        player1.show(primaryStage);
                    } catch (Exception ignored) {          //  <ignored>  потому что в классе Player есть обработчик исключений
                    }
                }
            );

        buttonPlayer2.setOnAction(e -> {
                    try {
                        player2.show(primaryStage);
                    } catch (Exception ignored) {
                    }
                }
        );

        buttonPlayer3.setOnAction(e -> {
                    try {
                        player3.show(primaryStage);
                    } catch (Exception ignored) {
                    }
                }
        );

        buttonPlayer4.setOnAction(e -> {
                    try {
                        player4.show(primaryStage);
                    } catch (Exception ignored) {
                    }
                }
        );

        buttonPlayer5.setOnAction(e -> {
                    try {
                        player5.show(primaryStage);
                    } catch (Exception ignored) {
                    }
                }
        );

        buttonPlayer6.setOnAction(e -> {
                    try {
                        player6.show(primaryStage);
                    } catch (Exception ignored) {
                    }
                }
        );


        buttonErrorMessage.setOnAction(e -> {
           ExceptionProcessing exeption = new ExceptionProcessing();
           exeption.exceptionWindowShow(primaryStage);  }
        );


        InputStream input = null;
        try {
            input = getClass().getResourceAsStream("resourses/images/music_player_picture.png");
            Image image = new Image(input);
            ImageView imageView = new ImageView(image);
            pictureMusicPlayer.setGraphic(imageView);
        } catch (Exception ex) {
            ExceptionProcessing exeption = new ExceptionProcessing("Файла изображения нет на месте!");
            exeption.exceptionWindowShow(primaryStage);
        }
        finally {
            if (input != null) { input.close(); }
        }

        GridPane centerPane = new GridPane();

        HBox hbox0 = new HBox();
        hbox0.getChildren().addAll(new Label("                     \n"));

        HBox hbox1 = new HBox();
        hbox1.getChildren().addAll(buttonPlayer1, new Label(price), pricePlayar1Label);

        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(buttonPlayer2, new Label(price), pricePlayar2Label);

        HBox hbox3 = new HBox();
        hbox3.getChildren().addAll(buttonPlayer3, new Label(price), pricePlayar3Label);

        HBox hbox4 = new HBox();
        hbox4.getChildren().addAll(buttonPlayer4, new Label(price), pricePlayar4Label);

        HBox hbox5 = new HBox();
        hbox5.getChildren().addAll(buttonPlayer5, new Label(price), pricePlayar5Label);

        HBox hbox6 = new HBox();
        hbox6.getChildren().addAll(buttonPlayer6, new Label(price), pricePlayar6Label);


        HBox hbox7 = new HBox();
        hbox7.getChildren().addAll(buttonErrorMessage);


       centerPane.add(hbox0, 2, 1);
       centerPane.add(new Label("            \n"), 3, 2);

       centerPane.add(hbox1, 5,3);   //  Вывод кнопок всех плееров на экран
       centerPane.add(hbox2, 5,8);
       centerPane.add(hbox3, 5,9);
       centerPane.add(hbox4, 5,10);
       centerPane.add(hbox5, 5,11);
       centerPane.add(hbox6, 5,12);

       centerPane.add(new Label("            \n"), 4, 13);
       centerPane.add(hbox7, 5,14);   //  Вывод тестовой кнопки "Error" на экран

       centerPane.add(pictureMusicPlayer, 8,17);


        root.setCenter(centerPane);

        primaryStage.setTitle(" Menu ");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.centerOnScreen();
        primaryStage.show();

    }

}
