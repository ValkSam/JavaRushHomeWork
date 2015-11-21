package myTests.algorithm.Recursion.calculateBest;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Valk on 20.06.15.
 */
public class Solution {
    public static void main(String args[]) {
        //String str = "cos(2+5)^3*sin(cos(2+5)^3)*5+1*2*4/4 + 3^4*(cos(sin(4+5)+tan(3))) + (2*(1+2^3)+4*2) - 3+(2) + 2*(1+3*4)  + ((3*4+1)/(3))*7*((1/5)+3*4)*( 1 + (2+(1+3)+(4)) * 5*(2+4) )^2+( 1 + (2+(1+3)+(4)) * 5*(2+4) )*2";
        String str = "(-2^(-1-(5-6-(1-2))))^2-(-(-(-6)))*sin(-(-(-sin(-sin(-8-9)))))-(2-(2-(2-4)))+2-2^2+((-cos(2-5)^3))*cos(10^10)*sin(cos(((-2+5)/10^10)^3))*5+1*2*4+tan(((sin(cos(((-1)))))))/4  /((1+2^3+4)+(1+1^2^(1-1*2)+4)*(1+2*3*(1+5*6)+4)+(1+2*3*(-1+5*6)^4)+((3^2)^2)*((-2^2)^2)+(( 2^2 )+2)*(( 2^2 )+2)) + 3^2*(-cos(-sin(-4+5)+tan((3)+sin(cos(sin(-(2+6)*3*cos(5))))))) + (2*(1+2^3)+4*2) - 3+(2) + 2*(1+3*4)  + ((3*4+1)/(3))*7*((1/5)+3*4)*( 1 + (2+(1+3)+(4)) * 5*(2+4) )^2+( 1 + (2+(1+3)+(4)) * 5*(2+4) )*2";

        //String str = "((-cos(2-5)^3))*cos(10^10)*sin(cos(((-2+5)/10^10)^3))*5+1*2*4+tan(((sin(cos(((-1)))))))/4  /((1+2^3+4)+(1+2^3^(1+5*6)+4)*(1+2*3*(1+5*6)+4)+(1+2*3*(-1+5*6)^4)+((4^2)^2)*((-2^2)^2)+(( 2^2 )+2)*(( 2^2 )+2)) + 3^2*(-cos(-sin(-4+5)+tan((3)+sin(cos(sin(-(2+6)*3*cos(5))))))) + (2*(1+2^3)+4*2) - 3+(2) + 2*(1+3*4)  + ((3*4+1)/(3))*7*((1/5)+3*4)*( 1 + (2+(1+3)+(4)) * 5*(2+4) )^2+( 1 + (2+(1+3)+(4)) * 5*(2+4) )*2";
        //String str = "2-(1+5)";
        //String str = "-2-(1+5)";
        //String str = "-sin(45)";
        //String str = "1*(-tan(-sin(45)))/(1*(-tan(-sin(80))))";
        //String str = "(1/tan(sin(cos(45))))";
        //String str = "1/tan(sin(cos(45)))";
        //String str = "1*(-tan(sin(45)))";
        //String str = "@sin(45)";
        //String str = "@(8.0)*3";


        //String str = "1+2^3+4"; //1+(2^3)+4        /((1+2^3+4)+(1+2^3^(1+5^6)+4)*(1+2^3^(1+5^6)+4)+(1+2^3^(1+5^6)^4)+((4^7)^2)*((4^7)^2)+(( 5^7 )+2)*(( 5^7 )+2))
        //String str = "1+2^3^(1+5^6)+4"; //1+(2^3^(1+(5^6)))+4
        //String str = "1+2^3^(1+5^6)^4"; //1+(2^3^(1+(5^6))^4)
        //String str = "(4^7)^2"; //(((4*7))*2)
        //String str = "( 5^7 )+2"; //((5*7))+2
        //String str = "(1+7*((5*7)+2))*3";
        //String str = "1+2*(3*4+5*6*(7*8+9))+10*11";  //1+(2*((3*4)+(5*6*((7*8)+9))))+(10*11)
        //String str = "tan(((sin(cos(((-1)))))))";

        //String str = "1+4/2/2+2^2+2*2-2^(2-1+1)";
        //String str = "2-(2-(2-(2-4)))";
        //String str = "(-2^(-1-(5-6-(1-2))))";
        //String str = "(1+3^2*(-cos(-sin(-4+5)+tan((3)+sin(cos(sin(-(2+6)*3*cos(5))))))) + (2*(1+2^3)+4*2) - 3+(2) + 2*(1+3*4)  + ((3*4+1)/(3))*7*((1/5)+3*4)*( 1 + (2+(1+3)+(4)) * 5*(2+4) )^2+( 1 + (2+(1+3)+(4)) * 5*(2+4) )*2";
        //String str = "-sin(-4)";

        str = str.replaceAll(" ", "");
        (new Solution()).recursion(str, 0);
        int k = (str.length()-str.replaceAll("\\p{Alpha}","").length())/3;
        k+= str.length()-str.replaceAll("[\\+\\-\\*/%\\^#]","").length();
        System.out.println();
        System.out.println(k);
        //System.out.println("result: "+formatExpression(1,str,0,0,-1));
    }

