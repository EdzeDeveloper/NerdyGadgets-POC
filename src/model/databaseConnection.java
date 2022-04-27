package model;

import java.sql.*;

public class databaseConnection {

	Connection myConnection = null;

	public Order findOrderByIdQuery(String query) throws SQLException {

        Order order = new Order();

		Statement statement = null;
		ResultSet result = null;

		try {
			statement = this.myConnection.createStatement();
			result = statement.executeQuery(query);

			while (result.next()) {
                order.setBestellingID(result.getInt("bestellingID"));
                order.setPersoonID(result.getInt("persoonID"));
                //etc. etc.
            }

		}
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

		return order;
	}

	public databaseConnection() throws SQLException {
		
		try {
			// 1. Connectie met de database maken
			this.myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nerdygadgets", "root" , "root");
		}
		// afhandelen van een exeption wanneer het de connectie/query het niet doet.
		catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
