package Player;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public abstract class Player {

private Button buttonStartMenu;
private Button buttonPlay;
private Button buttonStop;

public String playString = "Play";
public String stopString = "Stop";
public Label musicLabel;

Player() {
    this.buttonStartMenu = new Button(" Start Menu ");
    this.buttonPlay = new Button(playString);
    this.buttonStop = new Button(stopString);
    this.musicLabel = new Label ();
}


public void show(Stage primaryStage) throws IOException {

    BorderPane group = new BorderPane();
    AtomicReference<Stage> playerStage = new AtomicReference<>(new Stage());

    DropShadow dropShadow1 = new DropShadow();
    dropShadow1.setRadius(5.0);
    dropShadow1.setOffsetX(3.0);
    dropShadow1.setOffsetY(3.0);
    dropShadow1.setColor(Color.color(0.2, 0.9, 0.7));
    buttonStartMenu.setStyle("-fx-font-size: 18;");
    buttonStartMenu.setEffect(dropShadow1);

    shadowEffectAndStyleSetToButton(buttonPlay);
    shadowEffectAndStyleSetToButton(buttonStop);

    try {
        this.buttonStartMenu.setGraphic(addImageToButton(primaryStage, playerStage, "resourses/images/music.png"));
        this.buttonPlay.setGraphic(addImageToButton(primaryStage, playerStage, "resourses/images/player_play_button.png"));
        this.buttonStop.setGraphic(addImageToButton(primaryStage, playerStage, "resourses/images/player_stop_button.png"));
        this.musicLabel.setGraphic(addImageToButton(primaryStage, playerStage, "resourses/images/music_background.png"));
    }
    catch (Exception ex) {
        playerStage.get().close();
        ExceptionProcessing exeption = new ExceptionProcessing();
        exeption.exceptionWindowShow(primaryStage);
    }



    buttonStartMenu.setOnAction(e -> {
        PlayerMenu playerSceneMenu = new PlayerMenu();
        try {
            playerSceneMenu.playerWindowMenu(primaryStage);
        } catch (Exception ignored) {
        }
        playerStage.get().close();
        }
    );


    buttonPlay.setOnAction(e -> {
                playSong(primaryStage, playerStage);
            }
    );


    additionalButtonsConfig(primaryStage, playerStage);   //  Переопределен в тех классах, где нужны дополнительные кнопки


    HBox hboxControlPanel = new HBox();
    hboxControlPanel.getChildren().addAll(new Label("                        \n"), buttonPlay, buttonStop);

    hboxControlPanel = configPane(hboxControlPanel);   //  Переопределен в разных плеерах по-своему

    HBox hboxMusicImage = new HBox();
    hboxMusicImage = musicImageHBoxConfig(hboxMusicImage, musicLabel);  //  Переопределен в разных плеерах


    group.setTop(hboxControlPanel);
    group.setRight(hboxMusicImage);
    group.setCenter(buttonStartMenu);


    playerStage.get().setTitle(getName());
    playerStage.get().setScene(new Scene(group, 650, 300));
    playerStage.get().centerOnScreen();
    playerStage.get().show();


}


public ImageView addImageToButton(Stage primaryStage, AtomicReference<Stage> playerStage, String stringPathToResourse) throws IOException {

    ImageView imageView = null;
    InputStream input = null;
    try {
        input = getClass().getResourceAsStream(stringPathToResourse);
        Image image = new Image(input);
        imageView = new ImageView(image);
    } catch (Exception ex) {
        playerStage.get().close();
        ExceptionProcessing exeption = new ExceptionProcessing("Файла изображения нет на месте!");
        exeption.exceptionWindowShow(primaryStage);
    }
    finally {
        if (input != null) { input.close(); }
    }
    return imageView;
}


public void shadowEffectAndStyleSetToButton (Button button) {

    DropShadow dropShadow2 = new DropShadow();
    dropShadow2.setRadius(5.0);
    dropShadow2.setOffsetX(3.0);
    dropShadow2.setOffsetY(3.0);
    dropShadow2.setColor(Color.color(0.1, 0.6, 0.9));

    String setStyleForControlButtons = "-fx-background-color: \n" +
            "        rgba(0,0,0,0.08),\n" +
            "        linear-gradient(#5a61af, #51536d),\n" +
            "        linear-gradient(#e4fbff 0%,#cee6fb 10%, #a5d3fb 50%, #88c6fb 51%, #d5faff 100%);\n" +
            "    -fx-background-insets: 0 0 -1 0,0,1;\n" +
            "    -fx-background-radius: 5,5,4;\n" +
            "    -fx-padding: 3 30 3 30;\n" +
            "    -fx-text-fill: #242d35;\n" +
            "    -fx-font-size: 12px;";

    button.setEffect(dropShadow2);
    button.setStyle(setStyleForControlButtons);

}


public HBox configPane(HBox hboxControlPanel) {     //  Перегружен в Player3  (от которого наследуются Player4, Player5  и  Player6)
    return hboxControlPanel;
}


public HBox musicImageHBoxConfig(HBox musicImageHBox, Label musicLabel) {   //  Переопределен в Player6   (добавлена кнопка <shuffle>)
    musicImageHBox.getChildren().addAll(musicLabel);
    return musicImageHBox;
}




public void playSong(Stage primaryStage, AtomicReference<Stage> playerStage) {
    System.out.println("Play first song ");

    String playSong = PlaylistEnum.getNameOfFirstSong();

    String[] playSongSplitArray = playSong.split("/");
    String playSongShortName = playSongSplitArray[(playSongSplitArray.length) - 1];

    System.out.println("First song  :  " + playSongShortName);

    realPlaySong(primaryStage, playerStage, playSong, playSongShortName);

}



public void realPlaySong(Stage primaryStage, AtomicReference<Stage> playerStage, String nameOfSong, String playSongShortName) {
    System.out.println("Playing song  :  " + playSongShortName);

    Platform.runLater( () -> {
    try {
    String song = new File(nameOfSong).toURI().toString();
    Media audio = new Media(song);
    MediaPlayer mediaPlayer = new MediaPlayer(audio);
    mediaPlayer.play();

        buttonStop.setOnAction(arg0 -> {
            System.out.println("Stop playing the song ");
            mediaPlayer.stop();
        });
    }
    catch (Exception ex) {
        playerStage.get().close();
        ExceptionProcessing exeption = new ExceptionProcessing("Медиа-файла нет на месте!");
        exeption.exceptionWindowShow(primaryStage);
    }
    });

}


    public void realPlaySong(Stage primaryStage, AtomicReference<Stage> playerStage, String[] nameOfSong, String[] playSongShortName, int counter) {

        if (counter == nameOfSong.length)     //  Выход из рекурсии
            return;

        System.out.println("Playing song  :  " + playSongShortName[counter]);

        Platform.runLater( () -> {
            try {
                String song = new File(nameOfSong[counter]).toURI().toString();
                Media audio = new Media(song);
                MediaPlayer mediaPlayer = new MediaPlayer(audio);
                mediaPlayer.play();

                mediaPlayer.setOnEndOfMedia(() -> {
                    realPlaySong(primaryStage, playerStage, nameOfSong, playSongShortName, counter + 1);
                });

                buttonStop.setOnAction(arg0 -> {
                    System.out.println("Stop playing the song ");
                    mediaPlayer.stop();
                });
            }
            catch (Exception ex) {
                playerStage.get().close();
                ExceptionProcessing exeption = new ExceptionProcessing("Медиа-файла нет на месте!");
                exeption.exceptionWindowShow(primaryStage);
            }
        });

    }



public abstract void additionalButtonsConfig(Stage primaryStage, AtomicReference<Stage> playerStage);


public abstract String getPrice();


public abstract String getName();


}

