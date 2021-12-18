package cn.linjunyu.sort;

/**
 * 计数排序。完全版，可以对负数和正数进行排序
 *
 * @author LinJn
 * @since 2021/11/24 16:30
 */
public class CountingSort2 {

    public void sort(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        // 求最大值和最小值
        for (int num : nums) {
            min = Math.min(num, min);
            max = Math.max(num, max);
        }
        int[] temp = new int[max - min + 1];
        for (int num : nums) {
            temp[num - min] = temp[num - min] + 1;
        }
        int index = 0;
        for (int i = 0; i < temp.length; i++) {
            while (temp[i] > 0) {
                nums[index] = i + min;
                temp[i] = temp[i] - 1;
                index = index + 1;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {102, 101, 105, 103, 102, 104,-5};
        final CountingSort2 countingSort2 = new CountingSort2();
        countingSort2.sort(arr);
        System.out.println();
    }

}
