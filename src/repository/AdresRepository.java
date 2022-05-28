package repository;
import model.*;
import interfaces.CrudInterface;

import java.sql.*;

public class AdresRepository implements CrudInterface<Adres> {
    private Connection con = DBConnection.getConnection();

    @Override
    public void create(Adres obj) throws SQLException {
        String query = "INSERT INTO adres (adresID, straatnaam, huisnummer, huisletter, postcode, woonplaats, x, y) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, obj.getAdresId());
        preparedStatement.setString(2, obj.getStraatnaam());
        preparedStatement.setInt(3, obj.getHuisnummer());
        preparedStatement.setString(3, obj.getHuisletter());
        preparedStatement.setString(3, obj.getPostcode());
        preparedStatement.setString(3, obj.getWoonplaats());
        preparedStatement.setInt(3, obj.getX());
        preparedStatement.setInt(3, obj.getY());
        preparedStatement.executeUpdate();
    }

    @Override
    public Adres find(int id) throws SQLException {
        String query = "select * from adres where adresID= ?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, id);

        Adres graphInstance = new Adres();
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean check = false;

        while (resultSet.next()) {
            check = true;
            graphInstance.setAdresId(resultSet.getInt("adresID"));
            graphInstance.setStraatnaam(resultSet.getString("straatnaam"));
            graphInstance.setHuisnummer(resultSet.getInt("huisnummer"));
            graphInstance.setHuisletter(resultSet.getString("huisletter"));
            graphInstance.setPostcode(resultSet.getString("postcode"));
            graphInstance.setWoonplaats(resultSet.getString("woonplaats"));
            graphInstance.setX(resultSet.getInt("x"));
            graphInstance.setY(resultSet.getInt("y"));
        }

        if (check) {
            return graphInstance;
        }
        return null;
    }

    @Override
    public ResultSet findAll() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(Adres obj) throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(int id) throws SQLException {
        // TODO Auto-generated method stub
        
    }
}
