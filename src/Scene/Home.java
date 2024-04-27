package Scene;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import Scene.Navigator;
//import sun.audio.AudioStream;

//import javax.print.attribute.standard.Media;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;

public class Home extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(root, 1280, 720);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
        launch(args);
    }
}

