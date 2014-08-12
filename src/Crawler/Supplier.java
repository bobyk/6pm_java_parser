package Crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by User on 10.08.2014.
 */
public class Supplier {

    protected IDataLayer manager;

    public Supplier(IDataLayer manager) {
        this.manager = manager;
    }

    public void work(Integer countThread) {

        Thread threads[] = new Thread[countThread];

        for(int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(){
                @Override
                public void run() {
                    while(manager.canDoWork()) {
                        String url = manager.popNextUrl();

                        if (url != null) {
                            manager.pushDocument(
                                loadDocument(url)
                            );
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

    public Document loadDocument(String url) {
        Document document = null;

        try {

            document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.125 Safari/537.36")
                    .get();

        } catch (IOException e) {
            manager.dropTimer();
            System.out.println("Document did not load"+ e.getMessage());
        }

        return document;
    }

}
