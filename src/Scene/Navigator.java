package Scene;

import Controller.facing;
import Player.basePlayer;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import Controller.gameState;

public class Navigator {

    basePlayer player1 = new basePlayer("P1",3,2);
    basePlayer player2 = new basePlayer("P2",5,5);

    private gameState GAMESTATE = gameState.PLAYER1_TURN;

    @FXML
    private Label drawValue;

    @FXML
    private Label guide;

    @FXML
    private ImageView playerOne;

    @FXML
    private ImageView playerTwo;

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
        diceRolled = true;

        if (GAMESTATE.equals(gameState.PLAYER1_TURN)) {
            player1.setDiceValue(diceRoll);
            player1.setMove(diceRoll);
        }
        else if (GAMESTATE.equals(gameState.PLAYER2_TURN)) {
            player2.setDiceValue(diceRoll);
            player2.setMove(diceRoll);
        }

        enableButton();
        resetDisplay();
        player1StatUpdate();
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
    private Rectangle up;

    @FXML
    private Rectangle left;

    @FXML
    private Rectangle right;

    @FXML
    private Rectangle down;

    private void disableButton() {
        up.setDisable(true); // Disable the rectangle
        left.setDisable(true);
        right.setDisable(true);
        down.setDisable(true);
    }

    private void enableButton() {
        up.setDisable(false); // Disable the rectangle
        left.setDisable(false);
        right.setDisable(false);
        down.setDisable(false);
    }

    @FXML
    private AnchorPane root; // Add this field

    public void startGame() {

        GAMESTATE = gameState.PLAYER1_TURN;
        player1.setPlayerFacing(facing.SOUTH);
        player2.setPlayerFacing(facing.NORTH);
//        player1StatUpdate();
    }
    public void decreaseMove() {
        if (GAMESTATE.equals(gameState.PLAYER1_TURN)) {

            if (player1.getMove() == 7) {
                notification("please roll dice first");
            } else {
                player1.setMove(player1.getMove() - 1);
                System.out.println("YOU GOT " + player1.getMove() + " LEFT");
            }

            if (player1.getMove() <= 0 ) {
                isAttack();
                player2.setMove(7);
                GAMESTATE = gameState.PLAYER2_TURN;
                System.out.println(GAMESTATE);
                disableButton();
            }
        }
        if (GAMESTATE.equals(gameState.PLAYER2_TURN)) {

            if (player2.getMove() == 7) {
                notification("please roll dice first");
            }

            player2.setMove(player2.getMove() - 1);
            System.out.println("YOU GOT " + player2.getMove() + " LEFT");

            if (player2.getMove() <= 0) {
                isAttack();
                player1.setMove(7);
                GAMESTATE = gameState.PLAYER1_TURN;
                System.out.println(GAMESTATE);
                disableButton();
            }
        }
    }

    public void updateFacing(facing face) {
        if (GAMESTATE.equals(gameState.PLAYER1_TURN)) {
            player1.setPlayerFacing(face);
        } else {
            player2.setPlayerFacing(face);
        }
    }

    @FXML
    public void UP() {
        ImageView currentPlayer = getCurrentPlayerRectangle();

        int rowIndex = GridPane.getRowIndex(currentPlayer);
        int colIndex = GridPane.getColumnIndex(currentPlayer);

        if (canMoveTo(rowIndex - 1, colIndex)) {
            File file = new File("C:/Users/sakol/Desktop/PMPJ/src/res/p4.png");
            Image image = new Image(file.toURI().toString());
            currentPlayer.setImage(image);
            GridPane.setRowIndex(currentPlayer, rowIndex - 1);
            updateFacing(facing.NORTH);
            decreaseMove();
        }
    }

    @FXML
    public void DOWN() {
        ImageView currentPlayer = getCurrentPlayerRectangle();

        int rowIndex = GridPane.getRowIndex(currentPlayer);
        int colIndex = GridPane.getColumnIndex(currentPlayer);

        if (canMoveTo(rowIndex + 1, colIndex)) {
            File file = new File("C:/Users/sakol/Desktop/PMPJ/src/res/p2.png");
            Image image = new Image(file.toURI().toString());
            currentPlayer.setImage(image);
            GridPane.setRowIndex(currentPlayer, rowIndex + 1);
            updateFacing(facing.SOUTH);
            decreaseMove();
        }
    }

