package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mebat_000 on 12.08.2014.
 */
public class DataLayer extends Table {

    public String getNextUrl() {
        String url = "";

        String query = "SELECT url FROM parserUrl WHERE parsed = 0 ORDER BY id ASC LIMIT 1";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            if(ps.execute()) {
                ResultSet resultSet =  ps.getResultSet();
                url = resultSet.getString("url");

                Long id = resultSet.getLong("id");
                if(id > 0) {
                    query = "UPDATE parserUrl SET parsed = 1 WHERE id = ?";
                    ps = connection.prepareStatement(query);
                    ps.setLong(1, id);
                    ps.executeUpdate();
                }
            }
        }
        catch(SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return url;
    }

    public boolean insert() {
        String query = "INSERT INTO goods (title) VALUES (?)";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "title");
            ps.executeUpdate();
        }
        catch(SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

//        statement.executeUpdate("INSERT INTO goods (title) VALUES ()");

        return true;
    }

}
