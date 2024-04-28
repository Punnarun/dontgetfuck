package GameController;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import Player.*;
import GameInstance.GameData;

import java.io.IOException;

public class Character {

    private BasePlayer player1 = null;
    private BasePlayer player2 = null;

    @FXML private Rectangle startGameButton;
    @FXML private Label description;
    @FXML private Label startLabel;
    @FXML private AnchorPane root;

    private Timeline onClickAnimation;
    private int currentIndex = 0;

    public void initialize() {
        startTypingAnimation("Player 1 choose you Character");
    }

    @FXML private void onClick() {
        startTypingAnimation("Player 2 choose you Character");
    }

    @FXML private void onClickWarrior() {
        if (player1 == null) {
            onClick();
            player1 = new Warrior();
            GameData.setPlayer1(player1);
            startTypingAnimation("Player 2 choose you Character");
        } else if (player2 == null) {
            player2 = new Warrior();
            GameData.setPlayer2(player2);
            startGameButton.setDisable(false);
            startGameButton.setVisible(true);
            startLabel.setVisible(true);
        }
    }

    @FXML private void onClickTank() {
        if (player1 == null) {
            onClick();
            player1 = new Tank();
            GameData.setPlayer1(player1);
            startTypingAnimation("Player 2 choose you Character");
        } else if (player2 == null) {
            player2 = new Tank();
            GameData.setPlayer2(player2);
            startGameButton.setDisable(false);
            startGameButton.setVisible(true);
            startLabel.setVisible(true);
        }
    }

    @FXML private void onClickThief() {
        if (player1 == null) {
            onClick();
            player1 = new Thief();
            GameData.setPlayer1(player1);
            startTypingAnimation("Player 2 choose you Character");
        } else if (player2 == null) {
            player2 = new Thief();
            GameData.setPlayer2(player2);
            startGameButton.setDisable(false);
            startGameButton.setVisible(true);
            startLabel.setVisible(true);
        }
    }

    private void startTypingAnimation(String targetText) {
        currentIndex = 0;
        onClickAnimation = new Timeline(new KeyFrame(Duration.seconds(0.05), event -> {
            if (currentIndex <= targetText.length()) {
                description.setText(targetText.substring(0, currentIndex++));
            } else {
                onClickAnimation.stop();
            }
        }));
        onClickAnimation.setCycleCount(Timeline.INDEFINITE);
        onClickAnimation.play();
    }

    @FXML private void startGame() {
        try {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Scene/Game.fxml"))));
        } catch (IOException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
    }

    public BasePlayer getPlayer1() {
        return player1;
    }

    public void setPlayer1(BasePlayer player1) {
        this.player1 = player1;
    }

    public BasePlayer getPlayer2() {
        return player2;
    }

    public void setPlayer2(BasePlayer player2) {
        this.player2 = player2;
    }


}
