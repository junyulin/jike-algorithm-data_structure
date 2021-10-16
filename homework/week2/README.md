# 极客时间第一周作业

极客时间第二周作业



## leetcode 811.子域名访问

hash 的简单运用。需要注意 `.` 分隔符需要转义。

```java
public class Homework1 {

    /**
     * map 的简单运用，注意分隔符需要转义
     */
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        for (String cpdomain : cpdomains) {
            final String[] split = cpdomain.split(" ");
            final Integer count = Integer.valueOf(split[0]);
            // 注意转义
            final String[] split1 = split[1].split("\\.");
            map.put(split[1], map.getOrDefault(split[1], 0) + count);
            StringBuilder s = new StringBuilder();
            for (int i = split1.length - 1; i > 0; i--) {
                s.insert(0, split1[i]);
                String k = s.toString();
                map.put(k, map.getOrDefault(k, 0) + count);
                s.insert(0, ".");
            }
        }
        List<String> retList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            final String key = entry.getKey();
            final Integer value = entry.getValue();
            retList.add(value + " " + key);
        }
        return retList;
    }

    public static void main(String[] args) {
        final Homework1 homework = new Homework1();
        String[] str = {"9001 discuss.leetcode.com"};
        final List<String> list = homework.subdomainVisits(str);
    }
}
```



## leetcode 697. 数组的度

使用了两种解法：

- 第一种解法。把简单的问题复杂化了。看到连续子数组就想要使用前缀和的方式来解决，解决方式和 [leetcode .1248. 统计「优美子数组」](https://leetcode-cn.com/problems/count-number-of-nice-subarrays/) 类似，把数组内频数最高的元素置为 1，其他置为0，然后求连续子数组的和为最高频数。
- 第二种解法。实际上只要求最高频次的值的最前和最后索引即可。 使用 `count` 记录最高频次，用一个 `map` 记录每一个值出现的频次和最前最后索引的位置。

```java
public class Homework2 {

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
        final Homework2 homework = new Homework2();
        final int shortestSubArray = homework.findShortestSubArray3(nums);
        System.out.println(shortestSubArray);
    }
```



## leetcode 560. 和为 K 的子数组

前缀和 + 两数之和（差）的运用。

```java
public class Homework4 {

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
```

