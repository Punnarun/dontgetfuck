package Scene;

import Controller.gameState;
import Controller.player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class shopLogic {

    @FXML private Label currentMoney;
    @FXML private Rectangle slot1;
    @FXML private Rectangle slot2;
    @FXML private Rectangle slot3;
    @FXML private Rectangle slot4;

    private int playerMoney;

    @FXML
    private void initialize() {
        updatePlayerMoney();
    }

    private void updatePlayerMoney() {
        if (player.getGameState().equals(gameState.PLAYER1_TURN)) {
            playerMoney = player.getPlayer1Money();
        } else {
            playerMoney = player.getPlayer2Money();
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
            slot.setFill(Color.color(120.0/255.0 , 159.0/255.0 , 196.0/255.0 , 1.0));
        }
    }

    @FXML
    private void purchase(int cost) {
        if (playerMoney >= cost) {
            if (player.getGameState().equals(gameState.PLAYER1_TURN)) {
                player.setPlayer1Money(playerMoney - cost);
            } else {
                player.setPlayer2Money(playerMoney - cost);
            }
            updatePlayerMoney();
        }
    }

    @FXML private void purchase1() {
        purchase(5);
        if (player.getGameState().equals(gameState.PLAYER1_TURN)) {
            player.setPlayer1Slot(new int[]{player.getPlayer1Slot()[0] + 1 , player.getPlayer1Slot()[1], player.getPlayer1Slot()[2], player.getPlayer1Slot()[3]});
        } else {
            player.setPlayer2Slot(new int[]{player.getPlayer1Slot()[0] + 1 , player.getPlayer1Slot()[1], player.getPlayer1Slot()[2], player.getPlayer1Slot()[3]});
        }
    }

    @FXML private void purchase2() {
        purchase(10);
        if (player.getGameState().equals(gameState.PLAYER1_TURN)) {
            player.setPlayer1Slot(new int[]{player.getPlayer1Slot()[0] , player.getPlayer1Slot()[1] + 1, player.getPlayer1Slot()[2], player.getPlayer1Slot()[3]});
        } else {
            player.setPlayer2Slot(new int[]{player.getPlayer1Slot()[0] , player.getPlayer1Slot()[1] + 1, player.getPlayer1Slot()[2], player.getPlayer1Slot()[3]});
        }
    }

    @FXML private void purchase3() {
        purchase(15);
        if (player.getGameState().equals(gameState.PLAYER1_TURN)) {
            player.setPlayer1Slot(new int[]{player.getPlayer1Slot()[0] , player.getPlayer1Slot()[1], player.getPlayer1Slot()[2] + 1, player.getPlayer1Slot()[3]});
        } else {
            player.setPlayer2Slot(new int[]{player.getPlayer1Slot()[0] , player.getPlayer1Slot()[1], player.getPlayer1Slot()[2] + 1, player.getPlayer1Slot()[3]});
        }
    }

    @FXML private void purchase4() {
        purchase(20);
        if (player.getGameState().equals(gameState.PLAYER1_TURN)) {
            player.setPlayer1Slot(new int[]{player.getPlayer1Slot()[0], player.getPlayer1Slot()[1], player.getPlayer1Slot()[2], player.getPlayer1Slot()[3] + 1});
        } else {
            player.setPlayer2Slot(new int[]{player.getPlayer1Slot()[0], player.getPlayer1Slot()[1], player.getPlayer1Slot()[2], player.getPlayer1Slot()[3] + 1});
        }
    }

    @FXML
    private ScrollPane root;


    private Scene gameScene;

    @FXML
    private void goBack() {
        // Check if the game scene is already loaded
        System.out.println(gameScene);
        if (gameScene == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
            Parent gameRoot = null;
            try {
                gameRoot = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            gameScene = new Scene(gameRoot);
        }

        Stage stage = (Stage) currentMoney.getScene().getWindow(); // Get the current window
        stage.setScene(gameScene); // Set the game scene
    }
}