    public void recursion(final String expression, int countOperation) {
        String[] operands = new String[2];

        Map<String, String> rightOperandSplitters = new HashMap<String, String>() {{
            put("+", "[\\+\\-]");
            put("-", "[\\+\\-]");
            put("*", "[\\+\\-\\*/%]");
            put("/", "[\\+\\-\\*/%]");
            put("%", "[\\+\\-\\*/%]");
            put("^", "[\\+\\-\\*/%\\^#]");
            put("#", "[\\+\\-\\*/%\\^#]");
        }};

        String allSplitters = "[\\+\\-\\*/%\\^#]";
        String complexOperand = ".*[\\+\\-\\*/%\\^#]+.*";
        System.out.println();
        System.out.println(expression);

        int i = 0;
        while (i < expression.length()) {
            String cs = expression.substring(i, i + 1);
            if (cs.matches(allSplitters)) {
                //System.out.println("cs "+ cs);
                int leftPos = getBoundOperand(expression, i - 1, allSplitters, false);
                //System.out.println("leftPos "+leftPos);
                int rightPos = getBoundOperand(expression, i + 1, rightOperandSplitters.get(cs), true);
                //System.out.println("rightPos "+rightPos);
                if (leftPos < 0) {
                    operands[0] = "";
                    leftPos++;
                } else {
                    operands[0] = expression.substring(leftPos, i);
                }
                if (operands[0].isEmpty()) operands[0] = "0";
                operands[1] = expression.substring(i + 1, rightPos + 1);
                System.out.println(operands[0] + cs + operands[1]);
                if (operands[1].matches(complexOperand)) {
                    //правый операнд содержит знаки действий (составной)
                    if ("^".equals(cs)) cs = "#";
                    if ("/".equals(cs)) cs = "%";
                    if ("-".equals(cs)) {
                        //operands[1] = "@" + "("+operands[1]+")";
                        operands[1] = "@" + "("+operands[1]+")";
                        cs = "+";
                    }
                    String newExprPart = operands[1] + cs + operands[0]; //меняем местами операнды
                    String str = expression.substring(0, leftPos) + newExprPart + expression.substring(rightPos + 1);
                    //System.out.println("   new Expr "+str);
                    recursion(str, countOperation);
                    return;

                } else {
                    int operCount = checkAndCalculateFunc(operands);
                    String calculatedOperation = calculateExpression(operands, cs).replace("-", "@");
                    String str = expression.substring(0, leftPos) + calculatedOperation + expression.substring(rightPos + 1);
                    recursion(str, countOperation + 1 + operCount);
                    return;
                }
            }
            i++;
        }
        String str = expression.replace("@", "-");
        operands[0] = str;
        operands[1] = null;
        countOperation += checkAndCalculateFunc(operands);
        str = operands[0];
        str = String.format(Locale.ENGLISH, "%.2f", Double.valueOf(str));
        str = str.replaceAll("0+$", "");
        str = str.replaceAll("\\.+$", "");
        System.out.printf("%s %s", str, countOperation);
    }

