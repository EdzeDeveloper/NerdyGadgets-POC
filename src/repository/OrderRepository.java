package repository;

import model.Order;
import model.databaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class OrderRepository {
    databaseConnection database;

    public OrderRepository() throws SQLException {
        this.database = new databaseConnection();
    }

    public boolean create(int bestellingID, int persoonID, int adresID, String status, Date bestelDatum, Date leverDatum) {
            // do some create queries and return succes or failure.

            return true; //that was easy...
    }
    
    public Order findById(int id, boolean withOrderedProducts) throws SQLException {

        Order order = this.database.findOrderByIdQuery(id, withOrderedProducts);

        return order;
    }
}
