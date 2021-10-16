package cn.linjunyu.homeword.week2;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. 和为 K 的子数组
 *
 * @author LinJn
 * @since 2021/10/17 2:10
 */
public class Homeword4 {

    /**
     * 前缀和 + 两数之和（之差）
     */
    public int subarraySum(int[] nums, int k) {
        int[] arr = new int[nums.length + 1];
        arr[0] = 0;
        for (int i = 1; i < arr.length; i ++) {
            arr[i] = arr[i - 1] + nums[i - 1];
        }
        int ret = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i ++ ) {
            int key = arr[i] - k;
            if (map.containsKey(key)) {
                ret = ret + map.get(key);
            }
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        return ret;
    }

}