    @FXML
    public void LEFT() {
        ImageView currentPlayer = getCurrentPlayerRectangle();

        int rowIndex = GridPane.getRowIndex(currentPlayer);
        int colIndex = GridPane.getColumnIndex(currentPlayer);

        if (canMoveTo(rowIndex, colIndex - 1)) {
            File file = new File("C:/Users/sakol/Desktop/PMPJ/src/res/p3.png");
            Image image = new Image(file.toURI().toString());
            currentPlayer.setImage(image);
            GridPane.setColumnIndex(currentPlayer, colIndex - 1);
            updateFacing(facing.WEST);
            decreaseMove();
        }
    }

    @FXML
    public void RIGHT() {
        ImageView currentPlayer = getCurrentPlayerRectangle();

        int rowIndex = GridPane.getRowIndex(currentPlayer);
        int colIndex = GridPane.getColumnIndex(currentPlayer);

        if (canMoveTo(rowIndex, colIndex + 1)) {
            File file = new File("C:/Users/sakol/Desktop/PMPJ/src/res/p1.png");
            Image image = new Image(file.toURI().toString());
            currentPlayer.setImage(image);
            GridPane.setColumnIndex(currentPlayer, colIndex + 1);
            updateFacing(facing.EAST);
            decreaseMove();
        }
    }

    private ImageView getCurrentPlayerRectangle() {
        return GAMESTATE.equals(gameState.PLAYER1_TURN) ? playerOne : playerTwo;
    }

    private boolean canMoveTo(int rowIndex, int colIndex) {
        // Check if the destination tile is within the grid bounds
        if (rowIndex >= 0 && rowIndex < layoutContainer.getRowCount() &&
                colIndex >= 0 && colIndex < layoutContainer.getColumnCount()) {
            // Check if the destination tile is already occupied by another player
            for (Node node : layoutContainer.getChildren()) {
                if (node instanceof ImageView && false) {
                    int nodeRowIndex = GridPane.getRowIndex(node);
                    int nodeColIndex = GridPane.getColumnIndex(node);
                    if (nodeRowIndex == rowIndex && nodeColIndex == colIndex) {
                        return false; // Destination tile is occupied
                    }
                }
            }
            return true; // Destination tile is not occupied
        }
        return false; // Destination tile is out of bounds
    }


    @FXML
    private ImageView p1_atk1;
    @FXML
    private ImageView p1_atk2;
    @FXML
    private ImageView p1_atk3;
    @FXML
    private ImageView p1_atk4;
    @FXML
    private ImageView p1_atk5;


    @FXML
    private ImageView p1_hp1;
    @FXML
    private ImageView p1_hp2;
    @FXML
    private ImageView p1_hp3;
    @FXML
    private ImageView p1_hp4;
    @FXML
    private ImageView p1_hp5;

    public void resetDisplay() {
        System.out.println("RESET DISPLAY");
        p1_hp1.setVisible(false);
        p1_hp2.setVisible(false);
        p1_hp3.setVisible(false);
        p1_hp4.setVisible(false);
        p1_hp5.setVisible(false);

        p1_atk1.setVisible(false);
        p1_atk2.setVisible(false);
        p1_atk3.setVisible(false);
        p1_atk4.setVisible(false);
        p1_atk5.setVisible(false);

    }

    public void player1StatUpdate() {
        int player1HP = player1.getHp();
        int player1ATK = player1.getAtk();

        if (player1ATK >= 1) p1_atk1.setVisible(true);
        if (player1ATK >= 2) p1_atk2.setVisible(true);
        if (player1ATK >= 3) p1_atk3.setVisible(true);
        if (player1ATK >= 4) p1_atk4.setVisible(true);
        if (player1ATK >= 5) p1_atk5.setVisible(true);

        if (player1HP >= 1) p1_hp1.setVisible(true);
        if (player1HP >= 2) p1_hp2.setVisible(true);
        if (player1HP >= 3) p1_hp3.setVisible(true);
        if (player1HP >= 4) p1_hp4.setVisible(true);
        if (player1HP >= 5) p1_hp5.setVisible(true);
    }

