package ir.ac.kntu;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

public class HelperAnimation {
    Pane pane;

    public HelperAnimation(Pane pane){
        this.pane=pane;
    }

    public  void doctorAnimation() {

        final int numFrames = 3;
        final double pauseBetweenFrames = 1;

        ImageView imageView = new ImageView();
        Timeline timeline = new Timeline();
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\doctor4.png"));
        images.add(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\doctor3.png"));
        images.add(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\doctor4.png"));
        //images.add(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\doctor2.png"));
        for (int i = 0; i < numFrames; i++) {
            timeline.getKeyFrames().add(
                    new KeyFrame(
                            Duration.seconds(i * pauseBetweenFrames),
                            new KeyValue(imageView.imageProperty(), images.get(i))
                    )
            );
        }
        imageView.setX(515);
        imageView.setY(194);
        imageView.setFitHeight(140);
        imageView.setFitWidth(70);
        timeline.setCycleCount(Timeline.INDEFINITE);
        //timeline.setAutoReverse(true);
        pane.getChildren().add(imageView);
        timeline.play();


    }

    public  void redAnimation() {

        final int numFrames = 5;
        final double pauseBetweenFrames = 0.3;
        ImageView imageView = new ImageView();
        Timeline timeline = new Timeline();
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\red3.png"));
        images.add(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\red2.png"));
        images.add(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\red1.png"));
        images.add(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\red2.png"));
        images.add(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\red3.png"));
        for (int i = 0; i < numFrames; i++) {
            timeline.getKeyFrames().add(
                    new KeyFrame(
                            Duration.seconds(i * pauseBetweenFrames),
                            new KeyValue(imageView.imageProperty(), images.get(i))
                    )
            );
        }
        imageView.setX(50);
        imageView.setY(440);
        imageView.setFitHeight(60);
        imageView.setFitWidth(60);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        pane.getChildren().add(imageView);
    }

    public void yellowAnimation() {
        final int numFrames = 5;
        final double pauseBetweenFrames = 0.3;
        ImageView imageView = new ImageView();
        Timeline timeline = new Timeline();
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\yellow3.png"));
        images.add(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\yellow5.png"));
        images.add(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\yellow1.png"));
        images.add(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\yellow5.png"));
        images.add(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\yellow3.png"));
        for (int i = 0; i < numFrames; i++) {
            timeline.getKeyFrames().add(
                    new KeyFrame(
                            Duration.seconds(i * pauseBetweenFrames),
                            new KeyValue(imageView.imageProperty(), images.get(i))
                    )
            );
        }
        imageView.setX(80);
        imageView.setY(509);
        imageView.setFitHeight(60);
        imageView.setFitWidth(60);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        pane.getChildren().add(imageView);

    }

    public  void blueAnimation() {
        final int numFrames = 5;
        final double pauseBetweenFrames = 0.3;
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
        imageView.setX(140);
        imageView.setY(447);
        imageView.setFitHeight(60);
        imageView.setFitWidth(60);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        pane.getChildren().add(imageView);
    }
}
