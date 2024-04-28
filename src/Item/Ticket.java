package Item;

import GameInstance.GameData;
import GameInstance.GameState;

import java.util.Random;

public class Ticket implements Buyable{

    private String name = "Winning Ticket";
    private String description = "- When use, gain chance to instantly win by 70% instantly";
    private int price = 20;

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
                        GameData.getPlayer1Slot()[2], GameData.getPlayer1Slot()[3] + 1});
            } else {
                GameData.setPlayer2Slot(new int[]{GameData.getPlayer2Slot()[0], GameData.getPlayer2Slot()[1],
                        GameData.getPlayer2Slot()[2], GameData.getPlayer2Slot()[3] + 1});
            }
            return (playerMoney - price);
        }
        return playerMoney;
    }

    @Override
    public void useEffect() {
        Random random = new Random();
        int rand = random.nextInt(10);

        GameState userGameState = null;
        GameState opponentGamestate = null;

        if (GameData.getGameState().equals(GameState.PLAYER1_TURN)) {
            userGameState = GameState.PLAYER1_WIN;
            opponentGamestate = GameState.PLAYER2_WIN;
            GameData.setPlayer1Slot(new int[]{GameData.getPlayer1Slot()[0], GameData.getPlayer1Slot()[1],
                    GameData.getPlayer1Slot()[2], GameData.getPlayer1Slot()[3] - 1});
        } else if (GameData.getGameState().equals(GameState.PLAYER2_TURN)) {
            userGameState = GameState.PLAYER2_WIN;
            opponentGamestate = GameState.PLAYER1_WIN;
            GameData.setPlayer2Slot(new int[]{GameData.getPlayer1Slot()[0], GameData.getPlayer1Slot()[1],
                    GameData.getPlayer1Slot()[2], GameData.getPlayer1Slot()[3] - 1});
        }
        if (rand < 7) GameData.setGameState(userGameState);
        else GameData.setGameState(opponentGamestate);

    }
}
