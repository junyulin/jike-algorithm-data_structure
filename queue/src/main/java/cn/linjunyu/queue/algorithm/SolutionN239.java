package cn.linjunyu.queue.algorithm;

/**
 * leetcode 239. 滑动窗口最大值
 *
 * @author LinJn
 * @since 2021/10/5 14:50
 */
public class SolutionN239 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        return null;
    }

    /**
     * 暴力破解。o(nums.length * k) 时间复杂度
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length == 0) {
            return nums;
        }
        int size = 1;
        int[] arr = new int[nums.length - k + 1];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
        }
        arr[0] = max;
        for (int i = k; i < nums.length; i++) {
            max = Integer.MIN_VALUE;
            for (int j = i; j > i - k; j --) {
               if (nums[j] > max) {
                   max = nums[j];
               }
            }
            arr[size] = max;
            size = size + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,-1,-3,5,3,6,7};
        final SolutionN239 solutionN239 = new SolutionN239();
        final int[] ints = solutionN239.maxSlidingWindow(arr, 3);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

}
