package repository;
import model.*;
import repository.interfaces.CrudInterface;
import model.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class OrderRepository implements CrudInterface<Order> {
    private Connection con = DBConnection.getConnection();

    @Override
    public void add(Order obj) throws SQLException {

    }

    public void addGraphNodes(ArrayList<Order> nodes, int graphID) throws SQLException {
        for (Order node : nodes) {
            String query = "INSERT INTO graphnodes (graphID, nodeID) VALUES (?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, graphID);
            preparedStatement.setInt(2, node.getBestellingID());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Order get(int id) throws SQLException {
        String query = "select * from bestelling where bestellingID= ?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, id);

        Order orderInstance = new Order();
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean check = false;

        while (resultSet.next()) {
            check = true;
            orderInstance.setBestellingID(resultSet.getInt("bestellingID"));
            orderInstance.setPersoonID(resultSet.getInt("persoonID"));
            orderInstance.setStatus(resultSet.getString("status"));
            orderInstance.setBestelDatum(resultSet.getDate("besteldatum"));
            orderInstance.setLeverDatum(resultSet.getDate("leverdatum"));
            orderInstance.setAdresID(resultSet.getInt("leverAdresID"));
        }

        if (check) {
            return orderInstance;
        }
        return null;
    }

    @Override
    public ArrayList<Order> getAll(String key) throws SQLException {
        return null;
    }

    public ArrayList<Order> getNodesInGraph(int graphID) throws SQLException {
        String query = "SELECT * FROM graphnodes WHERE graphID = ?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, graphID);

        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Order> nodeList = new ArrayList<>();


        while(resultSet.next()) {
            nodeList.add(get(resultSet.getInt("nodeID")));
        }
        return nodeList;
    }

    @Override
    public void Update(Order obj) throws SQLException {

    }

    @Override
    public void Delete(String key) throws SQLException {

    }

    public ArrayList<Order> getRandomOrdersWithAdres(int numberOfOrders) throws SQLException {
        String query = "select * from bestelling JOIN adres a on bestelling.leverAdresId = a.adresID ORDER BY RAND() LIMIT ?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, numberOfOrders);

        ResultSet resultSet = preparedStatement.executeQuery();
        boolean check = false;

        ArrayList<Order> orderList = new ArrayList<>();
        while (resultSet.next()) {
            check = true;
            Order orderInstance = new Order();
            orderInstance.setBestellingID(resultSet.getInt("bestellingID"));
            orderInstance.setPersoonID(resultSet.getInt("persoonID"));
            orderInstance.setStatus(resultSet.getString("status"));
            orderInstance.setBestelDatum(resultSet.getDate("besteldatum"));
            orderInstance.setLeverDatum(resultSet.getDate("leverdatum"));
            orderInstance.setAdresID(resultSet.getInt("leverAdresID"));
            orderList.add(orderInstance);
        }

        if (check) {
            return orderList;
        }
        return null;
    }

//    public Order[] generateOrders(int numberOfOrders) throws SQLException {
//        ArrayList<Order> orderlist;
//        Random rand = new Random();
//        String besteldeProductenInsertValues = "";
//
//        for (int orderToBeGenerated = 0; orderToBeGenerated < numberOfOrders; orderToBeGenerated++) {
//            ArrayList<Product> besteldeProducten = new ArrayList<>();
//            // Generate dummy times
//            long currentTime = System.currentTimeMillis();
//            Date bestelDatum = new Date(currentTime);
//            Date leverDatum = new Date(currentTime);
//
//            int persoonID = rand.nextInt(30000);
//            // Get adress from Persoon
//            PersoonRepository persoonRepository = new PersoonRepository();
//            int adresID = persoonRepository.get(persoonID).getAdresID();
//
//
//            // Generate random products
//            int numberOfProducts = Math.max(1, rand.nextInt(8));
//            ResultSet producten = newDatabaseConnection.select("SELECT * FROM product ORDER BY RAND() LIMIT " + numberOfProducts);
//            for (int productToBeGenerated = 0; productToBeGenerated < numberOfProducts; productToBeGenerated++) {
//
//                int productID = 0;
//                String productNaam = null;
//                String prijs = null;
//
//                producten.next();
//                productID = producten.getInt("productID");
//                productNaam = producten.getString("productnaam");
//                prijs = producten.getString("prijs");
//
//                Product product = new Product(productID, productNaam, prijs);
//                besteldeProducten.add(product);
//            }
//            producten.close();
//            // Create Order object and add it to the array
//            orderlist[orderToBeGenerated] = new Order(orderToBeGenerated, persoonID, adresID, "Besteld", bestelDatum, leverDatum, besteldeProducten);
//
//            for (Product product : besteldeProducten) {
//                besteldeProductenInsertValues = besteldeProductenInsertValues + "INSERT INTO besteldeproducten VALUES " + "(" + orderToBeGenerated + "," + product.getProductID() + "," + 1 + ");";
//            }
//        }
//        // insert every bestelling in the database
//        String bestellingInsertValues = "";
//        for (Order currentOrder : orderlist) {
//            bestellingInsertValues = bestellingInsertValues + "INSERT INTO bestelling (bestellingID, persoonID, status, besteldatum, leverdatum, leverAdresId) VALUES " + "(" + currentOrder.getBestellingID() + "," + currentOrder.getPersoonID() + ",'" + currentOrder.getStatus() + "','" + currentOrder.getBestelDatum() + "','" + currentOrder.getLeverDatum() + "'," + currentOrder.getAdresID() + ");";
//        }
//        newDatabaseConnection.insertUpdateDelete(bestellingInsertValues);
//
//        // insert besteldeproducten in the database
//        newDatabaseConnection.insertUpdateDelete(besteldeProductenInsertValues);
//
//        return orderlist;
}
