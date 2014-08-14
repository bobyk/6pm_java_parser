package Models.DAO.Impl;

import Models.DAO.PropertyDAO;
import Models.logic.Property;
import Models.util.HibernateUtil;
import org.hibernate.Criteria;

import java.sql.SQLException;
import java.util.List;

public class PropertyDAOImpl extends Impl implements PropertyDAO {


    @Override
    public Property addProperty(Property property) throws SQLException {
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            Long id = (Long)session.save(property);
            session.getTransaction().commit();

            property.setId(id);
        }
        catch (Exception e) {
            System.out.println("Did not save");
        }

        return property;
    }

    @Override
    public void updateProperty(Property property) throws SQLException {

    }

    @Override
    public Property getPropertyById(Long id) throws SQLException {
        Property property = null;

        try {
            property = (Property)session.load(Property.class, id);
        } catch (Exception e) {
            System.out.println("Did not load item");
        }

        return property;
    }

    @Override
    public List getProperties() throws SQLException {
        List list = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(Property.class)
                    .setMaxResults(50);

            list = criteria.list();
        } catch (Exception e) {
            System.out.println("Did not load items");
        }

        return list;
    }

    @Override
    public Boolean deleteProperty(Property property) throws SQLException {

        if(property.getId() < 1)
            return false;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.createQuery("delete from Property where id = :id")
                    .setLong("id", property.getId())
                    .executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Did not delete item");
        }
        finally {
            if(session != null && session.isOpen())
            {
//                session.getTransaction().rollback();
                session.close();
            }
        }

        return true;
    }
}
