package Player;

public class Player5 extends Player3 {

public final String price;
public String name;

Player5() {
    this.price = "500";
    this.name = "Player5";

    createPlaylist();
}



@Override
public void createPlaylist() {                                     //  В этом плеере песни играют в обратном порядке, с конца плейлиста в начало
    int lengthOfPlaylist = PlaylistEnum.values().length;

    for (PlaylistEnum name : PlaylistEnum.values()) {
        lengthOfPlaylist--;
        playlist[name.ordinal()] = PlaylistEnum.getPlaylist()[lengthOfPlaylist];
    }
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
