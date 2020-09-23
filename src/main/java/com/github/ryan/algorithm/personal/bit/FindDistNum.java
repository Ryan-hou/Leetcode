package com.github.ryan.algorithm.personal.bit;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: FindDistNum
 * @date January 24,2018
 */
@Slf4j
public class FindDistNum {


    /**
     * 在一组整数中，除了一个单独的数字外，其余数字都两两一组
     * 思路：
     * 使用异或：两个相同的数字异或结果为0
     *
     * 异或的两个特性：
     * 顺序无关（异或的元素可以随意交换顺序，而不会影响结果）
     * 对一个数异或两次等于没有异或（两个相同的数字异或结果为0，任意数字和0异或为其自身）
     * @param nums
     * @return
     */
    public static int findNumFromPair(int[] nums) {
        if (nums == null || nums.length < 3) {
            throw new IllegalArgumentException("Wrong array input");
        }
        int res = 0;
        for (int num : nums) {
           res ^= num;
        }
        return res;
    }


    /**
     * 数组A中，除了某一个数字x之外，其他数字都出现了三次，而x出现了一次。
     * 不允许使用HashMap等数据结构，不允许使用排序
     *
     * 这一类的题目，即使简单的异或不能解决，也可以从二进制位、位操作方面去考虑，总之这样的大方向是不会错的。
     * 如果数组中的元素都是三个三个出现的，那么从二进制表示的角度，每个位上的1加起来，应该可以整除3。如果有一个数x只出现一次，会是什么情况呢？
     *  如果某个特定位上的1加起来，可以被3整除，说明对应x的那位是0，因为如果是1，不可能被3整除
     *  如果某个特定位上的1加起来，不可以被3整除，说明对应x的那位是1
     *
     * @param nums
     * @return
     */
    public static int findNumFromTriple(int[] nums) {

        int res = 0;
        for (int i = 0; i < 32; i++) {
            int bitCount = 0;
            for (int num : nums) {
                bitCount += getBit(num, i);
            }

            if (bitCount % 3 == 1) {
              res += (1 << i);
            }
        }

        return res;
    }


    /**
     * Take and int n as input, and return the kth bit.
     * @param n
     * @param k
     * @return
     */
    private static int getBit(int n, int k) {
        return (n >> k) & 1;
    }

    public static void main(String[] args) {
        int[] nums = {22, 22, 44, 44, 99, 99, 6};
        log.info("result = {}", findNumFromPair(nums));

        int[] triples = {2, 2, 2, 4, 4, 4, 8};
        log.info("result = {}", findNumFromTriple(triples));
    }

}
