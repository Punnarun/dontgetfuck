// When user navigate to the shop the game data will be saved here so when player get back the game can be continue

package GameInstance;

import Player.BasePlayer;

public class GameData {

    private static GameState gameState;
    private static BasePlayer player1;
    private static BasePlayer player2;
    private static int player1Money;
    private static int player2Money;
    private static boolean hasPlayed = false;
    private static int numberOfMoveLeft;
    private static int turnNumber;
    private static String description;
    private static boolean isMusicPlayed = false;

    public static boolean getIsMusicPlayed() {
        return isMusicPlayed;
    }

    public static void setIsMusicPlayed(boolean musicPlayed) {
        GameData.isMusicPlayed = musicPlayed;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        GameData.description = description;
    }

    public static int getTurnNumber() {
        return turnNumber;
    }

    public static void setTurnNumber(int turnNumber) {
        GameData.turnNumber = turnNumber;
    }

    public static int getNumberOfMoveLeft() {
        return numberOfMoveLeft;
    }

    public static void setNumberOfMoveLeft(int moveLeft) {
        GameData.numberOfMoveLeft = moveLeft;
    }

    public static boolean isHavePlayed() {
        return hasPlayed;
    }

    public static void setHavePlayed(boolean hasPlayed) {
        GameData.hasPlayed = hasPlayed;
    }

    private static int[] player1Slot = new int[]{0, 0, 0, 0};
    private static int[] player2Slot = new int[]{0, 0, 0, 0};

    public static BasePlayer getPlayer1() {
        return player1;
    }

    public static int[] getPlayer1Slot() {
        return player1Slot;
    }

    public static void setPlayer1Slot(int[] player1Slot) {
        GameData.player1Slot = player1Slot;
    }

    public static int[] getPlayer2Slot() {
        return player2Slot;
    }

    public static void setPlayer2Slot(int[] player2Slot) {
        GameData.player2Slot = player2Slot;
    }

    public static void setPlayer1(BasePlayer player1) {
        GameData.player1 = player1;
    }

    public static BasePlayer getPlayer2() {
        return player2;
    }

    public static void setPlayer2(BasePlayer player2) {
        GameData.player2 = player2;
    }

    public static int getPlayer1Money() {
        return player1Money;
    }

    public static void setPlayer1Money(int player1Money) {
        GameData.player1Money = player1Money;
    }

    public static int getPlayer2Money() {
        return player2Money;
    }

    public static void setPlayer2Money(int player2Money) {
        GameData.player2Money = player2Money;
    }

    public static GameState getGameState() {
        return gameState;
    }

    public static void setGameState(GameState gameState) {
        GameData.gameState = gameState;
    }
}