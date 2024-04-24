package Item;

import Player.basePlayer;

public class goldenApple extends baseItem{
    @Override
    public void itemEffect(basePlayer player , basePlayer opponent) {
        player.setHp(Math.min((player.getHp() + 1),5));
    }
}
