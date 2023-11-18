package DatabaseConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    private String url;
    private String user;
    private String password;


    public Connection getConnection() {
        this.url = System.getenv("jdbc_url") ;
        this.user = Parameters.USERNAME;
        this.password = Parameters.PASSWORD;
        try {
            return DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
