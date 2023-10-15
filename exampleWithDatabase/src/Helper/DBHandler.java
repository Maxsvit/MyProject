package Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler extends Config{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
            String ConnectionString = "jdbc:mysql://" + dbHost +
                    ":" + dbPort +
                    "/" + dbName;

           //Class.forName("com.mysql.jdbc.Driver");
            dbConnection = DriverManager.getConnection(ConnectionString,dbUser,dbPassword);

            return dbConnection;
        }
}
