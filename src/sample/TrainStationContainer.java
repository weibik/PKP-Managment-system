package sample;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TrainStationContainer {
    Map<String, TrainStation> stationsContainer = new HashMap<>();

    // adding station to the hashmap
    void addStationToTheMap(TrainStation station){
        stationsContainer.put(station.stationName, station);
    }

    TrainStation getTrainStation(String v) {
        for (TrainStation value : stationsContainer.values())
            if (value.stationName.equals(v))
                return value;
        return null;
    }


    // removing station from the hashmap
    void removeStationFromMap(TrainStation station){
        stationsContainer.remove(station.stationName, station);
    }

    // getting a list of stations without any trains and printing it
    void getEmptyStations(){
        List<TrainStation> listOfEmptyStations = new LinkedList<>();
        for(TrainStation value: stationsContainer.values()){
            if(value.listOfTrains.isEmpty()){
                listOfEmptyStations.add(value);
            }
        }
        System.out.println("Empty stations:");
        for (TrainStation list : listOfEmptyStations) {
            System.out.println("- " + list.stationName);
        }
        System.out.println();
    }

    // information about all of the train stations
    void stationInformation(TrainStation station){
        System.out.println("This train station is named " + station.stationName);
        System.out.println("Currently there are " + station.listOfTrains.size() + " trains\n    ");
    }


}
