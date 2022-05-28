package repository;

import model.Bestelling;
import repository.interfaces.CrudInterface;
import model.DBConnection;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BestellingRepository<T> implements CrudInterface<Bestelling>{
    private Connection con = DBConnection.getConnection();

    @Override
    public void add(Bestelling obj) throws SQLException {
      // TODO Auto-generated method stub
      // doen we nog niets mee
      
    }

    @Override
    public Bestelling find(int id) throws SQLException {
      String query
      = "select * from bestelling where bestellingID= ?";
      PreparedStatement preparedStatement
          = con.prepareStatement(query);

      preparedStatement.setInt(1, id);
      Bestelling returnInstance = new Bestelling();
      ResultSet resultSet = preparedStatement.executeQuery();
      boolean check = false;

      while (resultSet.next()) {
          check = true;
          returnInstance.setBestellingID(resultSet.getInt("bestellingID"));
          returnInstance.setPersoonID(resultSet.getInt("persoonID"));
          returnInstance.setStatus(resultSet.getString("status"));
          returnInstance.setBestelDatum(resultSet.getDate("besteldatum"));
          returnInstance.setLeverDatum(resultSet.getDate("leverdatum"));
          returnInstance.setLeverAdresId(resultSet.getInt("leverAdresId"));

          //get producten van de bestelling
          setAllOrderedProducts(returnInstance);
      }

      if (check == true) {
          return returnInstance;
      }
      return null;
    }

    @Override
    public void Update() throws SQLException {
      // TODO Auto-generated method stub
        
      // String query
      // = "update bestelling set status=?, "
      //   + "where bestellingID = ?";
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
      = "select * from bestelling";
      PreparedStatement preparedStatement
          = con.prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery();
      return resultSet;
    }

    public Bestelling setAllOrderedProducts(Bestelling bestelling) throws SQLException {
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
  }