package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
  
public class DBConnection {

    private static Connection con = null;
  
    static {
      String url = "jdbc:mysql://localhost:3306/nerdygadgets2";
      String user = "root";
      String pass = "";
      try {
          Class.forName("com.mysql.jdbc.Driver");
          con = DriverManager.getConnection(url, user, pass);
      }
      catch (ClassNotFoundException | SQLException e) {
          e.printStackTrace();
      }
    }

    public static Connection getConnection()
    {
        return con;
    }

	// public static Connection createConnection() throws SQLException {
	// 	try {
	// 		return DriverManager.getConnection("jdbc:mysql://localhost:3306/nerdygadgets2", "root" , "");
	// 	}
	// 	catch (Exception exception) {
	// 		exception.printStackTrace();
	// 	}
	// 	return null;
	// }

	// public static void insertUpdateDelete(String updateQuery) throws SQLException {
  //   	// check of er connectie gemaakt kan worden (wordt daarna direct gesloten)
  //     Connection dbConnection = createConnection();
  //     try (dbConnection) {
  //       int excecuteUpdate = createConnection().createStatement().executeUpdate(updateQuery);
  //       if(excecuteUpdate > 0) {
  //         System.out.println("item created / geupdated");
  //       } else if (excecuteUpdate == 0) {
  //         System.out.println("item deleted");
  //       } 
  //     } catch(SQLDataException e) {
  //       System.err.println("SQLState: " +
  //       ((SQLException)e).getSQLState());

  //        System.err.println("Error Code: " +
  //       ((SQLException)e).getErrorCode());
  //     } finally {
  //       dbConnection.close();
  //     }
	// }

	// public static ResultSet select(String query) throws SQLException {
	// 	// check of er connectie gemaakt kan worden (wordt daarna direct gesloten)
  //   Connection dbConnection = createConnection();
	// 	try (dbConnection) {
	// 		// als er connectie gemaakt kan worden deze
	// 		return createConnection().createStatement().executeQuery(query); 
	// 	} catch (SQLDataException e) {
  //     System.err.println("SQLState: " +
  //     ((SQLException)e).getSQLState());

  //      System.err.println("Error Code: " +
  //     ((SQLException)e).getErrorCode());
  //   } finally {
  //     dbConnection.close();
	// 	}
  //   return null;
	// }
}

