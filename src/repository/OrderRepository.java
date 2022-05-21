package repository;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class OrderRepository {
    public OrderRepository() {

    }

    public Order[] generateOrders(int numberOfOrders) throws SQLException {
        Order[] orderlist = new Order[numberOfOrders];
        Random rand = new Random();
        String besteldeProductenInsertValues = "";

        for (int orderToBeGenerated = 0; orderToBeGenerated < numberOfOrders; orderToBeGenerated++) {
            ArrayList<Product> besteldeProducten = new ArrayList<Product>();
            // Generate dummy times
            long currentTime = System.currentTimeMillis();
            Date bestelDatum = new Date(currentTime);
            Date leverDatum = new Date(currentTime);

            int persoonID = rand.nextInt(30000);
            // Get adress from Persoon
            ResultSet adresIDQuery = newDatabaseConnection.select("SELECT * FROM persoon WHERE persoonID = " + persoonID);
            adresIDQuery.next();
            int adresID = adresIDQuery.getInt("adresID");


            // Generate random products
            int numberOfProducts = Math.max(1, rand.nextInt(8));
            ResultSet producten = newDatabaseConnection.select("SELECT * FROM product ORDER BY RAND() LIMIT " + numberOfProducts);
            for (int productToBeGenerated = 0; productToBeGenerated < numberOfProducts; productToBeGenerated++) {

                int productID = 0;
                String productNaam = null;
                String prijs = null;

                producten.next();
                productID = producten.getInt("productID");
                productNaam = producten.getString("productnaam");
                prijs = producten.getString("prijs");

                Product product = new Product(productID, productNaam, prijs);
                besteldeProducten.add(product);
            }
            producten.close();
            // Create Order object and add it to the array
            orderlist[orderToBeGenerated] = new Order(orderToBeGenerated, persoonID, adresID, "Besteld", bestelDatum, leverDatum, besteldeProducten);

            for (Product product : besteldeProducten) {
                besteldeProductenInsertValues = besteldeProductenInsertValues + "INSERT INTO besteldeproducten VALUES " + "(" + orderToBeGenerated + "," + product.getProductID() + "," + 1 + ");";
            }
        }
        // insert every bestelling in the database
        String bestellingInsertValues = "";
        for (Order currentOrder : orderlist) {
            bestellingInsertValues = bestellingInsertValues + "INSERT INTO bestelling (bestellingID, persoonID, status, besteldatum, leverdatum, leverAdresId) VALUES " + "(" + currentOrder.getBestellingID() + "," + currentOrder.getPersoonID() + ",'" + currentOrder.getStatus() + "','" + currentOrder.getBestelDatum() + "','" + currentOrder.getLeverDatum() + "'," + currentOrder.getAdresID() + ");";
        }
        newDatabaseConnection.insertUpdateDelete(bestellingInsertValues);

        // insert besteldeproducten in the database
        newDatabaseConnection.insertUpdateDelete(besteldeProductenInsertValues);

        return orderlist;
    }
}