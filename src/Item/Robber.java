package Item;

import GameInstance.GameData;
import GameInstance.GameState;

public class Robber extends BaseItem implements Buyable{

    public Robber() {
        super("Robber" , "- When use, he will steal 50% of your Opponent Money for you" , 10);
    }

    @Override
    public int buy(int playerMoney) {

        if (playerMoney >= this.getPrice()) {
            if (GameData.getGameState().equals(GameState.PLAYER1_TURN)) {
                GameData.setPlayer1Slot(new int[]{GameData.getPlayer1Slot()[0], GameData.getPlayer1Slot()[1] + 1,
                        GameData.getPlayer1Slot()[2], GameData.getPlayer1Slot()[3]});
            } else {
                GameData.setPlayer2Slot(new int[]{GameData.getPlayer2Slot()[0], GameData.getPlayer2Slot()[1] + 1,
                        GameData.getPlayer2Slot()[2], GameData.getPlayer2Slot()[3]});
            }
            return (playerMoney - this.getPrice());
        }
        return playerMoney;
    }

    @Override
    public void useEffect() {

        int player1Money = GameData.getPlayer1Money();
        int player2Money = GameData.getPlayer2Money();

        if (GameData.getGameState().equals(GameState.PLAYER1_TURN)) {
            GameData.setPlayer1Money(player1Money + player2Money/2);
            GameData.setPlayer2Money(player2Money/2);
            GameData.setPlayer1Slot(new int[]{GameData.getPlayer1Slot()[0], GameData.getPlayer1Slot()[1] - 1,
                    GameData.getPlayer1Slot()[2], GameData.getPlayer1Slot()[3]});

        } else {
            GameData.setPlayer1Money(player1Money/2);
            GameData.setPlayer2Money(player1Money/2 + player2Money);
            GameData.setPlayer2Slot(new int[]{GameData.getPlayer2Slot()[0], GameData.getPlayer2Slot()[1] - 1,
                    GameData.getPlayer2Slot()[2], GameData.getPlayer2Slot()[3]});
        }
    }
}
