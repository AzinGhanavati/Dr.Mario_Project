package ir.ac.kntu;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Pill {
    private ImageView pillImageView;

    private double xPos;

    private double yPos;

    private boolean isHorizontal;

    private InnerColor color;

    public Pill(ImageView pill, double xPos, double yPos) {
        this.pillImageView = pill;
        pillImageView.setY(yPos);
        pillImageView.setX(xPos);
        pillImageView.setFitWidth(21.625);
        pillImageView.setFitHeight(25.312);
        this.xPos = xPos;
        this.yPos = yPos;
        this.isHorizontal = true;
    }

    public ImageView getPillImageView() {
        return pillImageView;
    }

    public void setPillImageView(ImageView pillImageView) {
        this.pillImageView = pillImageView;
    }

    public double getXPos() {
        return xPos;
    }

    public void setXPos(double xPos) {
        //if(263<=xPos && xPos<=414.378)
        this.xPos = xPos;
        pillImageView.setX(xPos);

    }

    public double getYPos() {
        return yPos;
    }

    public void setYPos(double yPos) {
        double lastY = 599.687;
        if (yPos <= lastY) {
            this.yPos = yPos;
            pillImageView.setY(yPos);
        }

    }

    public void setColor(InnerColor color) {
        this.color = color;
    }

    public InnerColor getColor() {
        return color;
    }

    public void pillHandler(KeyEvent event) {
        if (event.getCode() == KeyCode.D) {
            // pillImageView.setLayoutX(21.625+pillImageView.getLayoutX());
            setXPos(21.625 + getXPos());
        }
        if (event.getCode() == KeyCode.A) {
            // pillImageView.setLayoutX(pillImageView.getLayoutX()-21.625);
            setXPos(getXPos() - 21.625);
        }
    }
}
