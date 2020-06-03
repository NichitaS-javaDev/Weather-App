import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class Parser {

    Parser() throws IOException {
        GetInfo();
    }

    public Elements getDates() {
        return dates;
    }

    public Elements getTempMax() {
        return tempMax;
    }

    public Elements getTempMin() {
        return tempMin;
    }

    public Elements getSky() {
        return sky;
    }

    Elements dates, tempMax, tempMin, sky;

    public Document getPage() throws IOException {
        String url = "http://www.meteo.md/index.php/ru/weather/current-forecast/1770";
        Document page = Jsoup.parse(new URL(url), 20000);
        return page;
    }

    public void GetInfo() throws IOException {
        Document page = getPage();

        Element tableWth = page.select("div[class=forecast-list]").first();
        Elements tabelLines = tableWth.select("li");
        dates = tabelLines.select("span[class=date]");
        tempMax = tabelLines.select("span[class=max]");
        tempMin = tabelLines.select("span[class=min]");
        sky = tabelLines.select("span[class=icon]");
    }

}
