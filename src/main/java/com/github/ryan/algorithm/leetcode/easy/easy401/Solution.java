package com.github.ryan.algorithm.leetcode.easy.easy401;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    private int[] time = {1, 2, 4, 8, 1, 2, 4, 8, 16, 32};
    private List<String> res = new ArrayList<>();

    public List<String> readBinaryWatch(int num) {
        res.clear();
        read(0, 0, 0, num);
        return res;
    }


    // 在当前 hour 和 minute 下，从 time[start] 开始，读取 num 个数据构造一个合法的时间值
    private void read(int start, int hour, int minute, int num) {
        if (num == 0) {
            StringBuilder b = new StringBuilder();
            b.append(hour).append(":");
            if (minute < 10) {
                b.append(0);
            }
            b.append(minute);
            res.add(b.toString());
            return;
        }

        for (int i = start; i < time.length; i++) {
            int newHour = hour;
            int newMinute = minute;
            if (inHour(i)) {
                newHour += time[i];
            } else {
                newMinute += time[i];
            }

            if (newHour <= 11 && newMinute <= 59) {
                read(i + 1, newHour, newMinute, num - 1);
            }
        }
    }

    private boolean inHour(int idx) {
        return idx < 4;
    }

}
