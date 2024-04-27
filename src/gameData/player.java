package gameData;

import Player.basePlayer;

public class player {

    private static gameState gameState;
    private static basePlayer player1;
    private static basePlayer player2;
    private static int player1Money;
    private static int player2Money;
    private static boolean havePlayed = false;
    private static int moveLeft;
    private static int turnNumber;
    private static String description;
    private static boolean musicPlayed = false;
    private static int coinNumber;

    public static int getCoinNumber() {
        return coinNumber;
    }

    public static void setCoinNumber(int coinNumber) {
        player.coinNumber = coinNumber;
    }

    public static boolean getMusicPlayed() {
        return musicPlayed;
    }

    public static void setMusicPlayed(boolean musicPlayed) {
        player.musicPlayed = musicPlayed;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        player.description = description;
    }

    public static int getTurnNumber() {
        return turnNumber;
    }

    public static void setTurnNumber(int turnNumber) {
        player.turnNumber = turnNumber;
    }

    public static int getMoveLeft() {
        return moveLeft;
    }

    public static void setMoveLeft(int moveLeft) {
        player.moveLeft = moveLeft;
    }

    public static boolean isHavePlayed() {
        return havePlayed;
    }

    public static void setHavePlayed(boolean havePlayed) {
        player.havePlayed = havePlayed;
    }

    private static int[] player1Slot = new int[]{0, 0, 0, 0};
    private static int[] player2Slot = new int[]{0, 0, 0, 0};

    public static basePlayer getPlayer1() {
        return player1;
    }

    public static int[] getPlayer1Slot() {
        return player1Slot;
    }

    public static void setPlayer1Slot(int[] player1Slot) {
        player.player1Slot = player1Slot;
    }

    public static int[] getPlayer2Slot() {
        return player2Slot;
    }

    public static void setPlayer2Slot(int[] player2Slot) {
        player.player2Slot = player2Slot;
    }

    public static void setPlayer1(basePlayer player1) {
        player.player1 = player1;
    }

    public static basePlayer getPlayer2() {
        return player2;
    }

    public static void setPlayer2(basePlayer player2) {
        player.player2 = player2;
    }

    public static int getPlayer1Money() {
        return player1Money;
    }

    public static void setPlayer1Money(int player1Money) {
        player.player1Money = player1Money;
    }

    public static int getPlayer2Money() {
        return player2Money;
    }

    public static void setPlayer2Money(int player2Money) {
        player.player2Money = player2Money;
    }

    public static gameData.gameState getGameState() {
        return gameState;
    }

    public static void setGameState(gameData.gameState gameState) {
        player.gameState = gameState;
    }
}

