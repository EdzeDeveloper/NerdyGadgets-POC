package repository;

import model.Order;
import model.Persoon;
import model.Product;
import model.databaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class OrderRepository {
    databaseConnection database;

    public OrderRepository() {

    }

    public static Order createOrder(int bestellingID, int persoonID, int adresID, String status, Date bestelDatum, Date leverDatum, ArrayList<Product> besteldeProducten) {

        return new Order(bestellingID, persoonID, adresID, status, bestelDatum, leverDatum, besteldeProducten); //that was easy...
    }

    public Order findById(int id, boolean withOrderedProducts) throws SQLException {

        return this.database.findOrderByIdQuery(id, withOrderedProducts);
    }

    public Order[] generateOrders(int numberOfOrders) throws SQLException {
        Order[] orderlist = new Order[numberOfOrders];
        Random rand = new Random();
        ArrayList<Product> besteldeProducten = new ArrayList<Product>();

        for (int orderToBeGenerated = 0; orderToBeGenerated < numberOfOrders; orderToBeGenerated++) {
            this.database = new databaseConnection();
            // Generate dummy times
            long currentTime = System.currentTimeMillis();
            Date bestelDatum = new Date(currentTime);
            Date leverDatum = new Date(currentTime);

            int persoonID = rand.nextInt(30000);
            Persoon persoon = null;
            // Get adress from Persoon
            try {
                persoon = this.database.getPersoonById(persoonID);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }

            // Generate random products
            int numberOfProducts = rand.nextInt(8);
            for (int productToBeGenerated = 0; productToBeGenerated < numberOfProducts; productToBeGenerated++) {

                Product product = database.getProductById(3);

            }


            // Create Order object and add it to the array
            Order order = OrderRepository.createOrder(orderToBeGenerated, persoonID, persoon.getAdresID(), "Besteld", bestelDatum, leverDatum, besteldeProducten);
            orderlist[orderToBeGenerated] = order;
        }

        return orderlist;
    }
}