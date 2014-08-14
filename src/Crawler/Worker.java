package Crawler;

import Models.Goods;
import com.sun.corba.se.impl.orbutil.ObjectWriter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.org.mozilla.javascript.internal.json.JsonParser;

/**
 * Created by User on 10.08.2014.
 */
public class Worker {

    protected IDataLayer manager;

    public Worker(IDataLayer manager) {
        this.manager = manager;
    }

    public void work(Integer countThread) {
        Thread threads[] = new Thread[countThread];

        for(int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(){
                @Override
                public void run() {
                    while (manager.canDoWork()) {
                        Document document = manager.popDocument();

                        if (document != null) {
                            parsePages(document);
                            parseItems(document);
                            parseItem(document);
                        }

                        try {
                            sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };

            threads[i].start();
        }
    }

    public synchronized void parsePages(Document document) {
        Elements elements = document.select("#resultWrap .sort.top .pagination > a");

        if(elements.size() > 0) {
            for (Element element : elements) {
                String url = element.absUrl("href");
                manager.pushNextUrl(url);
            }
        }
    }

    public void parseItems(Document document) {
        Elements elements = document.select("#searchResults > a");

        for(Element element: elements) {
            String itemUrl = element.absUrl("href");
            manager.pushItemUrl(itemUrl);
            manager.pushNextUrl(itemUrl);
//            System.out.println("Item URL:"+ itemUrl);
        }
    }

    public void parseItem(Document document) {
        Element element = document.select("#productStage h1.title").first();

        if(element == null)
            return ;

        String titleH1 = element.text();
        manager.pushItem(document.baseUri(), titleH1);

    }

}
