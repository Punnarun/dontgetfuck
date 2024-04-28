package Scene;

import GameInstance.Facing;
import Item.*;
import Player.BasePlayer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;
import GameInstance.GameState;
import GameInstance.GameData;

public class GameLogic {

    //player
    private BasePlayer player1;
    private BasePlayer player2;
    private GameState GAMESTATE = GameState.PLAYER1_TURN;

    //Image of player facing to each Direction
    private Image facingUp = new Image(getClass().getResource("/Resource/p4.png").toString());
    private Image facingDown = new Image(getClass().getResource("/Resource/p2.png").toString());
    private Image facingLeft = new Image(getClass().getResource("/Resource/p3.png").toString());
    private Image facingRight = new Image(getClass().getResource("/Resource/p1.png").toString());

    @FXML private AnchorPane root;
    @FXML private Rectangle starter;

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
    @FXML private Label changeTurnLabel;
    @FXML private Label turn;
    @FXML private ImageView drawDice;
    @FXML private Rectangle diceBox;

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

    @FXML private ImageView upimg;
    @FXML private ImageView leftimg;
    @FXML private ImageView rightimg;
    @FXML private ImageView downimg;

    @FXML private Label turnCounter;

//    @FXML private Button shop;
    @FXML private ImageView cart;
    @FXML private Rectangle shopRec;

    private int turnNumber = 1;

