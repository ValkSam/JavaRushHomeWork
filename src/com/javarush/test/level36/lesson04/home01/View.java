package com.javarush.test.level36.lesson04.home01;

import java.util.List;

/**
 * Created by Valk on 09.07.15.
 */
public class View {
    private Controller controller = new Controller();
    public void fireEventShowData() {
        System.out.println(controller.onDataListShow());
    }





}
