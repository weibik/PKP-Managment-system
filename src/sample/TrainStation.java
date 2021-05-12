package sample;

import java.util.*;

public class TrainStation {
    String stationName;
    List<Train> listOfTrains = new LinkedList<>();
    int maximumCapacity;

    // constructor with list
    public TrainStation(String station, List<Train> list, int capacity){
        stationName = station;
        listOfTrains = list;
        maximumCapacity = capacity;
    }

    // constructor without list
    public TrainStation(String station, int capacity){
        stationName = station;
        maximumCapacity = capacity;
    }

    // adding train to the list
    public void addTrain(Train t){
        // only if there is still free place in the station
        if(listOfTrains.size() < maximumCapacity) {
            listOfTrains.add(t);
        }
        else {
            System.out.println("Max capacity reached!!");
        }


    }

    // deleting train from the list
    public void deleteTrain(Train t){
        listOfTrains.remove(t);
    }

    // sorting trains by name
    public void sorting() {
        Collections.sort(listOfTrains);
    }

    public void sortingByNumberOfSeats(){
        Collections.sort(listOfTrains);
    }

    // printing all trains in the station
    public void printingTrains(){
        for (Train listOfTrain : listOfTrains) {
            System.out.println("- " + listOfTrain.name);
        }
        System.out.println();
    }

    // printing information about each train
    public void printingAboutTrains(){
        for (Train listOfTrain : listOfTrains) {
            listOfTrain.printing();
        }
        System.out.println();
    }

    public String returnSomeInfo(){
        return "capacity: " + maximumCapacity;
    }



}
