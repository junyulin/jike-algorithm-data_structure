package cn.linjunyu.stack.algorithm;

import java.util.Stack;

/**
 * 根据 逆波兰表示法，求表达式的值。
 *
 * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *
 * 提示：后面的操作数和前面的值相关，非常符合前缀和，也就是可以使用栈来实现
 *
 * @author LinJn
 * @since 2021/10/4 16:28
 */
public class SolutionN150 {


    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if ("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token)) {
                final Integer y = stack.pop();
                final Integer x = stack.pop();
                final int calc = this.calc(x, y, token);
                stack.push(calc);
            } else {
                stack.push(Integer.valueOf(token));
            }
        }
        return stack.peek();
    }

    private int calc(Integer x, Integer y, String operation) {
        int num;
        switch (operation) {
            case "+" :
                num = x + y;
                break;
            case "-" :
                num = x - y;
                break;
            case "*":
                num = x * y;
                break;
            case "/":
                num = x / y;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return num;
    }

}
