package com.cczu.algorithm.leetcode;

/**
 * Given two binary strings a and b, return their sum as a binary string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 * <p>
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= a.length, b.length <= 104
 * a and b consist only of '0' or '1' characters.
 * Each string does not contain leading zeros except for the zero itself.
 *
 * @author yjz
 * @date 2022/4/4
 */
public class Solution_67 {
    public static void main(String[] args) {
        Solution_67 solution_67 = new Solution_67();
        System.out.println(solution_67.addBinary("1010", "1011"));
    }

//    public String addBinary(String a, String b) {
//        if (a.length() == 0) {
//            return b;
//        }
//        if (b.length() == 0) {
//            return a;
//        }
//        int lenA = a.length() - 1;
//        int lenB = b.length() - 1;
//        String str = "";
//        int pre = 0;
//        int tmp = Math.abs(lenA - lenB);
//        int tmpVal = Math.max(lenA, lenB);
//        for (int i = tmpVal; i >= 0; i--) {
//            int valueA = 0;
//            int valueB = 0;
//            boolean flag = (i - tmp) >= 0;
//            if (lenA > lenB) {
//                valueB = a.charAt(i) - '0';
//                if (flag) {
//                    valueA = b.charAt(i - tmp) - '0';
//                }
//            } else {
//                valueA = b.charAt(i) - '0';
//                if (flag) {
//                    valueB = a.charAt(i - tmp) - '0';
//                }
//            }
//            if ((valueA + valueB + pre)  > 1) {
//                int r = (valueA + valueB + pre) % 2;
//                str = String.valueOf(r).concat(str);
//                pre = 1;
//            } else {
//                str = String.valueOf(valueA + valueB + pre).concat(str);
//                pre = 0;
//            }
//        }
//
//        if (pre == 1) {
//            return "1".concat(str);
//        }
//        return str;
//    }

    public String addBinary(String a, String b) {
        StringBuilder str = new StringBuilder();
        int lenA = a.length() - 1, lenB = b.length() - 1, carry = 0;
        while (lenA >= 0 || lenB >= 0) {
            int sum = carry;
            if (lenA >= 0) {
                sum += a.charAt(lenA--) - '0';
            }
            if (lenB >= 0) {
                sum += b.charAt(lenB--) - '0';
            }
            str.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) {
            str.append(carry);
        }
        return str.reverse().toString();
    }
}
