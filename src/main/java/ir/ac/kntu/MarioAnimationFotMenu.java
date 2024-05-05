package ir.ac.kntu;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

public class MarioAnimationFotMenu implements Runnable {
    private Pane pane;

    public MarioAnimationFotMenu(Pane pane) {
        this.pane = pane;
    }

    @Override
    public void run() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                blueAnimation();
                doctorAnimation();
            }
        });

    }

    public void doctorAnimation() {

        final int numFrames = 3;
        final int pauseBetweenFrames = 1;

        ImageView imageView = new ImageView();
        Timeline timeline = new Timeline();
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\doctor1.png"));
        images.add(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\doctor2.png"));
        images.add(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\doctor1.png"));
        //images.add(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\doctor2.png"));
        for (int i = 0; i < numFrames; i++) {
            timeline.getKeyFrames().add(
                    new KeyFrame(
                            Duration.seconds(i * pauseBetweenFrames),
                            new KeyValue(imageView.imageProperty(), images.get(i))
                    )
            );
        }
        imageView.setX(110);
        imageView.setY(448);
        imageView.setFitHeight(100);
        imageView.setFitWidth(50);
        timeline.setCycleCount(Timeline.INDEFINITE);
        //timeline.setAutoReverse(true);
        timeline.play();
        pane.getChildren().add(imageView);


    }

    public void blueAnimation() {
        final int numFrames = 5;
        final int pauseBetweenFrames = 1;
        ImageView imageView = new ImageView();
        Timeline timeline = new Timeline();
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\blue1.png"));
        images.add(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\blue2.png"));
        images.add(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\blue3.png"));
        images.add(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\blue2.png"));
        images.add(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\blue1.png"));
        for (int i = 0; i < numFrames; i++) {
            timeline.getKeyFrames().add(
                    new KeyFrame(
                            Duration.seconds(i * pauseBetweenFrames),
                            new KeyValue(imageView.imageProperty(), images.get(i))
                    )
            );
        }
        imageView.setX(510);
        imageView.setY(490);
        imageView.setFitHeight(57);
        imageView.setFitWidth(57);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        pane.getChildren().add(imageView);
    }
}
