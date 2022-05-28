package repository;
import model.*;
import repository.interfaces.CrudInterface;
import model.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class PersoonRepository implements CrudInterface<Persoon> {
    private Connection con = DBConnection.getConnection();

    @Override
    public void add(Persoon obj) throws SQLException {

    }

    @Override
    public Persoon get(int id) throws SQLException {
        String query = "select * from persoon where persoonID= ?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, id);

        Persoon persoonInstance = new Persoon();
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean check = false;

        while (resultSet.next()) {
            check = true;
            persoonInstance.setPersoonID(resultSet.getInt("persoonID"));
            persoonInstance.setVoornaam(resultSet.getString("voornaam"));
            persoonInstance.setAchternaam(resultSet.getString("achternaam"));
            persoonInstance.setAdresID(resultSet.getInt("adresID"));
            persoonInstance.setEmail(resultSet.getString("email"));
        }

        if (check) {
            return persoonInstance;
        }
        return null;
    }

    @Override
    public ArrayList<Persoon> getAll(String key) throws SQLException {
        return null;
    }


    @Override
    public void Update(Persoon obj) throws SQLException {

    }

    @Override
    public void Delete(String key) throws SQLException {

    }
}
