package Scene;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import Player.*;

import java.io.IOException;

public class choosingPlayer {

    basePlayer player1 = null;
    basePlayer player2 = null;

    @FXML private Rectangle startGameButton;
    @FXML private Group warriorFrame;


    @FXML
    private void onEnter() {
        warriorFrame.setVisible(true);
    }

    @FXML
    private void onExit() {
        warriorFrame.setVisible(false);
    }

    private Timeline onClickAnimation;
    private int currentIndex = 0;
    @FXML
    private Label description;

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
            startTypingAnimation("Player 2 choose you Character");
        } else if (player2 == null) {
            player2 = new warrior();
            startGameButton.setDisable(false);
            startGameButton.setVisible(true);
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

}
