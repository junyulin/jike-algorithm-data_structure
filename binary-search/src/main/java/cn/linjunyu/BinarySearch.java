package cn.linjunyu;

/**
 *  二分查找的几种模板
 *
 * @author LinJn
 * @since 2021/11/4 9:25
 */
public class BinarySearch {

    /**
     * 升序且不重复的数组，找一个目标值 target 的索引
     */
    public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 升序且不重复的数组，找第一个大于等于目标值 target 的索引。target 一定存在在数组中（求后继）
     */
    public int binarySearch21(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    /**
     * 升序且不重复的数组，找第一个大于等于目标值 target 的索引。target 不一定存在在数组中（求后继）
     */
    public int binarySearch22(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    /**
     * 升序且不重复的数组，找第一个小于等于目标值 target 的索引。target 一定存在在数组中（求前驱）
     */
    public int binarySearch31(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    /**
     * 升序且不重复的数组，找第一个小于等于目标值 target 的索引。target 不一定存在在数组中（求前驱）
     */
    public int binarySearch32(int[] nums, int target) {
        int left = -1;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        int[] nums = {3, 5, 7, 8, 10};
        int target = 2;
        /*final BinarySearch binarySearch = new BinarySearch();
        final int j = binarySearch.binarySearch21(nums, target);
        System.out.println(j);
        final int k = binarySearch.binarySearch22(nums, target);
        System.out.println(k);*/
        final BinarySearch binarySearch = new BinarySearch();
        final int j = binarySearch.binarySearch31(nums, target);
        System.out.println(j);
        final int k = binarySearch.binarySearch32(nums, target);
        System.out.println(k);
    }
}
