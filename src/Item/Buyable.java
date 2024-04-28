package Item;

import GameInstance.GameData;

public interface Buyable {

    public int buy(int playerMoney);

    public void useEffect();
}
