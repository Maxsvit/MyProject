import Helper.DBHandler;

import java.sql.*;


public class Main {
    static private DBHandler dbHandler;
    static private Connection connection;
    static private PreparedStatement preparedStatement;
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        dbHandler = new DBHandler();
        connection = dbHandler.getDbConnection();
        writeToDb();
        readFromDb();
        updateDb("Mykola","Stepanchuk",21,"Shevchensko Street",2,"mosterp");
        deleteRowDb("martixmax");
    }

    public static void writeToDb() throws SQLException {
        String insert = "INSERT INTO user(first_name,last_name,user_name,adress,age,user_id)" +
                "VALUES(?,?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setString(1, "Albert");
        preparedStatement.setString(2, "Loriks");
        preparedStatement.setString(3, "alkers");
        preparedStatement.setString(4, "Marszalkowska 33");
        preparedStatement.setInt(5, 44);
        preparedStatement.setInt(6, 1);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public static void readFromDb() throws SQLException
    {
        String query = "SELECT * FROM user";
        PreparedStatement preparedStatementForRead = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatementForRead.executeQuery();
        while (resultSet.next()){
            System.out.println("Persona: " + resultSet.getString("first_name") + " "
                    + resultSet.getString("last_name") + " "
                    + resultSet.getInt("age") + " "
                    + resultSet.getString("adress"));
//            System.out.println("Last Name : " + resultSet.getString("last_name"));
//            System.out.println("Name: " + resultSet.getString("first_name"));
        }
    }
    public static void updateDb(String first_name,String last_name, int age, String adress, int id, String user_name) throws SQLException
    {
        String query = "UPDATE user SET first_name = ?,last_name = ?,user_name = ?,adress = ?,age = ? "
                + "WHERE user_id = ?";
        PreparedStatement preparedStatement2 = connection.prepareStatement(query);

        preparedStatement2.setString(1,first_name);
        preparedStatement2.setString(2,last_name);
        preparedStatement2.setString(3,user_name);
        preparedStatement2.setString(4,adress);
        preparedStatement2.setInt(5,age);
        preparedStatement2.setInt(6,id);
        preparedStatement2.executeUpdate();
        preparedStatement2.close();
    }
    public static void deleteRowDb(String username) throws SQLException
    {
        String query = "DELETE FROM user WHERE user_name = ?; ";

        PreparedStatement preparedStatement1 = connection.prepareStatement(query);
        preparedStatement1.setString(1,username);
        preparedStatement1.executeUpdate();
        preparedStatement1.close();


    }
}