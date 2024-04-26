package Scene;

import Controller.facing;
import Player.basePlayer;
import Player.warrior;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import Scene.choosingPlayer;
import Controller.player;

public class Navigator {

    //player
    private basePlayer player1;
    private basePlayer player2;
    private basePlayer dummy;
    private gameState GAMESTATE = gameState.PLAYER1_TURN;

    @FXML private AnchorPane root;
    @FXML private Rectangle starter;

    Image facingUp = new Image((new File("C:/Users/sakol/Desktop/PMPJ/src/res/p4.png")).toURI().toString());
    Image facingDown = new Image((new File("C:/Users/sakol/Desktop/PMPJ/src/res/p2.png")).toURI().toString());
    Image facingLeft = new Image((new File("C:/Users/sakol/Desktop/PMPJ/src/res/p3.png")).toURI().toString());
    Image facingRight = new Image((new File("C:/Users/sakol/Desktop/PMPJ/src/res/p1.png")).toURI().toString());

    //other FXML component
    @FXML private Rectangle up;
    @FXML private Rectangle left;
    @FXML private Rectangle right;
    @FXML private Rectangle down;
    @FXML private Label drawValue;
    @FXML private ImageView playerOne;
    @FXML private ImageView playerTwo;
    @FXML private GridPane layoutContainer;
    @FXML private Label money;
    @FXML private ImageView attack;
    @FXML private Rectangle changeTurn;
    @FXML private Label turn;
    @FXML private ImageView drawDice;

    @FXML private ImageView p1_atk1;
    @FXML private ImageView p1_atk2;
    @FXML private ImageView p1_atk3;
    @FXML private ImageView p1_atk4;
    @FXML private ImageView p1_atk5;

    @FXML private ImageView p1_hp1;
    @FXML private ImageView p1_hp2;
    @FXML private ImageView p1_hp3;
    @FXML private ImageView p1_hp4;
    @FXML private ImageView p1_hp5;

    @FXML private ImageView p2_atk1;
    @FXML private ImageView p2_atk2;
    @FXML private ImageView p2_atk3;
    @FXML private ImageView p2_atk4;
    @FXML private ImageView p2_atk5;

    @FXML private ImageView p2_hp1;
    @FXML private ImageView p2_hp2;
    @FXML private ImageView p2_hp3;
    @FXML private ImageView p2_hp4;
    @FXML private ImageView p2_hp5;

    @FXML private ImageView coin1;
    @FXML private ImageView coin2;
    @FXML private ImageView coin3;
    @FXML private ImageView coin4;
    @FXML private ImageView coin5;
    @FXML private ImageView coin6;
    @FXML private ImageView coin7;
    @FXML private ImageView coin8;

    @FXML private Label turnCounter;

    @FXML private Button shop;

    private int turnNumber = 1;

