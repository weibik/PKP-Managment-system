package sample;

import javafx.beans.property.SimpleStringProperty;

public class TrainRoute {

    public Train train;
    public TrainStation startStation;
    public TrainStation endStation;
    public String timeOfStart;
    public String timeOfEnd;


    public TrainRoute(Train train, TrainStation startStation, TrainStation endStation, String timeOfStart, String timeOfEnd){

        this.train = train;
        this.startStation = startStation;
        this.endStation = endStation;
        this.timeOfStart = timeOfStart;
        this.timeOfEnd = timeOfEnd;
    }

    public Train getTrain() {
        return train;
    }

    public TrainStation getStartStation() {
        return startStation;
    }

    public TrainStation getEndStation() {
        return endStation;
    }

    public String getTimeOfStart() {
        return timeOfStart;
    }

    public String getTimeOfEnd() {
        return timeOfEnd;
    }
}
