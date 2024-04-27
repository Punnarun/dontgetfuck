package Scene;

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
import gameData.player;

import java.io.IOException;

public class choosingPlayer {

    basePlayer player1 = null;
    basePlayer player2 = null;

    @FXML private Rectangle startGameButton;

    private Timeline onClickAnimation;
    private int currentIndex = 0;
    @FXML private Label description;
    @FXML private Label startLabel;

    public void initialize() {
        startTypingAnimation("Player 1 choose you Character");
    }

    @FXML
    private void onClick() {
        startTypingAnimation("Player 2 choose you Character");
    }

    @FXML
    private void onClickWarrior() {
        if (player1 == null) {
            onClick();
            player1 = new warrior();
            player.setPlayer1(player1);
            System.out.println("CHOSE");
            startTypingAnimation("Player 2 choose you Character");
        } else if (player2 == null) {
            player2 = new warrior();
            player.setPlayer2(player2);
            startGameButton.setDisable(false);
            startGameButton.setVisible(true);
            startLabel.setVisible(true);
            System.out.println("CHOSE");
        }

        System.out.println(player1);
        System.out.println(player2);
    }

    @FXML
    private void onClickTank() {
        if (player1 == null) {
            onClick();
            player1 = new tank();
            player.setPlayer1(player1);
            System.out.println("CHOSE");
            startTypingAnimation("Player 2 choose you Character");
        } else if (player2 == null) {
            player2 = new tank();
            player.setPlayer2(player2);
            startGameButton.setDisable(false);
            startGameButton.setVisible(true);
            startLabel.setVisible(true);
            System.out.println("CHOSE");
        }

        System.out.println(player1);
        System.out.println(player2);
    }

    @FXML
    private void onClickTheif() {
        if (player1 == null) {
            onClick();
            player1 = new theif();
            player.setPlayer1(player1);
            System.out.println("CHOSE");
            startTypingAnimation("Player 2 choose you Character");
        } else if (player2 == null) {
            player2 = new theif();
            player.setPlayer2(player2);
            startGameButton.setDisable(false);
            startGameButton.setVisible(true);
            startLabel.setVisible(true);
            System.out.println("CHOSE");
        }

        System.out.println(player1);
        System.out.println(player2);
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

    @FXML private AnchorPane root;

    @FXML
    private void startGame() {

        try {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Game.fxml"))));
        } catch (IOException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
    }

    public basePlayer getPlayer1() {
        return player1;
    }

    public void setPlayer1(basePlayer player1) {
        this.player1 = player1;
    }

    public basePlayer getPlayer2() {
        return player2;
    }

    public void setPlayer2(basePlayer player2) {
        this.player2 = player2;
    }


}
