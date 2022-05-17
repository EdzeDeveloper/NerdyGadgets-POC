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
            int startNodeID1 = deliveryList[delivery];
            int targetNodeID1 = deliveryList[delivery+1];

            // get the current edge information
            int currentEdgeID1 = 0;
            double currentEdgeCost1 = 0;

            // save the current best edge cost
            double bestCost = -1;
            int bestTargetNode1 = targetNodeID1;
            int bestStartNode2 = -1;
            int bestTargetNode2 = -1;

            // loop through all nodes and perform a 2-change if possible
            for (int node = 0; node < nodeList.length - 2; node++) {
                int startNodeID2 = deliveryList[node];
                int targetNodeID2 = deliveryList[node+1];

                // check if a 2-change is possible
                if (startNodeID1 == startNodeID2 || startNodeID1 == targetNodeID2 || targetNodeID1 == startNodeID2 || targetNodeID1 == targetNodeID2) {
                    continue;
                }

                // get the edge information of the possible 2-change
                int currentEdgeID2 = 0;
                double currentEdgeCost2 = 0;

                //save current situation
                double currentCost = currentEdgeCost1 + currentEdgeCost2;
                if (bestCost == -1) {
                    bestCost = currentCost;
                    bestTargetNode2 = targetNodeID2;
                }

                // perform a 2-change
                    // get the edge from startNode1 to targetNode2
                int targetEdgeID1 = 0;
                double targetEdgeCost1 = 0;

                    // get the edge from startNode1 to targetNode2
                int targetEdgeID2 = 0;
                double targetEdgeCost2 = 0;

                // save target situation
                double targetCost = targetEdgeCost1 + targetEdgeCost2;

                // compare the old situation to the new situation, if its better switch the targets
                if (targetCost < bestCost){
                    bestCost = targetCost;
                    bestTargetNode1 = targetNodeID2;
                    bestStartNode2 = startNodeID2;
                    bestTargetNode2 = targetNodeID1;
                }
            }

            // check if there is a better 2-change, if not go to next delivery
            if (targetNodeID1 == bestTargetNode1){
                continue;
            }

            // if there is a better 2-change, edit the route
            System.arraycopy(deliveryList, );
            for (int deliveryNumber = 0; deliveryNumber < deliveryList.length - 1; deliveryNumber++) {
                int currentDeliveryNumber = deliveryList[deliveryNumber];
                if (currentDeliveryNumber == startNodeID1 || currentDeliveryNumber == bestStartNode2){
                }
            }
        }



        return Route;
    }
}
