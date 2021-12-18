package cn.linjunyu.sort;

/**
 * 归并排序
 *
 * @author LinJn
 * @since 2021/11/19 17:00
 */
public class MergeSort {

    public void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    private void sort(int[] nums, int left, int right) {
        if (right <= left) {
            return;
        }
        int mid = (left + right) >> 1;
        sort(nums, left, mid);
        sort(nums, mid + 1, right);
        merge(nums, left, right, mid);
    }

    /**
     * 合并操作，其实就是合并两个有序数组
     */
    private void merge(int[] nums, int left, int right, int mid) {
        int[] temp = new int[right - left + 1];
        int iLeft = left;
        int iRight = mid + 1;
        int index = 0;
        while (iLeft <= mid && iRight <= right) {
            if (nums[iLeft] < nums[iRight]) {
                temp[index] = nums[iLeft];
                iLeft = iLeft + 1;
            } else {
                temp[index] = nums[iRight];
                iRight = iRight + 1;
            }
            index = index + 1;
        }
        while (iLeft <= mid) {
            temp[index] = nums[iLeft];
            iLeft = iLeft + 1;
            index = index + 1;
        }
        while (iRight <= right) {
            temp[index] = nums[iRight];
            iRight = iRight + 1;
            index = index + 1;
        }
        for (int i = 0; i < temp.length; i++) {
            nums[left] = temp[i];
            left = left + 1;
        }
    }

    public static void main(String[] args) {
        int[] nums = {9, 7, 0, 3, 6, 6, 8, 5, 5};
        final MergeSort mergeSort = new MergeSort();
        mergeSort.sort(nums);
        System.out.println();
    }

}
