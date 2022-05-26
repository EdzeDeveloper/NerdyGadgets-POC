package repository;

import model.DBConnection;
import model.Order;
import model.Product;
import repository.interfaces.CrudInterface;

import java.sql.*;
import java.util.ArrayList;

public class OrderRepository implements CrudInterface<Order> {
    private Connection con = DBConnection.getConnection();

    public boolean create(int bestellingID, int persoonID, int adresID, String status, Date bestelDatum, Date leverDatum) {
        // do some create queries and return succes or failure.

        return true; //that was easy...
    }

    private Order findOrder(int id) throws SQLException {
        Order order = new Order();

        String query
                = "select * from bestelling where bestellingID= ?";
        PreparedStatement preparedStatement
                = con.prepareStatement(query);

        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            order.setBestellingID(resultSet.getInt("bestellingID"));
            order.setPersoonID(resultSet.getInt("persoonID"));
        }

        return order;
    }

    public Order find(int id) throws SQLException {
        return findOrder(id);
    }

    //method overloading to accept an extra argument that executes extra queries to get orderedProducts.
    public Order find(int id, boolean withOrderedProducts) throws SQLException {
        Order order = findOrder(id);

        if(withOrderedProducts) {
            ArrayList<Product> orderedProducts = new ArrayList<>();

            String productsQuery
                    = "select * from product p join besteldeproducten bp on p.productID = bp.productID join bestelling b on b.bestellingID = bp.bestellingID where b.bestellingID= ?";
            PreparedStatement preparedProductsStatement
                    = con.prepareStatement(productsQuery);

            preparedProductsStatement.setInt(1, id);
            ResultSet productsResultSet = preparedProductsStatement.executeQuery();

            while (productsResultSet.next()) {
                Product product = new Product(productsResultSet.getInt("productID"), productsResultSet.getString("productNaam"));
                orderedProducts.add(product);
            }

            //set besteldeProducten on order. This is an ArrayList with products.
            order.setBesteldeProducten(orderedProducts);
        }

        return order;
    }

    public void update(Order obj) throws SQLException {
        // TODO Auto-generated method stub

        // String query
        // = "update Return set emp_name=?, "
        //   + " emp_address= ? where emp_id = ?";
        // PreparedStatement ps
        // = con.prepareStatement(query);
        // ps.setString(1, emp.getEmp_name());
        // ps.setString(2, emp.getEmp_address());
        // ps.setInt(3, emp.getEmp_id());
        // ps.executeUpdate();
    }

    public void delete(String key) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void create(Order obj) throws SQLException {

    }

    public ArrayList<Order> findAll(String key) throws SQLException {
        // // TODO Auto-generated method stubString query = "select * from Return";
        // PreparedStatement ps
        //     = con.prepareStatement(query);
        // ResultSet rs = ps.executeQuery();
        // List<Return> ls = new ArrayList();

        // while (rs.next()) {
        //     Return emp = new Return();
        //     // emp.setEmp_id(rs.getInt("emp_id"));
        //     // emp.setEmp_name(rs.getString("emp_name"));
        //     // emp.setEmp_address(rs.getString("emp_address"));
        //     ls.add(emp);
        // }
        // return ls;
        return null;
    }
}
