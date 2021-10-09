package cn.linjunyu.stack.algorithm;

import java.util.Stack;

/**
 *
 *
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop()—— 删除栈顶的元素。
 * top()—— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *
 * 提示：使用一个辅助栈，来存放每次比较的最小值。前缀最小值。
 *
 * @author LinJn
 * @since 2021/10/4 15:38
 */
public class SolutionN155 {

    private Stack<Integer> stack;

    private Stack<Integer> min;

    public SolutionN155() {
        this.stack = new Stack<>();
        this.min = new Stack<>();
    }

    public void push(int val) {
        if (min.isEmpty()) {
            min.push(val);
        } else {
            min.push(Math.min(val, min.peek()));
        }
        stack.push(val);
    }

    public void pop() {
        stack.pop();
        min.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min.peek();
    }

}
