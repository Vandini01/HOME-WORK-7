package Player;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class Player3 extends Player {

public final String price;
public String name;
public Button buttonPlayAllSongs;
public String playAllSongsString = "Play all songs";

String[] playlist = new String[PlaylistEnum.values().length];

Stage primaryStage;
AtomicReference<Stage> playerStage;

Player3() {
    this.price = "300";
    this.name = "Player3";
    this.buttonPlayAllSongs = new Button(playAllSongsString);

    buttonPlayAllSongs.setOnAction(e -> {
                playAllSongs();
            }
    );

    playerStage = new AtomicReference<>(new Stage());

    createPlaylist();
}



public void playAllSongs() {                    //  Здесь нет передачи в параметры primaryStage и AtomicReference<Stage> playerStage.
    System.out.print("\nPlay all songs : ");    //  Они передаются в метод <additionalButtonsConfig>, а оттуда в глобальные (для этого плеера) переменные,
                                                //  которые определены в конструкторе.  Это нужно на случай ошибки чтения файла, чтобы корректно закрыть окно пллера.

   String[] playlistShortNames = new String[(playlist.length)];

   for (int i = 0; i < playlist.length; i++) {
       String[] playSongSplitArray = playlist[i].split("/");
       playlistShortNames[i] = playSongSplitArray[(playSongSplitArray.length) - 1];
   }

   System.out.println(Arrays.toString(playlistShortNames) + "\n");


   realPlaySong(primaryStage, playerStage, playlist, playlistShortNames, 0);      //  Вывод всех песен на динамики

}


public void createPlaylist() {
    for (PlaylistEnum name : PlaylistEnum.values()) {
        this.playlist[name.ordinal()] = PlaylistEnum.getPlaylist()[name.ordinal()];
    }
}



public void additionalButtonsConfig(Stage primaryStage, AtomicReference<Stage> playerStage) {

    this.primaryStage = primaryStage;
    this.playerStage = playerStage;

    try {
        this.buttonPlayAllSongs.setGraphic(addImageToButton(primaryStage, playerStage, "resourses/images/player_play_button.png"));
        shadowEffectAndStyleSetToButton(buttonPlayAllSongs);
        }
    catch (Exception ex) {
        playerStage.get().close();
        ExceptionProcessing exeption = new ExceptionProcessing();
        exeption.exceptionWindowShow(primaryStage);
    }

}



public HBox configPane(HBox hboxControlPanel) {
    hboxControlPanel.getChildren().addAll(buttonPlayAllSongs);
    return hboxControlPanel;
}



@Override
  public String getPrice() {
      return this.price;
  }

@Override
  public String getName() {
      return this.name;
  }


}
