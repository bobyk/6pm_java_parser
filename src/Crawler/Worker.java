package Crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
                    while(true) {
                        Document document = manager.popDocument();

                        if (document != null) {
                            parsePages(document);
                            parseItems(document);
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

    public void parsePages(Document document) {
        Elements elements = document.select("#resultWrap .sort.top .pagination > a");

        for(Element element: elements) {
            String url = element.absUrl("href");
            manager.pushNextUrl(url);
        }
    }

    public void parseItems(Document document) {
        Elements elements = document.select("#searchResults > a");

        for(Element element: elements) {
            String itemUrl = element.absUrl("href");
            manager.pushItemUrl(itemUrl);
            //System.out.println("Item URL:"+ itemUrl);
        }
    }

}
