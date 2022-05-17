package model;

public class edgeModel {
    private int edgeID;
    private int startNodeID;
    private int targetNodeID;
    private double cost;

    public edgeModel(int edgeID, int startNodeID, int targetNodeID, double cost) {
        this.edgeID = edgeID;
        this.startNodeID = startNodeID;
        this.targetNodeID = targetNodeID;
        this.cost = cost;
    }

    public int getStartNodeID() {
        return startNodeID;
    }
    public int getTargetNodeID() {
        return targetNodeID;
    }
    public double getCost() {
        return cost;
    }
}
