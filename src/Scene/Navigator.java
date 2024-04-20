package Scene;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

public class Navigator {

    @FXML
    private Label drawValue;

    @FXML
    private Rectangle player;

    @FXML
    private GridPane layoutContainer;


    @FXML
    private Rectangle drawBg;

    @FXML
    private void goToGame() {
        //navigate to the game page
        System.out.println("TO GAME");
    }

    @FXML
    private void drawDice() {
        // Generate a random number between 1 and 6 (inclusive) for the dice roll
        Random random = new Random();
        int diceRoll = random.nextInt(6) + 1;

        drawValue.setVisible(true);
        drawValue.setText("You got " + diceRoll);
        drawBg.setVisible(true);

        // Create a fade transition for the label and rectangle
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), drawValue);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.play();

        fadeTransition.setOnFinished(event -> {
            drawValue.setVisible(false);
        });

        fadeTransition = new FadeTransition(Duration.seconds(3), drawBg);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.play();

        fadeTransition.setOnFinished(event -> {
            drawBg.setVisible(false);
        });

        // Optionally, you can also print the rolled value to the console
        System.out.println("Dice rolled: " + diceRoll);
    }


    @FXML
    private AnchorPane root; // Add this field

    @FXML
    private void initialize() {
        // Request focus for the root node to ensure key events are captured
        root.requestFocus();
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