    @FXML private void goToGame() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("character.fxml"));
        Parent gameRoot = loader.load();
        Scene gameScene = new Scene(gameRoot);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(gameScene);
    }

    @FXML private void notification(String text) {
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

    private int diceValue = 999;

    @FXML private void drawDice() {

        attack.setOpacity(0.25);

        drawDice.setDisable(true);
        drawDice.setOpacity(0.25);

        Random random = new Random();
        int diceRoll = random.nextInt(6) + 1;

        diceValue = diceRoll;

//        notification("You got " + diceRoll);
        turn.setText("Player 2 Turn : " + "You got " + diceRoll + " !");
        if (GAMESTATE.equals(gameState.PLAYER1_TURN)) turn.setText("Player 1 Turn : " + "You got " + diceRoll + " !");
        System.out.println("Dice rolled: " + diceRoll);

        if (GAMESTATE.equals(gameState.PLAYER1_TURN)) {
            player1.setDiceValue(diceRoll);
            player1.setMove(diceRoll);
        }
        else if (GAMESTATE.equals(gameState.PLAYER2_TURN)) {
            player2.setDiceValue(diceRoll);
            player2.setMove(diceRoll);
        }

        player.setMoveLeft(diceRoll);
        enableButton();
        shop.setDisable(true);
    }

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

    public void gameStart() {

        if (!player.isHavePlayed()) {
            System.out.println("YUNG MAI DAI PLAY");
            player1 = player.getPlayer1();
            player2 = player.getPlayer2();

            System.out.println(player1);
            System.out.println(player2);

            starter.setVisible(false);
            turn.setDisable(true);
            changeTurn.setDisable(true);
            coinInvisible();
            shop.setDisable(true);

            turnCounter.setText("Turn Number : 0");

            disableButton();

            System.out.println(GAMESTATE);
            player1.setPlayerFacing(facing.SOUTH);
            player2.setPlayerFacing(facing.NORTH);
            System.out.println("GAME START");
            System.out.println(player1.getMove());
            player1.setMove(0);
            player2.setMove(0);

            player1.setCurrentX(0);
            player1.setCurrentY(0);

            player2.setCurrentX(4);
            player2.setCurrentY(5);

            money.setText("x " + getCurrentBasePlayer().getMoney());

            player.setGameState(this.GAMESTATE);
            randomCoin();
            player.setHavePlayed(true);
            System.out.println("AFTER INIT  : " + player.isHavePlayed());
        } else {

            GAMESTATE = player.getGameState();

            drawDice.setDisable(true);
            drawDice.setOpacity(0.25);

            layoutContainer.getChildren().remove(playerOne);
            layoutContainer.getChildren().remove(playerTwo);

            starter.setVisible(false);
            player1 = player.getPlayer1();
            player2 = player.getPlayer2();

            player1.setMoney(player.getPlayer1Money());
            player2.setMoney(player.getPlayer2Money());

            layoutContainer.add(playerOne,player.getPlayer1().getCurrentY(),player.getPlayer1().getCurrentX());
            layoutContainer.add(playerTwo,player.getPlayer2().getCurrentY(),player.getPlayer2().getCurrentX());

            coinInvisible();
            shop.setDisable(true);

            if (player.getGameState().equals(gameState.PLAYER1_TURN)) {
                player1.setMove(player.getMoveLeft());
                System.out.println("player 1 move is " + player1.getMove());
                if (player1.getMove() == 0) {
                    disableButton();
                    shop.setDisable(false);
                };
            } else {
                player2.setMove(player.getMoveLeft());
                System.out.println("player 2 move is " + player2.getMove());
                if (player2.getMove() == 0) {
                    disableButton();
                    shop.setDisable(false);
                };
            }

            //update facing

            facing player1Facing = player1.getPlayerFacing();
            facing player2Facing = player2.getPlayerFacing();

            if (player1Facing.equals(facing.NORTH)) {
                playerOne.setImage(facingUp);
            } else if (player1Facing.equals(facing.EAST)) {
                playerOne.setImage(facingRight);
            } else if (player1Facing.equals(facing.SOUTH)) {
                playerOne.setImage(facingDown);
            } else if (player1Facing.equals(facing.WEST)) {
                playerOne.setImage(facingLeft);
            }

            if (player2Facing.equals(facing.NORTH)) {
                playerTwo.setImage(facingUp);
            } else if (player2Facing.equals(facing.EAST)) {
                playerTwo.setImage(facingRight);
            } else if (player2Facing.equals(facing.SOUTH)) {
                playerTwo.setImage(facingDown);
            } else if (player2Facing.equals(facing.WEST)) {
                playerTwo.setImage(facingLeft);
            }
        }

        turnNumber = player.getTurnNumber();
        turnCounter.setText("Turn Number : " + turnNumber);

        turn.setText(player.getDescription());

        player.setPlayer1Money(player1.getMoney());
        player.setPlayer2Money(player2.getMoney());
        resetDisplay();
        player1StatUpdate();
        player2StatUpdate();
        randomCoin();
        updatePlayerSlot();

    }

    public void decreaseMove() {

        System.out.println("PLAYER1 ROW: " + player1.getCurrentX());
        System.out.println("PLAYER1 COL: " + player1.getCurrentY());
        System.out.println("PLAYER2 ROW: " + player2.getCurrentX());
        System.out.println("PLAYER2 COL: " + player2.getCurrentY());


        if (GAMESTATE.equals(gameState.PLAYER1_TURN)) {
            System.out.println(player1.getMove());
            if (player1.getMove() == 7) {
                notification("please roll dice first");
            } else {
                player1.setMove(player1.getMove() - 1);
                System.out.println("YOU GOT " + player1.getMove() + " LEFT");
                turn.setText("Player 1 Turn : " + "You got " + player1.getMove() + " move(s) left !");
                detectDeath(getCurrentBasePlayer().getCurrentX(), getCurrentBasePlayer().getCurrentY() , player1);
                isAttackable();
                money.setText("x " + String.valueOf(getCurrentBasePlayer().getMoney()));
                player.setPlayer1Money(getCurrentBasePlayer().getMoney());
                checkCoin();
                player.setMoveLeft(player1.getMove());
            }

            if (player1.getMove() <= 0 ) {
                changeTurn.setDisable(false);
                player2.setMove(7);
                shop.setDisable(false);
                disableButton();
            }
        }
        if (GAMESTATE.equals(gameState.PLAYER2_TURN)) {

            if (player2.getMove() == 7) {
                notification("please roll dice first");
            } else {
                player2.setMove(player2.getMove() - 1);
                System.out.println("YOU GOT " + player2.getMove() + " LEFT");
                turn.setText("Player 2 Turn : " + "You got " + player2.getMove() + " move(s) left !");
                detectDeath(getCurrentBasePlayer().getCurrentX(), getCurrentBasePlayer().getCurrentY() , player2);
                isAttackable();
                money.setText("x " + String.valueOf(getCurrentBasePlayer().getMoney()));
                player.setPlayer2Money(getCurrentBasePlayer().getMoney());
                checkCoin();
                player.setMoveLeft(player2.getMove());
            }

            if (player2.getMove() <= 0) {
                changeTurn.setDisable(false);
                player1.setMove(7);
                shop.setDisable(false);
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

    @FXML public void UP() {
        ImageView currentPlayer = getCurrentPlayerRectangle();
        basePlayer player = (GAMESTATE.equals(gameState.PLAYER1_TURN) ? player1 : player2);

        int rowIndex = player.getCurrentX();
        int colIndex = player.getCurrentY();

        if (canMoveTo(rowIndex - 1, colIndex) && (player.getMove() > 0) && (player.getPlayerFacing() != facing.SOUTH)) {

            if (rowIndex <= 0) {
                detectDeath(1,1, getCurrentBasePlayer());
                return;
            }

//            File file = new File("C:/Users/sakol/Desktop/PMPJ/src/res/p4.png");
//            Image image = new Image(file.toURI().toString());
            currentPlayer.setImage(facingUp);
            GridPane.setRowIndex(currentPlayer, rowIndex - 1);
            player.setCurrentX(rowIndex - 1);
            player.setCurrentY(colIndex);

            System.out.println("MOVING TO X: " + player.getCurrentX() );
            System.out.println("MOVING TO Y: " + player.getCurrentY() );

            updateFacing(facing.NORTH);
            decreaseMove();
        }
    }

    @FXML public void DOWN() {
        ImageView currentPlayer = getCurrentPlayerRectangle();
        basePlayer player = (GAMESTATE.equals(gameState.PLAYER1_TURN) ? player1 : player2);

        int rowIndex = player.getCurrentX();
        int colIndex = player.getCurrentY();

        if ((canMoveTo(rowIndex + 1, colIndex)) && (player.getPlayerFacing() != facing.NORTH)) {

            if (rowIndex >= 4) {
                detectDeath(1,1 , getCurrentBasePlayer());
                return;
            }

//            File file = new File("C:/Users/sakol/Desktop/PMPJ/src/res/p2.png");
//            Image image = new Image(file.toURI().toString());
            currentPlayer.setImage(facingDown);
            GridPane.setRowIndex(currentPlayer, rowIndex + 1);
            player.setCurrentX(rowIndex + 1);
            player.setCurrentY(colIndex);

            System.out.println("MOVING TO : " + player.getCurrentX() );
            System.out.println("MOVING TO : " + player.getCurrentY() );

            updateFacing(facing.SOUTH);
            decreaseMove();
        }
    }

    @FXML public void LEFT() {
        ImageView currentPlayer = getCurrentPlayerRectangle();
        basePlayer player = (GAMESTATE.equals(gameState.PLAYER1_TURN) ? player1 : player2);

        int rowIndex = player.getCurrentX();
        int colIndex = player.getCurrentY();

        if ((canMoveTo(rowIndex, colIndex - 1)) && (player.getPlayerFacing() != facing.EAST)) {

            if (colIndex <= 0) {
                detectDeath(1,1 , getCurrentBasePlayer());
                return;
            }

//            File file = new File("C:/Users/sakol/Desktop/PMPJ/src/res/p3.png");
//            Image image = new Image(file.toURI().toString());
            currentPlayer.setImage(facingLeft);
            GridPane.setColumnIndex(currentPlayer, colIndex - 1);
            player.setCurrentX(rowIndex);
            player.setCurrentY(colIndex -1 );

            System.out.println("MOVING TO : " + player.getCurrentX() );
            System.out.println("MOVING TO : " + player.getCurrentY() );

            updateFacing(facing.WEST);
            decreaseMove();
        }
    }

    @FXML public void RIGHT() {
        ImageView currentPlayer = getCurrentPlayerRectangle();
        basePlayer player = (GAMESTATE.equals(gameState.PLAYER1_TURN) ? player1 : player2);

        int rowIndex = player.getCurrentX();
        int colIndex = player.getCurrentY();

        if ((canMoveTo(rowIndex, colIndex + 1)) && (player.getPlayerFacing() != facing.WEST)) {

            if (colIndex >= 5) {
                detectDeath(1,1 , getCurrentBasePlayer());
                return;
            }

//            File file = new File("C:/Users/sakol/Desktop/PMPJ/src/res/p1.png");
//            Image image = new Image(file.toURI().toString());
            currentPlayer.setImage(facingRight);
            GridPane.setColumnIndex(currentPlayer, colIndex + 1);
            player.setCurrentX(rowIndex);
            player.setCurrentY(colIndex + 1 );

            System.out.println("MOVING TO : " + player.getCurrentX() );
            System.out.println("MOVING TO : " + player.getCurrentY() );


            updateFacing(facing.EAST);
            decreaseMove();
        }
    }

    private ImageView getCurrentPlayerRectangle() {
        return GAMESTATE.equals(gameState.PLAYER1_TURN) ? playerOne : playerTwo;
    }

    private ImageView getOpponentPlayer() {
        return GAMESTATE.equals(gameState.PLAYER1_TURN) ? playerTwo : playerOne;
    }

    private basePlayer getOpponentBasePlayer() {
        return GAMESTATE.equals(gameState.PLAYER1_TURN) ? player2 : player1;
    }

    private basePlayer getCurrentBasePlayer() {
        return GAMESTATE.equals(gameState.PLAYER1_TURN) ? player1 : player2;
    }

    private boolean canMoveTo(int rowIndex, int colIndex) {
        if (rowIndex >= -1 && rowIndex < layoutContainer.getRowCount()+1 &&
                colIndex >= -1 && colIndex < layoutContainer.getColumnCount()+1) {

            if ((getOpponentBasePlayer().getCurrentX() == rowIndex) && (getOpponentBasePlayer().getCurrentY() == colIndex)) {
                System.out.println("HEY");
                return false;
            }
            return true;
        }
        return false; // Destination tile is out of bounds
    }

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

        p2_atk1.setVisible(false);
        p2_atk2.setVisible(false);
        p2_atk3.setVisible(false);
        p2_atk4.setVisible(false);
        p2_atk5.setVisible(false);

        p2_hp1.setVisible(false);
        p2_hp2.setVisible(false);
        p2_hp3.setVisible(false);
        p2_hp4.setVisible(false);
        p2_hp5.setVisible(false);
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

        if (player2ATK >= 1) p2_atk1.setVisible(true);
        if (player2ATK >= 2) p2_atk2.setVisible(true);
        if (player2ATK >= 3) p2_atk3.setVisible(true);
        if (player2ATK >= 4) p2_atk4.setVisible(true);
        if (player2ATK >= 5) p2_atk5.setVisible(true);

        if (player2HP >= 1) p2_hp1.setVisible(true);
        if (player2HP >= 2) p2_hp2.setVisible(true);
        if (player2HP >= 3) p2_hp3.setVisible(true);
        if (player2HP >= 4) p2_hp4.setVisible(true);
        if (player2HP >= 5) p2_hp5.setVisible(true);
    }

    public void isAttackable() {

        int currentPlayerRow = getCurrentBasePlayer().getCurrentX();
        int currentPlayerCol = getCurrentBasePlayer().getCurrentY();
        int opponentPlayerRow = getOpponentBasePlayer().getCurrentX();
        int opponentPlayerCol = getOpponentBasePlayer().getCurrentY();

        // Define the coordinates of adjacent tiles around the current player
        int[][] adjacentTiles = {
                {currentPlayerRow - 1, currentPlayerCol}, // Up
                {currentPlayerRow + 1, currentPlayerCol}, // Down
                {currentPlayerRow, currentPlayerCol - 1}, // Left
                {currentPlayerRow, currentPlayerCol + 1}  // Right
        };

        facing playerFacing = getCurrentBasePlayer().getPlayerFacing();

        // Check if any adjacent tile contains the opponent player
        for (int[] tile : adjacentTiles) {
            int row = tile[0];
            int col = tile[1];

            System.out.println("CHECKING..........");

            if ((row == opponentPlayerRow && col == opponentPlayerCol) && (getCurrentBasePlayer().getMove() == 0)) {
                if ((playerFacing == facing.NORTH && row < currentPlayerRow) ||
                        (playerFacing == facing.SOUTH && row > currentPlayerRow) ||
                        (playerFacing == facing.WEST && col < currentPlayerCol) ||
                        (playerFacing == facing.EAST && col > currentPlayerCol)) {
//                    System.out.println(getCurrentBasePlayer().getMove());
                    System.out.println("ATTACK!!!!!");
                    attack.setOpacity(1);
                }
            }
        }
    }

    public void isAttack() {

        int currentPlayerRow = getCurrentBasePlayer().getCurrentX();
        int currentPlayerCol = getCurrentBasePlayer().getCurrentY();
        int opponentPlayerRow = getOpponentBasePlayer().getCurrentX();
        int opponentPlayerCol = getOpponentBasePlayer().getCurrentY();

        // Define the coordinates of adjacent tiles around the current player
        int[][] adjacentTiles = {
                {currentPlayerRow - 1, currentPlayerCol}, // Up
                {currentPlayerRow + 1, currentPlayerCol}, // Down
                {currentPlayerRow, currentPlayerCol - 1}, // Left
                {currentPlayerRow, currentPlayerCol + 1}  // Right
        };

        facing playerFacing = getCurrentBasePlayer().getPlayerFacing();

        // Check if any adjacent tile contains the opponent player
        for (int[] tile : adjacentTiles) {
            int row = tile[0];
            int col = tile[1];

            System.out.println("CHECKING..........");

            if ((row == opponentPlayerRow && col == opponentPlayerCol) && (getCurrentBasePlayer().getMove() == 0)){
                if ((playerFacing == facing.NORTH && row < currentPlayerRow) ||
                        (playerFacing == facing.SOUTH && row > currentPlayerRow) ||
                        (playerFacing == facing.WEST && col < currentPlayerCol) ||
                        (playerFacing == facing.EAST && col > currentPlayerCol)) {
//                    System.out.println(getCurrentBasePlayer().getMove());
                    System.out.println("ATTACK!!!!!");
                    attack.setOpacity(0.25);
                    gotKnockback(getCurrentBasePlayer() , getOpponentBasePlayer() , getOpponentPlayer());

                    getOpponentBasePlayer().setHp(getOpponentBasePlayer().getHp() - getCurrentBasePlayer().getAtk());
                    System.out.println("CurrentPlayerHP" + getCurrentBasePlayer().getHp() );
                    System.out.println("OpponentPlayerHP" + getOpponentBasePlayer().getHp() );

                    System.out.println("Attack Power of Player " + getCurrentBasePlayer().getAtk());

                    System.out.println("ATTACKABLE");

                    resetDisplay();
                    player1StatUpdate();
                    player2StatUpdate();
                    detectDeath(getOpponentBasePlayer().getCurrentX() , getOpponentBasePlayer().getCurrentY() , getOpponentBasePlayer());
//                    return;
                } else {
                    System.out.println("Cannot attack, not facing the opponent.");
                    return;
                }
            }
        }
    }

    private void gotKnockback(basePlayer currentPlayer , basePlayer opponentPlayer, ImageView opponentImageView) {

        System.out.println("--------calculating knockback--------");

        int currentPlayerRow = getCurrentBasePlayer().getCurrentX();
        int currentPlayerCol = getCurrentBasePlayer().getCurrentY();
        int opponentPlayerRow = getOpponentBasePlayer().getCurrentX();
        int opponentPlayerCol = getOpponentBasePlayer().getCurrentY();

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

        if (knockbackRow < 0 || knockbackCol < 0 || knockbackCol > 5 || knockbackRow > 4) {
            System.out.println("CHECK THIS CASE");
            detectDeath(1,1 , getOpponentBasePlayer());
        }

        // Update the position of the opponent player's ImageView to simulate knockback
        else {

            detectDeath(knockbackRow , knockbackCol , getOpponentBasePlayer());

            GridPane.setRowIndex(opponentImageView, knockbackRow);
            GridPane.setColumnIndex(opponentImageView, knockbackCol);

            getOpponentBasePlayer().setCurrentX(knockbackRow);
            getOpponentBasePlayer().setCurrentY(knockbackCol);

            System.out.println("Opponent Got throw to X : " +  getOpponentBasePlayer().getCurrentX());
            System.out.println("Opponent Got throw to Y : " +  getOpponentBasePlayer().getCurrentY());

        }


    }

    @FXML private void changePlayerTurn() {

        if (GAMESTATE.equals(gameState.PLAYER1_TURN)) {
            if (player1.getMove() != 0) {
                changeTurn.setDisable(true);
                return;
            };
        } else  {
            if (player2.getMove() != 0) {
                changeTurn.setDisable(true);
                return;
            };
        }
        turnNumber++;
        turnCounter.setText("Turn Number : " + turnNumber);
        drawDice.setOpacity(1);
        drawDice.setDisable(false);

        if (GAMESTATE.equals(gameState.PLAYER1_TURN)) {
            GAMESTATE = gameState.PLAYER2_TURN;
            System.out.println("PLAYER 2 CurrentX : " + player2.getCurrentX());
            System.out.println("PLAYER 2 CurrentY : " + player2.getCurrentY());
            turn.setText("PLAYER2 TURN");
            shop.setDisable(true);
        } else {
            GAMESTATE = gameState.PLAYER1_TURN;
            System.out.println("PLAYER 1 CurrentX : " + player1.getCurrentX());
            System.out.println("PLAYER 1 CurrentY : " + player1.getCurrentY());
            turn.setText("PLAYER1 TURN");
            shop.setDisable(true);
        }

        if (turnNumber % 10 == 0) {
            player1.usePassiveSkill();
            player2.usePassiveSkill();
            resetDisplay();
            player1StatUpdate();
            player2StatUpdate();
        }

        money.setText("x " +String.valueOf(getCurrentBasePlayer().getMoney()));
        updatePlayerSlot();
        System.out.println("CHANGE TURNNNNN");
        player.setGameState(this.GAMESTATE);
    }

    private void detectDeath(int rowIndex , int colIndex , basePlayer checkDeathOfThisPlayer) {

        checkCoin();

        if (((rowIndex == 1 && colIndex == 1) || (rowIndex == 4 && colIndex == 2) || (rowIndex == 0 && colIndex == 3) || (rowIndex == 3 && colIndex == 4)) && checkDeathOfThisPlayer.equals(player1)) {player.setGameState(gameState.PLAYER2_WIN);}
        else if (((rowIndex == 1 && colIndex == 1) || (rowIndex == 4 && colIndex == 2) || (rowIndex == 0 && colIndex == 3) || (rowIndex == 3 && colIndex == 4)) && checkDeathOfThisPlayer.equals(player2)) {player.setGameState(gameState.PLAYER1_WIN);}
        else if (((rowIndex >= 5 || colIndex >= 6) || (rowIndex < 0 || colIndex < 0)) && checkDeathOfThisPlayer.equals(player1)) {player.setGameState(gameState.PLAYER2_WIN);}
        else if (((rowIndex >= 5 || colIndex >= 6) || (rowIndex < 0 || colIndex < 0)) && checkDeathOfThisPlayer.equals(player2)) {player.setGameState(gameState.PLAYER1_WIN);}
        else if (player1.getHp() <= 0) { player.setGameState(gameState.PLAYER2_WIN);}
        else if (player2.getHp() <= 0) { player.setGameState(gameState.PLAYER1_WIN);}
        else return;

        try {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Scene/GameOver.fxml"))));
        } catch (IOException e) {
        }
    }

    //debug button
    @FXML private void debug() {
        System.out.println("GOTO SHOPPPPP");
        player.setPlayer1(player1);
        player.setPlayer2(player2);
        player.setPlayer1Money(player1.getMoney());
        player.setPlayer2Money(player2.getMoney());
        player.setGameState(GAMESTATE);
        player.setTurnNumber(turnNumber);
        player.setDescription(turn.getText());

        try {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Scene/shop.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int[][] coinPos = new int[][]{{0,0}, {4,0}, {2,1}, {0,2}, {4,3}, {2,4}, {0,5}, {4,5}};

    private int currentCoinPosX;
    private int currentCoinPosY;

    @FXML private void coinInvisible() {
        coin1.setVisible(false);
        coin2.setVisible(false);
        coin3.setVisible(false);
        coin4.setVisible(false);
        coin5.setVisible(false);
        coin6.setVisible(false);
        coin7.setVisible(false);
        coin8.setVisible(false);
    }

    @FXML private void randomCoin() {
        Random random = new Random();
        int coinIndex = random.nextInt(8);

        while ((coinPos[coinIndex][0] == getCurrentBasePlayer().getCurrentX() && coinPos[coinIndex][1] == getCurrentBasePlayer().getCurrentY()) ||
                (coinPos[coinIndex][0] == getOpponentBasePlayer().getCurrentX() && coinPos[coinIndex][1] == getOpponentBasePlayer().getCurrentY())) {
            coinIndex = random.nextInt(8);
        }

        System.out.println("COIN index: " + coinIndex);
        System.out.println("x : " + coinPos[coinIndex][0]);
        System.out.println("y : " + coinPos[coinIndex][1]);

        coinInvisible(); // First, make all coins invisible
        if (coinIndex == 0) {
            coin1.setVisible(true);
        } else if (coinIndex == 1) {
            coin2.setVisible(true);
        } else if (coinIndex == 2) {
            coin3.setVisible(true);
        } else if (coinIndex == 3) {
            coin4.setVisible(true);
        } else if (coinIndex == 4) {
            coin5.setVisible(true);
        } else if (coinIndex == 5) {
            coin6.setVisible(true);
        } else if (coinIndex == 6) {
            coin7.setVisible(true);
        } else if (coinIndex == 7) {
            coin8.setVisible(true);
        }

        currentCoinPosX = coinPos[coinIndex][0];
        currentCoinPosY = coinPos[coinIndex][1];
    }

    private void checkCoin() {

        System.out.println("player posX " + getCurrentBasePlayer().getCurrentX());
        System.out.println("player posY " + getCurrentBasePlayer().getCurrentY());
        System.out.println("coin pos " + currentCoinPosX);
        System.out.println("coin pos " + currentCoinPosY);


        if (currentCoinPosX == getCurrentBasePlayer().getCurrentX() && currentCoinPosY == getCurrentBasePlayer().getCurrentY()) {
            coinInvisible();
            randomCoin();
            getCurrentBasePlayer().setMoney(getCurrentBasePlayer().getMoney() + 1);
        }
    }

    @FXML private Label winningTicket;
    @FXML private Label diceAmount;
    @FXML private Label robAmount;
    @FXML private Label appleAmount;

    private void updatePlayerSlot() {
//        System.out.println("slot update");
        if (GAMESTATE.equals(gameState.PLAYER1_TURN)) {
            money.setText("x " + player.getPlayer1Money());
            appleAmount.setText("x " + player.getPlayer1Slot()[0]);
            robAmount.setText("x " + player.getPlayer1Slot()[1]);
            diceAmount.setText("x " + player.getPlayer1Slot()[2]);
            winningTicket.setText("x " + player.getPlayer1Slot()[3]);
        } else if (GAMESTATE.equals(gameState.PLAYER2_TURN)) {
            money.setText("x " + player.getPlayer2Money());
            appleAmount.setText("x " + player.getPlayer2Slot()[0]);
            robAmount.setText("x " + player.getPlayer2Slot()[1]);
            diceAmount.setText("x " + player.getPlayer2Slot()[2]);
            winningTicket.setText("x " + player.getPlayer2Slot()[3]);
        }
    }

    private void updateAmount(int slot) {
        if (GAMESTATE.equals(gameState.PLAYER1_TURN)) {
            int[] inventory = player.getPlayer1Slot();
            if (slot == 0) {
                inventory[0]--;
                player.setPlayer1Slot(inventory);
            } else if (slot == 1) {
                inventory[1]--;
                player.setPlayer1Slot(inventory);
            } else if (slot == 2) {
                inventory[2]--;
                player.setPlayer1Slot(inventory);
            } else if (slot == 3) {
                inventory[3]--;
                player.setPlayer1Slot(inventory);
            }
        } else if (GAMESTATE.equals(gameState.PLAYER2_TURN)) {
            int[] inventory = player.getPlayer2Slot();
            if (slot == 0) {
                inventory[0]--;
                player.setPlayer2Slot(inventory);
            } else if (slot == 1) {
                inventory[1]--;
                player.setPlayer2Slot(inventory);
            } else if (slot == 2) {
                inventory[2]--;
                player.setPlayer2Slot(inventory);
            } else if (slot == 3) {
                inventory[3]--;
                player.setPlayer2Slot(inventory);
            }
        }
        updatePlayerSlot();
    }

    private int getItem(int slotNumber) {
        if (GAMESTATE.equals(gameState.PLAYER1_TURN)) {
            return player.getPlayer1Slot()[slotNumber];
        } else {
            return player.getPlayer2Slot()[slotNumber];
        }
    }



    @FXML private void useTicket() {
        //using player got 66% to win and lose instantly
        if ((getItem(3) <= 0)) return;
        Random random = new Random();
        int rand = random.nextInt(10);

        gameState userGameState = null;
        gameState opponentGamestate = null;
        if (getCurrentBasePlayer().equals(player1)) {
            userGameState = gameState.PLAYER1_WIN;
            opponentGamestate = gameState.PLAYER2_WIN;
        };
        if (getCurrentBasePlayer().equals(player2)) {
            userGameState = gameState.PLAYER2_WIN;
            opponentGamestate = gameState.PLAYER1_WIN;
        }
        if (rand >= 0 && rand < 7) player.setGameState(userGameState);
        else player.setGameState(opponentGamestate);

        try {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Scene/GameOver.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private void useDice() {
        if ((getItem(2) <= 0)) return;
        System.out.println("USE DICE");
        if (getCurrentBasePlayer().getMove() == 0) {
            drawDice.setOpacity(1);
            drawDice.setDisable(false);
            updateAmount(2);
        }
    }

    @FXML private void useRob() {
        if (getItem(1) <= 0) return;
        getCurrentBasePlayer().setMoney(getCurrentBasePlayer().getMoney() + (int)(getOpponentBasePlayer().getMoney()/2));
        getOpponentBasePlayer().setMoney((int)(getOpponentBasePlayer().getMoney()/2));
        player.setPlayer1Money(player1.getMoney());
        player.setPlayer2Money(player2.getMoney());

        System.out.println("PLAYER 1 MONEY" + player1.getMoney());
        System.out.println("PLAYER 2 MONEY" + player2.getMoney());
        updatePlayerSlot();
        updateAmount(1);
        money.setText("x " + String.valueOf(getCurrentBasePlayer().getMoney()));
    }

    @FXML private void useApple() {
        System.out.println("USE APPLE");
        if ((getItem(0) <= 0 ) || getCurrentBasePlayer().getHp() >= 5) return;
        System.out.println("HP BEFORE ADD" + getCurrentBasePlayer().getHp());
        getCurrentBasePlayer().setHp(5);
        System.out.println("HP AFTER ADD" + getCurrentBasePlayer().getHp());
        resetDisplay();
        player1StatUpdate();
        player2StatUpdate();
        updateAmount(0);
    }
}