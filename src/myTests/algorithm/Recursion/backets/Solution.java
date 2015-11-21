package myTests.algorithm.Recursion.backets;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Valk on 15.06.15.
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recursion("cos(2+5)^3*sin(cos(2+5)^3)*5+1*2*4/4 + 3^4*(cos(sin(4+5)+tan(3))) + (2*(1+2^3)+4*2) - 3+(2) + 2*(1+3*4)  + ((3*4+1)/(3))*7*((1/5)+3*4)*( 1 + (2+(1+3)+(4)) * 5*(2+4) )^2+( 1 + (2+(1+3)+(4)) * 5*(2+4) )*2", 0); //expected output 0.5 6
        //solution.recursion("(2*(1+2^3)+4*2) - 3+(2) + 2*(1+3*4)", 0); //expected output 0.5 6
        //solution.recursion("( 5*(2+4) )^2+( 1 + (2+(1+3)+(4)) * 5*(2+4) )*2", 0); //expected output 0.5 6
        //solution.recursion("2+3*4", 0); //expected output 0.5 6
    }

    public void recursion(final String expression, int countOperation) {
        String str = expression.replaceAll(" ", "").toLowerCase();
        str = AddBacket.formatExpression(2, AddBacket.formatExpression(1, str, 0), 0);
        System.out.println(str+" <<");
        System.out.println();
        String[] operands = new String[2];
        Pattern pattern = Pattern.compile("(\\({1}[\\p{ASCII}&&[^\\(\\)]]*\\){1})"); //выбираем внутренние скобки
        Matcher matcher = pattern.matcher(str);
        boolean isBracket = matcher.find();
        do {
            //для каждой пары скобок ...
            String localExpresion_3level;
            String initialLocalExpression;
            if (isBracket) {
                localExpresion_3level = matcher.group(); //выражение внутри скобок
                initialLocalExpression = localExpresion_3level;
                localExpresion_3level = localExpresion_3level.substring(1, localExpresion_3level.length() - 1); //убрать обрамляющие скобки
            } else {
                localExpresion_3level = str; //скобок нет - работаем со всем выражением
                initialLocalExpression = localExpresion_3level;
            }
            String[] operandList_3level = localExpresion_3level.split("[\\+\\-\\*\\^/]");
            if (operandList_3level[0].isEmpty())
                operandList_3level[0] = "0"; //если впереди пустой символ (пустая строка или впереди знак "-") - заменить его на 0 (в массиве всегда будет хотя бы одна запись, даже если строка пустая)
            String tmpStr = localExpresion_3level.replaceAll("[\\p{ASCII}&&[^\\+\\-\\*\\^/]]", ""); //оставляем только знаки действий
            String[] operationArr_3level = tmpStr.split(""); //помещаем знаки действий в массив

                    //последовательно выполняем действия ...
                    int k = 0;
                    do { //цикл фор до предпоследнего элемента не годится, т.к. надо иметь возможность обработать одиночный операнд на предмет расчета функции
                        operands[0] = operandList_3level[k];
                        operands[1] = (k+1)<operandList_3level.length ? operandList_3level[k+1] :null;
                        countOperation = checkAndCalculateFunc(operands, countOperation); //если под скобкой аргумент функции - вычислем функцию для аргумента
                        countOperation = calculateExpression(operands, operationArr_3level[k], countOperation);
                        operandList_3level[k] = operands[0];
                        if ((k+1)<operandList_3level.length) {
                            operandList_3level[k + 1] = operands[1]; //результат во второй операнд - создаем цепочку вычислений
                        }
                        k++;
                    } while (k < operandList_3level.length-1);
                    //... последовательно выполняем действия


            localExpresion_3level = operandList_3level[operandList_3level.length - 1]; //выражение в скобке заменяем результатом
            localExpresion_3level = localExpresion_3level.replace("-", "@");
            //... последовательно выполняем действия
            //заменяем пару скобок на результат...
            System.out.printf("%s $$$ %s **", initialLocalExpression, localExpresion_3level);
            System.out.println();
            str = str.replace(initialLocalExpression, localExpresion_3level);
            //...заменяем пару скобок на результат
        } while (matcher.find());
        System.out.println(str);
        System.out.println("==========================");
        //... прошли самый глубокий уровень скобок
        pattern = Pattern.compile("[\\+\\-\\*/\\^\\(]"); //проверяем, есть ли необходимость еще одного прохода. Критерий необходимости: наличие знаком действий (минус как знак числа замаскирован @) или наличие скобки
        matcher = pattern.matcher(str);
        if (matcher.find()) { //если нужен еще проход
            recursion(str, countOperation);
        } else {
            operands[0] = str;
            operands[1] = null;
            countOperation = checkAndCalculateFunc(operands, countOperation);
            str = operands[0];
            str = String.format(Locale.ENGLISH, "%.2f", Double.valueOf(str));
            str = str.replaceAll("0+$", "");
            str = str.replaceAll("\\.+$", "");
            System.out.printf("%s %s", str, countOperation);
        }
        //implement
    }

    private static int checkAndCalculateFunc(String[] operands, Integer countOperation) {
        for (int i = 0; i < 2; i++) {
            if (operands[i]!=null) {
                operands[i] = operands[i].replace("@", "-");
                String func = operands[i].replaceAll("[0-9]+[E|e]", "");
                func = func.replaceAll("[0-9-.]*$", "");
                if ("sin".equals(func)) {
                    operands[i] = String.valueOf(Math.sin(Math.toRadians(Double.valueOf(operands[i].substring(func.length())))));
                    countOperation++;
                } else if ("cos".equals(func)) {
                    operands[i] = String.valueOf(Math.cos(Math.toRadians(Double.valueOf(operands[i].substring(func.length())))));
                    countOperation++;
                } else if ("tan".equals(func)) {
                    operands[i] = String.valueOf(Math.tan(Math.toRadians(Double.valueOf(operands[i].substring(func.length())))));
                    countOperation++;
                }
            }
        }
        return countOperation;
    }
    private static int calculateExpression(String[] operands, String operation, Integer countOperation) {
        switch (operation) {
            case "^": {
                operands[1] = String.valueOf(Math.pow(Double.valueOf(operands[0]), Double.valueOf(operands[1]))); //результат - во второй операнд
                countOperation++;
                break;
            }
            case "*": {
                operands[1] = String.valueOf(Double.valueOf(operands[0]) * Double.valueOf(operands[1]));
                countOperation++;
                break;
            }
            case "/": {
                operands[1] = String.valueOf(Double.valueOf(operands[0]) / Double.valueOf(operands[1]));
                countOperation++;
                break;
            }
            case "+": {
                operands[1] = String.valueOf(Double.valueOf(operands[0]) + Double.valueOf(operands[1]));
                countOperation++;
                break;
            }
            case "-": {
                operands[1] = String.valueOf(Double.valueOf(operands[0]) - Double.valueOf(operands[1]));
                countOperation++;
                break;
            }
        }
        return countOperation;
    }

    public Solution() {
        //don't delete
    }
}
