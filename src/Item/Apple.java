package Item;

import GameInstance.GameData;
import GameInstance.GameState;

public class Apple implements Buyable{

    private String name = "Golden Apple";
    private String description = "- When use, player will instantly heal you HP by maximum";
    private int price = 5;



    @Override
    public int buy(int playerMoney) {
        if (playerMoney >= price) {
            if (GameData.getGameState().equals(GameState.PLAYER1_TURN)) {
                GameData.setPlayer1Slot(new int[]{GameData.getPlayer1Slot()[0] + 1, GameData.getPlayer1Slot()[1],
                        GameData.getPlayer1Slot()[2], GameData.getPlayer1Slot()[3]});
            } else {
                GameData.setPlayer2Slot(new int[]{GameData.getPlayer2Slot()[0] + 1, GameData.getPlayer2Slot()[1],
                        GameData.getPlayer2Slot()[2], GameData.getPlayer2Slot()[3]});
            }
            return (playerMoney - price);
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
}
