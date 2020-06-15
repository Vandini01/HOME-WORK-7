package Player;

import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class Player1 extends Player {

public final String price;
public String name;

Player1() {
    this.price = "100";
    this.name = "Player1";
}



@Override
  public void additionalButtonsConfig(Stage primaryStage, AtomicReference<Stage> playerStage) {
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