    private static int getBoundOperand(String expression, int i, String splitter, boolean straight) {
        int bracketBalans = 0;
        int pos = -1;
        if (i < 0) return pos;
        while (true) {
            String cs = expression.substring(i, i + 1);
            if ("(".equals(cs)) bracketBalans++;
            if (")".equals(cs)) bracketBalans--;
            if ((bracketBalans < 0) && (straight)) {
                pos = i - 1;
            } else if ((bracketBalans > 0) && (!straight)) {
                pos = i + 1;
            } else if (bracketBalans == 0) {
                if (cs.matches(splitter)) {
                    pos = i + (straight ? -1 : 1);
                }
            }
            if (pos == -1) {
                if ((straight) && (i == (expression.length() - 1))) pos = expression.length() - 1;
                if ((!straight) && (i == 0)) pos = 0;
            }
            if (pos != -1) break;
            i += (straight ? 1 : -1);
        }
        return pos;
    }

    private static int checkAndCalculateFunc(String[] operands) {
        int countOperation = 0;
        for (int i = 0; i < 2; i++) {
            if (operands[i] != null) {
                String operand = operands[i];
                operand = operand.replace("@", "-").replaceAll("\\)", "").replaceAll("^\\(","");
                while (operand != (operand=operand.replaceAll("(\\(\\()+", "\\("))){};
                String[] funcChain = operand.split("\\(");
                for (int j = funcChain.length-1; j>= 1; j--) {
                    String func = funcChain[j - 1];
                    if (func.isEmpty()) break;
                    String arg = funcChain[j];
                    String sign = "";
                    if (func.startsWith("-")) {
                        sign = "-";
                        func = func.substring(1);
                    }
                    System.out.println("func " + func);
                    if ("sin".equals(func)) {
                        funcChain[j-1] = (sign + String.valueOf(Math.sin(Math.toRadians(Double.valueOf(arg))))).replace("--", "");
                        operands[i] = funcChain[j-1];
                        countOperation++;
                    } else if ("cos".equals(func.trim())) {
                        funcChain[j-1] = (sign + String.valueOf(Math.cos(Math.toRadians(Double.valueOf(arg))))).replace("--", "");
                        operands[i] = funcChain[j-1];
                        countOperation++;
                    } else if ("tan".equals(func.trim())) {
                        funcChain[j-1] = (sign + String.valueOf(Math.tan(Math.toRadians(Double.valueOf(arg))))).replace("--", "");
                        operands[i] = funcChain[j-1];
                        countOperation++;
                    } else {
                        funcChain[j-1] = (sign+func+arg).replace("--", "");
                        operands[i] = funcChain[j-1];
                    }
                }
                operands[i] = operands[i].replace("@", "-").replaceAll("\\(", "").replaceAll("\\)", "");
            }
        }
        return countOperation;
    }

    private static String calculateExpression(String[] operands, String operation) {
        String operand0 = operands[0].replaceAll("\\(", "").replaceAll("\\)", "").replace("--", "");
        String operand1 = operands[1].replaceAll("\\(", "").replaceAll("\\)", "").replace("--", "");
        switch (operation) {
            case "^":
                return String.valueOf(Math.pow(Double.valueOf(operand0), Double.valueOf(operand1)));
            case "#":
                return String.valueOf(Math.pow(Double.valueOf(operand1), Double.valueOf(operand0)));
            case "*":
                return String.valueOf(Double.valueOf(operand0) * Double.valueOf(operand1));
            case "/":
                return String.valueOf(Double.valueOf(operand0) / Double.valueOf(operand1));
            case "%":
                return String.valueOf(Double.valueOf(operand1) / Double.valueOf(operand0));
            case "+":
                return String.valueOf(Double.valueOf(operand0) + Double.valueOf(operand1));
            case "-": {
                if (operand0.isEmpty()) operand0 = "0";
                return String.valueOf(Double.valueOf(operand0) - Double.valueOf(operand1));
            }
        }
        return null;
    }
}
