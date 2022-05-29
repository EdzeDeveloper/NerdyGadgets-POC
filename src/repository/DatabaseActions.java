package repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;

import model.DBConnection;

public class DatabaseActions {
    private Connection con = DBConnection.getConnection();

    public void emptyGraphFromDatabase(String query) throws SQLException {;
        PreparedStatement preparedStatement
        = con.prepareStatement(query);
        preparedStatement.executeUpdate();
    }
}
