package com.github.ryan.jianzhi.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className AlgoDesignCategory
 * @date October 16,2018
 */
public class AlgoDesignCategory {

    /**
     * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
     * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
     * 输入描述:
     * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
     *
     * 递归回溯法
     */
    private boolean[] used;
    private ArrayList<String> res = new ArrayList<>();

    public ArrayList<String> Permutation(String str) {
        res.clear();
        if (str == null || "".equals(str)) return res;

        used = new boolean[str.length()];
        char[] strArr = str.toCharArray();
        genePermetation(strArr, 0, new StringBuilder());

        Set<String> set = new HashSet<>(res);
        ArrayList<String> ret = new ArrayList<>(set);
        Collections.sort(ret);
        return ret;
    }

    // 语义：s保存了一个有index元素的排列
    // 向这个排列的末尾增加第index+1个元素，获得一个有index+1个元素的排列
    private void genePermetation(char[] strArr, int index, StringBuilder s) {
        if (index == strArr.length) {
            res.add(new String(s.toString()));
            return;
        }

        for (int i = 0; i < strArr.length; i++) {
            if (!used[i]) {
                s.append(strArr[i]);
                int len = s.toString().length();
                used[i] = true;
                genePermetation(strArr, index + 1, s);
                // 回溯后恢复状态
                s.deleteCharAt(len - 1);
                used[i] = false;
            }
        }
        return;
    }
}
