package repository;
import model.Order;
import model.Return;
import model.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import interfaces.CrudInterface;

public class ReturnRepository<T> implements CrudInterface<Return>{
    private Connection con = DBConnection.getConnection();

    public void create(Return obj) throws SQLException {
        String query
                = "INSERT INTO retour (bestellingID, reden) " +
                "VALUES (?, ?);";
        PreparedStatement preparedStatement
                = con.prepareStatement(query);

        preparedStatement.setInt(1, obj.getBestellingID());
        preparedStatement.setString(2, obj.getReden());

        preparedStatement.executeUpdate();

        //  Get the last inserted ID and set the retour ID to the obj.
        String lastIdQuery
                = "SELECT LAST_INSERT_ID() as last_id;";
        PreparedStatement lastIdStatement
                = con.prepareStatement(lastIdQuery);

        ResultSet lastIdResult = lastIdStatement.executeQuery();
        while (lastIdResult.next()) {
            obj.setRetourID(lastIdResult.getInt("last_id"));
        }

        //insert products into product-return pivot table.
        Map<Integer, Integer> returnedProducts = obj.getReturnedProducts();
        returnedProducts.forEach((productID, quantity) -> {
            try {
                String pivotQuery
                        = "INSERT INTO retourproducten (productID, retourID, aantal) " +
                            "VALUES (?, ?, ?);";

                PreparedStatement pivotStatement
                        = con.prepareStatement(pivotQuery);

                pivotStatement.setInt(1, productID);
                pivotStatement.setInt(2, obj.getRetourID());
                pivotStatement.setInt(3, quantity);

                pivotStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        //Update order status into order.STATUS_RETURN_REGISTERED constant.
        String updateStatusQuery
                = "UPDATE bestelling SET status= ? WHERE bestellingID= ?;";
        PreparedStatement updateStatusStatement
                = con.prepareStatement(updateStatusQuery);

        updateStatusStatement.setString(1, Order.STATUS_RETURN_REGISTERED);
        updateStatusStatement.setInt(2, obj.getBestellingID());

        updateStatusStatement.executeUpdate();
    }

    @Override
    public Return find(int id) throws SQLException {
      String query
          = "select * from retour where retourID= ?";
      PreparedStatement preparedStatement
          = con.prepareStatement(query);

      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      Return returnInstance = new Return();

      while (resultSet.next()) {
          returnInstance.setBestellingID(resultSet.getInt("bestellingID"));
          returnInstance.setReden(resultSet.getString("reden"));
          returnInstance.setRetourID(resultSet.getInt("retourID"));
      }

      //We don't need a check, when we can't find the ID in db, we just return an empty object.
      return returnInstance;
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
    public void update(Return obj) {
        // TODO Auto-generated method stub
    }

    @Override
    public void delete(int id) throws SQLException {
        // TODO Auto-generated method stub
        String query
        = "delete from retour where retourID = ?";
        PreparedStatement preparedStatement
        = con.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        
    }
  }
