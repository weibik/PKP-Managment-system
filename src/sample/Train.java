package sample;

import java.awt.*;
import java.io.Serializable;

public class Train implements Comparable<Train>, Serializable {
    String name;
    int numberOfSeats;
    double maxSpeed;
    TrainCondition condition;

    // constructor
    public Train(String name, int numberOfSeats, double maxSpeed, TrainCondition condition){
        this.name = name;
        this.numberOfSeats = numberOfSeats;
        this.maxSpeed = maxSpeed;
        this.condition = condition;
    }

    public void modifyTrain(String name, int numberOfSeats, double maxSpeed, TrainCondition condition){
        this.name = name;
        this.numberOfSeats = numberOfSeats;
        this.maxSpeed = maxSpeed;
        this.condition = condition;
    }

    public String getName(){ return name; }

    public int getNumberOfSeats(){ return numberOfSeats; }

    public double getMaxSpeed(){ return maxSpeed; }

    public String getCondition(){
        return condition.name();
    }

    @Override
    public int compareTo(Train t) {
        return this.name.compareTo(t.name);
    }

    public void setDelayed(Train t){
        t.condition = TrainCondition.DELAYED;
    }

    public void setBroken(Train t){
        t.condition = TrainCondition.BROKEN;
    }

    public void printing(){
        System.out.println("PKP presents train named - " + name + "." );
        System.out.println("This train has " + numberOfSeats + " seats and its maximum speed is " + maxSpeed + " km/h.");
        System.out.println("Current condition: " + condition + "\n");
    }

}
