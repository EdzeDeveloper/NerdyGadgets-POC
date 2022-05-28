package repository;
import model.*;
import repository.interfaces.CrudInterface;
import model.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class EdgeRepository implements CrudInterface<Edge> {
    private Connection con = DBConnection.getConnection();

    @Override
    public void create(Edge obj) throws SQLException {
        String query = "INSERT INTO edge (edgeID, startNodeID, targetNodeID, cost) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, obj.getEdgeID());
        preparedStatement.setInt(2, obj.getStartNodeID());
        preparedStatement.setInt(3, obj.getTargetNodeID());
        preparedStatement.setDouble(4, obj.getCost());
        preparedStatement.executeUpdate();
    }

    public void addGraphEdges(ArrayList<Edge> edges, int graphID) throws SQLException {
        for (Edge edge : edges) {
            String query = "INSERT INTO graphedges (graphID, edgeID) VALUES (?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, graphID);
            preparedStatement.setInt(2, edge.getEdgeID());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Edge find(int id) throws SQLException {
        String query = "select * from edge where edgeID= ?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, id);

        Edge edgeInstance = new Edge();
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean check = false;

        while (resultSet.next()) {
            check = true;
            edgeInstance.setEdgeID(resultSet.getInt("edgeID"));
            edgeInstance.setStartNodeID(resultSet.getInt("startNodeID"));
            edgeInstance.setTargetNodeID(resultSet.getInt("targetNodeID"));
            edgeInstance.setCost(resultSet.getDouble("cost"));
        }

        if (check) {
            return edgeInstance;
        }
        return null;
    }

    @Override
    public ArrayList<Edge> getAll(String key) throws SQLException {
        return null;
    }

    public ArrayList<Edge> getEdgesInGraph(int graphID) throws SQLException {
        String query = "SELECT * FROM graphedges WHERE graphID = ?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, graphID);

        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Edge> edgeList = new ArrayList();


        while(resultSet.next()) {
            edgeList.add(find(resultSet.getInt("edgeID")));
        }
        return edgeList;
    }

    public ArrayList<Edge> getEdgesConnectedToNode(Order node) throws SQLException {
        String query = "SELECT * FROM edge WHERE startNodeID = ? OR targetNodeID = ?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, node.getBestellingID());
        preparedStatement.setInt(2, node.getBestellingID());

        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Edge> edgesConnectedToNode = new ArrayList();


        while(resultSet.next()) {
            edgesConnectedToNode.add(find(resultSet.getInt("edgeID")));
        }
        return edgesConnectedToNode;
    }

    public Edge getEdgeConnectingTwoNodes(Order node1, Order node2) throws SQLException {
        String query = "SELECT * FROM edge WHERE (startNodeID = ? AND targetNodeID = ?) OR (startNodeID = ? AND targetNodeID = ?)";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, node1.getBestellingID());
        preparedStatement.setInt(2, node2.getBestellingID());
        preparedStatement.setInt(3, node2.getBestellingID());
        preparedStatement.setInt(4, node1.getBestellingID());

        ResultSet resultSet = preparedStatement.executeQuery();
        Edge edgesConnectedToNode = null;


        while(resultSet.next()) {
            edgesConnectedToNode = find(resultSet.getInt("edgeID"));
        }
        return edgesConnectedToNode;
    }

    @Override
    public void Update(Edge obj) throws SQLException {

    }

    @Override
    public void Delete(String key) throws SQLException {

    }
}
