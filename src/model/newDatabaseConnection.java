package model;
import java.sql.*;
import java.util.ArrayList;

public class newDatabaseConnection {

    public static Connection createConnection() throws SQLException {

        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/nerdygadgets", "root" , "");
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public Boolean create(String updateQuery) throws SQLException {
        try (Connection dbConnection = createConnection()) {
            int i = createConnection().createStatement().executeUpdate(updateQuery);
            if (i > 0) {
                System.out.println("nieuwe item toegevoegd.");
                return true;
            }
            System.out.println("Fout gegaan met toevoegen van item.");
        }
        return false;
    }

    public static ResultSet read(String query) throws SQLException {
        // check of er connectie gemaakt kan worden (wordt daarna direct gesloten)
        Connection dbConnection = createConnection();
        try (dbConnection) {
            // als er connectie gemaakt kan worden deze
            return createConnection().createStatement().executeQuery(query);
        } finally {
            System.out.println("vgm is die nu closed2222");
            dbConnection.close();
        }
    }

    // public update(String query) {

    // }
    // public delete(String query) {

    // }

}