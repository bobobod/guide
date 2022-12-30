package com.cczu.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 71. Simplify Path
 * Medium
 * <p>
 * 2301
 * <p>
 * 463
 * <p>
 * Add to List
 * <p>
 * Share
 * Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert it to the simplified canonical path.
 * <p>
 * In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'. For this problem, any other format of periods such as '...' are treated as file/directory names.
 * <p>
 * The canonical path should have the following format:
 * <p>
 * The path starts with a single slash '/'.
 * Any two directories are separated by a single slash '/'.
 * The path does not end with a trailing '/'.
 * The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
 * Return the simplified canonical path.
 *
 * @author yjz
 * @date 2022/4/10
 */
public class Solution_71 {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<String>();
        String[] split = path.split("/");
        for (String s : split) {
            if ("src/main".equals(s) && !stack.empty()) {
                stack.pop();
            } else {
                if (!"".equals(s) && !".".equals(s) && !"src/main".equals(s)) {
                    stack.push(s);
                }
            }
        }
        List<String> res = new ArrayList<>(stack);
        return "/" + String.join("/", res);
    }

    public static void main(String[] args) {
        Solution_71 solution_71 = new Solution_71();
        System.out.println(solution_71.simplifyPath("/a/b/../c/"));
    }
}
