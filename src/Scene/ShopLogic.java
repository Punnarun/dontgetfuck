package Scene;

import GameInstance.GameState;
import GameInstance.GameData;
import Item.Dice;
import Item.Robbing;
import Item.Ticket;
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

    @FXML private Label LabelSlot1;
    @FXML private Label DescriptionSlot1;
    @FXML private Label LabelSlot2;
    @FXML private Label DescriptionSlot2;
    @FXML private Label LabelSlot3;
    @FXML private Label DescriptionSlot3;
    @FXML private Label LabelSlot4;
    @FXML private Label DescriptionSlot4;

    @FXML private Label priceSlot1;
    @FXML private Label priceSlot2;
    @FXML private Label priceSlot3;
    @FXML private Label priceSlot4;

    private int playerMoney;
    private Apple apple = new Apple();
    private Robbing rob = new Robbing();
    private Dice dice = new Dice();
    private Ticket ticket = new Ticket();

    @FXML private void initialize() {
        updatePlayerMoney(0,false);
        LabelSlot1.setText(apple.getName());
        DescriptionSlot1.setText(apple.getDescription());
        priceSlot1.setText("x " + apple.getPrice());

        LabelSlot2.setText(rob.getName());
        DescriptionSlot2.setText(rob.getDescription());
        priceSlot2.setText("x " + rob.getPrice());

        LabelSlot3.setText(dice.getName());
        DescriptionSlot3.setText(dice.getDescription());
        priceSlot3.setText("x " + dice.getPrice());

        LabelSlot4.setText(ticket.getName());
        DescriptionSlot4.setText(ticket.getDescription());
        priceSlot4.setText("x " + ticket.getPrice());
    }

    private void updatePlayerMoney(int moneyLeft , boolean isBoughtOrder) {

        if (GameData.getGameState().equals(GameState.PLAYER1_TURN)) {
            if (isBoughtOrder) GameData.setPlayer1Money(moneyLeft);
            playerMoney = GameData.getPlayer1Money();
        } else {
            if (isBoughtOrder) GameData.setPlayer2Money(moneyLeft);
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


    @FXML private void purchaseApple() {
        Apple apple = new Apple();
        int moneyLeft = apple.buy(playerMoney);
        updatePlayerMoney(moneyLeft , true);
    }

    @FXML private void purchaseRob() {
        Robbing rob = new Robbing();
        int moneyLeft = rob.buy(playerMoney);
        updatePlayerMoney(moneyLeft , true);

    }

    @FXML private void purchaseDice() {
        Dice dice = new Dice();
        int moneyLeft = dice.buy(playerMoney);
        updatePlayerMoney(moneyLeft , true);
    }

    @FXML private void purchaseTicket() {
        Ticket ticket = new Ticket();
        int moneyLeft = ticket.buy(playerMoney);
        updatePlayerMoney(moneyLeft , true);
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
