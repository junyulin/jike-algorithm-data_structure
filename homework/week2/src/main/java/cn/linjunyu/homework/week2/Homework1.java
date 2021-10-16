package cn.linjunyu.homework.week2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 811. 子域名访问计数
 *
 * @author LinJn
 * @since 2021/10/17 4:35
 */
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
