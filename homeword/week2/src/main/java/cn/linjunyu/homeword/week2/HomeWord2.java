package cn.linjunyu.homeword.week2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 697. 数组的度
 *
 * @author LinJn
 * @since 2021/10/17 4:38
 */
public class HomeWord2 {

    /**
     * 第一个版本：把简单问题复杂化的版本。
     * 1、用一个 map 来记录每个数出现的频数，用一个 count 来记录最大的频数（可能存在多个）。
     * 2、对数组进行转化：最大频数的值设置为 1，其他设置为 0，问题转化为求子数组的和为最大频数，且子数组要为最短（前缀和 + 两数之和）
     */
    public int findShortestSubArray(int[] nums) {
        // 求数组度。count 用来记录最大频数。map 来用记录每个数出现的频数
        int count = 1;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(nums[0], count);
        for (int i = 1; i < nums.length; i ++) {
            int c = map.getOrDefault(nums[i], 0) + 1;
            if (c > count) {
                count = c;
            }
            map.put(nums[i], c);
        }
        int len = nums.length;
        // 遍历 map
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            final Integer n = entry.getKey();
            final Integer v = entry.getValue();
            // 找频数最高的
            if (v == count) {
                // 转化数组：将数组中值为 v 的设置为 1, 其他设置为0 并求前缀和
                int[] arr = new int[nums.length + 1];
                arr[0] = 0;
                for (int i = 1; i < arr.length; i ++) {
                    int val = 1;
                    if (nums[i - 1] != n) {
                        val = 0;
                    }
                    arr[i] = arr[i - 1] + val;
                }
                // 使用两数之和（差）求数组的子数组和为 count
                Map<Integer, Integer> map2 = new HashMap<>();
                for (int i = 0; i < arr.length; i ++) {
                    int key = arr[i] - count;
                    if (map2.containsKey(key)) {
                        int index = map2.get(key);
                        if (i - index < len) {
                            // 最短的子数组优先
                            len = i - index;
                        }
                    }
                    map2.put(arr[i], i);
                }
            }
        }
        return len;
    }

    /**
     * 第二个版本
     * 两个 map。map 用来记录频数，indexMap 用来记录每个数对应的的索引位置。
     * 在 map 中找到最大频数的数，在 indexMpa 中找到对应的索引，最短子数组也就是求最前面的索引和最后面的索引差
     */
    public int findShortestSubArray2(int[] nums) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i ++) {
            int c = map.getOrDefault(nums[i], 0) + 1;
            if (c > count) {
                count = c;
            }
            map.put(nums[i], c);
            final List<Integer> list = indexMap.getOrDefault(nums[i], new ArrayList<>());
            list.add(i);
            indexMap.put(nums[i], list);
        }
        int len = nums.length;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            final Integer k = entry.getKey();
            final Integer v = entry.getValue();
            if (v == count) {
                final List<Integer> list = indexMap.get(k);
                final int l = list.get(list.size() - 1) - list.get(0) + 1;
                if (l < len) {
                    len = l;
                }
            }
        }
        return len;
    }

    /**
     * 第三个版本：第二个版本优化一下
     */
    public int findShortestSubArray3(int[] nums) {
        // 记录最大频数
        int count = 0;
        // map 的 value 为一个数组。索引 0 存储频数，索引 1 存储第一次出现的位置，索引 2 存储最后出现的位置
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i ++) {
            final int num = nums[i];
            if (map.containsKey(num)) {
                final int c = ++ map.get(num)[0];
                if (c > count) {
                    count = c;
                }
                map.get(num)[2] = i;
            } else {
                if (1 > count) {
                    count = 1;
                }
                map.put(num, new int[]{1, i, i});
            }
        }
        int len = nums.length;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            final int[] v = entry.getValue();
            // 找到频数最大的数，然后最后出现的位置减去第一次出现的位置
            if (count == v[0] && v[2] - v[1] + 1 < len) {
                len = v[2] - v[1] + 1;
            }
        }
        return len;
    }

    public static void main(String[] args) {
//         int[] nums = {1, 2, 2, 3, 1};
//        int[] nums = {2,1,1,2,1,3,3,3,1,3,1,3,2};
        int[] nums = {1,3,2,2,3,1};
        final HomeWord2 homeWord = new HomeWord2();
        final int shortestSubArray = homeWord.findShortestSubArray3(nums);
        System.out.println(shortestSubArray);
    }
}
