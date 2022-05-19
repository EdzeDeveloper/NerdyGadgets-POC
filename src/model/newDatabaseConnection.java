package model;
import java.sql.*;
import java.util.ArrayList;

public class newDatabaseConnection {

	public static Connection createConnection() throws SQLException {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/nerdygadgets", "root" , "");
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public static void insertUpdateDelete(String updateQuery) throws SQLException {
    	// check of er connectie gemaakt kan worden (wordt daarna direct gesloten)
      Connection dbConnection = createConnection();
      try (dbConnection) {
        int excecuteUpdate = createConnection().createStatement().executeUpdate(updateQuery);
        if(excecuteUpdate > 0) {
          System.out.println("item created / geupdated");
        } else if (excecuteUpdate == 0) {
          System.out.println("item deleted");
        } 
      } 
	}

	public static ResultSet read(String query) throws SQLException {
		// check of er connectie gemaakt kan worden (wordt daarna direct gesloten)
    Connection dbConnection = createConnection();
		try (dbConnection) {
			// als er connectie gemaakt kan worden deze
			return createConnection().createStatement().executeQuery(query); 
		} finally {
      dbConnection.close();
		}
	}
}

