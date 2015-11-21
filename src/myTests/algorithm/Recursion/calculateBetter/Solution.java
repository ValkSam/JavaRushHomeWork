package myTests.algorithm.Recursion.calculateBetter;
//сначала раставляем скобки, потом идем по структуре скобок, в каждой скобке расчитываем
// выражение (независимо от того сколько операций в этом выражении - главно, что после расстановки скобок все эти операции одноранговые)

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
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recursion("(-2^(-1-(5-6-(1-2))))^2-(-(-(-6)))*sin(-(-(-sin(-sin(-8-9)))))-(2-(2-(2-4)))+2-2^2+((-cos(2-5)^3))*cos(10^10)*sin(cos(((-2+5)/10^10)^3))*5+1*2*4+tan(((sin(cos(((-1)))))))/4  /((1+2^3+4)+(1+1^2^(1-1*2)+4)*(1+2*3*(1+5*6)+4)+(1+2*3*(-1+5*6)^4)+((3^2)^2)*((-2^2)^2)+(( 2^2 )+2)*(( 2^2 )+2)) + 3^2*(-cos(-sin(-4+5)+tan((3)+sin(cos(sin(-(2+6)*3*cos(5))))))) + (2*(1+2^3)+4*2) - 3+(2) + 2*(1+3*4)  + ((3*4+1)/(3))*7*((1/5)+3*4)*( 1 + (2+(1+3)+(4)) * 5*(2+4) )^2+( 1 + (2+(1+3)+(4)) * 5*(2+4) )*2", 0); //expected output 33529146.1495601833672508
    }

    public void recursion(final String expression, int countOperation) {
        String str = expression.replaceAll(" ", "").toLowerCase();
        str = formatExpression(2, formatExpression(1, str, 0), 0);
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

    private static String formatExpression(int level, String expression, int i) {
        int bracketBalans = 0;
        int rightPos = -1;
        int firstValidSymbPos = -1;
        boolean validSymbDetected = false;
        String validSymb = null;
        String splitter = null;
        if (level == 1) {
            validSymb = "[\\^]";
            splitter = "[\\+\\-\\*/]";
        }
        if (level == 2) {
            validSymb = "[\\*/]";
            splitter = "[\\+\\-]";
        }

        while (i < expression.length()) {
            String cs = expression.substring(i, i + 1);
            if (cs.matches(validSymb)&&(!validSymbDetected)) {
                validSymbDetected = true;
            }
            if (cs.matches(validSymb)&&(firstValidSymbPos==-1)) {
                firstValidSymbPos = i;
            }

            if (validSymbDetected) {
                if ("(".equals(cs)) bracketBalans++;
                if (")".equals(cs)) bracketBalans--;

                if (bracketBalans > 0) {
                    if (cs.matches(validSymb)) {
                        rightPos = getBoundOperand(expression, firstValidSymbPos-1, splitter, true);
                        i-= 2; //т.к. правую скобку будем добавлять впереди, то на 1 шаг назад компенсируем излишнее увеличение индекса при добавлении скобок
                        //и еще на 1 шаг назад для того, чтобы остаться на том же символе после +1 шага на итерации - это надо, чтобы переустановились firstValidSymbPos и validSymbDetected.
                    }
                } else if (bracketBalans < 0) {
                    rightPos = i - 1;
                } else { //==0)
                    if (cs.matches(splitter)) {
                        rightPos = i - 1;
                    }
                }
                if (i == (expression.length() - 1)) rightPos = expression.length() - 1;
            }

            if (rightPos != -1) {
                expression = expression.substring(0, rightPos+1) + ")" + expression.substring(rightPos+1);
                int leftPos = getBoundOperand(expression, firstValidSymbPos-1, splitter, false);
                expression = expression.substring(0, leftPos) + "(" + expression.substring(leftPos);
                i+=2; //компенсируем добавление двух скобок
                //
                //return formatExpression(level, expression, i+1); //или обнулить переменные и продолжить цикл без рекурсии
                //или обнулить переменные и продолжить цикл без рекурсии
                bracketBalans = 0;
                rightPos = -1;
                firstValidSymbPos = -1;
                validSymbDetected = false;
            }
            i++;
        }
        return expression;
    }

    private static int getBoundOperand(String expression, int i, String splitter, boolean straight){
        int bracketBalans = 0;
        int pos = -1;
        while(true){
            String cs = expression.substring(i, i+1);
            if ("(".equals(cs)) bracketBalans++;
            if (")".equals(cs)) bracketBalans--;

            if ((bracketBalans<0)&&(straight)){
                pos = i-1;
            } else if ((bracketBalans>0)&&(!straight)){
                pos = i+1;
            } else if (bracketBalans==0) {
                if (cs.matches(splitter)){
                    pos = i + (straight ? -1 : 1);
                }
            }

            if ((straight)&&(i==(expression.length()-1))) pos = expression.length()-1;
            if ((!straight)&&(i==0)) pos = 0;

            if (pos != -1) break;
            i+= (straight ? 1 : -1);
        }
        return pos;
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
