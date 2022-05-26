package model;
import java.sql.*;
import java.util.ArrayList;

public class databaseConnection {

	Connection dbConnection = null;

	public databaseConnection() throws SQLException {
		
		try {
			this.dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nerdygadgets2", "root" , "");
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public Order findOrderByIdQuery(int id) throws SQLException {

		Order order = new Order();

		Statement statement = null;
		ResultSet result = null;

		try {
			statement = this.dbConnection.createStatement();
			result = statement.executeQuery("select * from bestelling where bestellingID = " + id + " limit 1");

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
			
			if (dbConnection != null) {
				dbConnection.close();
			}
		}

		return order;
	}

	//method overloading to accept an extra argument that executes extra queries to get orderedProducts.
	public Order findOrderByIdQuery(int id, boolean withOrderedProducts) throws SQLException {

		Order order = new Order();

		Statement statement = null;
		ResultSet result = null;

		try {
			statement = this.dbConnection.createStatement();
			result = statement.executeQuery("select * from bestelling where bestellingID = " + id + " limit 1");

			while (result.next()) {
                order.setBestellingID(result.getInt("bestellingID"));
                order.setPersoonID(result.getInt("persoonID"));
                //etc. etc.
            }

			if(withOrderedProducts) {
				ArrayList<Product> besteldeProducten = new ArrayList<Product>();

				result = statement.executeQuery("select * from product p join besteldeproducten bp on p.productID = bp.productID join bestelling b on b.bestellingID = bp.bestellingID where b.bestellingID = " + id);
				
				while (result.next()) {
					Product product = new Product(result.getInt("productID"), result.getString("productNaam"));
					besteldeProducten.add(product);
				}

				//set besteldeProducten on order. This is an ArrayList with products.
				order.setBesteldeProducten(besteldeProducten);
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
			
			if (dbConnection != null) {
				dbConnection.close();
			}
		}

		return order;
	}

	public ArrayList<Return> getAllReturnedOrdersQuery() throws SQLException {
		Statement statement = null;
		ResultSet result = null;
		ArrayList returnedOrders = new ArrayList<Return>();
		try {
			statement = this.dbConnection.createStatement();
			// later aanpasbaar op resultaat tussen datums
			// resultaat toevoegen aan de 
			result = statement.executeQuery("select * from retour");

			while (result.next()) {

				int bestellingID = result.getInt("bestellingID"); 
				String reden = result.getString("reden"); 
				int retourID = result.getInt("retourID"); 
				Return returnModel = new Return(bestellingID, reden, retourID); 

				returnedOrders.add(returnModel);
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
			
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return returnedOrders;
	}
	// public update(String query) {

	// }
	// public delete(String query) {

	// }

}
