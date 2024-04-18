package Controller;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Polygon;

public class player {
    private Polygon player1;
    private GridPane layoutContainer;
    private int player1Row = 0; // Initial row position of Player 1
    private int player1Col = 0; // Initial column position of Player 1

    public GameController(GridPane layoutContainer) {
        this.layoutContainer = layoutContainer;
        // Create Player 1 polygon and add it to the layout container
        player1 = createPlayerPolygon();
        layoutContainer.getChildren().add(player1);
    }

    // Method to create the Player 1 polygon
    private Polygon createPlayerPolygon() {
        Polygon player = new Polygon();
        player.getPoints().addAll(new Double[]{
                0.0, 0.0,
                100.0, 100.0, // Example points for triangle shape, adjust as needed
                0.0, 100.0});
        player.setFill(javafx.scene.paint.Color.RED); // Set the fill color

        // Set initial position of Player 1 in the grid
        GridPane.setRowIndex(player, player1Row);
        GridPane.setColumnIndex(player, player1Col);

        return player;
    }

    // Method to handle key press events
    public void handleKeyPress(KeyCode code) {
        switch (code) {
            case W:
                movePlayerUp();
                break;
            case A:
                // Implement movement to the left
                break;
            case S:
                // Implement movement down
                break;
            case D:
                // Implement movement to the right
                break;
            default:
                // Ignore other key presses
                break;
        }
    }

    // Method to move Player 1 up
    private void movePlayerUp() {
        if (player1Row > 0) { // Check if player is not already at the top row
            player1Row--;
            GridPane.setRowIndex(player1, player1Row);
        }
    }

}
