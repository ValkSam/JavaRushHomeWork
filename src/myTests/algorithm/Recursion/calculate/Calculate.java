package myTests.algorithm.Recursion.calculate;
//проходим по изначальной структуре скобок (не модифицируя эту структуру). Для каждой скобки расчитываем выражение.
// При этом операции в выражениях могут быть разноранговые

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Рекурсия для мат.выражения
На вход подается строка - математическое выражение.
Выражение включает целые и дробные числа, скобки (), пробелы, знак отрицания -, возведение в степень ^, sin(x), cos(x), tan(x)
Для sin(x), cos(x), tan(x) выражение внутри скобок считать градусами, например, cos(3 + 19*3)=0.5
Степень задается так: a^(1+3) и так a^4, что эквивалентно a*a*a*a.
С помощью рекурсии вычислить выражение и количество математических операций. Вывести через пробел результат в консоль.
Результат выводить с точностью до двух знаков, для 0.33333 вывести 0.33, использовать стандартный принцип округления.
Не создавайте статические переменные и поля класса.
Пример, состоящий из операций sin * - + * +:
sin(2*(-5+1.5*4)+28)
Результат:
0.5 6
*/
public class Calculate {
    public static void main(String[] args) {
        Calculate calculate = new Calculate();
        calculate.recursion("(-2^(-1-(5-6-(1-2))))^2-(-(-(-6)))*sin(-(-(-sin(-sin(-8-9)))))-(2-(2-(2-4)))+2-2^2+((-cos(2-5)^3))*cos(10^10)*sin(cos(((-2+5)/10^10)^3))*5+1*2*4+tan(((sin(cos(((-1)))))))/4  /((1+2^3+4)+(1+1^2^(1-1*2)+4)*(1+2*3*(1+5*6)+4)+(1+2*3*(-1+5*6)^4)+((3^2)^2)*((-2^2)^2)+(( 2^2 )+2)*(( 2^2 )+2)) + 3^2*(-cos(-sin(-4+5)+tan((3)+sin(cos(sin(-(2+6)*3*cos(5))))))) + (2*(1+2^3)+4*2) - 3+(2) + 2*(1+3*4)  + ((3*4+1)/(3))*7*((1/5)+3*4)*( 1 + (2+(1+3)+(4)) * 5*(2+4) )^2+( 1 + (2+(1+3)+(4)) * 5*(2+4) )*2", 0); //expected output 33529146.1495601833672508
    }

    public void recursion(final String expression, int countOperation) {
        String str = expression.replaceAll(" ", "").toLowerCase();
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
            String[] operandList_3level = localExpresion_3level.split("[+-]"); //разбиваем на группы, разделенные действиями +/-
            if (operandList_3level[0].isEmpty())
                operandList_3level[0] = "0"; //если впереди пустой символ (пустая строка или впереди знак "-") - заменить его на 0 (в массиве всегда будет хотя бы одна запись, даже если строка пустая)
            String tmpStr = localExpresion_3level.replaceAll("[\\p{ASCII}&&[^+-]]", ""); //оставляем только знаки действий
            String[] operationArr_3level = tmpStr.split(""); //помещаем знаки действий в массив
            //пытаемся каждый операнд разбить на более мелкие ...
            for (int i = 0; i < operandList_3level.length; i++) {
                String localExpresion_2level = operandList_3level[i];
                String[] operandList_2level = localExpresion_2level.split("[*/]");
                tmpStr = localExpresion_2level.replaceAll("[\\p{ASCII}&&[^*/]]", "");
                String[] operationArr_2level = tmpStr.split("");
                //пытаемся каждый операнд разбить на более мелкие ...
                for (int j = 0; j < operandList_2level.length; j++) {
                    String localExpresion_1level = operandList_2level[j];
                    String[] operandList_1level = localExpresion_1level.split("[\\^]");
                    tmpStr = localExpresion_1level.replaceAll("[\\p{ASCII}&&[^\\^]]", "");
                    String[] operationArr_1level = tmpStr.split("");
                    //последовательно выполняем действия ... (возведения в степень)
                    int k = 0;
                    do { //цикл фор до предпоследнего элемента не годится, т.к. надо иметь возможность обработать одиночный операнд на предмет расчета функции
                        operands[0] = operandList_1level[k];
                        operands[1] = (k+1)<operandList_1level.length ? operandList_1level[k+1] :null;
                        countOperation = checkAndCalculateFunc(operands, countOperation); //если под скобкой аргумент функции - вычислем функцию для аргумента
                        countOperation = calculateExpression(operands, operationArr_1level[k], countOperation);
                        operandList_1level[k] = operands[0];
                        if ((k+1)<operandList_1level.length) {
                            operandList_1level[k + 1] = operands[1]; //результат во второй операнд - создаем цепочку вычислений
                        }
                        k++;
                    } while (k < operandList_1level.length-1);
                    //... последовательно выполняем действия
                    operandList_2level[j] = operandList_1level[operandList_1level.length - 1]; //разбитый операнд заменяем результатом (находится в последнем операнде)
                    operandList_2level[j] = operandList_2level[j].replace("-", "@"); //маскируем знак отрицательного числа
                }
                //...пытаемся каждый операнд разбить на более мелкие
                //последовательно выполняем действия ... (умножение/деление)
                for (int j = 0; j < operandList_2level.length - 1; j++) {
                    operands[0] = operandList_2level[j];
                    operands[1] = operandList_2level[j+1];
                    countOperation = checkAndCalculateFunc(operands, countOperation); //если под скобкой аргумент функции - вычислем функцию для аргумента
                    countOperation = calculateExpression(operands, operationArr_2level[j], countOperation);
                    operandList_2level[j+1] = operands[1]; //результат во второй операнд - создаем цепочку вычислений
                }
                //... последовательно выполняем действия
                operandList_3level[i] = operandList_2level[operandList_2level.length - 1]; //разбитый операнд заменяем результатом
                operandList_3level[i] = operandList_3level[i].replace("-", "@");
            }
            //...пытаемся каждый операнд разбить на более мелкие
            //последовательно выполняем действия ... (сложение/вычитание)
            for (int i = 0; i < operandList_3level.length - 1; i++) {
                operands[0] = operandList_3level[i];
                operands[1] = operandList_3level[i+1];
                countOperation = checkAndCalculateFunc(operands, countOperation); //если под скобкой аргумент функции - вычислем функцию для аргумента
                countOperation = calculateExpression(operands, operationArr_3level[i], countOperation);
                operandList_3level[i+1] = operands[1]; //результат во второй операнд - создаем цепочку вычислений
            }
            localExpresion_3level = operandList_3level[operandList_3level.length - 1]; //выражение в скобке заменяем результатом
            localExpresion_3level = localExpresion_3level.replace("-", "@");
            //... последовательно выполняем действия
            //заменяем пару скобок на результат...
            str = str.replace(initialLocalExpression, localExpresion_3level);
            //...заменяем пару скобок на результат
        } while (matcher.find());
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

    public Calculate() {
        //don't delete
    }
}
