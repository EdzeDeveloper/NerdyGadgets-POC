package repository;

import model.Product;
import model.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import interfaces.CrudInterface;

public class ProductRepository<T> implements CrudInterface<Product>{
    private Connection con = DBConnection.getConnection();

    @Override
    public Product find(int id) throws SQLException {
      String query
      = "select * from product where productID= ?";
      PreparedStatement preparedStatement
          = con.prepareStatement(query);

      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      boolean check = false;
      Product returnProductInstance = new Product();
      while (resultSet.next()) {
        check = true;
        returnProductInstance.setProductID(resultSet.getInt("productID"));
        returnProductInstance.setProductNaam(resultSet.getString("productnaam"));
        returnProductInstance.setPrijs(resultSet.getString("prijs"));
      }

      if (check == true) {
          return returnProductInstance;
      }
      return returnProductInstance;
    }

    @Override
    public ResultSet findAll() throws SQLException {
      String query
      = "select * from retour";
      PreparedStatement preparedStatement
          = con.prepareStatement(query);

      ResultSet resultSet = preparedStatement.executeQuery();
      return resultSet;
    }

    @Override
    public void create(Product obj) throws SQLException {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void update(Product obj) throws SQLException {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void delete(int id) throws SQLException {
      // TODO Auto-generated method stub
      
    }
  }

