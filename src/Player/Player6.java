package Player;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class Player6 extends Player3 {

public final String price;
public String name;
public Button buttonShuffle;
public String shuffleString = "Shuffle songs";

Player6() {
    this.price = "600";
    this.name = "Player6";
    this.buttonShuffle = new Button(shuffleString);

    buttonShuffle.setOnAction(e -> {
        shufflePlaylist();
        }
    );
}


public void shufflePlaylist() {
   System.out.println("Shuffle playlist !");

String stringTemp = new String();
int playlistLength = playlist.length;
int random;

     for (int i = 0; i < 20; i++) {
          random = ((int) (Math.random() * playlistLength));    //  Случайное число в пределах длины массива

          if (random < (playlistLength - 1)) {
              stringTemp = playlist[random + 1];
              playlist[random + 1] = playlist[random];
              playlist[random] = stringTemp;
          } else {
              stringTemp = playlist[0];
              playlist[0] = playlist[random];
              playlist[random] = stringTemp;
          }
     }
}


@Override
public void additionalButtonsConfig(Stage primaryStage, AtomicReference<Stage> playerStage) {

   try {
       this.buttonPlayAllSongs.setGraphic(addImageToButton(primaryStage, playerStage, "resourses/images/player_play_button.png"));
       shadowEffectAndStyleSetToButton(buttonPlayAllSongs);

       this.buttonShuffle.setGraphic(addImageToButton(primaryStage, playerStage, "resourses/images/shuffle_button.png"));
       shadowEffectAndStyleSetToButton(buttonShuffle);
   }
   catch (Exception ex) {
          playerStage.get().close();
          ExceptionProcessing exeption = new ExceptionProcessing();
          exeption.exceptionWindowShow(primaryStage);
   }
}


@Override
public HBox musicImageHBoxConfig(HBox musicImageHBox, Label musicLabel) {                               //  (добавлена кнопка <shuffle>)
    musicImageHBox.getChildren().addAll(buttonShuffle, new Label("      \n"), musicLabel);
    return musicImageHBox;
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
