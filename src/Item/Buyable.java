package Item;

import GameInstance.GameData;

public interface Buyable {

    int buy(int playerMoney);

    void useEffect();
}
