package Scene;

import GameInstance.GameState;
import GameInstance.GameData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import Item.Apple;

import java.io.IOException;

public class ShopLogic {

    @FXML private Label currentMoney;
    @FXML private Rectangle slot1;
    @FXML private Rectangle slot2;
    @FXML private Rectangle slot3;
    @FXML private Rectangle slot4;

    private int playerMoney;

    @FXML private void initialize() {
        updatePlayerMoney();
    }

    private void updatePlayerMoney() {
        if (GameData.getGameState().equals(GameState.PLAYER1_TURN)) {
            playerMoney = GameData.getPlayer1Money();
        } else {
            playerMoney = GameData.getPlayer2Money();
        }
        currentMoney.setText("x " + playerMoney);
        updateSlotColors();
    }

    private void updateSlotColors() {
        setSlotColor(slot1, playerMoney >= 5);
        setSlotColor(slot2, playerMoney >= 10);
        setSlotColor(slot3, playerMoney >= 15);
        setSlotColor(slot4, playerMoney >= 20);
    }

    private void setSlotColor(Rectangle slot, boolean condition) {
        if (condition) {
            double red = 82.0 / 255.0;
            double green = 177.0 / 255.0;
            double blue = 255.0 / 255.0;
            slot.setFill(Color.color(red, green, blue, 1));
        } else {
            slot.setFill(Color.color(120.0 / 255.0, 159.0 / 255.0, 196.0 / 255.0, 1.0));
        }
    }

    @FXML private boolean purchase(int cost) {
        if (playerMoney >= cost) {
            System.out.println("PLAYER MONEY " + playerMoney);
            if (GameData.getGameState().equals(GameState.PLAYER1_TURN)) {
                GameData.setPlayer1Money(playerMoney - cost);
            } else {
                GameData.setPlayer2Money(playerMoney - cost);
            }
            updatePlayerMoney();
            return true;
        }
        return false;
    }

    @FXML private void purchase2(int moneyLeft) {
        if (GameData.getGameState().equals(GameState.PLAYER1_TURN)) {
            GameData.setPlayer1Money(moneyLeft);
        } else {
            GameData.setPlayer2Money(moneyLeft);
        }
        updatePlayerMoney();

    }

    @FXML private void purchaseApple() {
        System.out.println("APPLE BUY!");
        Apple apple = new Apple();
        int moneyLeft = apple.buy(playerMoney);
        System.out.println("MONEY LEFT" + moneyLeft);
        purchase2(moneyLeft);
    }

    @FXML private void purchaseRob() {
        if (purchase(10)) {
            if (GameData.getGameState().equals(GameState.PLAYER1_TURN)) {
                GameData.setPlayer1Slot(new int[]{GameData.getPlayer1Slot()[0], GameData.getPlayer1Slot()[1] + 1, GameData.getPlayer1Slot()[2], GameData.getPlayer1Slot()[3]});
            } else {
                GameData.setPlayer2Slot(new int[]{GameData.getPlayer2Slot()[0], GameData.getPlayer2Slot()[1] + 1, GameData.getPlayer2Slot()[2], GameData.getPlayer2Slot()[3]});
            }
        }

    }

    @FXML private void purchaseDice() {
        if (purchase(15)) {
            if (GameData.getGameState().equals(GameState.PLAYER1_TURN)) {
                GameData.setPlayer1Slot(new int[]{GameData.getPlayer1Slot()[0], GameData.getPlayer1Slot()[1], GameData.getPlayer1Slot()[2] + 1, GameData.getPlayer1Slot()[3]});
            } else {
                GameData.setPlayer2Slot(new int[]{GameData.getPlayer2Slot()[0], GameData.getPlayer2Slot()[1], GameData.getPlayer2Slot()[2] + 1, GameData.getPlayer2Slot()[3]});
            }
        }

    }

    @FXML private void purchaseTicket() {
        if (purchase(20)) {
            if (GameData.getGameState().equals(GameState.PLAYER1_TURN)) {
                GameData.setPlayer1Slot(new int[]{GameData.getPlayer1Slot()[0], GameData.getPlayer1Slot()[1], GameData.getPlayer1Slot()[2], GameData.getPlayer1Slot()[3] + 1});
            } else {
                GameData.setPlayer2Slot(new int[]{GameData.getPlayer2Slot()[0], GameData.getPlayer2Slot()[1], GameData.getPlayer2Slot()[2], GameData.getPlayer2Slot()[3] + 1});
            }
        }

    }

    @FXML private ScrollPane root;

    @FXML private void goBack() {
        try {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Game.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
