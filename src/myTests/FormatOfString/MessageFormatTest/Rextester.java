package myTests.FormatOfString.MessageFormatTest;

import java.lang.*;
import java.text.*;
import java.util.*;

/**
 * Created by Valk on 02.04.15.
 */
public class Rextester {
    public static void main(String[] args) {
        {
            //вариант 1
            String value_1 = "знач_1";
            String result = MessageFormat.format("Это шаблон. В скобках параметры {1} и {0}", value_1, "знач_2");
            System.out.println(result);                       //Это шаблон. В скобках параметры знач_2 и знач_1
            //
            //вариант 2
            MessageFormat form = new MessageFormat("Это шаблон. В скобках параметры {1} и {0}");
            Object[] formArgs = {value_1, "знач_2"};
            result = form.format(formArgs);
            System.out.println(result);                       //Это шаблон. В скобках параметры знач_2 и знач_1
            //или
            result = form.format(new Object[] {value_1, "знач_2"});
            System.out.println(result);                       //Это шаблон. В скобках параметры знач_2 и знач_1
            //
        }
        /*
        Параметры, которые в скобках {}

        Варианты содержимого:
            { ArgumentIndex }
         	{ ArgumentIndex , FormatType }
         	{ ArgumentIndex , FormatType , FormatStyle }
        */
        {
            String result1 = MessageFormat.format("Форматированная дата {0}", new Date());
            String result2 = MessageFormat.format("Форматированная дата {0, date}", new Date());
            String result3 = MessageFormat.format("Форматированная дата {0, date, long}", new Date());
            System.out.println(result1); //Форматированная дата 4/2/15 12:05 PM
            System.out.println(result2); //Форматированная дата Apr 2, 2015
            System.out.println(result3); //Форматированная дата April 2, 2015
            //
        }
        /*
        Формат подставляемых значений можно описывать отдельно, через метод setFormats.
        В метод  setFormats передается массив элементов типа    !!Format!!.
        Каждый элемент этого массива соответствует элементам !!строки шаблона!! с соответсвующим !!порядком следования!!. (т.е.в последовательности "...{4}...{1}...", первым будет {4}, хоть его индекс и больше {1})
        При этом, количество  элементов в данных массивах может не совпадать:
        лишние в setFormats отбрасываются.
        Если в setFormats меньше элементов - то на лишние в элементы шаблона действие setFormats не распространяется
        */
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); //SimpleDateFormat - наследник Format
            MessageFormat form = new MessageFormat("Это даты{0,date,long} и {1,date,long}"); //сразу указали длинный формат дат
            Object[] formArgs = {new Date(), new Date(new Date().getTime()+1000000000)};
            form.setFormats(new Format[]{dateFormat, dateFormat} ); //переустановили формат на dd-MM-yyyy
            String result = form.format(formArgs);
            System.out.println(result); //Это даты02-04-2015 и 14-04-2015
        }
        //передаем в setFormats меньшее количество элементов, чем значений в format(formArgs)
        //При этом следование параметров в шаблоне не совпадает со следованием элементов в массиве значений (сначала {2} потом {1}, а {0} вообще нет)
        //Это чтобы показать, что порядок setFormats связан с порядком значений в format, а не с их порядком в шаблоне:
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); //SimpleDateFormat - наследник Format
            MessageFormat form = new MessageFormat("Это даты{2,date,long} и {1,date,long}"); //сразу указали длинный формат дат. Порядок следования меньшая дата - большая дата
            Object[] formArgs = {"строка", new Date(), new Date(new Date().getTime()+1000000000)};
            form.setFormats(new Format[]{dateFormat} ); //переустановили формат на dd-MM-yyyy, только для первого значения. Первое в шаблоне - это {2,date,long}
            String result = form.format(formArgs);
            System.out.println(result); //Это даты14-04-2015 и April 2, 2015    . Т.е. setFormats отформатировал первый элемент шаблона в порядке их следования, а не в порядке индекса
            //
            //т.е. чтобы понять на что действует формат, указанный в setFormats - смотрим на шаблон и на его элементы в порядке фактического следования
        }

        /*Вариант форматирования значений через setFormat (не путать с setFormats)
        Форматирование конкретной позиции в шаблоне, независимо от того какое значение будет туда подставляться
        */
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); //SimpleDateFormat - наследник Format
            MessageFormat form = new MessageFormat("Это даты{2,date,long} и {1,date,long}"); //сразу указали длинный формат дат. Порядок следования меньшая дата - большая дата
            Object[] formArgs = {"строка", new Date(), new Date(new Date().getTime()+1000000000)};
            form.setFormat(0, dateFormat); //переустановили формат на dd-MM-yyyy, только для первого значения (нумерация с 0). Первое в шаблоне - это {2,date,long}
            String result = form.format(formArgs);
            System.out.println(result); //Это даты14-04-2015 и April 2, 2015    . Т.е. setFormats отформатировал первый элемент шаблона в порядке их следования, а не в порядке индекса
            //
            //т.е. чтобы понять на что действует формат, указанный в setFormats - смотрим на шаблон и на его элемент с соответсвующим индексом - индексация в порядке фактического следования элементов шаблона
        }

        /*Форматирование с помощью setFormatsByArgumentIndex*/
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); //SimpleDateFormat - наследник Format
            MessageFormat form = new MessageFormat("Это даты{2,date,long} и {1,date,long}"); //сразу указали длинный формат дат. Порядок следования меньшая дата - большая дата
            Object[] formArgs = {"строка", new Date(), new Date(new Date().getTime()+1000000000)};
            form.setFormatsByArgumentIndex(new Format[]{null, dateFormat} ); //переустановили формат на dd-MM-yyyy, для значения с индексами 0 и 1 в массиве значений (в format).
            //Для значения с индексом 0 ("строка") формат сбросили на значение по умолчанию (указали null).
            //При этом это значение мы и не испоользуем в шаблоне, но пришлось указать его формат в setFormatsByArgumentIndex, чтобы
            //подобраться к значению с индексом 1 (new Date())
            String result = form.format(formArgs);
            System.out.println(result); //Это датыApril 14, 2015 и 02-04-2015
            //
            form.setFormatsByArgumentIndex(new Format[]{null, dateFormat, null} ); //переустановили формат на dd-MM-yyyy, для значения с индексами 0 и 1 в массиве значений (в format).
            //Для значения с индексом 0 и 2 ("строка" и new Date(new Date().getTime()+1000000000)) форматы сбросили на значение по умолчанию (указали null).
            String result1 = form.format(formArgs);
            System.out.println(result1); //Это даты4/14/15 2:53 AM и 02-04-2015
            //
            //т.е. чтобы понять на что действует формат, указанный в setFormatsByArgumentIndex - смотрим на массив значений (в format) и на индексы его элементов, сопоставляя их с инндексами в setFormatsByArgumentIndex
        }

        /*Форматирование с помощью setFormatByArgumentIndex не путать с setFormatsByArgumentIndex
        Форматирование конкретного значения в массиве значений, независимо в каком месте шаблона оно будет использоваться*/
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); //SimpleDateFormat - наследник Format
            MessageFormat form = new MessageFormat("Это даты {2,date,long} и {1,date,long}"); //сразу указали длинный формат дат. Порядок следования меньшая дата - большая дата
            Object[] formArgs = {"строка", new Date(), new Date(new Date().getTime()+1000000000)};
            form.setFormatByArgumentIndex(1, dateFormat); //переустановили формат на dd-MM-yyyy, для значения с индексом 1  (new Date() ) в массиве значений (в format).

            String result = form.format(formArgs);
            System.out.println(result); //Это датыApril 14, 2015 и 02-04-2015
            //
            form.setFormatByArgumentIndex(3, dateFormat); // пытаемся установить формат для значения с индексом 3, который не существует - ошибки не будет, просто ничего не произойдет
            String result1 = form.format(formArgs);
            System.out.println(result1); //Это датыApril 14, 2015 и 02-04-2015
            //
            //т.е. чтобы понять на что действует формат, указанный в setFormatByArgumentIndex - смотрим на массив значений (в format), на элемент с соответсвующим индексом
        }


    }
}
