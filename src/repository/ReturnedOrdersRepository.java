package repository;
import model.Order;
import model.databaseConnection;
import java.sql.*;

public class ReturnedOrdersRepository {
    databaseConnection database;

    public ReturnedOrdersRepository() throws SQLException {
        this.database = new databaseConnection();
    }

    public void getReturnedProductsRepository() {
        Order order = this.database.findOrderByIdQuery(id, withOrderedProducts);

        return order;
    }

}
