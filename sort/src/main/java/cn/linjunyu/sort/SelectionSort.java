package cn.linjunyu.sort;

/**
 * 选择排序
 *
 * 实现：找最小值
 *
 * @author LinJn
 * @since 2021/11/10 21:55
 */
public class SelectionSort {

    public void sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j ++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = temp;
        }
    }

    public static void main(String[] args) {
        final SelectionSort selectionSort = new SelectionSort();
        int[] nums = {5,3,9,2,4,4};
        selectionSort.sort(nums);
        System.out.println();
    }
}
