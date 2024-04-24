package Item;

import Player.basePlayer;

public abstract class baseItem {

    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public abstract void itemEffect(basePlayer currentPlayer , basePlayer opponent);
}
