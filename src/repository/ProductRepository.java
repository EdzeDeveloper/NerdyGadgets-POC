package repository;

import model.Product;
import repository.interfaces.CrudInterface;
import model.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRepository<T> implements CrudInterface<Product>{
    private Connection con = DBConnection.getConnection();

    @Override
    public void add(Product obj) throws SQLException {
      // TODO Auto-generated method stub
      
    }

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
    public void Update() throws SQLException {
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

    @Override
    public void Delete(String key) throws SQLException {
      // TODO Auto-generated method stub
      
    }

    @Override
    public ResultSet getAll() throws SQLException {
      String query
      = "select * from retour";
      PreparedStatement preparedStatement
          = con.prepareStatement(query);

      ResultSet resultSet = preparedStatement.executeQuery();
      return resultSet;
    }
  }

