package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

public class Main extends Application {

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


/*
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

    private TrainStationContainer containerOfStations(){
        TrainStation pkpCracow = new TrainStation("Cracow station", 20 );
        TrainStation pkpWarsaw = new TrainStation("Warsaw station" , 30);
        TrainStation pkpRzeszow = new TrainStation("Rzeszow station", 10);

        TrainStationContainer container = new TrainStationContainer();
        container.addStationToTheMap(pkpCracow);
        container.addStationToTheMap(pkpWarsaw);
        container.addStationToTheMap(pkpRzeszow);

        return container;
    }

    public  ObservableList<TrainRoute> getRoutes(){
        ObservableList<TrainRoute> routes = FXCollections.observableArrayList();
        routes.add(new TrainRoute(trains.get(0), stations.getTrainStation("Cracow Station"), stations.getTrainStation("Warsaw Station"), "16:20", "20:50"));
        routes.add(new TrainRoute(trains.get(1), stations.getTrainStation("Cracow Station"), stations.getTrainStation("Wroclaw Station"), "13:10", "18:21"));
        routes.add(new TrainRoute(trains.get(2), stations.getTrainStation("Wroclaw Station"), stations.getTrainStation("Warsaw Station"), "05:15", "11:01"));
        routes.add(new TrainRoute(trains.get(3), stations.getTrainStation("Wroclaw Station"), stations.getTrainStation("Cracow Station"), "09:38", "13:00"));
        routes.add(new TrainRoute(trains.get(4), stations.getTrainStation("Warsaw Station"), stations.getTrainStation("Cracow Station"), "12:42", "17:49"));
        routes.add(new TrainRoute(trains.get(5), stations.getTrainStation("Warsaw Station"), stations.getTrainStation("Wroclaw Station"), "22:05", "03:14"));

        return routes;
    }

 */

}
