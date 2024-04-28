package Item;

import GameInstance.GameData;
import GameInstance.GameState;

public class Dice implements Buyable{

    private String name = "Cheating Dice";
    private String description = "- When use, player will able to throw the dice twice";
    private int price = 15;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int buy(int playerMoney) {
        if (playerMoney >= price) {
            if (GameData.getGameState().equals(GameState.PLAYER1_TURN)) {
                GameData.setPlayer1Slot(new int[]{GameData.getPlayer1Slot()[0], GameData.getPlayer1Slot()[1],
                        GameData.getPlayer1Slot()[2] + 1, GameData.getPlayer1Slot()[3]});
            } else {
                GameData.setPlayer2Slot(new int[]{GameData.getPlayer2Slot()[0], GameData.getPlayer2Slot()[1],
                        GameData.getPlayer2Slot()[2] + 1, GameData.getPlayer2Slot()[3]});
            }
            return (playerMoney - price);
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
