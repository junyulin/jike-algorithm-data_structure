package cn.linjunyu.stack.algorithm;

import java.util.Stack;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * @author LinJn
 * @since 2021/10/4 22:13
 */
public class SolutionN84 {

    static class Bar {
        int width;
        int height;
    }

    public int largestRectangleArea(int[] heights) {
        int area = 0;
        int[] newHeight = new int[heights.length + 1];
        System.arraycopy(heights, 0, newHeight, 0, heights.length);
        newHeight[heights.length] = 0;
        Stack<Bar> stack = new Stack<>();
        for (int height : newHeight) {
            int width = 0;
            while (!stack.isEmpty() && stack.peek().height > height) {
                final Bar pop = stack.pop();
                width = width + pop.width;
                area = Math.max(area, pop.height * width);
            }
            Bar bar = new Bar();
            bar.width = 1 + width;
            bar.height = height;
            stack.push(bar);
        }
        return area;
    }

    /**
     * 朴素解法：o(n^2) 的时间复杂度。leetcode 超出时间限制
     */
    public int largestRectangleArea2(int[] heights) {
        int area = 0;
        for (int i = 0; i < heights.length; i ++) {
            int num = 0;
            for (int j = i; j >= 0; j --) {
                if (heights[i] <= heights[j]) {
                    num = num + 1;
                } else {
                    break;
                }
            }
            for (int j = i + 1; j < heights.length; j ++) {
                if (heights[i] <= heights[j]) {
                    num = num + 1;
                } else {
                    break;
                }
            }
            int val = heights[i] * num;
            if (val > area) {
                area = val;
            }
        }
        return area;
    }

    public static void main(String[] args) {
        final SolutionN84 solutionN84 = new SolutionN84();
        int[] arr = {2,1,5,6,2,3};
        final int i = solutionN84.largestRectangleArea(arr);
        System.out.println(i);
    }
}
