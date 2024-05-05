package ir.ac.kntu;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
//import javafx.scene.media.Media;
import javax.print.attribute.standard.Media;
import java.io.IOException;


/**
 * @author Sina Rostami
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AppliedList.getInstance().addPlayer(new Player(0.0,"sara",1));
        AppliedList.getInstance().addPlayer(new Player(10.0,"azin",3));
        AppliedList.getInstance().addPlayer(new Player(5.0,"raha",2));
        AppliedList.getInstance().addPlayer(new Player(15.0,"nima",1));
       /*try {
            AppliedList.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        Pane root = new Pane();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Dr.Mario");
        Image icon = new Image("D:\\Advanced programing\\AsnaAshariCodes\\project4\\src\\main\\resources\\images\\iconPhoto.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setHeight(700);
        primaryStage.setWidth(700);
        primaryStage.setResizable(false);
        Menu menu = new Menu(root, scene);
        //menu.setMenu(scene);
        menu.setFirstMenu(scene);
        //Game game=new Game(root,1,"i","Fever",scene);
        //game.gameGui();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}