package Crawler;

import org.jsoup.nodes.Document;

/**
 * Created by User on 10.08.2014.
 */
public interface IDataLayer {

    public void pushDocument(Document document);
    public Document popDocument();

    public void pushNextUrl(String url);
    public String popNextUrl();
    public Boolean issetNextUrl();

    public void pushItemUrl(String url);
    public String popItemUrl();

    public void pushItem(String url, String jsonItem);
    public String popItem();

    public void dropTimer();
    public void doNothing();
    public Boolean canDoWork();

}
