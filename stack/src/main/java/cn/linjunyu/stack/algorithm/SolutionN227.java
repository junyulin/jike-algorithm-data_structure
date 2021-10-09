package cn.linjunyu.stack.algorithm;

import java.util.Stack;

/**
 * leetcode 227
 *
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 *
 * 整数除法仅保留整数部分。
 *
 * @author LinJn
 * @since 2021/10/4 16:57
 */
public class SolutionN227 {

    public int calculate(String s) {
        // 多加个空格，保证数据能够被全部读到
        s = s + " ";
        Stack<String> suffixExpression = new Stack<>();
        Stack<Character> operationCodes = new Stack<>();
        StringBuilder nums = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            // 这一段代码是为了读取超过 10 的数字
            final char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                nums.append(c);
                continue;
            } else {
                final String s1 = nums.toString();
                if (!s1.isEmpty()) {
                    suffixExpression.push(nums.toString());
                    nums = new StringBuilder();
                }
            }
            //
            if (c == ' ') {
                continue;
            }
            // 判断符号的优先级，如果栈顶符号的优先级高，那么需要计算，如果当前的优先级比较高，那么存到栈顶中去，这个时候后面的数字还不知道，先存到栈顶中去
            int currRank = this.getRank(c);
            while (!operationCodes.isEmpty() && this.getRank(operationCodes.peek()) >= currRank) {
                suffixExpression.push(String.valueOf(operationCodes.pop()));
            }
            // 入栈
            operationCodes.push(c);
        }
        while (!operationCodes.isEmpty()) {
            suffixExpression.push(String.valueOf(operationCodes.pop()));
        }
        final String[] strings = suffixExpression.toArray(new String[]{});
        return evalRPN(strings);
    }

    private int getRank(Character operationCode) {
        if (operationCode == '+' || operationCode == '-') {
            return 1;
        } else if (operationCode == '*' || operationCode == '/') {
            return 2;
        }
        return -1;
    }

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

    public static void main(String[] args) {
        final SolutionN227 solutionN227 = new SolutionN227();
        final int calculate = solutionN227.calculate("1+2*5/3+6/4*2");
        System.out.println(calculate);
    }
}
