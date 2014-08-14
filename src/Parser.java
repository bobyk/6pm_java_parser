
import Crawler.Manager;
import Models.DAO.Factory;
import Models.logic.Property;

import java.sql.SQLException;
import java.util.List;


public class Parser {

    public static void main(String[] args) throws SQLException {
//        PropertyConfigurator.configure("z:/hosts/git/6pm_java_parser/bin/log4j.properties");

        Property property = new Property();
        property.setTitle("Test");
        property.setType(1);


        for(int i = 0; i < 10000; i++)
            property = Factory.getInstance().getPropertyDAO().addProperty(property);

        List<Property> list = Factory.getInstance().getPropertyDAO().getProperties();

        for(Property prop: list) {
            System.out.println(prop.getTitle());
        }

        if(property.getId() != null)
        {
//            Factory.getInstance().getPropertyDAO().deleteProperty(property);
        }


//        Manager manager = new Manager();
//        manager.pushNextUrl(
////                "http://www.6pm.com/money-clips~1"
////                "http://www.6pm.com/tri-folds~1"
//                "http://www.6pm.com/converse~1?s=isNew/desc/goLiveDate/desc/recentSalesStyle/desc/"
////                "http://www.6pm.com/clothing~5B"
////                "http://www.6pm.com/women-sneakers-athletic-shoes~f"
//        );
//        manager.run();


    }

}
