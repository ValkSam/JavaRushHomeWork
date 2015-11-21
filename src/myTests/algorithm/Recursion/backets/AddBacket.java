package myTests.algorithm.Recursion.backets;

/**
 * Created by Valk on 16.06.15.
 */
public class AddBacket {
    public static void main(String args[]) {
        //String str = "cos(2+5)^3*sin(cos(2+5)^3)*5+1*2*4/4 + 3^4*(cos(sin(4+5)+tan(3))) + (2*(1+2^3)+4*2) - 3+(2) + 2*(1+3*4)  + ((3*4+1)/(3))*7*((1/5)+3*4)*( 1 + (2+(1+3)+(4)) * 5*(2+4) )^2+( 1 + (2+(1+3)+(4)) * 5*(2+4) )*2";
        //String str = "cos(2+5)^3*sin(cos(-2+5)^3)*5+1*2*4/4  /((1+2^3+4)+(1+2^3^(1+5*6)+4)*(1+2*3*(1+5*6)+4)+(1+2*3*(-1+5*6)^4)+((4^2)^2)*((-2^2)^2)+(( 2^2 )+2)*(( 2^2 )+2)) + 3^2*(-cos(-sin(-4+5)+tan(3))) + (2*(1+2^3)+4*2) - 3+(2) + 2*(1+3*4)  + ((3*4+1)/(3))*7*((1/5)+3*4)*( 1 + (2+(1+3)+(4)) * 5*(2+4) )^2+( 1 + (2+(1+3)+(4)) * 5*(2+4) )*2";
        //String str = "2*(1+3*4)";
        String str = "2*(3*4+1)";

        //String str = "((1+2^3+4)+sin(1+2/3/(1+5/6)+4)/(1+2*3/(1+5*6)+4)+(1+2*3*cos(1+5*6)*4)+((4^7)^2)+((4*7)*2)+(( 5*7 )+2)*(( 5*7 )+2))*sin(tan(cos(2*3)*sin(5+4)*tan(-3)))";
        //String str = "(1+7*((5*7)+2))*3"; //((1+(7*(((5*7))+2)))*3)
        //String str = "sin(tan(cos(2*3)*sin(5+4)*tan(-3)))";
        //String str = "((1+2^3+4)+sin(1+2/3/(1+5/6)+4)/(1+2*3/(1+5*6)+4)+(1+2*3*cos(1+5*6)*4)+((4^7)^2)+((4*7)*2)+(( 5*7 )+2)*(( 5*7 )+2))";

        //String  str = "sin(cos(5)^3)"; //неверно работает: надо sin((cos(5)^3)) имеем (sin(cos(5)^3)).  Этому мешает if (bracketCount==0) afterBracketCount = 0;
        //String str = "1*2*4/4 + (3^4)*5";
        //String str = "(1)*(2)*3"; // if (bracketCount==0) afterBracketCount = 0; - обеспечивает правильность работы

        // ((1+(2^3)+4)+sin(1+2/3/(1+(5/6))+4)/(1+(2*3/(1+(5*6))+4))+(1+(2*3*cos(1+(5*6))*4))+((((4^7))^2))+((((4*7))*2))+((((5*7))+2)*(((5*7))+2)))
        // sin(tan((cos((2*3))*sin(5+4)*tan(-3))))
        // ((1+(2^3)+4)+sin(1+2/3/(1+(5/6))+4)/(1+(2*3/(1+(5*6))+4))+(1+(2*3*cos(1+(5*6))*4))+((((4^7))^2))+((((4*7))*2))+((((5*7))+2)*(((5*7))+2))*sin(tan((cos((2*3))*sin(5+4)*tan(-3)))))


        //String str = "3^4*5";
        //String str = "(3^4*5)";
        //String str = "(1+3^4*5)";
        //String str = "(  (2+(1+3)+(4)) ^ 5)";
        //String str = "(  (2+(1+3)+(4)) ^ 5)+2";
        //String str = "( 1 + (2+(1+3)+(4)) ^ 5)^2";
        //String str = "( 1 + (2+(1+3)+(4)) ^ 5^(2+4) )^2";
        //String str = "1+2^3";
        //String str = "(2+1)*5*(2+1)";
        //String str = "3^4*5*(2+1)";

        //String str = "(1+(2+(1+3)+(4))^5^(4+2))";
        //String str = "(1+2^3)+4*2";
        //String str = "3^4*(cos(sin(4+5)+tan(3)))";


        //String str = "1 + 2*3*4/5 - 6";
        //String str = "2*3*4/5";



        //String str = "1+2^3+4"; //1+(2^3)+4        /((1+2^3+4)+(1+2^3^(1+5^6)+4)*(1+2^3^(1+5^6)+4)+(1+2^3^(1+5^6)^4)+((4^7)^2)*((4^7)^2)+(( 5^7 )+2)*(( 5^7 )+2))
        //String str = "1+2^3^(1+5^6)+4"; //1+(2^3^(1+(5^6)))+4
        //String str = "1+2^3^(1+5^6)^4"; //1+(2^3^(1+(5^6))^4)
        //String str = "(4^7)^2"; //(((4*7))*2)
        //String str = "( 5^7 )+2"; //((5*7))+2
        //String str = "(1+7*((5*7)+2))*3";
        //String str = "1+2*(3*4+5*6*(7*8+9))+10*11";  //1+(2*((3*4)+(5*6*((7*8)+9))))+(10*11)

        str = str.replaceAll(" ", "");


        System.out.println("result: "+formatExpression(2, formatExpression(1,str,0), 0));
        System.out.println("result:");
        //System.out.println("result: "+formatExpression(1,str,0,0,-1));
    }

    public static String formatExpression(int level, String expression, int i) {
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
                } else { //(bracketBalans==0)
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

    public static int getBoundOperand(String expression, int i, String splitter, boolean straight){
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
}
