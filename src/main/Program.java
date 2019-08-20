package main;

import controller.PlayerController;
import generator.DungeonGenerator;
import generator.ItemGenerator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ui.LoadingScreen;
import utils.Settings;

public class Program extends Application {
    
    public static Pane pane;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("2D Roguelike");
        primaryStage.show();
        
        LoadingScreen loadScreen = new LoadingScreen();
        Scene loadingScreen = loadScreen.getScreen();
        primaryStage.setScene(loadingScreen);

        this.pane = new Pane();
        DungeonGenerator dg = new DungeonGenerator();
        ItemGenerator ig = new ItemGenerator();
        
        Scene scene = new Scene(this.pane, Settings.WIDTH, Settings.HEIGHT);

        this.pane.getChildren().addAll(dg.getDungeonMap(), PlayerController.playerStats);

        scene.setOnKeyPressed(event -> {
            PlayerController.updatePlayerStats();
            PlayerController.movePlayer((KeyCode) event.getCode());
        });

        scene.setOnKeyReleased(event -> {
            PlayerController.updatePlayerStats();
        });

        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        Program.launch(args);
    }
}
