package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;
import javafx.scene.control.Alert;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class routeController extends Main implements Initializable{

    private final ArrayList<Train> trains = listOfTrains();
    private final ArrayList<TrainStation> stations = listOfStations();
    private final ObservableList<TrainRoute> routes = getRoutes();
    private ObservableList<TrainRoute> userTickets = FXCollections.observableArrayList();
    private TrainRoute clickedRoute;

    @FXML
    public String firstStation;
    @FXML
    public String lastStation;
    @FXML
    private Label dateTime;
    @FXML
    private TableColumn<TrainRoute, String> nameColumn;
    @FXML
    private TableColumn<TrainRoute, String> startTimeColumn;
    @FXML
    private TableColumn<TrainRoute, String> endTimeColumn;
    @FXML
    private TableView<TrainRoute> tableOfRoutes;
    @FXML
    private Label fromLabel;
    @FXML
    private Label toLabel;
    @FXML
    private Button buy;
    @FXML
    private Button book;
    @FXML
    private TableView <TrainRoute> tableOfTickets;
    @FXML
    private TableColumn<TrainRoute, String> ticketTimeColumn;

    public routeController(String start, String end){
        firstStation = start;
        lastStation = end;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initClock();
        fromLabel.setText(firstStation);
        toLabel.setText(lastStation);
        showRoutes();
    }

    public void buyTicket(ActionEvent event) throws Exception {
        if(clickedRoute != null){
            userTickets.add(clickedRoute);
            ticketTimeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTimeOfStart()));
            tableOfTickets.setItems(userTickets);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No route is chosen", ButtonType.CANCEL);
            alert.showAndWait();
        }
    }

    private void initClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dateTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void showRoutes(){

        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTrain().getName()));
        startTimeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTimeOfStart()));
        endTimeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTimeOfEnd()));

        tableOfRoutes.setItems(getCertainRoutes());

        tableOfRoutes.setRowFactory(tv -> {
            TableRow<TrainRoute> row = new TableRow<>();
            row.setOnMouseEntered(event -> {
                Train train = row.getItem().getTrain();
                String info = "Max speed: " + train.getMaxSpeed() + "\nnumber of seats: " + train.getNumberOfSeats();
                Tooltip tooltip = new Tooltip(info);
                row.setTooltip(tooltip);
            });
            row.setOnMouseClicked(event ->{
                   clickedRoute = row.getItem();
            });
            return row;
        });
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

    private ArrayList<TrainStation> listOfStations(){
        ArrayList <TrainStation> list = new ArrayList<>();
        TrainStation pkpCracow = new TrainStation("Cracow station", 20 );
        TrainStation pkpWarsaw = new TrainStation("Warsaw station" , 30);
        TrainStation pkpRzeszow = new TrainStation("Rzeszow station", 10);

        list.add(pkpCracow);
        list.add(pkpWarsaw);
        list.add(pkpRzeszow);

        return list;
    }

    public  ObservableList<TrainRoute> getRoutes(){
        ObservableList<TrainRoute> routes = FXCollections.observableArrayList();
        routes.add(new TrainRoute(trains.get(0), stations.get(0), stations.get(1), "16:20", "20:50"));
        routes.add(new TrainRoute(trains.get(1), stations.get(1), stations.get(0), "13:10", "18:21"));
        routes.add(new TrainRoute(trains.get(2), stations.get(1), stations.get(2),"05:15", "11:01"));
        routes.add(new TrainRoute(trains.get(3), stations.get(2), stations.get(1), "09:38", "13:00"));
        routes.add(new TrainRoute(trains.get(4), stations.get(2), stations.get(0),"12:42", "17:49"));
        routes.add(new TrainRoute(trains.get(5), stations.get(0), stations.get(2), "22:05", "03:14"));

        routes.add(new TrainRoute(trains.get(1), stations.get(0), stations.get(1), "12:25", "17:20"));
        routes.add(new TrainRoute(trains.get(0), stations.get(1), stations.get(0), "13:43", "18:41"));
        routes.add(new TrainRoute(trains.get(3), stations.get(1), stations.get(2),"03:15", "07:06"));
        routes.add(new TrainRoute(trains.get(2), stations.get(2), stations.get(1), "11:25", "14:20"));
        routes.add(new TrainRoute(trains.get(5), stations.get(2), stations.get(0),"17:34", "22:49"));
        routes.add(new TrainRoute(trains.get(4), stations.get(0), stations.get(2), "12:52", "19:01"));

        return routes;
    }

    public ObservableList<TrainRoute> getCertainRoutes(){
        ObservableList<TrainRoute> certainRoutes = FXCollections.observableArrayList();
        for (TrainRoute route : routes) {
            if (route.startStation.getStationName().equals(firstStation) && route.endStation.getStationName().equals(lastStation)) {
                certainRoutes.add(route);
            }
        }
        return certainRoutes;
    }
}
