
import Crawler.Manager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Parser {

    public static void main(String[] args) {

        Manager manager = new Manager();
        manager.pushNextUrl(
                "http://www.6pm.com/converse~1?s=isNew/desc/goLiveDate/desc/recentSalesStyle/desc/"
        );
        manager.run();


//        Browser browser = new Browser();
//        browser.getElements(
//            "http://www.6pm.com/mens-shoes~s?s=isNew/desc/goLiveDate/desc/recentSalesStyle/desc/#!/men-sneakers-athletic-shoes~1",
//            "#searchResults > a > .productName",
//            ".sort.top .pagination > a"
//        );

    }

}
