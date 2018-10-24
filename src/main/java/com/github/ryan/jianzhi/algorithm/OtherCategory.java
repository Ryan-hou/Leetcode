package com.github.ryan.jianzhi.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className OtherCategory
 * @date October 17,2018
 */
public class OtherCategory {

    /**
     * LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,
     * 2个小王(一副牌原本是54张^_^)...他随机从中抽出了5张牌,想测测自己的手气,
     * 看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！
     * “红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高兴了,
     * 他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。
     * 上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),
     * “So Lucky!”。LL决定去买体育彩票啦。 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何，
     * 如果牌能组成顺子就输出true，否则就输出false。为了方便起见,你可以认为大小王是0。
     *
     * 分类讨论大小王存在的各种情况，从0张到最多的4张
     */
    public boolean isContinuous(int [] numbers) {
        // assert numbers != null && numbers.length == 5;
        if (numbers == null || numbers.length != 5) return false;

        int kingNum = 0;
        Set<Integer> noKingSet = new HashSet<>();
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 0) {
                kingNum++;
            } else {
                noKingSet.add(numbers[i]);
            }

        }

        // if (kingNum == 4) return true;
        // 除了大小王，还有重复的牌面
        if (noKingSet.size() != numbers.length - kingNum)  return false;

        List<Integer> noKingList = new ArrayList<>(noKingSet);
        Collections.sort(noKingList);
        int gap = noKingList.get(noKingList.size() - 1) - noKingList.get(0);
        /**
         if (kingNum == 0 && gap == 4) {
         return true;
         }
         if (kingNum == 1 && (gap == 3 || gap == 4)) {
         return true;
         }
         if (kingNum == 2 && (gap == 2 || gap == 3 || gap == 4)) {
         return true;
         }
         if (kingNum == 3 && (gap == 1 || gap == 2 || gap == 3 || gap ==4)) {
         return true;
         }
         */
        if (gap <= 4 && gap >= (4 - kingNum)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 字符串转为数字
     *
     * 将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)，
     * 要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0。
     * 输入描述:
     * 输入一个字符串,包括数字字母符号,可以为空
     * 输出描述:
     * 如果是合法的数值表达则返回该数字，否则返回0
     * 示例1
     * 输入
     * +2147483647
     * 1a33
     * 输出
     * 复制
     * 2147483647
     * 0
     */
    public int StrToInt(String str) {
        // 借鉴 Integer.parseInt 的思路，统一转为负数处理溢出情况

        if (str == null) return 0;

        int res = 0;
        boolean negative = false;
        int i = 0, len = str.length();
        int limit = -Integer.MAX_VALUE;
        int digit;

        if (len <= 0) return 0;
        char firstChar = str.charAt(0);
        if (firstChar < '0') {
            // Possible leading '+' or '-'
            if (firstChar == '-') {
                negative = true;
                limit = Integer.MIN_VALUE;
            } else if (firstChar != '+') {
                return 0;
            }

            if (len == 1) return 0;
            i++;
        }

        while (i < len) {
            // Accumulating negatively avoids surprises near MAX_VALUE
            digit = str.charAt(i++) - '0';
            if (digit < 0 || digit > 9) {
                return 0;
            }

            res *= 10;
            if (res < limit + digit) {
                // 溢出
                return 0;
            }
            res -= digit;
        }
        return negative ? res : -res;
    }
}
