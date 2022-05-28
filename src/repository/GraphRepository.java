package repository;
import model.*;
import repository.*;
import repository.interfaces.CrudInterface;

import java.sql.*;
import java.util.ArrayList;

public class GraphRepository implements CrudInterface<Graph> {
    private Connection con = DBConnection.getConnection();

    @Override
    public void create(Graph obj) throws SQLException {
        String query = "INSERT INTO graph (graphID) VALUES (?)";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, obj.getGraphID());
        preparedStatement.executeUpdate();
    }

    @Override
    public Graph find(int id) throws SQLException {
        String query = "select * from edge where edgeID= ?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, id);

        Graph graphInstance = new Graph();
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean check = false;

        while (resultSet.next()) {
            check = true;
            graphInstance.setGraphID(resultSet.getInt("graphID"));
        }

        if (check) {
            return graphInstance;
        }
        return null;
    }

    @Override
    public ArrayList<Graph> getAll(String key) throws SQLException {
        return null;
    }


    @Override
    public void Update(Graph obj) throws SQLException {

    }

    @Override
    public void Delete(String key) throws SQLException {

    }
}
