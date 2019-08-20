package ui;

import domain.Item;
import javafx.animation.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import main.Program;

public class TextAnimations {
    
    public TextAnimations(String text, String color, double locX, double locY) {
        
        Text fadeText = new Text(text);
        Color textColor = Color.RED;
        
        fadeText.setTranslateX(locX);
        fadeText.setTranslateY(locY);
        
        fadeText.yProperty().setValue(locY);
        
        if (color.equals("WHITE")) {
            textColor = Color.WHITE;
        }
        
        fadeText.setFill(textColor);
        
        Program.pane.getChildren().add(fadeText);
        
        KeyValue fadeStart = new KeyValue(fadeText.opacityProperty(), 100);
        KeyValue startLocY = new KeyValue(fadeText.yProperty(), 0);
        
        KeyValue moveLocY = new KeyValue(fadeText.yProperty(), -16);
        KeyValue fadeStop = new KeyValue(fadeText.opacityProperty(), 0);
        KeyFrame start = new KeyFrame(Duration.seconds(0), fadeStart, startLocY);
        KeyFrame end = new KeyFrame(Duration.seconds(1), fadeStop, moveLocY);
        
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.getKeyFrames().addAll(start, end);
        timeline.play();
    }
}
