package cn.linjunyu.homeword.week1;

/**
 * 作业1：
 * leecode 66. 加一
 *
 * @author LinJn
 * @since 2021/10/4 9:13
 */
public class Homeword1 {

    /**
     * 第一版本：依次遍历数组，然后加1，需要处理加1之后超过10的情况
     */
    public int[] plusOne(int[] digits) {
        int num = 1;
        for (int i = digits.length - 1; i >= 0; i --) {
            int val = digits[i];
            int j = val + num;
            if (j == 10) {
                digits[i] = 0;
            } else {
                digits[i] = j;
                num = 0;
            }
        }
        // 判断数组的第一位是否超过10，如果是需要处理
        if (num == 1) {
            int[] retArr = new int[digits.length + 1];
            retArr[0] = 1;
            for (int i = 0; i < digits.length; i ++) {
                retArr[i + 1] = digits[i];
            }
            digits = retArr;
        }
        return digits;
    }

    /**
     * 上面版本的优化。当最高位加1后超过10，后面的低位数只有一种情况，那就是全部是 0
     */
    public int[] plusOne2(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i] = digits[i] + 1;
                return digits;
            }
            digits[i] = 0;
        }
        int[] arry = new int[digits.length + 1];
        arry[0] = 1;
        return arry;
    }

    public static void main(String[] args) {
        final Homeword1 homeword1 = new Homeword1();
        int[] arr = {9,8,7,6,5,4,3,2,1,0};
        final int[] ints = homeword1.plusOne(arr);
        System.out.println();
    }

}
