package cn.linjunyu.sort;

/**
 * 计算排序。适用于正整数，且数字不大的排序
 *
 * 思想：利用数组的有序性来排序
 *
 * @author LinJn
 * @since 2021/11/13 0:12
 */
public class CountingSort {

    public void sort(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int[] arr = new int[max + 1];
        for (int num : nums) {
            arr[num] ++;
        }
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i]; j++) {
                nums[index ++] = i;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {9, 7, 0, 3, 6, 6, 8, 5, 5,};
        final CountingSort countingSort = new CountingSort();
        countingSort.sort(nums);
        System.out.println();
    }

}

