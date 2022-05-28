package repository;

import model.DBConnection;
import model.Order;
import model.Product;

import java.sql.*;
import java.util.ArrayList;

import interfaces.CrudInterface;

public class OrderRepository<T> implements CrudInterface<Order> {
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

    public Order findAndSetOrder(int id) throws SQLException {
      String query
      = "select * from bestelling where bestellingID= ?";
      PreparedStatement preparedStatement
          = con.prepareStatement(query);
      Order returnOrderInstance = new Order();
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      boolean check = false;

      while (resultSet.next()) {
          check = true;
          returnOrderInstance.setBestellingID(resultSet.getInt("bestellingID"));
          returnOrderInstance.setPersoonID(resultSet.getInt("persoonID"));
          returnOrderInstance.setStatus(resultSet.getString("status"));
          returnOrderInstance.setBestelDatum(resultSet.getDate("besteldatum"));
          returnOrderInstance.setLeverDatum(resultSet.getDate("leverdatum"));
          returnOrderInstance.setLeverAdresId(resultSet.getInt("leverAdresId"));

          //get producten van de bestelling
          setAllOrderedProducts(returnOrderInstance);
      }

      if (check == true) {
          return returnOrderInstance;
      }
      return null;
    }

    @Override
    public Order find(int id) throws SQLException {
        return findOrder(id);
    }

    public Order setAllOrderedProducts(Order bestelling) throws SQLException {
        ProductRepository ProductRepository = new ProductRepository();
        String query
        = "select * from besteldeproducten where bestellingID= ?";
        PreparedStatement preparedStatement
            = con.prepareStatement(query);
  
        ArrayList<Product> besteldeProducten = bestelling.getBesteldeProducten();
  
        preparedStatement.setInt(1, bestelling.getBestellingID());
  
        ResultSet resultSet = preparedStatement.executeQuery();
        // add product aan lijst van de bestelling
        while (resultSet.next()) {
          besteldeProducten.add(ProductRepository.find(resultSet.getInt("productID")));
        }
        return bestelling;
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

    @Override
    public void update(Order bestelling) throws SQLException {
        String query
        = "update bestelling set status=? "
          + " where bestellingID = ?";
        PreparedStatement preparedStatement
        = con.prepareStatement(query);
        preparedStatement.setString(1, bestelling.getStatus());
        preparedStatement.setInt(2, bestelling.getBestellingID());
        preparedStatement.executeUpdate();
    }

    public void delete(Order bestelling) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void create(Order obj) throws SQLException {

    }

    public ResultSet findAll() throws SQLException {
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

    @Override
    public void delete(int id) throws SQLException {
        // TODO Auto-generated method stub
        
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
	
    public ArrayList<Order> getNodesInGraph(int graphID) throws SQLException {
        String query = "SELECT * FROM graphnodes WHERE graphID = ?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, graphID);

        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Order> nodeList = new ArrayList<>();


        while(resultSet.next()) {
            nodeList.add(find(resultSet.getInt("nodeID")));
        }
        return nodeList;
    }
}