    public void player2StatUpdate() {
        int player2HP = player2.getHp();
        int player2ATK = player2.getAtk();

//        if (player2ATK >= 1) p2_atk1.setVisible(true);
//        if (player2ATK >= 2) p1_atk2.setVisible(true);
//        if (player2ATK >= 3) p1_atk3.setVisible(true);
//        if (player1ATK >= 4) p1_atk4.setVisible(true);
//        if (player1ATK >= 5) p1_atk5.setVisible(true);
//
//        if (player1HP >= 1) p1_hp1.setVisible(true);
//        if (player1HP >= 2) p1_hp2.setVisible(true);
//        if (player1HP >= 3) p1_hp3.setVisible(true);
//        if (player1HP >= 4) p1_hp4.setVisible(true);
//        if (player1HP >= 5) p1_hp5.setVisible(true);
    }


    @FXML
    private ImageView attack;

    public void isAttack() {
        basePlayer currentPlayer;
        basePlayer opponentPlayer;

        if (GAMESTATE.equals(gameState.PLAYER1_TURN)) {
            currentPlayer = player1;
            opponentPlayer = player2;
        } else {
            currentPlayer = player2;
            opponentPlayer = player1;
        }

        int currentPlayerRow = GridPane.getRowIndex(currentPlayer == player1 ? playerOne : playerTwo);
        int currentPlayerCol = GridPane.getColumnIndex(currentPlayer == player1 ? playerOne : playerTwo);
        int opponentPlayerRow = GridPane.getRowIndex(opponentPlayer == player1 ? playerOne : playerTwo);
        int opponentPlayerCol = GridPane.getColumnIndex(opponentPlayer == player1 ? playerOne : playerTwo);

        // Define the coordinates of adjacent tiles around the current player
        int[][] adjacentTiles = {
                {currentPlayerRow - 1, currentPlayerCol}, // Up
                {currentPlayerRow + 1, currentPlayerCol}, // Down
                {currentPlayerRow, currentPlayerCol - 1}, // Left
                {currentPlayerRow, currentPlayerCol + 1}  // Right
        };

        facing playerFacing = currentPlayer.getPlayerFacing();

        // Check if any adjacent tile contains the opponent player
        for (int[] tile : adjacentTiles) {
            int row = tile[0];
            int col = tile[1];

            if ((row == opponentPlayerRow && col == opponentPlayerCol)){
                // Check if the attacking player is facing the opponent
                if ((playerFacing == facing.NORTH && row < currentPlayerRow) ||
                        (playerFacing == facing.SOUTH && row > currentPlayerRow) ||
                        (playerFacing == facing.WEST && col < currentPlayerCol) ||
                        (playerFacing == facing.EAST && col > currentPlayerCol)) {
                    System.out.println(currentPlayer.getMove());
                    System.out.println("ATTACK!!!!!");
                    attack.setOpacity(1);
                    gotKnockback(currentPlayer , opponentPlayer , playerTwo);
                    return; // Attack detected, no need to check further
                } else {
                    // The player is not facing the opponent
                    System.out.println("Cannot attack, not facing the opponent.");
                    return;
                }
            }
        }
    }


    private void gotKnockback(basePlayer currentPlayer , basePlayer opponentPlayer, ImageView opponentImageView) {
        int currentPlayerRow = GridPane.getRowIndex(currentPlayer == player1 ? playerOne : playerTwo);
        int currentPlayerCol = GridPane.getColumnIndex(currentPlayer == player1 ? playerOne : playerTwo);
        int opponentPlayerRow = GridPane.getRowIndex(opponentPlayer == player1 ? playerOne : playerTwo);
        int opponentPlayerCol = GridPane.getColumnIndex(opponentPlayer == player1 ? playerOne : playerTwo);

        // Calculate the direction of knockback based on the current player's facing direction
        int knockbackRow = opponentPlayerRow;
        int knockbackCol = opponentPlayerCol;
        switch (currentPlayer.getPlayerFacing()) {
            case NORTH:
                knockbackRow--;
                break;
            case SOUTH:
                knockbackRow++;
                break;
            case WEST:
                knockbackCol--;
                break;
            case EAST:
                knockbackCol++;
                break;
        }

        // Update the position of the opponent player's ImageView to simulate knockback
        GridPane.setRowIndex(opponentImageView, knockbackRow);
        GridPane.setColumnIndex(opponentImageView, knockbackCol);
    }

}
