package Scene;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create AnchorPane as the root
        AnchorPane root = new AnchorPane();
        root.setPrefSize(1280, 720);

        // Load castle background image
        Image castleBgImage = new Image(getClass().getResourceAsStream("../res/castleBg.png"));
        ImageView castleBgImageView = new ImageView(castleBgImage);
        castleBgImageView.setFitWidth(1280);
        castleBgImageView.setFitHeight(720);
        castleBgImageView.setOpacity(0.6);

        // Create warrior group
        Group warriorGroup = new Group();
        warriorGroup.setLayoutX(50);
        warriorGroup.setLayoutY(210);
        warriorGroup.setOnMouseClicked(event -> onClickWarrior());
        warriorGroup.setOnMouseEntered(event -> onEnter());
        warriorGroup.setOnMouseExited(event -> onExit());

        // Create warrior rectangle
        Rectangle warriorRectangle = new Rectangle(9, 9, 325, 443);
        warriorRectangle.setArcWidth(5);
        warriorRectangle.setArcHeight(5);
        warriorRectangle.setFill(Color.web("#e7e7e7"));
        warriorRectangle.setOpacity(0.8);
        warriorRectangle.setStroke(Color.BLACK);
        warriorRectangle.setEffect(new DropShadow());

        // Create warrior image
        Image warriorImage = new Image(getClass().getResourceAsStream("../res/warrior.png"));
        ImageView warriorImageView = new ImageView(warriorImage);
        warriorImageView.setLayoutX(68);
        warriorImageView.setLayoutY(153);
        warriorImageView.setFitWidth(208);
        warriorImageView.setFitHeight(308);
        warriorImageView.setEffect(new ColorAdjust(0, 0, 0.17, 0));

        // Create warrior labels
        Label warriorNameLabel = new Label("Warrior");
        warriorNameLabel.setLayoutX(90);
        warriorNameLabel.setLayoutY(45);
        warriorNameLabel.setFont(new Font("Californian FB Bold", 48));

        Label warriorSkillLabel = new Label("-Special Skill-");
        warriorSkillLabel.setLayoutX(107);
        warriorSkillLabel.setLayoutY(110);
        warriorSkillLabel.setFont(new Font("Californian FB", 24));

        // Add components to warrior group
        warriorGroup.getChildren().addAll(warriorRectangle, warriorImageView, warriorNameLabel, warriorSkillLabel);

        // Add warrior group to root
        root.getChildren().addAll(castleBgImageView, warriorGroup);

        // Create scene and set it to the stage
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    private void onClickWarrior() {
        System.out.println("Warrior clicked!");
    }

    private void onEnter() {
        System.out.println("Mouse entered!");
    }

    private void onExit() {
        System.out.println("Mouse exited!");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
