package cn.linjunyu.everyday.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 *
 * @author LinJn
 * @since 2021/10/11 16:44
 */
public class Day8 {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            final int key = target - nums[i];
            if (map.containsKey(key)) {
                return new int[]{map.get(key), i};
            }
            map.put(nums[i], key);
        }
        return new int[2];
    }

}
