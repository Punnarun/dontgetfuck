package Scene;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

//import javax.print.attribute.standard.Media;
import java.io.File;

public class Home extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(root,1280,720);
            primaryStage.setScene(scene);
            primaryStage.show();

            String musicFile = "C:/Users/sakol/Desktop/PMPJ/src/res/bgMusic.mp3";
            Media sound = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);

            // Set the music to loop indefinitely
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

            // Play the music
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

