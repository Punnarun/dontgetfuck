package Item;

import GameInstance.GameData;
import GameInstance.GameState;

public class Apple extends BaseItem implements Buyable{

    public Apple() {
        super("Golden Apple","- When use, player will instantly heal you HP by maximum" , 5);
    }

    @Override
    public int buy(int playerMoney) {
        if (playerMoney >= this.getPrice()) {
            if (GameData.getGameState().equals(GameState.PLAYER1_TURN)) {
                GameData.setPlayer1Slot(new int[]{GameData.getPlayer1Slot()[0] + 1, GameData.getPlayer1Slot()[1],
                        GameData.getPlayer1Slot()[2], GameData.getPlayer1Slot()[3]});
            } else {
                GameData.setPlayer2Slot(new int[]{GameData.getPlayer2Slot()[0] + 1, GameData.getPlayer2Slot()[1],
                        GameData.getPlayer2Slot()[2], GameData.getPlayer2Slot()[3]});
            }
            return (playerMoney - this.getPrice());
        }
        return playerMoney;
    }

    @Override
    public void useEffect() {
        if (GameData.getGameState().equals(GameState.PLAYER1_TURN)) {
            GameData.getPlayer1().setHp(5);
            GameData.setPlayer1Slot(new int[]{GameData.getPlayer1Slot()[0] - 1, GameData.getPlayer1Slot()[1],
                    GameData.getPlayer1Slot()[2], GameData.getPlayer1Slot()[3]});
        } else {
            GameData.getPlayer2().setHp(5);
            GameData.setPlayer1Slot(new int[]{GameData.getPlayer1Slot()[0] - 1, GameData.getPlayer1Slot()[1],
                    GameData.getPlayer1Slot()[2], GameData.getPlayer1Slot()[3]});
        }

    }
}
