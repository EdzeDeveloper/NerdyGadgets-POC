package repository;

import model.Order;
import model.databaseConnection;

import java.sql.*;

public class OrderRepository {
    databaseConnection database;

    public OrderRepository() throws SQLException {
        this.database = new databaseConnection();
    }

    public boolean create(int bestellingID, int persoonID, int adresID, String status, Date bestelDatum, Date leverDatum) {
            // do some create queries and return succes or failure.
            return true;
    }
    
    public Order findById(int id) throws SQLException {

        Order order = this.database.findOrderByIdQuery("select * from bestelling where bestellingID = " + id + " limit 1");

        return order;
    }
}
