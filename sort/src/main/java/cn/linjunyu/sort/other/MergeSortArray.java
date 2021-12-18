package cn.linjunyu.sort.other;

/**
 * 合并两个有序的数组
 *
 * @author LinJn
 * @since 2021/11/19 17:02
 */
public class MergeSortArray {

    public int[] merge(int[] nums1, int[] nums2) {
        int[] ret = new int[nums1.length + nums2.length];
        int i = 0;
        int j = 0;
        int index = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                ret[index] = nums1[i];
                i = i + 1;
            } else {
                ret[index] = nums2[j];
                j = j + 1;
            }
            index = index + 1;
        }
        if (i < nums1.length) {
            for (int i1 = i; i1 < nums1.length; i1++) {
                ret[index] = nums1[i1];
                index = index + 1;
            }
        }
        if (j < nums2.length) {
            for (int j2 = j; j2 < nums2.length; j2++) {
                ret[index] = nums2[j2];
                index = index + 1;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] num1 = new int[]{1, 3, 3, 7};
        int[] num2 = new int[]{1, 1, 2, 4, 6, 9};
        final MergeSortArray mergeSortArray = new MergeSortArray();
        final int[] merge = mergeSortArray.merge(num1, num2);
        System.out.println(merge);
    }

}
