package repository;
import model.*;
import repository.interfaces.CrudInterface;
import model.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class OrderRepository implements CrudInterface<Order> {
    private Connection con = DBConnection.getConnection();

    @Override
    public void add(Order obj) throws SQLException {

    }

    @Override
    public Order get(int id) throws SQLException {
        String query = "select * from bestelling where bestellingID= ?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, id);

        Order orderInstance = new Order();
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean check = false;

        while (resultSet.next()) {
            check = true;
            orderInstance.setBestellingID(resultSet.getInt("bestellingID"));
            orderInstance.setPersoonID(resultSet.getInt("persoonID"));
            orderInstance.setStatus(resultSet.getString("status"));
            orderInstance.setBestelDatum(resultSet.getDate("besteldatum"));
            orderInstance.setLeverDatum(resultSet.getDate("leverdatum"));
            orderInstance.setAdresID(resultSet.getInt("leverAdresID"));
        }

        if (check) {
            return orderInstance;
        }
        return null;
    }

    @Override
    public ArrayList<Order> getAll(String key) throws SQLException {
        return null;
    }

    public ArrayList<Order> getNodesInGraph(int graphID) throws SQLException {
        String query = "SELECT * FROM graphnodes WHERE graphID = ?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, graphID);

        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Order> nodeList = new ArrayList<>();


        while(resultSet.next()) {
            nodeList.add(get(resultSet.getInt("nodeID")));
        }
        return nodeList;
    }

    @Override
    public void Update(Order obj) throws SQLException {

    }

    @Override
    public void Delete(String key) throws SQLException {

    }
}
