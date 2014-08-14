package Models.DAO;


import Models.logic.Property;

import java.sql.SQLException;
import java.util.List;

public interface PropertyDAO {
    public Property addProperty(Property property) throws SQLException;
    public void updateProperty(Property property) throws SQLException;
    public Property getPropertyById(Long id) throws SQLException;
    public List getProperties() throws SQLException;
    public Boolean deleteProperty(Property property) throws SQLException;
}
