package Models;

import java.sql.*;

/**
 * Created by mebat_000 on 06.08.2014.
 */
abstract public class Table {

    protected static Connection connection = null;
    protected static Statement statement = null;

    public Table() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect("jdbc:mysql://192.168.0.102/test", "dev", "6tmwmyhZzw5E6ZR9");
        }
        catch(Exception e) {

        }
    }

    public void connect(String dsn, String user, String password) {
        try {
            connection = DriverManager.getConnection(dsn, user, password);
            statement = connection.createStatement();
        }
        catch(SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

//    public boolean insert() {
//        String query = "INSERT INTO goods (title) VALUES (?)";
//
//        try {
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setString(1, "title");
//            ps.executeUpdate();
//        }
//        catch(SQLException ex) {
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());
//        }
//
////        statement.executeUpdate("INSERT INTO goods (title) VALUES ()");
//
//        return true;
//    }

    public void close() {
        try {
            connection.close();
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
