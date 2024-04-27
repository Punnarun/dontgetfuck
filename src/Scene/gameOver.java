package Scene;

import gameData.gameState;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import gameData.player;

public class gameOver {

    @FXML Label winningPlayer;

    @FXML
    private void initialize() {
        if (player.getGameState().equals(gameState.PLAYER1_WIN)) {
            winningPlayer.setText("PLAYER1 WIN!");
        } else winningPlayer.setText("PLAYER2 WIN!");
    }
}
