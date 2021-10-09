package cn.linjunyu.stack.algorithm;

import java.util.Stack;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 * 1、左括号必须用相同类型的右括号闭合。
 * 2、左括号必须以正确的顺序闭合。
 *
 * 提示：一个序列，最近相关性，要求匹配，优先想到使用 栈
 *
 * @author LinJn
 * @since 2021/10/4 14:40
 */
public class SolutionN20 {

    public boolean isValid(String s) {
        if (s.length() % 2 > 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            if ('(' == c || '{' == c || '[' == c) {
                stack.push(c);
                continue;
            }
            if (stack.isEmpty()) {
                return false;
            }
            final Character peek = stack.peek();
            if ('(' ==  peek && ')' == c) {
                stack.pop();
            } else if ('{' == peek && '}' == c) {
                stack.pop();
            } else if ('[' == peek && ']' == c) {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        final SolutionN20 solutionN20 = new SolutionN20();
        final boolean valid = solutionN20.isValid("]");
        System.out.println(valid);
    }
}
