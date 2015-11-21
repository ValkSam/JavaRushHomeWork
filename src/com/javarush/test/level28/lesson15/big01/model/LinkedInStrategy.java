package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valk on 23.05.15.
 */
public class LinkedInStrategy implements Strategy {
    private static final String URL_FORMAT = "http://www.linkedin.com/vsearch/j?keywords=java+%s&page_num=%d";

    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> result = new ArrayList<>();
        try {
            int page = 0;
            while (true) {
                Document document = getDocument(searchString, page);
                Elements elements = document.getElementsByAttributeValue("class", "bd");
                if (elements.size() == 0) break;
                for (Element element : elements) {
                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(element.getElementsByAttributeValue("class", "title").text());
                    vacancy.setCompanyName(element.getElementsByAttributeValue("class", "description").text());
                    vacancy.setCity(element.getElementsByAttributeValue("class", "separator").first().getElementsByTag("bdi").text());
                    vacancy.setSiteName(document.title());
                    vacancy.setUrl(element.getElementsByAttributeValue("class", "title").attr("href"));
                    vacancy.setSalary("");
                    result.add(vacancy);
                }
                page++;
            }
        } catch (IOException e) {
        }
        return result;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        searchString = URLEncoder.encode(searchString, "UTF-8");
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page)).userAgent("Mozilla/5.0 (Windows NT 6.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2398.0 Safari/537.36").referrer("https://www.linkedin.com/vsearch/j?keywords=java%20&page_num=1&locationType=I&countryCode=ua&openFacets=L,C,SB&rsid=1321037881432389664198").get();
        /*Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent("Mozilla/5.0 (Windows NT 6.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2398.0 Safari/537.36")
                .referrer("https://www.linkedin.com/vsearch/j?keywords=java%20&page_num=1&locationType=I&countryCode=ua&openFacets=L,C,SB&rsid=1321037881432389664198")
                .data();*/
    }
}
