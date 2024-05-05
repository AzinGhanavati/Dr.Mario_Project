package ir.ac.kntu;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    private Pane pane;

    private int level;

    private Speed speed;

    private String musicType;

    private Scene scene;

    private Player player;
    private ArrayList<ArrayList<Cell>> cells;

    private ArrayList<ArrayList<Cell>> vertical;

    private ArrayList<ArrayList<Cell>> horizontal;

    private boolean check = false;

    private boolean isSend = true;

    private boolean isPause = false;

    private boolean isGameOver = false;

    public Game(Pane pane, int level, Speed speed, String musicType, Scene scene,Player player) {
        this.player=player;
        this.pane = pane;
        this.level = level;
        this.speed = speed;
        this.musicType = musicType;
        this.scene = scene;
        this.cells = new ArrayList<>();
        for (int i = 0; i < 17; i++) {
            ArrayList<Cell> cellArrayList = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                InnerColor color = InnerColor.BLACK;
                if (i == 16) {
                    color = InnerColor.YELLOW;
                }
                    cellArrayList.add(new Cell(263 + (j * 21.265), 220 + (i * 25.312), color));
            }
            cells.add(cellArrayList);
        }
        this.horizontal = new ArrayList<>();
        this.vertical = new ArrayList<>();
    }

    public void gameGui() {
        ImageView imageView = new ImageView(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\MainScene2.png"));
        imageView.setFitWidth(700);
        imageView.setFitHeight(700);
        pane.getChildren().add(imageView);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                HelperAnimation anime = new HelperAnimation(pane);
                anime.doctorAnimation();
                anime.redAnimation();
                anime.blueAnimation();
                anime.yellowAnimation();
            }
        });
        System.out.println(1+player.getLastLevel());
        HelperForGmae.printScore(pane,player);
        gameInfo();
        setViruses(level);
        Timeline timeline = new Timeline();
        KeyFrame kf = new KeyFrame(Duration.millis(10000),
                event -> {
                    if (isGameOver) {
                        timeline.stop();
                    }
                    startToMove();
                });
        timeline.getKeyFrames().add(kf);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    public void gameInfo() {
        Label label = new Label("LEVEL");
        label.setFont(Font.font("Impact", FontWeight.EXTRA_BOLD, 30));
        label.setTextFill(javafx.scene.paint.Color.BLACK);
        label.setLayoutX(505);
        label.setLayoutY(435);
        Label l = new Label(String.valueOf(player.getLastLevel()));
        l.setFont(Font.font("Impact", FontWeight.EXTRA_BOLD, 27));
        l.setLayoutX(590);
        l.setLayoutY(458);
        pane.getChildren().add(l);
        pane.getChildren().add(label);
        Label label1 = new Label("SPEED");
        label1.setFont(Font.font("Impact", FontWeight.EXTRA_BOLD, 30));
        label1.setTextFill(javafx.scene.paint.Color.BLACK);
        label1.setLayoutX(505);
        label1.setLayoutY(500);
        Label l1 = new Label(speed.toString());
        l1.setFont(Font.font("Impact", FontWeight.EXTRA_BOLD, 25));
        l1.setLayoutX(590);
        l1.setLayoutY(530);
        pane.getChildren().add(l1);
        pane.getChildren().add(label1);
        Label label2 = new Label("SPEED");
        label2.setFont(Font.font("Impact", FontWeight.EXTRA_BOLD, 30));
        label2.setTextFill(javafx.scene.paint.Color.BLACK);
        label2.setLayoutX(505);
        label2.setLayoutY(565);
        Label l2 = new Label(String.valueOf(level * 4));
        l2.setFont(Font.font("Impact", FontWeight.EXTRA_BOLD, 25));
        l2.setLayoutX(590);
        l2.setLayoutY(595);
        pane.getChildren().add(l2);
        pane.getChildren().add(label2);
    }

    public void startToMove() {
        if (isSend) {
            isSend = false;
            pillMovement();
        }
    }

    public void pillMovement() {
        Pill pill1 = choosePill(1);
        Pill pill2 = choosePill(2);
        pane.getChildren().add(pill1.getPillImageView());
        pane.getChildren().add(pill2.getPillImageView());
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(1100),
                e -> changeYPos(pill1, pill2)));
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                pill1.pillHandler(keyEvent);
                pill2.pillHandler(keyEvent);
                rotatePill(keyEvent, pill1, pill2);
            }
        });
    }

    public void changeYPos(Pill pill1, Pill pill2) {
        Cell cell1 = findCell(pill1.getXPos(), pill1.getYPos() + 25.312);
        Cell cell2 = findCell(pill2.getXPos(), pill2.getYPos() + 25.312);
        if (cell1.getColor() == InnerColor.BLACK && cell2.getColor() == InnerColor.BLACK && cell2.getImageView() == null && cell1.getImageView() == null) {
            pill1.setYPos(pill1.getYPos() + 25.312);
            pill2.setYPos(pill2.getYPos() + 25.312);
            check = true;
        } else {
            cells.get((int) ((pill1.getYPos() - 220) / 25.312)).get((int) ((pill1.getXPos() - 263) / 21.625)).setColor(pill1.getColor());
            cells.get((int) ((pill2.getYPos() - 220) / 25.312)).get((int) ((pill2.getXPos() - 263) / 21.625)).setColor(pill2.getColor());
            // if (check) {
            //saveSamePositions2(cell1);
            //saveSamePositions2(cell2);
            //checkConsecutiveColor();
            //check = false;
            // }
            if ((pill1.getXPos() == 327.875 && pill1.getYPos() == 220) || (pill1.getXPos() == 349.5 && pill1.getYPos() == 220)) {
                gameOver();
            }
            if ((pill2.getXPos() == 327.875 && pill2.getYPos() == 220) || (pill2.getXPos() == 349.5 && pill2.getYPos() == 220)) {
                gameOver();
            }
            isSend = true;
        }
    }

    public void gameOver() {
        Thread.currentThread().setUncaughtExceptionHandler((thread, throwable) -> {
            System.out.println("Handler caught exception: " + throwable.getMessage());
        });
        isGameOver = true;
        for (int i = 0; i < 17; i++) {
            ArrayList<Cell> cellArrayList = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                Cell cell = new Cell(263 + (j * 21.265), 220 + (i * 25.312), InnerColor.YELLOW);
                if (i <= 15) {
                    ImageView imageView = new ImageView(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\black.png"));
                    imageView.setFitWidth(21.625);
                    imageView.setFitHeight(25.312);
                    cell.setImageView(imageView);
                    pane.getChildren().add(cell.getImageView());
                }
                cellArrayList.add(cell);
            }
            cells.set(i, cellArrayList);
        }
        try {
            AppliedList.write(AppliedList.getInstance());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageView gameOver = new ImageView(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\GameOver.png"));
        gameOver.setFitHeight(150);
        gameOver.setFitWidth(160);
        gameOver.setX(270);
        gameOver.setY(280);
        pane.getChildren().add(gameOver);
    }

    public void checkConsecutiveColor() {
        for (int i = 0; i < vertical.size(); i++) {
            int sizeColor = 0;
            ArrayList<Cell> sameColorCell = new ArrayList<>();
            sortVertical(vertical.get(i));
            InnerColor targetColor = vertical.get(i).get(0).getColor();
            for (int j = 0; j < vertical.get(i).size(); i++) {
                if (vertical.get(i).get(j).getColor() == targetColor) {
                    sizeColor++;
                    sameColorCell.add(vertical.get(i).get(j));
                } else if (vertical.get(i).get(j).getColor() != targetColor && sizeColor <= 3) {
                    sizeColor = 1;
                    targetColor = vertical.get(i).get(j).getColor();
                    sameColorCell = new ArrayList<>();
                    sameColorCell.add(vertical.get(i).get(j));
                } else if (vertical.get(i).get(j).getColor() != targetColor && sizeColor > 3) {
                    showRemoveObj(sameColorCell);
                }
            }
        }
        for (int i = 0; i < horizontal.size(); i++) {
            int sizeColor = 0;
            ArrayList<Cell> sameColorCell = new ArrayList<>();
            sortHorizontal(horizontal.get(i));
            InnerColor targetColor = horizontal.get(i).get(0).getColor();
            System.out.println(horizontal.size() + "*");
            for (int j = 0; j < horizontal.get(i).size(); i++) {
                System.out.println(horizontal.size() + "&");
                if (horizontal.get(i).get(j).getColor() == targetColor) {
                    sizeColor++;
                    sameColorCell.add(horizontal.get(i).get(j));
                } else if (horizontal.get(i).get(j).getColor() != targetColor && sizeColor <= 3) {
                    sizeColor = 1;
                    targetColor = horizontal.get(i).get(j).getColor();
                    sameColorCell = new ArrayList<>();
                    sameColorCell.add(horizontal.get(i).get(j));
                } else if (horizontal.get(i).get(j).getColor() != targetColor && sizeColor > 3) {
                    showRemoveObj(sameColorCell);
                }
            }
        }
    }

    public void showRemoveObj(ArrayList<Cell> cells) {
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(1000),
                e -> removeGameObj(cells)));
        tl.setCycleCount(1);
        tl.play();
    }

    public void removeGameObj(ArrayList<Cell> cells) {
        for (int i = 0; i < cells.size(); i++) {
            InnerColor color = cells.get(i).getColor();
            switch (color) {
                case RED:
                    cells.get(i).setImageView(new ImageView(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\removeRed.png")));
                    pane.getChildren().add(cells.get(i).getImageView());
                    break;
                case BLUE:
                    cells.get(i).setImageView(new ImageView(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\removeBlue.png")));
                    pane.getChildren().add(cells.get(i).getImageView());
                    break;
                case YELLOW:
                    cells.get(i).setImageView(new ImageView(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\removeYellow.png")));
                    pane.getChildren().add(cells.get(i).getImageView());
                    break;
                default:
                    return;
            }
        }
    }

    public ArrayList<Cell> sortVertical(ArrayList<Cell> cells) {
        ArrayList newCell = new ArrayList<>();
        for (int i = 0; i < cells.size() - 1; i++) {
            for (int j = 0; j < cells.size(); j++) {
                if (cells.get(j).getYPos() < cells.get(j + 1).getYPos()) {
                    double temp = cells.get(j).getYPos();
                    cells.get(j).setYPos(cells.get(j + 1).getYPos());
                    cells.get(j + 1).setYPos(temp);
                }
            }
        }
        return cells;
    }

    public ArrayList<Cell> sortHorizontal(ArrayList<Cell> cells) {
        ArrayList newCell = new ArrayList<>();
        for (int i = 0; i < cells.size() - 1; i++) {
            for (int j = 0; j < cells.size(); j++) {
                if (cells.get(j).getXPos() < cells.get(j + 1).getXPos()) {
                    double temp = cells.get(j).getXPos();
                    cells.get(j).setXPos(cells.get(j + 1).getXPos());
                    cells.get(j + 1).setXPos(temp);
                }
            }
        }
        return cells;
    }

    public Cell findCell(double x, double y) {
        int row = (int) ((x - 263) / 21.625);
        int column = (int) ((y - 220) / 25.312);
        System.out.println("&&"+cells.get(column).size());
        return cells.get(column).get(row);
    }

    public Pill choosePill(int i) {
        double deltaX = 21.625;
        Random random = new Random();
        int choose = random.nextInt(3);
        switch (choose) {
            case 0:
                ImageView imageView = new ImageView(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\blue.png"));
                Pill pill0 = new Pill(imageView, 263 + ((i + 2) * deltaX), 220);
                pill0.setColor(InnerColor.BLUE);
                return pill0;
            case 1:
                ImageView imageView1 = new ImageView(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\Red.png"));
                Pill pill1 = new Pill(imageView1, 263 + ((i + 2) * deltaX), 220);
                pill1.setColor(InnerColor.RED);
                return pill1;
            case 2:
                ImageView imageView2 = new ImageView(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\Yellow.png"));
                Pill pill2 = new Pill(imageView2, 263 + ((i + 2) * deltaX), 220);
                pill2.setColor(InnerColor.YELLOW);
                return pill2;
            default:
                return null;
        }
    }

    public void setViruses(int level) {
        for (int i = 0; i < level * 4; i++) {
            Random random = new Random();
            int row = random.nextInt(8);
            int column = 3 + random.nextInt(14);
            int choose = random.nextInt(3);
            switch (choose) {
                case 0:
                    System.out.println(cells.get(column).size()+"**");
                    if (cells.get(column).get(row).getColor() == InnerColor.BLACK) {
                        setVirusBlue(row, column);
                    } else {
                        i = i - 1;
                        continue;
                    }
                    break;
                case 1:
                    if (cells.get(column).get(row).getColor() == InnerColor.BLACK) {
                        setVirusRed(row, column);
                    } else {
                        i = i - 1;
                        continue;
                    }
                    break;
                case 2:
                    if (cells.get(column).get(row).getColor() == InnerColor.BLACK) {
                        setVirusYellow(row, column);
                    } else {
                        i = i - 1;
                        continue;
                    }
                    break;
                default:

            }
        }
    }

    private void setVirusYellow(int row, int column) {
        ImageView imageView2 = new ImageView(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\ys1.png"));
        cells.get(column).get(row).setImageView(imageView2);
        cells.get(column).get(row).setColor(InnerColor.YELLOW);
        saveSamePositions2(cells.get(column).get(row));
        pane.getChildren().add(imageView2);
    }

    private void setVirusRed(int row, int column) {
        ImageView imageView1 = new ImageView(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\rs1.png"));
        cells.get(column).get(row).setImageView(imageView1);
        cells.get(column).get(row).setColor(InnerColor.RED);
        saveSamePositions2(cells.get(column).get(row));
        pane.getChildren().add(imageView1);
    }

    private void setVirusBlue(int row, int column) {
        ImageView imageView = new ImageView(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\bs1.png"));
        cells.get(column).get(row).setImageView(imageView);
        cells.get(column).get(row).setColor(InnerColor.BLUE);
        saveSamePositions2(cells.get(column).get(row));
        pane.getChildren().add(imageView);
    }

    public void saveSamePositions2(Cell cell) {
        if (horizontal.size() == 0 && vertical.size() == 0) {
            ArrayList<Cell> v = new ArrayList<>();
            v.add(cell);
            ArrayList<Cell> h = new ArrayList<>();
            h.add(cell);
            vertical.add(v);
            horizontal.add(h);
        } else {
            Boolean isExistVertical = false;
            for (int i = 0; i < vertical.size(); i++) {
                if (vertical.get(i).get(0).getXPos() == cell.getXPos()) {
                    vertical.get(i).add(cell);
                    isExistVertical = true;
                    break;
                }
            }
            if (!isExistVertical) {
                ArrayList<Cell> newCells = new ArrayList<>();
                newCells.add(cell);
                vertical.add(newCells);
            }
            Boolean isExistHorizontal = false;
            for (int i = 0; i < horizontal.size(); i++) {
                if (horizontal.get(i).get(0).getYPos() == cell.getYPos()) {
                    horizontal.get(i).add(cell);
                    isExistHorizontal = true;
                    break;
                }
            }
            if (!isExistHorizontal) {
                ArrayList<Cell> newCells = new ArrayList<>();
                newCells.add(cell);
                horizontal.add(newCells);
            }
        }
        System.out.println("vSizeP:" + vertical.size());
    }

    public void rotatePill(KeyEvent event, Pill pill1, Pill pill2) {
        Position position = findPosition(pill1, pill2);
        if (event.getCode() == KeyCode.Z) {
            switch (position) {
                case RIGHT:
                    pill2.setYPos(pill1.getYPos() + 25.312);
                    pill2.setXPos(pill1.getXPos());
                    break;
                case DOWN:
                    pill2.setYPos(pill1.getYPos());
                    pill2.setXPos(pill1.getXPos() - 21.625);
                    break;
                case LEFT:
                    pill2.setXPos(pill1.getXPos());
                    pill2.setYPos(pill1.getYPos() - 25.312);
                    break;
                case UP:
                    pill2.setYPos(pill1.getYPos());
                    pill2.setXPos(pill1.getXPos() + 21.625);
                default:
                    return;
            }
        }
    }

    public Position findPosition(Pill pill1, Pill pill2) {
        if (pill1.getYPos() == pill2.getYPos() && pill1.getXPos() + 21.625 == pill2.getXPos()) {
            return Position.RIGHT;
        } else if (pill1.getYPos() == pill2.getYPos() && pill1.getXPos() - 21.625 == pill2.getXPos()) {
            return Position.LEFT;
        } else if (pill1.getXPos() == pill2.getXPos() && pill1.getYPos() + 25.312 == pill2.getYPos()) {
            return Position.DOWN;
        } else if (pill1.getXPos() == pill2.getXPos() && pill1.getYPos() - 25.312 == pill2.getYPos()) {
            return Position.UP;
        }
        return Position.RIGHT;
    }



}
