package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ResourceBundle;

@SuppressWarnings("ThrowablePrintedToSystemOut")
public class sampleController implements Initializable {

    @FXML
    public ChoiceBox<String> start;
    @FXML
    public ChoiceBox<String> end;
    @FXML
    private Label dateTime;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        start.getItems().addAll("Cracow station", "Rzeszow station", "Warsaw station");
        end.getItems().addAll("Cracow station", "Rzeszow station", "Warsaw station");
        initClock();
    }

    public void searchRoute(ActionEvent event){
        String nameStart = start.getValue();
        String nameEnd = end.getValue();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("route.fxml"));
            routeController controller = new routeController(nameStart, nameEnd);
            loader.setController(controller);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
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
}
