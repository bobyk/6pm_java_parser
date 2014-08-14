package Models.DAO;


import Models.DAO.Impl.PropertyDAOImpl;

public class Factory {

    private static PropertyDAO propertyDAO = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance() {
        if(instance == null) {
            instance = new Factory();
        }

        return instance;
    }

    public PropertyDAO getPropertyDAO() {
        if(propertyDAO == null)
            propertyDAO = new PropertyDAOImpl();

        return propertyDAO;
    }


}
