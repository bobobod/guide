package com.cczu.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. Subsets
 * Medium
 * <p>
 * 9712
 * <p>
 * 154
 * <p>
 * Add to List
 * <p>
 * Share
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * <p>
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * Example 2:
 * <p>
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 * @author yjz
 * @date 2022/4/21
 */
public class Solution_78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        // 回溯遍历计算 分别获取 长度为 0 1 2 3 的结果
        for(int i = 0;i <= nums.length; i++){
            backTrack(res,new ArrayList(),nums,0,i);
        }
        return res;
    }

    public void backTrack(List<List<Integer>> res,List<Integer> tmpList,int[] nums,int startIndex,int size){
        if(tmpList.size() == size){
            res.add(new ArrayList(tmpList));
        }else{
            for(int i = startIndex;i < nums.length; i++){
                tmpList.add(nums[i]);
                backTrack(res,tmpList,nums,i + 1,size);
                tmpList.remove(tmpList.size() - 1);
            }
        }
    }
    
}
