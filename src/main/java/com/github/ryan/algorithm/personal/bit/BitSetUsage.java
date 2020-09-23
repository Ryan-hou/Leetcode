package com.github.ryan.algorithm.personal.bit;

import lombok.extern.slf4j.Slf4j;

import java.util.BitSet;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * bit－map的基本思想：
 * 32位机器上，对于一个整型数，比如int a=1 在内存中占32bit位，这是为了方便计算机的运算。
 * 但是对于某些应用场景而言，这属于一种巨大的浪费，因为我们可以用对应的32bit位对应存储十进制的0-31个数，而这就是Bit-map的基本思想。
 * Bit-map算法利用这种思想处理大量整型数据的排序、查询以及去重。
 * bit-map 不支持非运算
 * 参考文章：
 * http://mp.weixin.qq.com/s/M_SQHzJFUvkVE9LCwHb2mA
 * http://mp.weixin.qq.com/s/96Iv8ZKAm6ozob4YCnFWqA
 * @className: BitSetUsage
 * @date January 24,2018
 */
@Slf4j
public class BitSetUsage {


    /**
     * 使用bit-map来排序：
     * 假设数据均为非负整数，且不重复，使用bit数组的下标值表示数据值，bit数组元素是否true表示数据是否存在
     * （数组是天然的map结构，数组下标作为key，常用于空间换时间）
     * 时间复杂度：O(n) n为nums数组的数据规模
     * 空间复杂度：O(n) n为数组中数据的规模
     * @param nums
     * @param range：数据的范围，用来确定bit的个数
     */
    public static void sort(int[] nums, int range) {
        BitSet bitSet = new BitSet(range);

        for (int num : nums) {
            bitSet.set(num);
        }

        for (int i = 0; i < range; i++) {
            if (bitSet.get(i)) {
                System.out.print(i + ", ");
            }
        }
    }

    /**
     * 在大量数据中去除重复的数字
     * 比如，2.5亿个整数中找出不重复的整数的个数，内存空间不足以容纳这2.5亿个整数。
     * 根据“内存空间不足以容纳这2.5亿个整数”我们可以快速的联想到Bit-map。下边关键的问题就是怎么设计我们的Bit-map来表示这2.5亿个数字的状态了。
     * 其实这个问题很简单，一个数字的状态只有三种，分别为不存在，只有一个，有重复。
     * 因此，我们只需要2bits就可以对一个数字的状态进行存储了，假设我们设定一个数字不存在为00，存在一次01，存在两次及其以上为11。
     * 那我们大概需要存储空间几十兆左右。
     * @param nums
     * @param range
     */
    public static void remDuplicate(int[] nums, int range) {

        BitSet bitSet = new BitSet(range);

        for (int num : nums) {
            boolean firstPos = bitSet.get(num * 2);
            boolean secondPos = bitSet.get(num * 2 + 1);

            if (!firstPos && !secondPos) {
                // 00
                bitSet.set(num * 2 + 1);
            } else if (!firstPos && secondPos) {
                // 01
                bitSet.set(num * 2);
            }
        }

        for (int i = 0; i < range; i ++) {
            boolean firstPos = bitSet.get(i * 2);
            boolean secondPos = bitSet.get(i * 2 + 1);
            if (!firstPos && secondPos) {
                // 01
                System.out.print(i + ", ");
            }
        }

    }

    private static void testSort() {
        //假设我们要对0-7内的5个元素(4,7,2,5,3)排序
        int[] nums = {4, 7, 2, 5, 3};
        int range = 8;
        sort(nums, range);
    }

    private static void testRemDuplicate() {
        int[] nums = {4, 6, 6, 6, 7, 7, 8, 9, 9};
        int range = 10;
        remDuplicate(nums, range);
    }

    public static void main(String[] args) {

        // testSort();
        testRemDuplicate();


//        long cur = System.currentTimeMillis();
//        for (int i = 0; i < 1000000000;i++) {
//
//        }
//        log.info("iterate 1billion nums consume: {}ms", System.currentTimeMillis() - cur); //6ms
    }

}
