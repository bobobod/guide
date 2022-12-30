package com.cczu.algorithm.leetcode;

import java.util.Arrays;

/**
 *
 The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

 P   A   H   N
 A P L S I I G
 Y   I   R
 And then read line by line: "PAHNAPLSIIGYIR"

 Write the code that will take a string and make this conversion given a number of rows:

 string convert(string s, int numRows);
 Example 1:

 Input: s = "PAYPALISHIRING", numRows = 3
 Output: "PAHNAPLSIIGYIR"
 Example 2:

 Input: s = "PAYPALISHIRING", numRows = 4
 Output: "PINALSIGYAHRPI"
 Explanation:

 P     I    N
 A   L S  I G
 Y A   H R
 P     I
 * @author jianzhen.yin
 * @date 2020/9/25
 */
public class Solution_6 {
    public static String convert(String s, int numRows) {
        String[] strArr = new String[numRows];
        Arrays.fill(strArr, "");
        // period  (2*numRows-2)
        int period = 2 * numRows - 2;
        for (int i = 0; i < s.length(); i++) {
            // special conditional
            if (numRows == 1) {
                strArr[0] += s.charAt(i);
                continue;
            }
            int mod = i % period;
            if (mod < numRows){
                strArr[mod] += s.charAt(i);
            }else {
                strArr[period - mod] += s.charAt(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            sb.append(strArr[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 1));
    }
}
