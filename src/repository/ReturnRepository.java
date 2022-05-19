package repository;
import model.Return;
// import model.Order;
import model.databaseConnection;
import java.sql.*;
import java.util.ArrayList;

public class ReturnRepository{
  databaseConnection database;

  public ReturnRepository() throws SQLException {
      this.database = new databaseConnection();
  }

  // create query
      
  public ArrayList<Return> getAll() throws SQLException {
		return this.database.getAllReturnedOrdersQuery();
  }
}
