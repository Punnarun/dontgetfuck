package Scene;

import Player.basePlayer;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;
import Controller.gameState;

public class Navigator {

    private gameState gameState;

    @FXML
    private Label drawValue;

    @FXML
    private Rectangle player;

    @FXML
    private GridPane layoutContainer;

    private boolean diceRolled = false;

    @FXML
    private void goToGame() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
        Parent gameRoot = loader.load();
        Scene gameScene = new Scene(gameRoot);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(gameScene);

        System.out.println("TO GAME");
        startGame();
    }

    @FXML
    private void notification(String text) {
        drawValue.setVisible(true);
        drawValue.setText(text);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), drawValue);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.play();

        fadeTransition.setOnFinished(event -> {
            drawValue.setVisible(false);
        });

        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.play();
    }

    @FXML
    private void drawDice() {
        Random random = new Random();
        int diceRoll = random.nextInt(6) + 1;

        notification("You got " + diceRoll);
        System.out.println("Dice rolled: " + diceRoll);
        diceRolled = true; // Signal that the dice has been rolled
        startGame(); // Start the game once the dice is rolled
    }

    private Timeline timeline;
    private int countdownSeconds = 10;

    @FXML
    private Rectangle countdown;

    private void startCountdown() {
        // Initialize the timeline to update the countdown every second
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            countdownSeconds--;
            updateCountdown();
            if (countdownSeconds <= 0) {
                endTurn();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateCountdown() {
        double maxWidth = countdown.getWidth();
        double currentWidth = (double) countdownSeconds / 10 * maxWidth;
        countdown.setWidth(currentWidth);
        System.out.println("CURRENT WIDTH" + currentWidth);
    }

    private void endTurn() {
        // Logic to end the turn...

        // Stop the countdown timer
        timeline.stop();
        countdown.setWidth(0); // Reset the countdown rectangle width
        countdownSeconds = 10; // Reset the countdown seconds for the next turn
        System.out.println("END TURN");
        startCountdown();
    }

    @FXML
    private AnchorPane root; // Add this field

    public void startGame() {
        if (!diceRolled) {
            // Wait for the dice to be rolled
            return;
        }

        basePlayer player1 = new basePlayer("P1",10,10);
        basePlayer player2 = new basePlayer("P2",10,10);
        System.out.println("Player 1 please Roll the Dice");
        System.out.println("HI");
        startCountdown();
    }

    @FXML
    public void UP() {
        int rowIndex = GridPane.getRowIndex(player);
        if (rowIndex > 0) {
            GridPane.setRowIndex(player, rowIndex - 1);
        }
    }

    @FXML
    public void DOWN() {
        int rowIndex = GridPane.getRowIndex(player);
        if (rowIndex < layoutContainer.getRowCount() - 1) {
            GridPane.setRowIndex(player, rowIndex + 1);
        }
    }

    @FXML
    public void LEFT() {
        int colIndex = GridPane.getColumnIndex(player);
        if (colIndex > 0) {
            GridPane.setColumnIndex(player, colIndex - 1);
        }
    }

    @FXML
    public void RIGHT() {
        int colIndex = GridPane.getColumnIndex(player);
        if (colIndex < layoutContainer.getColumnCount() - 1) {
            GridPane.setColumnIndex(player, colIndex + 1);
        }
    }
}
