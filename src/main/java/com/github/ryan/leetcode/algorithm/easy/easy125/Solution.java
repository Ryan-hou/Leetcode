package com.github.ryan.leetcode.algorithm.easy.easy125;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 12,2018
 */
@Slf4j
public class Solution {

    /**
     * 去除字符串中的非字母和数字字符，字母统一为小写，然后反转字符串，通过equals判断字符串内容
     * @param s
     * @return
     */
    public static boolean isPalindromeOne(String s) {
        String parsedParam = s.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
        String reverseStr = new StringBuilder(parsedParam).reverse().toString();
        if (parsedParam.equals(reverseStr)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 字符串转为字符数组，使用头尾对撞指针进行判断
     * 时间复杂度O(n)
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        char[] toValidate = s.toCharArray();

        int l = 0, r = toValidate.length - 1; // [l...r]为待操作的数据
        while (l < r) {
            if (isIllegalChar(toValidate[l])) {
                l++;
                continue;
            }
            if (isIllegalChar(toValidate[r])) {
                r--;
                continue;
            }

            if (compareWithNoCase(toValidate[l], toValidate[r])) {
                l++;
                r--;
            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean compareWithNoCase(char a, char b) {
        return String.valueOf(a).equalsIgnoreCase(String.valueOf(b));
    }

    private static boolean isIllegalChar(char c) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(String.valueOf(c));
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
//        String parsedParam = "728**91_-@@$djdjf".replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
//        log.info("result = {}", parsedParam);
//        log.info("result = {}", isIllegalChar('8'));
        String str = "A man, a plan, a canal: Panama";
        log.info("result = {}", isPalindrome(str));


    }

}
