package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Valk on 17.05.15.
 */
public class HtmlView implements View {
    private final String filePath = "./src/" + this.getClass().getPackage().getName().replaceAll("\\.", "/") + "/vacancies.html";

    private Controller controller;

    @Override
    public void update(List<Vacancy> vacancies) {
        updateFile(getUpdatedFileContent(vacancies));
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }


    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        try {
            Document document = getDocument();
            Elements elements = document.getElementsByAttributeValue("class", "vacancy template");
            Element lastElement = elements.first();
            Element templElement = elements.first().clone();
            templElement.removeAttr("style");
            templElement.removeClass("template");
            document.getElementsByAttributeValue("class", "vacancy").remove();

            for (Vacancy vacancy : vacancies){
                Element element = templElement.clone();
                element.getElementsByAttributeValue("class","city").append(vacancy.getCity());
                element.getElementsByAttributeValue("class", "companyName").append(vacancy.getCompanyName());
                element.getElementsByAttributeValue("class", "salary").append(vacancy.getSalary());
                element.getElementsByAttributeValue("href", "url").first().attr("href", vacancy.getUrl()).appendText(vacancy.getTitle());

                lastElement.before(element.outerHtml());
            }

            return document.outerHtml();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Some exception occurred";
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

    private void updateFile(String fileBody) {
        File file = new File(filePath);
        try {
            PrintWriter out = new PrintWriter(file);
            out.write(fileBody);
            out.close();
        } catch (Exception e) {
        }
    }
}
