package Crawler;

import Models.Goods;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.jsoup.nodes.Document;

import java.util.Hashtable;
import java.util.Stack;

/**
 * Created by User on 10.08.2014.
 */
public class Manager implements IDataLayer {
    protected Hashtable<String, String> loadedUrls = new Hashtable<String, String>();
    protected Stack<Document> documents = new Stack<Document>();
    protected Stack<String> urls = new Stack<String>();
    protected Stack<String> itemUrls = new Stack<String>();
    protected Boolean working = true;

    protected Goods goods = new Goods();

    public void run() {

        Supplier supplier = new Supplier(this);
        Worker   worker = new Worker(this);

        supplier.work(4);
        worker.work(2);

    }

    @Override
    public void pushDocument(Document document) {
        documents.push(document);
        System.out.println("Push document: "+ document.hashCode());
    }

    @Override
    public synchronized Document popDocument() {
        if(documents.size() > 0) {
            Document document = documents.pop();
            System.out.println("Pop document: " + document.hashCode());

            return document;
        }

        return null;
    }

    @Override
    public synchronized void pushNextUrl(String url) {
        if(loadedUrls.contains(url) == false) {
            urls.push(url);
            loadedUrls.put(url, url);

            System.out.println("Push url: " + url);
        }
    }

    @Override
    public synchronized String popNextUrl() {
        if(urls.size() > 0) {
            String url = urls.pop();
            System.out.println("Pop url: " + url);

            return url;
        }

        return null;
    }

    @Override
    public void pushItemUrl(String url) {
        itemUrls.push(url);
//        System.out.println("Items url: "+ url);
    }

    @Override
    public String popItemUrl() {
        if(itemUrls.size() > 0) {
            String url = itemUrls.pop();
//            System.out.println("Items URL: " + url);

            return url;
        }

        return null;
    }

    @Override
    public void pushItem(String url, String jsonItem) {
        goods.insert(url, jsonItem);
        System.out.println("JSON: "+ jsonItem);
    }

    @Override
    public String popItem() {
        return null;
    }

    @Override
    public void doNothing() {
        working = false;
    }

    @Override
    public Boolean canDoWork() {
        return working;
    }
}
