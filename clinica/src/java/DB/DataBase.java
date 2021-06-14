package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author erika
 */
public class DataBase {

    /*
    static String host = "localhost";
    static String port = "3306";
    static String database = "clinic";
    
     */
    static String user = "sa";
    static String password = "";
    static String URLDatabaseFile = "jdbc:h2:~/clinic;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE";

    static Connection cnx = null;
    static ResultSet st = null;
    static PreparedStatement pst = null;

    public static Connection Conect() {
        Properties data = new Properties();
        data.put("user", user);
        data.put("password", password);

        try {
            Class.forName("org.h2.Driver");
            cnx = DriverManager.getConnection(URLDatabaseFile, data);
            return cnx;
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        return null;
    }
}
