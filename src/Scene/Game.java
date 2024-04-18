package Scene;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Game extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Game.fxml"));
            Parent root = fxmlLoader.load();

            // Find the layout container (GridPane) in the FXML file
            GridPane layoutContainer = (GridPane) root.lookup("#layoutContainer");

            // Create ImageView elements and add them to the layout container
            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 6; col++) {
                    ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("../res/stone.jpg")));
                    imageView.setFitWidth(86.0);
                    imageView.setFitHeight(86.0);
                    imageView.setPreserveRatio(true);
                    imageView.setSmooth(true);
                    GridPane.setRowIndex(imageView, row);
                    GridPane.setColumnIndex(imageView, col);
                    GridPane.setHalignment(imageView, javafx.geometry.HPos.CENTER);
                    GridPane.setValignment(imageView, javafx.geometry.VPos.CENTER);
                    layoutContainer.getChildren().add(imageView);
                }
            }

            Polygon player1 = new Polygon();
            player1.getPoints().addAll(new Double[]{
                    0.0, 0.0,
                    100.0, 100.0, // Example points for triangle shape, adjust as needed
                    0.0, 100.0});
            player1.setFill(javafx.scene.paint.Color.RED); // Set the fill color

// Set player1 position in a separate cell from the ImageView elements
            GridPane.setRowIndex(player1, 0);
            GridPane.setColumnIndex(player1, 0);

            Polygon player2 = new Polygon();
            player2.getPoints().addAll(new Double[]{
                    0.0, 0.0,
                    100.0, 100.0, // Example points for triangle shape, adjust as needed
                    0.0, 100.0});
            player2.setFill(javafx.scene.paint.Color.BLUE); // Set the fill color

// Set player2 position in a separate cell from the ImageView elements
            GridPane.setRowIndex(player2, 5);
            GridPane.setColumnIndex(player2, 5);

// Add player1 and player2 polygons to layoutContainer
            layoutContainer.getChildren().addAll(player1, player2);



            Scene scene = new Scene(root, 1280, 720);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
