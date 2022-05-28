package repository;
import model.Edge;
import model.Order;
import model.Route;
import repository.interfaces.CrudInterface;
import model.DBConnection;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteRepository implements CrudInterface<Route> {
    private Connection con = DBConnection.getConnection();

    @Override
    public void create(Route obj) throws SQLException {
        String query = "INSERT INTO route (routeID, aantalkm, graphID) VALUES (?,?,?)";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, obj.getRouteId());
        preparedStatement.setDouble(2, obj.getAantalkm());
        preparedStatement.setInt(3, obj.getGraphID());
        preparedStatement.executeUpdate();
    }

    public void addDeliveryOrder(ArrayList<Order> deliveryList, int routeID) throws SQLException {
        int orderNumber = 0;
        for (Order order : deliveryList) {
            String query = "INSERT INTO bestellingenlijst (routeID, bestellingID, routeVolgordeNummer) VALUES (?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, routeID);
            preparedStatement.setInt(2, order.getBestellingID());
            preparedStatement.setInt(3, orderNumber);
            preparedStatement.executeUpdate();
            orderNumber++;
        }
    }

     public ArrayList<Order> getDeliveryOrder(int routeID) throws SQLException {
         String query = "select * from bestellingenlijst where routeID= ? ORDER BY routeVolgordeNummer";
         PreparedStatement preparedStatement = con.prepareStatement(query);
         preparedStatement.setInt(1, routeID);

         ResultSet resultSet = preparedStatement.executeQuery();
         ArrayList<Order> deliveryOrder = new ArrayList<>();
         OrderRepository orderRepository = new OrderRepository();

         while(resultSet.next()) {
             deliveryOrder.add(orderRepository.find(resultSet.getInt("bestellingID")));
         }
         return deliveryOrder;
     }

    @Override
    public Route find(int id) throws SQLException {
        String query = "select * from route where routeID= ?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, id);

        Route routeInstance = new Route();
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean check = false;

        while (resultSet.next()) {
            check = true;
            routeInstance.setRouteId(resultSet.getInt("routeID"));
            routeInstance.setAantalkm(resultSet.getInt("aantalkm"));
            routeInstance.setGraphID(resultSet.getInt("graphID"));
        }

        if (check) {
            return routeInstance;
        }
        return null;
    }

    @Override
    public ArrayList<Route> getAll(String key) throws SQLException {
        return null;
    }

    @Override
    public void Update(Route obj) throws SQLException {
        String query = "UPDATE route SET aantalkm = ? WHERE routeID = ?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setDouble(1, obj.getAantalkm());
        preparedStatement.setInt(2, obj.getRouteId());
        preparedStatement.executeUpdate();
    }

    public void updateDeliveryOrder(ArrayList<Order> deliveryList, int routeID) throws SQLException {
        int orderNumber = 0;
        for (Order order : deliveryList) {
            String query = "UPDATE bestellingenlijst SET routeVolgordeNummer = ? WHERE routeID = ? AND bestellingID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, orderNumber);
            preparedStatement.setInt(2, routeID);
            preparedStatement.setInt(3, order.getBestellingID());
            preparedStatement.executeUpdate();
            orderNumber++;
        }
    }

    @Override
    public void Delete(String key) throws SQLException {

    }
}
