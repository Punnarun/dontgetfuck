package Scene;

import GameInstance.GameState;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import GameInstance.GameData;

public class GameOver {

    @FXML Label winningPlayer;

    @FXML
    private void initialize() {
        if (GameData.getGameState().equals(GameState.PLAYER1_WIN)) {
            winningPlayer.setText("PLAYER1 WIN!");
        } else winningPlayer.setText("PLAYER2 WIN!");
    }
}
