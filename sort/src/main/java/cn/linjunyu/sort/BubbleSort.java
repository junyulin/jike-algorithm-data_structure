package cn.linjunyu.sort;

/**
 * 冒泡排序
 *
 * 实现：将最大值排到最后面
 *
 * @author LinJn
 * @since 2021/11/10 22:05
 */
public class BubbleSort {

    public void sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length - i; j ++) {
                if (nums[j -1] > nums[j]) {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        final BubbleSort selectionSort = new BubbleSort();
        int[] nums = {5,3,9,2,4,4};
        selectionSort.sort(nums);
        System.out.println();
    }

}
