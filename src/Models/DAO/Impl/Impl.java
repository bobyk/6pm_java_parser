package Models.DAO.Impl;

import Models.util.HibernateUtil;
import org.hibernate.Session;

import javax.swing.*;

/**
 * Created by mebat_000 on 14.08.2014.
 */
public class Impl {

    protected static Session session = null;

    public Impl() {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "MY :::: Error I/0", JOptionPane.OK_OPTION);
        }
        finally {
            if(session != null && session.isOpen()) {
                session.close();
            }
        }
    }



    @Override
    protected void finalize() throws Throwable {
        if(session != null && session.isOpen()) {
//            session.getTransaction().rollback();
            session.close();
        }

        super.finalize();
    }


}
