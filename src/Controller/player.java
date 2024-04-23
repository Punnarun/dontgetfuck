package Controller;

import Player.basePlayer;

public class player {

    private static basePlayer player1;
    private static basePlayer player2;

    public static basePlayer getPlayer1() {
        return player1;
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
}

