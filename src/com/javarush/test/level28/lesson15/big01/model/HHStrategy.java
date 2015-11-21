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
 * Created by Valk on 15.05.15.
 */
public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> result = new ArrayList<>();
        try {
            int page = 0;
            while (true) {
                Document document = getDocument(searchString, page);
                Elements elements = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
                if (elements.size() == 0) break;
                for (Element element : elements) {
                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(element.getElementsByAttributeValueEnding("data-qa", "-title").text());
                    vacancy.setCompanyName(element.getElementsByAttributeValueEnding("data-qa", "-employer").text());
                    vacancy.setCity(element.getElementsByAttributeValueEnding("data-qa", "-address").text());
                    vacancy.setSiteName(document.title());
                    vacancy.setUrl(element.getElementsByAttributeValueEnding("data-qa", "-title").attr("href"));
                    vacancy.setSalary(element.getElementsByAttributeValueEnding("data-qa", "-compensation").text());
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
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page)).userAgent("Mozilla/5.0 (Windows NT 6.0; WOW64; rv:37.0) Gecko/20100101 Firefox/37.0").referrer("google.ru").get();
    }
}
