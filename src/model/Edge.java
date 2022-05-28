package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Edge {

    private int edgeID;
    private int startNodeID;
    private int targetNodeID;
    private double cost;

    public Edge(){

    }

    public Edge(int edgeID, int startNodeID, int targetNodeID, double cost) {
        this.edgeID = edgeID;
        this.startNodeID = startNodeID;
        this.targetNodeID = targetNodeID;
        this.cost = cost;
    }

    public int getEdgeID() {
        return edgeID;
    }

    public void setEdgeID(int edgeID) {
        this.edgeID = edgeID;
    }

    public int getStartNodeID() {
        return startNodeID;
    }

    public void setStartNodeID(int startNodeID) {
        this.startNodeID = startNodeID;
    }

    public int getTargetNodeID() {
        return targetNodeID;
    }

    public void setTargetNodeID(int targetNodeID) {
        this.targetNodeID = targetNodeID;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}