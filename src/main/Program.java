package main;

import controller.PlayerController;
import generator.DungeonGenerator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.Settings;

public class Program extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        DungeonGenerator dg = new DungeonGenerator();
        Scene scene = new Scene(pane, Settings.WIDTH, Settings.HEIGHT);
        pane.getChildren().add(dg.getDungeonMap());
        scene.setOnKeyPressed(event -> PlayerController.movePlayer((KeyCode) event.getCode()));
        primaryStage.setTitle("Test project");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Program.launch(args);
    }
}
