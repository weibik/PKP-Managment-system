package sample;
public enum TrainCondition {
    OLD("OLD"),
    NEW("NEW"),
    DELAYED("DELAYED"),
    PENDOLINO("PENDOLINO"),
    BROKEN("BROKEN");

    public final String label;

    TrainCondition(String label) {
        this.label = label;
    }
}
