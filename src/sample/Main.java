package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

public class Main extends Application {

    private final ArrayList<Train> trains = listOfTrains();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));

        Scene scene = new Scene(root);

        Image icon = new Image("/resources/icon.png");
        primaryStage.getIcons().add(icon);

        primaryStage.setTitle("PKP management system");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private ArrayList<Train> listOfTrains(){
        ArrayList<Train> trains = new ArrayList<>();
        trains.add(new Train("King Kong", 500, 150, TrainCondition.PENDOLINO));
        trains.add(new Train("Ghidora", 200, 200, TrainCondition.BROKEN));
        trains.add(new Train("Golden Arrow", 200, 250, TrainCondition.NEW));
        trains.add(new Train("Godzilla", 1000, 75, TrainCondition.OLD ));
        trains.add(new Train("Sodoma", 200, 100, TrainCondition.DELAYED));
        trains.add(new Train("Desperados", 300, 200, TrainCondition.PENDOLINO));

        return trains;
    }

}
