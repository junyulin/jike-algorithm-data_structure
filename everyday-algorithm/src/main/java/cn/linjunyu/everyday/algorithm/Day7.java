package cn.linjunyu.everyday.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 155. 最小栈
 *
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *
 *
 * @author LinJn
 * @since 2021/10/10 20:14
 */
public class Day7 {

    private Deque<Integer> deque;

    private Deque<Integer> minDeque;

    public Day7() {
        this.deque = new ArrayDeque<>();
        this.minDeque = new ArrayDeque<>();
    }

    public void push(int val) {
        if (deque.isEmpty()) {
            minDeque.push(val);
        } else {
            int min = minDeque.peek();
            if (val < min) {
                min = val;
            }
            minDeque.push(min);
        }
        deque.push(val);
    }

    public void pop() {
        this.deque.pop();
        this.minDeque.pop();
    }

    public int top() {
        return this.deque.peek();
    }

    public int getMin() {
        return this.minDeque.peek();
    }
}
