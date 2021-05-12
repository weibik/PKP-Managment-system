package sample;

public class Train implements Comparable<Train>{
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

    // implementing of compareTo method from interface Comparable
    @Override
    public int compareTo(Train t) {
        return this.name.compareTo(t.name);
    }

    // changing condition of a train to DELAYED
    public void setDelayed(Train t){
        t.condition = TrainCondition.DELAYED;
    }

    //  changing condition of a train to BROKEN
    public void setBroken(Train t){
        t.condition = TrainCondition.BROKEN;
    }

    // printing information
    public void printing(){
        System.out.println("PKP presents train named - " + name + "." );
        System.out.println("This train has " + numberOfSeats + " seats and its maximum speed is " + maxSpeed + " km/h.");
        System.out.println("Current condition: " + condition + "\n");
    }

    public String returnSomeInfo(){
        return numberOfSeats + " seats, " + maxSpeed + " km/h, " + condition;
    }

}
