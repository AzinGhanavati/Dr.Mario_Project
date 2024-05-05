package ir.ac.kntu;

import javafx.scene.image.ImageView;

public class Cell {

    private ImageView imageView;

    private double xPos;

    private double yPos;

    private InnerColor color= InnerColor.BLACK;


    public Cell(double xPos, double yPos, InnerColor color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.color = color;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
        imageView.setY(yPos);
        imageView.setX(xPos);
        imageView.setFitWidth(21.625);
        imageView.setFitHeight(25.312);
    }

    public double getXPos() {
        return xPos;
    }

    public void setXPos(double xPos) {
        this.xPos = xPos;
    }

    public double getYPos() {
        return yPos;
    }

    public void setYPos(double yPos) {
        this.yPos = yPos;
    }

    public InnerColor getColor() {
        return color;
    }

    public void setColor(InnerColor color) {
        this.color = color;
    }
}
