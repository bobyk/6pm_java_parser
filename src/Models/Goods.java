package Models;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by mebat_000 on 06.08.2014.
 */
public class Goods extends Table {

    public String url = "";
    public String json = "";

    public boolean insert(String url, String json) {
        String query = "INSERT INTO goods (url, data) VALUES (?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, url);
            ps.setString(2, json);
            ps.executeUpdate();
        }
        catch(SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return false;
        }

        return true;
    }



}
