package com.cczu.algorithm.sword2offer;

import java.util.*;

/**
 * 剑指 Offer II 033. 变位词组
 * 给定一个字符串数组 strs ，将 变位词 组合在一起。 可以按任意顺序返回结果列表。
 * <p>
 * 注意：若两个字符串中每个字符出现的次数都相同，则称它们互为变位词。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 示例 2:
 * <p>
 * 输入: strs = [""]
 * 输出: [[""]]
 * 示例 3:
 * <p>
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 */
public class Solution_33 {
    //    public List<List<String>> groupAnagrams(String[] strs) {
//        List<List<String>> result = new ArrayList<>();
//        boolean[] used = new boolean[strs.length];
//        for (int i = 0; i < strs.length; i++) {
//            if (used[i]) {
//                // 已经被使用了
//                continue;
//            }
//            List<String> tmp = new ArrayList<>();
//            tmp.add(strs[i]);
//            for (int j = i + 1; j < strs.length; j++) {
//                if (check(strs[i], strs[j])) {
//                    used[j] = true;
//                    tmp.add(strs[j]);
//                }
//            }
//            result.add(tmp);
//        }
//        return result;
//    }
//
//    private boolean check(String s, String t) {
//        if (s.length() != t.length()) {
//            return false;
//        }
//        int[] arr = new int[26];
//        for (int i = 0; i < s.length(); i++) {
//            arr[s.charAt(i) - 'a']++;
//        }
//        for (int i = 0; i < t.length(); i++) {
//            int index = t.charAt(i) - 'a';
//            arr[index]--;
//            if (arr[index] < 0) {
//                return false;
//            }
//        }
//        return true;
//    }
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> tmp = map.getOrDefault(key, new ArrayList<>());
            tmp.add(str);
            map.put(key, tmp);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        Solution_33 solution_33 = new Solution_33();
        String[] strs = {"", ""};
        System.out.println(solution_33.groupAnagrams(strs));
    }

}
