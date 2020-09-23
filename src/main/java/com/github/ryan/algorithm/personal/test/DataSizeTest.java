package com.github.ryan.algorithm.personal.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: DataSizeTest
 * @date January 31,2018
 */

/**
 * 时间复杂度O(n): n为问题规模，在业界中我们取O(f(n))的最低上界，
 * 一般采用平均时间复杂度，不使用特殊用例下存在的最坏或者最好时间复杂度
 *
 * 问题规模 (可以通过测试不同的数据规模，来验证自己的算法时间复杂度)：
 * 要想在1s内解决问题：
 *
 * O(n^2) 可以处理大约 10^4 级别的数据 (10^3)
 * O(nlogn) 可以处理大约 10^7 级别的数据 (10^6)
 * O(n) 可以处理大约 10^8 级别的数据 (10^7)
 * 安全起见，可以再降低一个数量级，来根据算法的数据规模确定可以使用的时间复杂度为什么级别
 *
 * logaN = logab(常数)*logbN (log的底数可以忽略，O(logn)是非常理想的时间复杂度，比线性级别O(n)优秀的多)
 *
 * 空间复杂度：
 * 额外开辟一个新的数组容量，则空间复杂度为 O(n)
 * 递归调用是有空间代价的，取决于递归深度
 */

@Slf4j
public class DataSizeTest {

    public static void main(String[] args) {

        for (int x = 1; x <= 9; x++) {

            int n = (int) Math.pow(10, x);

            long startTime = System.currentTimeMillis();
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum = sum + 1;
            }
            long endTime = System.currentTimeMillis();

            System.out.println("10^" + x + ": " + (endTime - startTime) + "ms");
        }
    }
}
