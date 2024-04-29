package Item;

import GameInstance.GameData;
import GameInstance.GameState;

public class Dice extends BaseItem implements Buyable{

    public Dice() {
        super("Cheating Dice" , "- When use, player will able to throw the dice twice" , 15);
    }

    @Override
    public int buy(int playerMoney) {
        if (playerMoney >= this.getPrice()) {
            if (GameData.getGameState().equals(GameState.PLAYER1_TURN)) {
                GameData.setPlayer1Slot(new int[]{GameData.getPlayer1Slot()[0], GameData.getPlayer1Slot()[1],
                        GameData.getPlayer1Slot()[2] + 1, GameData.getPlayer1Slot()[3]});
            } else {
                GameData.setPlayer2Slot(new int[]{GameData.getPlayer2Slot()[0], GameData.getPlayer2Slot()[1],
                        GameData.getPlayer2Slot()[2] + 1, GameData.getPlayer2Slot()[3]});
            }
            return (playerMoney - this.getPrice());
        }
        return playerMoney;
    }

    @Override
    public void useEffect() {
        if (GameData.getGameState().equals(GameState.PLAYER1_TURN)) {
            GameData.setPlayer1Slot(new int[]{GameData.getPlayer1Slot()[0], GameData.getPlayer1Slot()[1],
                    GameData.getPlayer1Slot()[2] - 1, GameData.getPlayer1Slot()[3]});
        } else {
            GameData.setPlayer2Slot(new int[]{GameData.getPlayer2Slot()[0], GameData.getPlayer2Slot()[1],
                    GameData.getPlayer2Slot()[2] - 1, GameData.getPlayer2Slot()[3]});
        }
    }
}
