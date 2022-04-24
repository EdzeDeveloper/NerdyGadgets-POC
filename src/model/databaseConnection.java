package model;

import java.sql.*;

public class databaseConnection {

	public databaseConnection() throws SQLException {

		Connection myConnection = null;
		Statement statement = null;
		ResultSet result = null;
		
		try {
			// 1. Connectie met de database maken
			myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nerdygadgets", "root" , "");
			
			// 2. Voorbeeld van het maken van een statement
			statement = myConnection.createStatement();
			
			// 3. Voorbeeld uitvoeren van een query
			result = statement.executeQuery("select * from bestelling");
			
			// 4. Voorbeeld uitwerken van een resultaat
			while (result.next()) {
				System.out.println(result.getString("naam") + ", " + result.getString("naam"));
			}
		}
		// afhandelen van een exeption wanneer het de connectie/query het niet doet.
		catch (Exception exception) {
			exception.printStackTrace();
		}
		finally {
			if (result != null) {
				result.close();
			}
			
			if (statement != null) {
				statement.close();
			}
			
			if (myConnection != null) {
				myConnection.close();
			}
		}
	}

}
