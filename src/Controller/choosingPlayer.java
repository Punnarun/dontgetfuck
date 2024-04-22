package Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class choosingPlayer {


    @FXML
    private Group warriorFrame;

    @FXML
    private void onEnter() {
        warriorFrame.setVisible(true);
    }

    @FXML
    private void onExit() {
        warriorFrame.setVisible(false);
    }

    @FXML
    private AnchorPane root; // Add this field

    private Timeline onClickAnimation;
    private int currentIndex = 0;
    @FXML
    private Label description;

    public void initialize() {
        startTypingAnimation("Player 1 choose you Character");
    }

//    public choosingPlayer() {
//    }

    @FXML
    private void onClick() {
        startTypingAnimation("Player 2 choose you Character");
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
}
