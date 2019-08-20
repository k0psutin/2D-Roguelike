package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import utils.Settings;

public class LoadingScreen extends BorderPane {
    
    private Scene scene;
      
    public LoadingScreen() {
       
        BorderPane mainScreen = new BorderPane();
        mainScreen.setBackground(new javafx.scene.layout.Background(new BackgroundFill[]{new BackgroundFill(javafx.scene.paint.Color.BLACK, null, null)}));
        
        Text loading = new Text("Generating stuff....");
        Text title = new Text("2D Rogue Java project.");
        Font font = new Font("Arial", 50.0);
        
        title.setFont(font);
        title.setFill(Color.WHITE);
        loading.setFill(Color.WHITE);
        
        mainScreen.setTop(title);
        mainScreen.setAlignment(title, Pos.CENTER);
        mainScreen.setAlignment(loading, Pos.CENTER);
        mainScreen.setCenter(loading);
        
        this.scene = new Scene(mainScreen, Settings.WIDTH, Settings.HEIGHT);
    }

    public Scene getScreen() {
        return this.scene;
    }
    
    
}
