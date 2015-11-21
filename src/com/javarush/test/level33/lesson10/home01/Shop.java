package com.javarush.test.level33.lesson10.home01;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Valk on 11.06.15.
 */
@XmlRootElement
public class Shop {
    @XmlElementWrapper(name = "goods")
    public String[] names;
    public int count;
    public double profit;
    public String[] secretData;
}

