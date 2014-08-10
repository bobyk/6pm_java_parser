package Crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

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
                    while(true) {
                        String url = manager.popNextUrl();
//                        System.out.print(url);

                        if (url != null) {
                            try {
                                Document document = Jsoup.connect(url).get();
                                manager.pushDocument(document);
                            } catch (Exception e) {
                                System.out.println("Document did not load"+ e.getMessage());
                            }
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

}
