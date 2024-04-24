package Item;

import Player.basePlayer;

public class rob extends baseItem{


    @Override
    public void itemEffect(basePlayer currentPlayer, basePlayer opponent) {
        currentPlayer.setMoney(currentPlayer.getMoney() + (int)(opponent.getMoney()/2));
        opponent.setMoney((int)(opponent.getMoney()/2));
    }
}
