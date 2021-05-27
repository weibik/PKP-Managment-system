package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class routeController extends Main implements Initializable, Serializable {

    private  ArrayList<Train> trains;
    private  ArrayList<TrainStation> stations;
    private  ObservableList<TrainRoute> routes;

    private ObservableList<TrainRoute> userTickets = FXCollections.observableArrayList();
    private TrainRoute clickedRoute;


    @FXML
    public Button save;
    @FXML
    public Button show;
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
    private Button book;
    @FXML
    private TableView <TrainRoute> tableOfTickets;
    @FXML
    private TableColumn<TrainRoute, String> ticketTimeColumn;

    public routeController(String start, String end) throws IOException {
        firstStation = start;
        lastStation = end;
        deserialize();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initClock();
        fromLabel.setText(firstStation);
        toLabel.setText(lastStation);
        showRoutes();
    }

    @SuppressWarnings("Never used")
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
                if(row.getItem().getTrain() != null){
                    Train train = row.getItem().getTrain();
                    String info = "Max speed: " + train.getMaxSpeed() + "\nnumber of seats: " + train.getNumberOfSeats();
                    Tooltip tooltip = new Tooltip(info);
                    row.setTooltip(tooltip);
                }
            });
            row.setOnMouseClicked(event ->{
                   clickedRoute = row.getItem();
            });
            return row;
        });
    }

    private void serialize(){
        try
        {
            String filename = "routes.ser";
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(new ArrayList<TrainRoute>(routes));

            out.close();
            file.close();
            System.out.println("Routes serialized");

            filename = "trains.ser";
            file = new FileOutputStream(filename);
            out = new ObjectOutputStream(file);
            out.writeObject(trains);

            filename = "stations.ser";
            file = new FileOutputStream(filename);
            out = new ObjectOutputStream(file);
            out.writeObject(stations);
        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
    }

    public void saveTickets(ActionEvent event){
        try
        {
            String filename = "tickets.ser";
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(new ArrayList<TrainRoute>(userTickets));

            out.close();
            file.close();
            System.out.println("Tickets serialized");
        }
        catch (IOException ex)
        {
            System.out.println("IOException is caught");
        }
    }

    @SuppressWarnings("unchecked")
    public void showTickets(ActionEvent event){
        String filename = "tickets.ser";
        ArrayList<TrainRoute> deserializedTickets = new ArrayList<>();

        try
        {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            deserializedTickets = (ArrayList<sample.TrainRoute>) in.readObject();
            userTickets = FXCollections.observableList(deserializedTickets);

            ticketTimeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTimeOfStart()));
            tableOfTickets.setItems(userTickets);

            in.close();
            file.close();
            System.out.println("Tickets has been deserialized ");

        }
        catch(IOException | ClassNotFoundException ex)
        {
            System.out.println("IOException is caught");
        }
    }


    @SuppressWarnings("unchecked")
    public void deserialize(){
        String routesFileName = "routes.ser";
        String trainsFileName = "trains.ser";
        String stationsFileName = "stations.ser";

        ArrayList<TrainRoute> deserializedRoutes = new ArrayList<>();

        try
        {
            FileInputStream file = new FileInputStream(routesFileName);
            ObjectInputStream in = new ObjectInputStream(file);
            deserializedRoutes = (ArrayList<sample.TrainRoute>) in.readObject();
            routes = FXCollections.observableList(deserializedRoutes);
            in.close();
            file.close();
            System.out.println("Routes has been deserialized ");

            file = new FileInputStream(trainsFileName);
            in = new ObjectInputStream(file);
            trains = (ArrayList<Train>) in.readObject();
            System.out.println("Trains has been deserialized ");

            file = new FileInputStream(stationsFileName);
            in = new ObjectInputStream(file);
            stations = (ArrayList<TrainStation>) in.readObject();
            System.out.println("Stations has been deserialized ");
        }
        catch(IOException | ClassNotFoundException ex)
        {
            System.out.println("IOException is caught");
        }

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

/*

    private ArrayList<Train> listOfTrains() throws IOException {
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



    @SuppressWarnings("unchecked")
    public ArrayList<Train> deserializeTrains(){
        String filename = "trains.ser";
        ArrayList<Train> deserializedTrains = new ArrayList<>();
        try
        {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            deserializedTrains = (ArrayList<Train>) in.readObject();

            in.close();
            file.close();

            System.out.println("Trains has been deserialized ");
        }
        catch(IOException | ClassNotFoundException ex)
        {
            System.out.println("IOException is caught");
        }
        return deserializedTrains;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<TrainStation> deserializeStations(){
        String filename = "stations.ser";
        ArrayList<TrainStation> deserializedStations = new ArrayList<>();
        try
        {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            deserializedStations = (ArrayList<TrainStation>) in.readObject();

            in.close();
            file.close();

            System.out.println("Stations has been deserialized ");
        }
        catch(IOException | ClassNotFoundException ex)
        {
            System.out.println("IOException is caught");
        }
        return deserializedStations;
    }

    @SuppressWarnings("unchecked")
    public ObservableList<TrainRoute> deserializeRoutes(){
        String filename = "routes.ser";
        ArrayList<TrainRoute> deserializedRoutes = new ArrayList<>();
        try
        {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            deserializedRoutes = (ArrayList<TrainRoute>) in.readObject();

            in.close();
            file.close();

            System.out.println("Routes has been deserialized ");
        }
        catch(IOException | ClassNotFoundException ex)
        {
            System.out.println("IOException is caught");
        }
        return FXCollections.observableList(deserializedRoutes);
    }

 */
