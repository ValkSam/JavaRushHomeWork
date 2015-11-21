package myTests.FormatOfString.MessageFormatTest.ChoiceFormatTest;

import java.text.ChoiceFormat;
import java.text.MessageFormat;
import java.util.Date;

/**
 * Created by Valk on 02.04.15.
 */
public class Rextester {
    public static void main(String[] args) {
        double[] limits = {0, 3};
        String[] formats = {"показана дата {1,date,short}", "выведено число {2}"};
        ChoiceFormat chFormat = new ChoiceFormat(limits, formats);
        {
            MessageFormat form = new MessageFormat("1. для значения {2} {0}");
            form.setFormatByArgumentIndex(0, chFormat); //для элемента с индексом 0 в масиве значений (Long.valueOf(i))
            // установили формат, предполагающий варианты в зависимости от значения этого элемента
            //т.е. устанавливая формат, мы ориентируемся на массив значений, а именно на знание какой индекс
            // будет иметь в этом массиве элемент, который будет использоваться для выбора в chFormat
            //
            for (int i = 0; i < 6; i++) {
                System.out.println(form.format(new Object[]{Long.valueOf(i), new Date(), i}));
            }
        }
        System.out.println("============================================");
        //
        {
            //а можно так: ориентируемся не на индекс в масиве значений , а на параметр (его номер по порядку следования)
            //в строке шаблона
            MessageFormat form = new MessageFormat("2. для значения {2,number,percent} {0}");
            //ВАЖНО: " ... {2 ,number,percent} ..." (пробел перед запятой) - будет ошибка
            form.setFormat(1, chFormat); //для параметра с номером 1 в строке шаблона (это параметр {0}. Параметр {2} имеет номер 0)
            // установили формат, предполагающий варианты в зависимости от фактического значения этого параметра
            //
            for (int i = 0; i < 6; i++) {
                System.out.println(form.format(new Object[]{Long.valueOf(i), new Date(), i}));
            }
        }
        System.out.println("============================================");
        //
        {
            //а можно так: использование FormatType = choice
            MessageFormat form = new MessageFormat("3. для значения {2} {0,choice, 0#показана дата {1,date,short}|3#выведено число {2}}");
            for (int i = 0; i < 6; i++) {
                System.out.println(form.format(new Object[]{Long.valueOf(i), new Date(), i}));
            }
        }
    }
}