    @FXML private void goToGame() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Character.fxml"));
        Parent gameRoot = loader.load();
        Scene gameScene = new Scene(gameRoot);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(gameScene);
    }

    @FXML private void drawDice() {

        Random random = new Random();
        int diceRoll = random.nextInt(6) + 1;

        getCurrentBasePlayer().setDiceValue(diceRoll);
        getCurrentBasePlayer().setMoveLeft(diceRoll);
        GameData.setNumberOfMoveLeft(diceRoll);
        enableButton();

        //update GUI and GameFlow
        turn.setText("Player 2 Turn : " + "You got " + diceRoll + " !");
        if (GAMESTATE.equals(GameState.PLAYER1_TURN)) turn.setText("Player 1 Turn : " + "You got " + diceRoll + " !");

        cart.setDisable(true);
        cart.setOpacity(0.25);
        shopRec.setDisable(true);
        attack.setOpacity(0.25);
        drawDice.setDisable(true);
        diceBox.setDisable(true);
        drawDice.setOpacity(0.25);
    }

    //disable player control button
    private void disableButton() {
        up.setDisable(true);
        left.setDisable(true);
        right.setDisable(true);
        down.setDisable(true);

        upimg.setDisable(true);
        leftimg.setDisable(true);
        rightimg.setDisable(true);
        downimg.setDisable(true);
    }

    //enable player control button
    private void enableButton() {
        up.setDisable(false);
        left.setDisable(false);
        right.setDisable(false);
        down.setDisable(false);

        upimg.setDisable(false);
        leftimg.setDisable(false);
        rightimg.setDisable(false);
        downimg.setDisable(false);
    }

    public void gameStart() {

        if (!GameData.isHavePlayed()) {
            player1 = GameData.getPlayer1();
            player2 = GameData.getPlayer2();

            //button control
            starter.setVisible(false);
            turn.setDisable(true);
            changeTurn.setDisable(true);
            changeTurnLabel.setDisable(true);
            coinInvisible();
            cart.setDisable(true);
            cart.setOpacity(0.25);
            shopRec.setDisable(true);
            disableButton();
            turnCounter.setText("Turn Number : 0");

            //initial player
            player1.setPlayerFacing(Facing.SOUTH);
            player2.setPlayerFacing(Facing.NORTH);

            player1.setMoveLeft(0);
            player2.setMoveLeft(0);

            player1.setCurrentX(0);
            player1.setCurrentY(0);

            player2.setCurrentX(4);
            player2.setCurrentY(5);

            money.setText("x " + getCurrentBasePlayer().getMoney());

            GameData.setGameState(this.GAMESTATE);
            randomCoin();
            GameData.setHavePlayed(true);

        } else {

            GAMESTATE = GameData.getGameState();

            drawDice.setDisable(true);
            diceBox.setDisable(true);
            drawDice.setOpacity(0.25);
            starter.setVisible(false);

            layoutContainer.getChildren().remove(playerOne);
            layoutContainer.getChildren().remove(playerTwo);

            player1 = GameData.getPlayer1();
            player2 = GameData.getPlayer2();

            player1.setMoney(GameData.getPlayer1Money());
            player2.setMoney(GameData.getPlayer2Money());

            layoutContainer.add(playerOne, GameData.getPlayer1().getCurrentY(), GameData.getPlayer1().getCurrentX());
            layoutContainer.add(playerTwo, GameData.getPlayer2().getCurrentY(), GameData.getPlayer2().getCurrentX());

            coinInvisible();
            cart.setDisable(true);
            cart.setOpacity(0.25);
            shopRec.setDisable(true);


            if (GameData.getGameState().equals(GameState.PLAYER1_TURN)) {
                player1.setMoveLeft(GameData.getNumberOfMoveLeft());
                if (player1.getMoveLeft() == 0) {
                    disableButton();
                    cart.setDisable(false);
                    cart.setOpacity(1);
                    shopRec.setDisable(false);
                };
            } else {
                player2.setMoveLeft(GameData.getNumberOfMoveLeft());

                if (player2.getMoveLeft() == 0) {
                    disableButton();
                    cart.setDisable(false);
                    cart.setOpacity(1);
                    shopRec.setDisable(false);
                };
            }

            //update facing
            Facing player1Facing = player1.getPlayerFacing();
            Facing player2Facing = player2.getPlayerFacing();

            if (player1Facing.equals(Facing.NORTH)) {
                playerOne.setImage(facingUp);
            } else if (player1Facing.equals(Facing.EAST)) {
                playerOne.setImage(facingRight);
            } else if (player1Facing.equals(Facing.SOUTH)) {
                playerOne.setImage(facingDown);
            } else if (player1Facing.equals(Facing.WEST)) {
                playerOne.setImage(facingLeft);
            }

            if (player2Facing.equals(Facing.NORTH)) {
                playerTwo.setImage(facingUp);
            } else if (player2Facing.equals(Facing.EAST)) {
                playerTwo.setImage(facingRight);
            } else if (player2Facing.equals(Facing.SOUTH)) {
                playerTwo.setImage(facingDown);
            } else if (player2Facing.equals(Facing.WEST)) {
                playerTwo.setImage(facingLeft);
            }
        }

        turnNumber = GameData.getTurnNumber();
        turnCounter.setText("Turn Number : " + turnNumber);

        turn.setText(GameData.getDescription());

        GameData.setPlayer1Money(player1.getMoney());
        GameData.setPlayer2Money(player2.getMoney());
        resetDisplay();
        player1StatUpdate();
        player2StatUpdate();
        randomCoin();
        updatePlayerSlot();
    }

    private void decreaseMove() {

        if (GAMESTATE.equals(GameState.PLAYER1_TURN)) {

            player1.setMoveLeft(player1.getMoveLeft() - 1);
            turn.setText("Player 1 Turn : " + "You got " + player1.getMoveLeft() + " move(s) left !");
            detectDeath(getCurrentBasePlayer().getCurrentX(), getCurrentBasePlayer().getCurrentY() , player1);
            isAttackable();
            money.setText("x " + String.valueOf(getCurrentBasePlayer().getMoney()));
            GameData.setPlayer1Money(getCurrentBasePlayer().getMoney());
            GameData.setNumberOfMoveLeft(player1.getMoveLeft());
            checkCoin();

            if (player1.getMoveLeft() <= 0 ) {
                changeTurn.setDisable(false);
                changeTurnLabel.setDisable(false);
                player2.setMoveLeft(7);
                cart.setDisable(false);
                cart.setOpacity(1);
                shopRec.setDisable(false);
                disableButton();
            }
        }

        if (GAMESTATE.equals(GameState.PLAYER2_TURN)) {

            player2.setMoveLeft(player2.getMoveLeft() - 1);
            turn.setText("Player 2 Turn : " + "You got " + player2.getMoveLeft() + " move(s) left !");
            detectDeath(getCurrentBasePlayer().getCurrentX(), getCurrentBasePlayer().getCurrentY() , player2);
            isAttackable();
            money.setText("x " + String.valueOf(getCurrentBasePlayer().getMoney()));
            GameData.setPlayer2Money(getCurrentBasePlayer().getMoney());
            GameData.setNumberOfMoveLeft(player2.getMoveLeft());
            checkCoin();

            if (player2.getMoveLeft() <= 0) {
                changeTurn.setDisable(false);
                changeTurnLabel.setDisable(false);
                player1.setMoveLeft(7);
                cart.setDisable(false);
                cart.setOpacity(1);
                shopRec.setDisable(false);
                disableButton();

            }
        }
    }

    private void updateFacing(Facing face) {
        if (GAMESTATE.equals(GameState.PLAYER1_TURN)) {
            player1.setPlayerFacing(face);
        } else {
            player2.setPlayerFacing(face);
        }
    }

    @FXML private void UP() {
        ImageView currentPlayer = getCurrentPlayerImage();
        BasePlayer player = (GAMESTATE.equals(GameState.PLAYER1_TURN) ? player1 : player2);

        int rowIndex = player.getCurrentX();
        int colIndex = player.getCurrentY();

        if (canMoveTo(rowIndex - 1, colIndex) && (player.getMoveLeft() > 0) && (player.getPlayerFacing() != Facing.SOUTH)) {

            if (rowIndex <= 0) {
                detectDeath(1,1, getCurrentBasePlayer());
                return;
            }

            currentPlayer.setImage(facingUp);
            GridPane.setRowIndex(currentPlayer, rowIndex - 1);
            player.setCurrentX(rowIndex - 1);
            player.setCurrentY(colIndex);
            updateFacing(Facing.NORTH);
            decreaseMove();
        }
    }

    @FXML private void DOWN() {
        ImageView currentPlayer = getCurrentPlayerImage();
        BasePlayer player = (GAMESTATE.equals(GameState.PLAYER1_TURN) ? player1 : player2);

        int rowIndex = player.getCurrentX();
        int colIndex = player.getCurrentY();

        if ((canMoveTo(rowIndex + 1, colIndex)) && (player.getPlayerFacing() != Facing.NORTH)) {

            if (rowIndex >= 4) {
                detectDeath(1,1 , getCurrentBasePlayer());
                return;
            }

            currentPlayer.setImage(facingDown);
            GridPane.setRowIndex(currentPlayer, rowIndex + 1);
            player.setCurrentX(rowIndex + 1);
            player.setCurrentY(colIndex);
            updateFacing(Facing.SOUTH);
            decreaseMove();
        }
    }

    @FXML private void LEFT() {
        ImageView currentPlayer = getCurrentPlayerImage();
        BasePlayer player = (GAMESTATE.equals(GameState.PLAYER1_TURN) ? player1 : player2);

        int rowIndex = player.getCurrentX();
        int colIndex = player.getCurrentY();

        if ((canMoveTo(rowIndex, colIndex - 1)) && (player.getPlayerFacing() != Facing.EAST)) {

            if (colIndex <= 0) {
                detectDeath(1,1 , getCurrentBasePlayer());
                return;
            }

            currentPlayer.setImage(facingLeft);
            GridPane.setColumnIndex(currentPlayer, colIndex - 1);
            player.setCurrentX(rowIndex);
            player.setCurrentY(colIndex -1 );
            updateFacing(Facing.WEST);
            decreaseMove();
        }
    }

    @FXML private void RIGHT() {
        ImageView currentPlayer = getCurrentPlayerImage();
        BasePlayer player = (GAMESTATE.equals(GameState.PLAYER1_TURN) ? player1 : player2);

        int rowIndex = player.getCurrentX();
        int colIndex = player.getCurrentY();

        if ((canMoveTo(rowIndex, colIndex + 1)) && (player.getPlayerFacing() != Facing.WEST)) {

            if (colIndex >= 5) {
                detectDeath(1,1 , getCurrentBasePlayer());
                return;
            }

            currentPlayer.setImage(facingRight);
            GridPane.setColumnIndex(currentPlayer, colIndex + 1);
            player.setCurrentX(rowIndex);
            player.setCurrentY(colIndex + 1 );
            updateFacing(Facing.EAST);
            decreaseMove();
        }
    }

    private ImageView getCurrentPlayerImage() {
        return GAMESTATE.equals(GameState.PLAYER1_TURN) ? playerOne : playerTwo;
    }

    private ImageView getOpponentPlayerImage() {
        return GAMESTATE.equals(GameState.PLAYER1_TURN) ? playerTwo : playerOne;
    }

    private BasePlayer getOpponentBasePlayer() {
        return GAMESTATE.equals(GameState.PLAYER1_TURN) ? player2 : player1;
    }

    private BasePlayer getCurrentBasePlayer() {
        return GAMESTATE.equals(GameState.PLAYER1_TURN) ? player1 : player2;
    }

    private boolean canMoveTo(int rowIndex, int colIndex) {
        if (rowIndex >= -1 && rowIndex < layoutContainer.getRowCount() + 1 &&
                colIndex >= -1 && colIndex < layoutContainer.getColumnCount() + 1) {

            if ((getOpponentBasePlayer().getCurrentX() == rowIndex) && (getOpponentBasePlayer().getCurrentY() == colIndex)) {
                return false;
            }
            return true;
        }
        return false;
    }

    private void resetDisplay() {
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

    private void player1StatUpdate() {
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

    private void player2StatUpdate() {
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

    private void isAttackable() {

        int currentPlayerRow = getCurrentBasePlayer().getCurrentX();
        int currentPlayerCol = getCurrentBasePlayer().getCurrentY();
        int opponentPlayerRow = getOpponentBasePlayer().getCurrentX();
        int opponentPlayerCol = getOpponentBasePlayer().getCurrentY();

        int[][] adjacentTiles = {
                {currentPlayerRow - 1, currentPlayerCol}, // Up
                {currentPlayerRow + 1, currentPlayerCol}, // Down
                {currentPlayerRow, currentPlayerCol - 1}, // Left
                {currentPlayerRow, currentPlayerCol + 1}  // Right
        };

        Facing playerFacing = getCurrentBasePlayer().getPlayerFacing();

        for (int[] tile : adjacentTiles) {
            int row = tile[0];
            int col = tile[1];

            if ((row == opponentPlayerRow && col == opponentPlayerCol) && (getCurrentBasePlayer().getMoveLeft() == 0)) {
                if ((playerFacing == Facing.NORTH && row < currentPlayerRow) ||
                        (playerFacing == Facing.SOUTH && row > currentPlayerRow) ||
                        (playerFacing == Facing.WEST && col < currentPlayerCol) ||
                        (playerFacing == Facing.EAST && col > currentPlayerCol)) {
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

        int[][] adjacentTiles = {
                {currentPlayerRow - 1, currentPlayerCol}, // Up
                {currentPlayerRow + 1, currentPlayerCol}, // Down
                {currentPlayerRow, currentPlayerCol - 1}, // Left
                {currentPlayerRow, currentPlayerCol + 1}  // Right
        };

        Facing playerFacing = getCurrentBasePlayer().getPlayerFacing();

        for (int[] tile : adjacentTiles) {
            int row = tile[0];
            int col = tile[1];

            if ((row == opponentPlayerRow && col == opponentPlayerCol) && (getCurrentBasePlayer().getMoveLeft() == 0)){
                if ((playerFacing == Facing.NORTH && row < currentPlayerRow) ||
                        (playerFacing == Facing.SOUTH && row > currentPlayerRow) ||
                        (playerFacing == Facing.WEST && col < currentPlayerCol) ||
                        (playerFacing == Facing.EAST && col > currentPlayerCol)) {
                    attack.setOpacity(0.25);
                    gotKnockback(getCurrentBasePlayer() , getOpponentBasePlayer() , getOpponentPlayerImage());

                    getOpponentBasePlayer().setHp(getOpponentBasePlayer().getHp() - getCurrentBasePlayer().getAtk());
                    resetDisplay();
                    player1StatUpdate();
                    player2StatUpdate();
                    detectDeath(getOpponentBasePlayer().getCurrentX() , getOpponentBasePlayer().getCurrentY() , getOpponentBasePlayer());
                }
            }
        }
    }

    private void gotKnockback(BasePlayer currentPlayer , BasePlayer opponentPlayer, ImageView opponentImageView) {

        int opponentPlayerRow = getOpponentBasePlayer().getCurrentX();
        int opponentPlayerCol = getOpponentBasePlayer().getCurrentY();

        int knockbackRow = opponentPlayerRow;
        int knockbackCol = opponentPlayerCol;
        switch (currentPlayer.getPlayerFacing()) {
            case NORTH: knockbackRow--; break;
            case SOUTH: knockbackRow++; break;
            case WEST:  knockbackCol--; break;
            case EAST:  knockbackCol++; break;
        }

        if (knockbackRow < 0 || knockbackCol < 0 || knockbackCol > 5 || knockbackRow > 4) {
            detectDeath(1,1 , getOpponentBasePlayer());
            return;
        }

        if ((knockbackRow == 1 && knockbackCol == 1) || (knockbackRow == 4 && knockbackCol == 2) || (knockbackRow == 0 && knockbackCol == 3) || (knockbackRow == 3 && knockbackCol == 4)) {
            detectDeath(1,1 , getOpponentBasePlayer());
            return;
        }

        else {

            detectDeath(knockbackRow , knockbackCol , getOpponentBasePlayer());

            GridPane.setRowIndex(opponentImageView, knockbackRow);
            GridPane.setColumnIndex(opponentImageView, knockbackCol);

            getOpponentBasePlayer().setCurrentX(knockbackRow);
            getOpponentBasePlayer().setCurrentY(knockbackCol);

            knockbackWithCoin();

        }


    }

    @FXML private void changePlayerTurn() {

        if (GAMESTATE.equals(GameState.PLAYER1_TURN)) {
            if (player1.getMoveLeft() != 0) {
                changeTurn.setDisable(true);
                changeTurnLabel.setDisable(true);
                return;
            };
        } else  {
            if (player2.getMoveLeft() != 0) {
                changeTurn.setDisable(true);
                changeTurnLabel.setDisable(true);
                return;
            };
        }
        turnNumber++;
        turnCounter.setText("Turn Number : " + turnNumber);
        drawDice.setOpacity(1);
        diceBox.setDisable(false);
        drawDice.setDisable(false);

        if (GAMESTATE.equals(GameState.PLAYER1_TURN)) {
            GAMESTATE = GameState.PLAYER2_TURN;

            turn.setText("PLAYER2 TURN");
            cart.setDisable(true);
            cart.setOpacity(0.25);
            shopRec.setDisable(true);
        } else {
            GAMESTATE = GameState.PLAYER1_TURN;

            turn.setText("PLAYER1 TURN");
            cart.setDisable(true);
            cart.setOpacity(0.25);
            shopRec.setDisable(true);
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

        GameData.setGameState(this.GAMESTATE);
    }

    private void detectDeath(int rowIndex , int colIndex , BasePlayer checkDeathOfThisPlayer) {

        checkCoin();

        if (((rowIndex == 1 && colIndex == 1) || (rowIndex == 4 && colIndex == 2) || (rowIndex == 0 && colIndex == 3) || (rowIndex == 3 && colIndex == 4)) && checkDeathOfThisPlayer.equals(player1)) {
            GameData.setGameState(GameState.PLAYER2_WIN);}
        else if (((rowIndex == 1 && colIndex == 1) || (rowIndex == 4 && colIndex == 2) || (rowIndex == 0 && colIndex == 3) || (rowIndex == 3 && colIndex == 4)) && checkDeathOfThisPlayer.equals(player2)) {
            GameData.setGameState(GameState.PLAYER1_WIN);}
        else if (((rowIndex >= 5 || colIndex >= 6) || (rowIndex < 0 || colIndex < 0)) && checkDeathOfThisPlayer.equals(player1)) {
            GameData.setGameState(GameState.PLAYER2_WIN);}
        else if (((rowIndex >= 5 || colIndex >= 6) || (rowIndex < 0 || colIndex < 0)) && checkDeathOfThisPlayer.equals(player2)) {
            GameData.setGameState(GameState.PLAYER1_WIN);}
        else if (player1.getHp() <= 0) { GameData.setGameState(GameState.PLAYER2_WIN);}
        else if (player2.getHp() <= 0) { GameData.setGameState(GameState.PLAYER1_WIN);}
        else return;

        try {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("GameOver.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveGameDate() {
        GameData.setPlayer1(player1);
        GameData.setPlayer2(player2);
        GameData.setPlayer1Money(player1.getMoney());
        GameData.setPlayer2Money(player2.getMoney());
        GameData.setGameState(GAMESTATE);
        GameData.setTurnNumber(turnNumber);
        GameData.setDescription(turn.getText());
    }

    @FXML private void goToShop() throws IOException {
        saveGameDate();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Shop.fxml"));
        Parent gameRoot = loader.load();
        Scene gameScene = new Scene(gameRoot);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(gameScene);
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

        coinInvisible();
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

        if (currentCoinPosX == getCurrentBasePlayer().getCurrentX() && currentCoinPosY == getCurrentBasePlayer().getCurrentY()) {
            coinInvisible();
            randomCoin();
            getCurrentBasePlayer().setMoney(getCurrentBasePlayer().getMoney() + 1);
        }
    }

    private void knockbackWithCoin() {

        int opponentX = getOpponentBasePlayer().getCurrentX();
        int opponentY = getOpponentBasePlayer().getCurrentY();

        if (opponentX == currentCoinPosX && opponentY == currentCoinPosY) {
            coinInvisible();
            randomCoin();
            getOpponentBasePlayer().setMoney(getOpponentBasePlayer().getMoney() + 1);
        }
    }

    @FXML private Label winningTicket;
    @FXML private Label diceAmount;
    @FXML private Label robAmount;
    @FXML private Label appleAmount;

    private void updatePlayerSlot() {

        if (GAMESTATE.equals(GameState.PLAYER1_TURN)) {
            money.setText("x " + GameData.getPlayer1Money());
            appleAmount.setText("x " + GameData.getPlayer1Slot()[0]);
            robAmount.setText("x " + GameData.getPlayer1Slot()[1]);
            diceAmount.setText("x " + GameData.getPlayer1Slot()[2]);
            winningTicket.setText("x " + GameData.getPlayer1Slot()[3]);
        } else if (GAMESTATE.equals(GameState.PLAYER2_TURN)) {
            money.setText("x " + GameData.getPlayer2Money());
            appleAmount.setText("x " + GameData.getPlayer2Slot()[0]);
            robAmount.setText("x " + GameData.getPlayer2Slot()[1]);
            diceAmount.setText("x " + GameData.getPlayer2Slot()[2]);
            winningTicket.setText("x " + GameData.getPlayer2Slot()[3]);
        }
    }

    private int getItem(int slotNumber) {
        if (GAMESTATE.equals(GameState.PLAYER1_TURN)) {
            return GameData.getPlayer1Slot()[slotNumber];
        } else {
            return GameData.getPlayer2Slot()[slotNumber];
        }
    }

    @FXML private void useTicket() {
        if ((getItem(3) <= 0)) return;
        saveGameDate();

        Ticket ticket = new Ticket();
        ticket.useEffect();

        try {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("GameOver.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private void useDice() {

        saveGameDate();

        if ((getItem(2) <= 0)) return;
        if (getCurrentBasePlayer().getMoveLeft() == 0) {

            Dice dice = new Dice();
            dice.useEffect();
            drawDice.setOpacity(1);
            diceBox.setDisable(false);
            drawDice.setDisable(false);
            updatePlayerSlot();
        }
    }

    @FXML private void useRob() {

        saveGameDate();

        if (getItem(1) <= 0) return;
        Robbing rob = new Robbing();
        rob.useEffect();

        money.setText("x " + String.valueOf(getCurrentBasePlayer().getMoney()));
        updatePlayerSlot();
    }

    @FXML private void useApple() {

        saveGameDate();

        if ((getItem(0) <= 0 ) || getCurrentBasePlayer().getHp() >= 5) return;
        Apple apple = new Apple();
        apple.useEffect();
        player1 = GameData.getPlayer1();
        player2 = GameData.getPlayer2();

        resetDisplay();
        player1StatUpdate();
        player2StatUpdate();
        updatePlayerSlot();
    }

    private AudioClip song = new AudioClip(getClass().getResource("/Resource/revengeSong.mp3").toExternalForm());

    @FXML private ImageView unmute;
    @FXML private ImageView mute;

    @FXML private void playMusic() {
        if (!GameData.getIsMusicPlayed()) {
            song.play();
            song.setCycleCount(10);
            unmute.setVisible(true);
            mute.setVisible(false);
            GameData.setIsMusicPlayed(true);
        } else {
            song.stop();
            GameData.setIsMusicPlayed(false);
            unmute.setVisible(false);
            mute.setVisible(true);
        }
    }

}