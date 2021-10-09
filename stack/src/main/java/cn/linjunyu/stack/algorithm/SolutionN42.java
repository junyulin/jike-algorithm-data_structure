package cn.linjunyu.stack.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 * @author LinJn
 * @since 2021/10/5 11:32
 */
public class SolutionN42 {

    static class Bar {
        int width;
        int height;
    }

    public int trap(int[] height) {
        int area = 0;
        Deque<Bar> stack = new ArrayDeque<>();
        for (int h : height) {
            int width = 0;
            while (!stack.isEmpty() && h > stack.peek().height) {
                final Bar pop = stack.pop();
                // 取底下的高度
                final int bottomHeight = pop.height;
                width = width + pop.width;
                // 如果左侧是空，说明雨水是接不住的，不接
                if (stack.isEmpty()) {
                    continue;
                }
                // 计算接住雨水两端的大小，雨水能否接住取决于两端的最短
                int minHeight = Math.min(h, stack.peek().height);
                // 加一下面积
                area = area + (minHeight - bottomHeight) * width;
            }
            Bar bar = new Bar();
            bar.height = h;
            bar.width = width + 1;
            stack.push(bar);
        }
        return area;
    }

    public static void main(String[] args) {
        final SolutionN42 solutionN42 = new SolutionN42();
        int[] arry = {4,2,0,3,2,5};
        final int trap = solutionN42.trap(arry);
        System.out.println(trap);
    }

}
