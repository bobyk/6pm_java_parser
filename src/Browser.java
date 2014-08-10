import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

/**
 * Created by User on 05.08.2014.
 */
public class Browser {

    protected Hashtable<String, String> pages = new Hashtable<String, String>();

    public Document createDocument(String url) {

        Document document = null;

        try {
            document = Jsoup.connect(url).get();
        }
        catch(Exception e) {

        }

        return document;
    }

    public Elements getElements(String url, final String itemSelector, final String pagesSelector) {
        Elements elements = createDocument(url).select(pagesSelector);

        for(final Element element: elements) {
            String page = element.absUrl("href");
            System.out.println("page: " + page);

            if(!pages.contains(page)) {
                try {
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            new Browser().getElements(element.absUrl("href"), itemSelector, pagesSelector);
                        }
                    };
                    thread.start();
                } catch (Exception e) {
                }
            }

            pages.put(page, page);
        }

        Elements items = createDocument(url).select(itemSelector);

        for(final Element item: items) {
            System.out.println("Item: " + item.text());
        }

        return items;
    }
}
