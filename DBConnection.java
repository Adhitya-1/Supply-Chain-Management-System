import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    public Connection con;
    String url = "jdbc:mysql://localhost:3306";
    String db = "supplychainmanagement";
    String user = "root";
    String pass = "Adhitya@1";

    public Connection mkDataBase() throws SQLException {
        try {
            // Load the MySQL JDBC driver class
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Corrected the URL format and included the database name in the URL
            String fullUrl = url + "/" + db;

            // Establish the connection
            con = DriverManager.getConnection(fullUrl, user, pass);
        } catch (ClassNotFoundException ex) {
            // Handle class not found exception
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
