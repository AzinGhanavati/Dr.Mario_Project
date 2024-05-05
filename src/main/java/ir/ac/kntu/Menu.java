package ir.ac.kntu;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class Menu {

    private Pane pane;

    private Speed speed = Speed.LOW;

    private String musicType = "FEVER";

    private int level;

    private Scene scene;

    private Player player;


    public Menu(Pane pane, Scene scene) {
        this.pane = pane;
        this.scene = scene;
    }

    public void setFirstMenu(Scene scene) {
        ImageView imageView = new ImageView("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\bfirstBackground3.jpg");
        imageView.setFitWidth(700);
        imageView.setFitHeight(700);
        ImageView bigPill = getBigPill();
        Text t1 = new Text("1 PLAYER GAME");
        t1.setFont(Font.font("Comic Sans MS", 25));
        t1.setFill(Color.YELLOW);
        TextFlow textFlow = new TextFlow(t1);
        Button button = getButton(textFlow);
        Thread thread = getThread1(scene, t1, button);

        Text t2 = new Text("CHOOSE PLAYER");
        t2.setFont(Font.font("Comic Sans MS", 23));
        t2.setFill(Color.RED);
        //t2.setFill(Color.rgb(255,98,50));
        TextFlow textFlow2 = new TextFlow(t2);
        Button button2 = new Button();
        button2.setGraphic(textFlow2);
        button2.setLayoutY(485);
        button2.setLayoutX(195);
        button2.setPrefWidth(250);
        button2.setPrefHeight(50);
        button2.setStyle("-fx-background-color: #000000");
        eventForChoosePlayer(scene, t2, button2);


        Text t3 = new Text("NEW PLAYER");
        t3.setFont(Font.font("Comic Sans MS", 23));
        t3.setFill(Color.GREEN);
        TextFlow textFlow3 = new TextFlow(t3);
        Button button3 = new Button();
        button3.setGraphic(textFlow3);
        button3.setLayoutY(550);
        button3.setLayoutX(195);
        button3.setPrefWidth(250);
        button3.setPrefHeight(50);
        button3.setStyle("-fx-background-color: #000000");
        button3.setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.HAND);
                t3.setFill(Color.rgb(64, 255, 50));
            }
        });
        button3.setOnMouseExited(new EventHandler() {
            public void handle(Event me) {
                scene.setCursor(Cursor.DEFAULT); //Change cursor to crosshair
                t3.setFill(Color.GREEN);
            }
        });

        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createNewPlayer();
            }
        });

        pane.getChildren().add(imageView);
        pane.getChildren().add(bigPill);
        pane.getChildren().add(button);
        pane.getChildren().add(button2);
        pane.getChildren().add(button3);
        thread.start();
    }

    public void createNewPlayer() {
        Pane innerPane = new Pane();
        ImageView imageView = new ImageView(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\backgroundMenu.png"));
        imageView.setFitWidth(700);
        imageView.setFitHeight(700);
        innerPane.setPrefSize(700, 700);
        Text t = new Text("BACK");
        t.setFont(Font.font("Comic Sans MS", 15));
        t.setFill(Color.BLACK);
        Button back = new Button();
        back.setLayoutX(300);
        back.setLayoutY(539);
        back.setGraphic(t);
        back.setStyle("-fx-background-color: #C71585");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setFirstMenu(scene);
            }
        });
        ImageView imageView1 = new ImageView(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\back.png"));
        imageView1.setFitHeight(500);
        imageView1.setFitWidth(500);
        imageView1.setX(100);
        imageView1.setY(100);
        innerPane.getChildren().add(imageView);
        innerPane.getChildren().add(imageView1);
        innerPane.getChildren().add(back);
        enterName(innerPane);
        pane.getChildren().add(innerPane);
    }

    public void enterName(Pane innerPane) {
        Label label = new Label("NAME:");
        label.setFont(Font.font("Comic Sans MS", 15));
        label.setTextFill(Color.LIGHTBLUE);
        label.setLayoutX(240);
        label.setLayoutY(270);
        TextField textField = new TextField();
        textField.setLayoutX(295);
        textField.setLayoutY(270);
        Text t = new Text("OK");
        t.setFont(Font.font("Comic Sans MS", 15));
        t.setFill(Color.BLACK);
        Button button = new Button();
        button.setLayoutX(500);
        button.setLayoutY(270);
        button.setGraphic(t);
        button.setStyle("-fx-background-color: #C71585");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setNamePlayer(textField.getText());
            }
        });
        innerPane.getChildren().add(label);
        innerPane.getChildren().add(textField);
        innerPane.getChildren().add(button);
    }

    public void setNamePlayer(String s) {
        Player player1 = new Player(0.0, s, 1);
        this.player = player1;
        AppliedList.getInstance().addPlayer(this.player);
    }

    private void eventForChoosePlayer(Scene scene, Text t2, Button button2) {
        button2.setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.HAND);
                t2.setFill(Color.rgb(255, 98, 50));

            }
        });
        button2.setOnMouseExited(new EventHandler() {
            public void handle(Event me) {
                scene.setCursor(Cursor.DEFAULT); //Change cursor to crosshair
                t2.setFill(Color.RED);

            }
        });
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                choosePlayer();
            }
        });
    }

    @NotNull
    private Thread getThread1(Scene scene, Text t1, Button button) {
        button.setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.HAND);
                t1.setFill(Color.ORANGE);
            }
        });
        button.setOnMouseExited(new EventHandler() {
            public void handle(Event me) {
                scene.setCursor(Cursor.DEFAULT); //Change cursor to crosshair
                t1.setFill(Color.YELLOW);
            }
        });
        MarioAnimationFotMenu m = new MarioAnimationFotMenu(pane);
        Thread thread = new Thread(m);
        button.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setMenu(scene);
            }
        }));
        return thread;
    }

    public void choosePlayer() {
        Pane scrollPane = new Pane();
        ImageView imageView = new ImageView(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\index.png"));
        imageView.setFitWidth(700);
        imageView.setFitHeight(700);
        scrollPane.setPrefSize(700, 700);
        Text t = new Text("BACK");
        t.setFont(Font.font("Comic Sans MS", 15));
        t.setFill(Color.BLACK);
        Button back = new Button();
        back.setLayoutX(120);
        back.setLayoutY(539);
        back.setGraphic(t);
        back.setStyle("-fx-background-color: #C71585");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setFirstMenu(scene);
            }
        });
        ImageView imageView1 = new ImageView(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\back.png"));
        imageView1.setFitHeight(500);
        imageView1.setFitWidth(500);
        imageView1.setX(100);
        imageView1.setY(100);
        scrollPane.getChildren().add(imageView);
        scrollPane.getChildren().add(imageView1);
        scrollPane.getChildren().add(back);
        chooseButtonForPlayer(scrollPane);
        pane.getChildren().add(scrollPane);
    }


    @NotNull
    private Button getButton(TextFlow textFlow) {
        Button button = new Button();
        button.setGraphic(textFlow);
        button.setLayoutY(420);
        button.setLayoutX(195);
        button.setPrefWidth(250);
        button.setPrefHeight(50);
        button.setStyle("-fx-background-color: #000000");
        return button;
    }

    @NotNull
    private ImageView getBigPill() {
        ImageView bigPill = new ImageView("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\Dr_Mario_logo.png");
        bigPill.setX(62);
        bigPill.setY(92);
        bigPill.setFitHeight(200);
        bigPill.setFitWidth(570);
        return bigPill;
    }


    public void setMenu(Scene scene) {
        ImageView imageView = new ImageView(new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\menu2.jpg"));
        imageView.setFitWidth(700);
        imageView.setFitHeight(700);
        //imageView.fitWidthProperty().bind(pane.widthProperty());
        //imageView.fitHeightProperty().bind(pane.heightProperty());

        Label label = new Label("1 PLAYER GAME");
        label.setFont(Font.font("Comic Sans MS", 30));
        label.setTextFill(Color.LIGHTYELLOW);
        label.setLayoutX(235);
        label.setLayoutY(111);

        pane.getChildren().add(imageView);
        pane.getChildren().add(label);
        setButtons();

    }


    public void setButtons() {
        Button buttonVirusLevel = getButtonVirusLevel();

        Button buttonSpeed = getButtonSpeed();

        Button buttonMusicType = getButtonMusicType();

        Button go = getButtonGo();
        pane.getChildren().add(buttonVirusLevel);
        pane.getChildren().add(buttonSpeed);
        pane.getChildren().add(buttonMusicType);
        pane.getChildren().add(go);
        eventForLevel(buttonVirusLevel);
    }

    @NotNull
    private Button getButtonGo() {
        Button go = new Button();
        ImageView letsGo = new ImageView("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\go.png");
        letsGo.setFitHeight(40);
        letsGo.setFitWidth(130);
        go.setGraphic(letsGo);
        go.setPrefWidth(70);
        go.setPrefHeight(30);
        go.setLayoutY(550);
        go.setLayoutX(100);
        eventForGo(go);
        go.setStyle("-fx-background-color: #000000");
        return go;
    }

    @NotNull
    private Button getButtonMusicType() {
        ImageView musicImage = new ImageView("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\musicType.png");
        musicImage.setFitWidth(130);
        musicImage.setFitHeight(40);
        Button buttonMusicType = new Button();
        buttonMusicType.setGraphic(musicImage);
        buttonMusicType.setPrefWidth(70);
        buttonMusicType.setPrefHeight(30);
        buttonMusicType.setLayoutY(430);
        buttonMusicType.setLayoutX(100);
        musicImage.getAccessibleText();
        buttonMusicType.setStyle("-fx-background-color: #ffD700");
        eventForMusic(buttonMusicType);
        return buttonMusicType;
    }

    @NotNull
    private Button getButtonSpeed() {
        ImageView speedImage = new ImageView("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\speed.png");
        speedImage.setFitWidth(130);
        speedImage.setFitHeight(40);
        Button buttonSpeed = new Button();
        buttonSpeed.setGraphic(speedImage);
        buttonSpeed.setPrefWidth(70);
        buttonSpeed.setPrefHeight(30);
        buttonSpeed.setLayoutY(300);
        buttonSpeed.setLayoutX(100);
        buttonSpeed.setStyle("-fx-background-color: #ffDf00");
        eventForSpeed(buttonSpeed);
        return buttonSpeed;
    }

    @NotNull
    private Button getButtonVirusLevel() {
        ImageView virusLevelImage = new ImageView("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\virusLevel.png");
        virusLevelImage.setFitWidth(130);
        virusLevelImage.setFitHeight(40);
        Button buttonVirusLevel = new Button();
        buttonVirusLevel.setGraphic(virusLevelImage);
        buttonVirusLevel.setPrefWidth(70);
        buttonVirusLevel.setPrefHeight(30);
        buttonVirusLevel.setLayoutY(170);
        buttonVirusLevel.setLayoutX(100);
        buttonVirusLevel.setStyle("-fx-background-color: #ffD700");
        return buttonVirusLevel;
    }

    public void eventForLevel(Button button) {
        Label label1 = getLabel1();
        Label ans = getAns();
        Button up = getButtonUp();
        Button down = getButtonDown();
        EventHandler<ActionEvent> eventEventHandler1 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handler(ans, up, down);
            }
        };
        button.setOnAction(eventEventHandler1);
        pane.getChildren().add(label1);
        pane.getChildren().add(ans);
        pane.getChildren().add(up);
        pane.getChildren().add(down);
    }

    private void handler(Label ans, Button up, Button down) {
        EventHandler<ActionEvent> eventEventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int i = Integer.parseInt(ans.getText());
                ans.setText(String.valueOf(i + 1));
            }
        };
        up.setOnAction(eventEventHandler);
        level = Integer.parseInt(ans.getText());
        EventHandler<ActionEvent> eventEventHandler1 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (Integer.parseInt(ans.getText()) != 1) {
                    int i = Integer.parseInt(ans.getText());
                    ans.setText(String.valueOf(i - 1));
                }
            }
        };
        down.setOnAction(eventEventHandler1);
    }

    @NotNull
    private Button getButtonDown() {
        Button down = new Button();
        down.setLayoutX(240);
        down.setLayoutY(263);
        down.setPrefHeight(10);
        down.setPrefWidth(10);
        down.setStyle("-fx-background-color: #000000");
        ImageView downImage = new ImageView("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\down.png");
        downImage.setFitWidth(25);
        downImage.setFitHeight(25);

        down.setGraphic(downImage);
        return down;
    }

    @NotNull
    private Button getButtonUp() {
        Button up = new Button();
        up.setLayoutY(232);
        up.setLayoutX(240);
        up.setPrefHeight(30);
        up.setPrefWidth(30);
        ImageView upImage = new ImageView("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\up.png");
        upImage.setFitHeight(28);
        upImage.setFitWidth(25);
        up.setGraphic(upImage);
        up.setStyle("-fx-background-color: #000000");
        return up;
    }

    @NotNull
    private Label getAns() {
        Label ans = new Label("1");
        ans.setLayoutX(200);
        ans.setLayoutY(243);
        ans.setTextFill(Color.WHITE);
        ans.setFont(Font.font("Comic Sans MS", 20));
        ans.setStyle("-fx-border-color: orange;");
        return ans;
    }

    @NotNull
    private Label getLabel1() {
        Label label1 = new Label("Level:");
        label1.setFont(Font.font("Comic Sans MS", 25));
        label1.setTextFill(Color.WHITE);
        label1.setLayoutY(240);
        label1.setLayoutX(130);
        return label1;
    }


    public void eventForSpeed(Button button) {
        RadioButton lowButton = getLowButton();

        RadioButton medButton = getMedButton();

        RadioButton hiButton = getHiButton();

        Label label = new Label("1P");
        label.setFont(Font.font("Comic Sans MS", 24));
        label.setLayoutY(362);
        label.setLayoutX(132);
        label.setTextFill(Color.ORANGE);
        AtomicReference<String> speed = new AtomicReference<>("");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                findSpeed(lowButton, medButton, hiButton);
            }
        });
        pane.getChildren().add(lowButton);
        pane.getChildren().add(medButton);
        pane.getChildren().add(hiButton);
        pane.getChildren().add(label);
    }

    @NotNull
    private RadioButton getHiButton() {
        RadioButton hiButton = new RadioButton("HI");
        hiButton.setFont(Font.font("Comic Sans MS", 20));
        hiButton.setLayoutX(440);
        hiButton.setLayoutY(379);
        hiButton.setTextFill(Color.RED);
        return hiButton;
    }

    @NotNull
    private RadioButton getMedButton() {
        RadioButton medButton = new RadioButton("MED");
        medButton.setFont(Font.font("Comic Sans MS", 20));
        medButton.setLayoutX(300);
        medButton.setLayoutY(379);
        medButton.setStyle(" -fx-selected-color: yellow;");
        medButton.setTextFill(Color.GREEN);
        return medButton;
    }

    @NotNull
    private RadioButton getLowButton() {
        RadioButton lowButton = new RadioButton("LOW");
        lowButton.setFont(Font.font("Comic Sans MS", 20));
        lowButton.setLayoutX(169);
        lowButton.setLayoutY(379);
        lowButton.setTextFill(Color.LIGHTYELLOW);
        return lowButton;
    }

    public void findSpeed(RadioButton radioButton1, RadioButton radioButton2, RadioButton radioButton3) {
        if (radioButton1.isSelected()) {
            this.speed = Speed.LOW;
        } else if (radioButton2.isSelected()) {
            this.speed = Speed.MED;
        } else {
            this.speed = Speed.HI;
        }
    }

    public void eventForMusic(Button button) {
        RadioButton feverButton = new RadioButton("FEVER");
        feverButton.setFont(Font.font("Comic Sans MS", 20));
        feverButton.setLayoutX(160);
        feverButton.setLayoutY(510);
        feverButton.setTextFill(Color.ORANGE);

        RadioButton chillButton = new RadioButton("CHILL");
        chillButton.setFont(Font.font("Comic Sans MS", 20));
        chillButton.setLayoutX(310);
        chillButton.setLayoutY(510);
        chillButton.setStyle(" -fx-selected-color: yellow;");
        chillButton.setTextFill(Color.ORANGE);
        RadioButton offButton = new RadioButton("OFF");
        offButton.setFont(Font.font("Comic Sans MS", 20));
        offButton.setLayoutX(460);
        offButton.setLayoutY(510);
        offButton.setTextFill(Color.RED);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                eventForMusic(feverButton, chillButton, offButton);
            }
        });

        pane.getChildren().add(feverButton);
        pane.getChildren().add(chillButton);
        pane.getChildren().add(offButton);
    }

    public void eventForMusic(RadioButton radioButton1, RadioButton radioButton2, RadioButton radioButton3) {
        if (radioButton1.isSelected()) {
            this.musicType = "FEVER";
        } else if (radioButton2.isSelected()) {
            this.musicType = "CHILL";
        } else {
            this.musicType = "OFF";
        }
    }


    public void eventForGo(Button button) {
        button.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent k) {
                if (k.getCode().equals(KeyCode.ENTER)) {
                    Game game = new Game(pane, 1, speed, musicType, scene, player);
                    game.gameGui();
                }
            }
        });
    }

    public void chooseButtonForPlayer(Pane pane) {
        ArrayList<RadioButton> radioButtons = new ArrayList<>();
        ToggleGroup group = new ToggleGroup();
        for (int i = 0; i < AppliedList.getPlayers().size(); i++) {
            RadioButton radioButton = new RadioButton(AppliedList.getPlayers().get(i).toString());
            radioButton.setFont(Font.font("Comic Sans MS", 20));
            radioButton.setLayoutX(130);
            radioButton.setLayoutY(170 + i * 40);
            radioButton.setTextFill(Color.PINK);
            radioButton.setToggleGroup(group);
            radioButtons.add(radioButton);
            pane.getChildren().add(radioButton);
        }
        Text t = new Text("OK");
        t.setFont(Font.font("Comic Sans MS", 15));
        t.setFill(Color.BLACK);
        Button button = new Button();
        button.setLayoutX(500);
        button.setLayoutY(539);
        button.setGraphic(t);
        button.setStyle("-fx-background-color: #C71585");
        String infoStr;
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                findPlayer(radioButtons);
            }
        });
        pane.getChildren().add(button);
    }

    public void findPlayer(ArrayList<RadioButton> radioButtons) {
        String s = "";
        for (int i = 0; i < radioButtons.size(); i++) {
            if (radioButtons.get(i).isSelected()) {
                s = radioButtons.get(i).getText();
                break;
            }
        }
        for (int j = 0; j < AppliedList.getPlayers().size(); j++) {
            if (s.equals(AppliedList.getPlayers().get(j).toString())) {
                this.player = AppliedList.getPlayers().get(j);
                break;
            }
        }
    }
}