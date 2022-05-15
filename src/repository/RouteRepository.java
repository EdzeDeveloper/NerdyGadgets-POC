package repository;

import model.*;

public class RouteRepository {
    private Route Route;
    private graphModel graphModel;
    private int[] deliveryList;

    public RouteRepository(int numberOfNodes) {
        Route = new Route(numberOfNodes);
        graphModel = Route.getGraphModel();
        deliveryList = new int[numberOfNodes];
    }

    public Route nearestNeighbor() {
        int[] nodeList = graphModel.getNodeList();
        edgeModel[] edgeList = graphModel.getEdgeList();
        int aantalkm = 0;

        // Add the first node to the list
        deliveryList[0] = nodeList[0];

        // Compile a list of all edges connected to the current node

        for (int delivery = 0; delivery < deliveryList.length - 1; delivery++) {
            edgeModel[] edgesConnectedToNode = new edgeModel[nodeList.length - 1];
            int deliveryNodeID = deliveryList[delivery];

            int edgesConnectedToNodeIndex = 0;
            for (edgeModel edge : edgeList) {
                int startNodeID = edge.getStartNodeID();
                int targetNodeID = edge.getTargetNodeID();
                if (startNodeID == deliveryNodeID || targetNodeID == deliveryNodeID) {
                    edgesConnectedToNode[edgesConnectedToNodeIndex] = edge;
                    edgesConnectedToNodeIndex++;
                    if (edgesConnectedToNodeIndex == edgesConnectedToNode.length) {
                        break;
                    }
                }
            }

            int lowestCostEdgeID;
            double lowestCostEdgeCost = -1;
            int nextNode = 0;
            for (edgeModel edge : edgesConnectedToNode) {
                double cost = edge.getCost();
                if (lowestCostEdgeCost == -1 || cost < lowestCostEdgeCost) {
                    int edgeStartNodeID = edge.getStartNodeID();
                    int edgeTargetNodeID = edge.getTargetNodeID();

                    int nextNodeTemp = 0;
                    if (deliveryList[delivery] == edgeStartNodeID){
                        nextNodeTemp = edgeTargetNodeID;
                    } else {
                        nextNodeTemp = edgeStartNodeID;
                    }

                    // Check if the node is already in the deliverylist
                    boolean nodeIsInDeliveryList = false;
                    for (int deliveryNode = 0; deliveryNode < edgesConnectedToNodeIndex; deliveryNode++) {
                        if (deliveryList[deliveryNode] == nextNodeTemp){
                            nodeIsInDeliveryList = true;
                            break;
                        }
                    }

                    if (!nodeIsInDeliveryList) {
                        nextNode = nextNodeTemp;
                        lowestCostEdgeCost = cost;
                    }

                }
                if (cost == 0){
                    break;
                }
            }
            aantalkm += lowestCostEdgeCost;
            deliveryList[delivery+1] = nextNode;
        }

        Route.setDeliveryOrder(deliveryList);
        Route.setAantalkm(aantalkm);
        return Route;
    }

    public Route twoOpt() {
        int[] nodeList = graphModel.getNodeList();
        edgeModel[] edgeList = graphModel.getEdgeList();

        // Perform 2-changes for every edge
        for (int delivery = 0; delivery < deliveryList.length - 1; delivery++) {
            int startNodeID = deliveryList[delivery];
            int targetNodeID = deliveryList[delivery+1];

            // get the current edge
            int currentEdgeID = 0;
            int currentEdgeCost = 0;

            // loop through all nodes and perform a 2-change if possible
            for (int node:nodeList) {

            }
        }



        return Route;
    }
}
