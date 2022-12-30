package com.cczu.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the first non-repeating character in it and return its index. If it doesn't exist, return -1.
 *
 * Examples:
 *
 * s = "leetcode"
 * return 0.
 *
 * s = "loveleetcode"
 * return 2.
 *
 *
 * Note: You may assume the string contains only lowercase English letters.
 * @author jianzhen.yin
 * @date 2020/9/28
 */
public class Solution_387 {
    public int firstUniqChar1(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char tmp = s.charAt(i);
            if (map.containsKey(tmp)) {
                map.put(tmp, null);
            } else {
                map.put(tmp, i);
            }
        }
        int res = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() != null) {
                res = Math.min(entry.getValue(), res);
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
        public int firstUniqChar (String s){
            for (char c : s.toCharArray()) {
                int index = s.indexOf(c);
                int lastIndex = s.lastIndexOf(c);
                if (index == lastIndex) {
                    return index;
                }
            }
            return -1;
        }

}