package cn.linjunyu.sort;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 插入排序
 *
 * 实现：从未排序的数组中取第一个数，插入到已排好序的数组中。从后往前插，就可以不断比较交换
 *
 * @author LinJn
 * @since 2021/11/10 22:14
 */
public class InsertionSort {

    public void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int index = i;
            while (index > 0 && nums[index] < nums[index - 1]) {
                int temp = nums[index];
                nums[index] = nums[index - 1];
                nums[index - 1] = temp;
                index = index - 1;
            }
        }
    }

    public int[] sortArray(int[] nums) {
        Queue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.offer(num);
        }
        int i = 0;
        while (!queue.isEmpty()) {
            nums[i++] = queue.remove();
        }
        return nums;
    }

    public static void main(String[] args) {
        final InsertionSort selectionSort = new InsertionSort();
        int[] nums = {5,3,9,2,4,4};
        selectionSort.sort(nums);
        System.out.println();
    }

}
