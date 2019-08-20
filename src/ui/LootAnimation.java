package ui;

import domain.Item;
import javafx.animation.*;
import javafx.geometry.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.util.Duration;
import main.Program;

public class LootAnimation {
    
    public LootAnimation(Item item, double locX, double locY) {
        
        Font font = new Font("Arial", 15.0);
        
        VBox icon = new VBox();
        VBox desc = new VBox();
        HBox itemBox = new HBox();

        itemBox.setPadding(new Insets(20.0, 20.0, 20.0, 20.0));
        itemBox.setAlignment(Pos.BASELINE_CENTER);
        itemBox.setSpacing(10.0);
        
//        icon.setOpacity(0);
//        desc.setOpacity(0);
//        itemBox.setOpacity(0);

        ImageView itemImage = new ImageView(item.getImage());

        itemImage.setFitWidth(35.0);
        itemImage.setFitHeight(35.0);

        Text description = new Text(item.toString());
        
        description.setFill(Color.WHITE);
        description.setFont(font);

        icon.getChildren().add(itemImage);
        desc.getChildren().add(description);
        itemBox.getChildren().addAll(icon, desc);
        
        itemBox.setTranslateX(locX - 100);
        itemBox.setTranslateY(locY);
        
        Program.pane.getChildren().add(itemBox);
        
        KeyValue fadeStart = new KeyValue(itemBox.opacityProperty(), 75);
        KeyValue startLocY = new KeyValue(itemBox.translateYProperty(), locY);
        
        KeyValue moveLocY = new KeyValue(itemBox.translateYProperty(), locY + 16);
        KeyValue fadeStop = new KeyValue(itemBox.opacityProperty(), 0);
        KeyFrame start = new KeyFrame(Duration.seconds(0), fadeStart, startLocY);
        KeyFrame end = new KeyFrame(Duration.millis(2200), fadeStop, moveLocY);
        
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.getKeyFrames().addAll(start, end);
        timeline.play();
    }
}
