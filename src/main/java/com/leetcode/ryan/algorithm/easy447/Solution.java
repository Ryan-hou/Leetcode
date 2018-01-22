package com.leetcode.ryan.algorithm.easy447;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 22,2018
 */
@Slf4j
public class Solution {

    /**
     * 思路：根据题目的限定，数据容量为 500，设计满足数据规模时间复杂度的算法
     * 遍历每一个点，然后计算出剩余点到该点的距离（使用距离的平方，避免开方带来精度损失）
     * 把距离和到该点为此距离的点的个数保存到map中
     * @param points
     * @return
     */
    public static int numberOfBoomerangs(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Integer, Integer> disCountMap = new HashMap<>(points.length * 2);
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    int dis = dis(points[i], points[j]);
//                    if (disCountMap.get(dis) == null) {
//                        disCountMap.put(dis, 1);
//                    } else {
//                        disCountMap.put(dis, disCountMap.get(dis) + 1);
//                    }
                    disCountMap.put(dis, disCountMap.getOrDefault(dis, 0) + 1);
                }
            }

            for (Map.Entry<Integer, Integer> entry : disCountMap.entrySet()) {
//                if (entry.getValue() > 1) {
                    res += entry.getValue() * (entry.getValue() - 1);
//                }
            }
        }
        return res;
    }

    // 数据范围为[-10000, 10000] 最大距离为 2000^2*2,可以用 int表示，不会越界
    private static int dis(int[] pa, int[] pb) {
        return (pa[0] - pb[0]) * (pa[0] - pb[0])
                + (pa[1] - pb[1]) * (pa[1] - pb[1]);
    }

    public static void main(String[] args) {

        int[][] points = {{0, 0}, {1, 0}, {2, 0}};

        log.info("count = {}", numberOfBoomerangs(points));

    }


}
