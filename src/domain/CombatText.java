package domain;

import generator.DungeonGenerator;
import javafx.animation.FadeTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import main.Program;

public class CombatText {

    public CombatText(String text, String color, double locX, double locY) {

        Text fadeText = new Text(text);
        Color textColor = Color.RED;
       fadeText.setTranslateX(locX);
        fadeText.setTranslateY(locY);

        if (color.equals("WHITE")) {
            textColor = Color.WHITE;
        }

        fadeText.setFill(textColor);

        FadeTransition fade = new FadeTransition();

        fade.setCycleCount(1);
        fade.setFromValue(10);
        fade.setToValue(0);
        fade.setNode(fadeText);
        fade.setDuration(Duration.seconds(2));

        Program.pane.getChildren().add(fadeText);
        fade.play();
        

        fade.setOnFinished(event -> {
            event.consume();
            Program.pane.getChildren().remove(fadeText);
        });
    }
}
