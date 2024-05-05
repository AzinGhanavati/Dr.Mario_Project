package ir.ac.kntu;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HelperForGmae {
    public static void printScore(Pane pane, Player player){
        Label label = new Label("TOP");
        label.setFont(Font.font("Impact", FontWeight.EXTRA_BOLD, 30));
        label.setTextFill(javafx.scene.paint.Color.BLACK);
        label.setLayoutX(50);
        label.setLayoutY(130);
        Label l = new Label("0010000");
        l.setFont(Font.font("Impact", FontWeight.EXTRA_BOLD, 27));
        l.setLayoutX(50);
        l.setLayoutY(160);
        Label label1 = new Label("SCORE");
        label1.setFont(Font.font("Impact", FontWeight.EXTRA_BOLD, 30));
        label1.setTextFill(javafx.scene.paint.Color.BLACK);
        label1.setLayoutX(50);
        label1.setLayoutY(210);
        Label l1 = new Label(String.valueOf(player.getScore()));
        l1.setFont(Font.font("Impact", FontWeight.EXTRA_BOLD, 27));
        l1.setLayoutX(50);
        l1.setLayoutY(240);
        pane.getChildren().add(l);
        pane.getChildren().add(label);
        pane.getChildren().add(l1);
        pane.getChildren().add(label1);
    }
}
