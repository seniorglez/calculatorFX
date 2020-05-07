package hellofx.calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorMachine {

    public static double calculate(final String str) {
        return new ExpressionParser(normalizeMathExpression(str)).parse();
    }
    protected static String normalizeMathExpression(String s){
        Map p = new HashMap<String,String>();
        p.put("[·]","*");
        p.put("[÷]","/");
        return expressionNormalizer(s,p);
    }
    protected static String expressionNormalizer(String expression, Map <String,String> p){
        AtomicReference<String> result = new AtomicReference<>();
        result.set(expression);
        p.forEach((R,S)->result.set(normalizeChar(R,S,result.get())));
        return  result.get();
    }

    protected  static String normalizeChar(String regex, String subst, String expression){
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(expression);
        return matcher.replaceAll(subst);
    }
}
