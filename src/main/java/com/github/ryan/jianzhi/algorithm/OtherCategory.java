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

    /**
     * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
     * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
     */
    public boolean isNumeric(char[] str) {

        // 分类讨论：小数点，E/e,+/-1 和整数
        // 小数点只能出现一次，且不能在 E/e 之后
        // E/e 只能出现一次，且后面一定有数字
        // +/- 号可以出现在开头，且后面有数字；或者前面跟着E/e

        if (str == null) return false;

        boolean hasE = false, hasSign = false, hasPoint = false;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '.') {
                if (hasPoint || hasE || (str.length - 1 == i)) return false;
                hasPoint = true;
            } else if (str[i] == 'E' || str[i] == 'e') {
                if (hasE || (str.length - 1 == i)) return false;
                hasE = true;
            }  else if (str[i] == '+' || str[i] == '-') {
                // 第二次出现
                if (hasSign && str[i - 1] != 'e' && str[i - 1] != 'E') return false;
                // 第一次出现,出现在开头
                if (!hasSign && i == 0 && (str.length == 1)) return false;
                // 第一次出现且出现在e/E之后
                if (!hasSign && i > 0 && str[i - 1] != 'e' && str[i - 1] != 'E') return false;
                hasSign = true;
            } else if (!Character.isDigit(str[i])){
                return false;
            }
        }
        return true;
    }

    /**
     * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
     * 每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     */
    // 二维数组查找，可以利用二分查找，先找到第0行中小于target的最大列或者找到了target直接返回
    // 从第0列到上面找到的列值，每一列按行进行二分查找
    public boolean Find(int target, int [][] array) {

        if (array == null) return false;
        int rows = array.length;
        if (rows <= 0) return false;
        int cols = array[0].length;
        if (cols <= 0) return false;

        // 找到第一行中是否存在target，有则返回true，否则返回小于target的最大值(mid)
        // array[0][l...r] 中查找target
        int l = 0, r = cols - 1;
        int mid = 0;
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if (array[0][mid] == target) {
                return true;
            } else if (array[0][mid] > target) {
                r = mid - 1;
            } else {
                // array[0][mid] < target
                if (mid + 1 > r || array[0][mid + 1] > target) {
                    break;
                } else {
                    l = mid + 1;
                }
            }
        }

        if (mid == 0 && array[0][0] > target) return false;

        for (int i = 0; i <= mid; i ++) {
            // 在i列进行二分查找
            int up = 0, down = rows - 1;
            while (up <= down) {
                // middle 区分 mid, up,down区分l,r
                int middle = up + ((down - up) >> 2);
                if (array[middle][i] == target){
                    return true;
                } else if (array[middle][i] > target) {
                    down = middle - 1;
                } else {
                    // array[mid][i] < target
                    up = middle + 1;
                }
            }
        }
        return false;
    }

    public boolean Find2(int target, int [][] array) {
        if (array == null) return false;
        int rows = array.length;
        if (rows <= 0) return false;
        int cols = array[0].length;

        // 从左下角开始，当target比左下角大时，往右走，比左下角小时，往上走
        int i = rows - 1;
        int j = 0;
        // array[i][j]为左下角的值
        while (i >= 0 && j <= cols - 1) {
            if (array[i][j] == target) {
                return true;
            } else if (array[i][j] > target) {
                i--; // 往上走，递减
            } else {
                // array[i][j] < target
                j++; // 往右走，递增
            }
        }
        return false;

    }
}
