package model;

import java.sql.*;
import java.util.ArrayList;

public class databaseConnection {

	Connection myConnection = null;

	public databaseConnection() throws SQLException {

		try {
			this.myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root" , "");
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
			statement = this.myConnection.createStatement();
			result = statement.executeQuery("select * from nerdygadgets.bestelling where bestellingID = " + id + " limit 1");

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

	//method overloading to accept an extra argument that executes extra queries to get orderedProducts.
	public Order findOrderByIdQuery(int id, boolean withOrderedProducts) throws SQLException {

		Order order = new Order();

		Statement statement = null;
		ResultSet result = null;

		try {
			statement = this.myConnection.createStatement();
			result = statement.executeQuery("select * from nerdygadgets.bestelling where bestellingID = " + id + " limit 1");

			while (result.next()) {
				order.setBestellingID(result.getInt("bestellingID"));
				order.setPersoonID(result.getInt("persoonID"));
				//etc. etc.
			}

			if(withOrderedProducts) {
				ArrayList<Product> besteldeProducten = new ArrayList<Product>();

				result = statement.executeQuery("select * from nerdygadgets.product p join nerdygadgets.besteldeproducten bp on p.productID = bp.productID join nerdygadgets.bestelling b on b.bestellingID = bp.bestellingID where b.bestellingID = " + id);

				while (result.next()) {
					Product product = new Product(result.getInt("productID"), result.getString("productNaam"), result.getString("prijs"));
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

			if (myConnection != null) {
				myConnection.close();
			}
		}

		return order;
	}

	public Persoon getPersoonById(int id) throws SQLException {


		Persoon persoon = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			statement = this.myConnection.createStatement();
			result = statement.executeQuery("select * from nerdygadgets.persoon where persoonID = " + id);

			int persoonID = 0;
			int adresID = 0;
			String voornaam = null;
			String achternaam = null;
			String email = null;
			while (result.next()) {
				persoonID = result.getInt("persoonID");
				adresID = result.getInt("adresID");

				voornaam = result.getString("voornaam");
				achternaam = result.getString("achternaam");
				email = result.getString("email");
			}
			persoon = new Persoon(persoonID, adresID, voornaam, achternaam, email);
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

		return persoon;
	}

	public Product getProductById(int id) throws SQLException {


		Product product = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			statement = this.myConnection.createStatement();
			result = statement.executeQuery("select * from nerdygadgets.product");

			int productID = 0;

			String productNaam = null, prijs = null;

			while (result.next()) {
				productID = result.getInt("productID");
				productNaam = result.getString("productnaam");
				prijs = result.getString("prijs");
			}
			product = new Product(productID, productNaam, prijs);
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

		return product;
	}

}