package com.javarush.test.level28.lesson15.big01;

import com.javarush.test.level28.lesson15.big01.model.HHStrategy;
import com.javarush.test.level28.lesson15.big01.model.LinkedInStrategy;
import com.javarush.test.level28.lesson15.big01.model.Model;
import com.javarush.test.level28.lesson15.big01.model.Provider;
import com.javarush.test.level28.lesson15.big01.view.HtmlView;

/**
 * Created by Valk on 15.05.15.
 */
public class Aggregator {
    public static void main(String[] args) {
        HtmlView view = new HtmlView();
        //Provider HHProvider = new Provider(new HHStrategy());
        Provider LINProvider = new Provider(new LinkedInStrategy());
        //Model model = new Model(view, new Provider[] {HHProvider});
        Model model = new Model(view, new Provider[] {LINProvider});
        Controller controller = new Controller(model);
        view.setController(controller);
        view.userCitySelectEmulationMethod();
    }
}
