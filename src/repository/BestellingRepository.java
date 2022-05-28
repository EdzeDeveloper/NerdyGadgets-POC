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
    public void create(Bestelling obj) throws SQLException {
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
    public ResultSet findAll() throws SQLException {
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

    @Override
    public void update(Bestelling obj) throws SQLException {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void delete(String key) throws SQLException {
      // TODO Auto-generated method stub
      
    }
  }