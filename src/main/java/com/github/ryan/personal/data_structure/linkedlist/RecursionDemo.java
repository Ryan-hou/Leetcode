package com.github.ryan.personal.data_structure.linkedlist;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className RecursionDemo
 * @date August 09,2018
 */
@Slf4j
public class RecursionDemo {

    public static int sum(int[] arr) {
        assert arr != null;
        // return sum(arr, 0, 0);
        return sum(arr, 0);
    }

    // 计算arr[start...arr.length)这个区间内所有数字的和
    private static int sum(int[] arr, int start) {
        // 递归出口 -- 空数组的和
        // 1.也即原问题分解成的最基本的问题
        if (start == arr.length) {
            return 0;
        }

        // 2.把原问题转化成更小的问题（难点！）
        // sum(arr, start) --> arr[start] + sum(arr, start + 1)
        // 问题规模减小
        return arr[start] + sum(arr, start + 1);
    }

     // correct but stupid! think about it!
//    private static int sum(int[] arr, int start, int sum) {
//        if (start == arr.length) {
//            return sum;
//        }
//
//        sum += sum(arr, start + 1, sum) + arr[start++];
//        return sum;
//    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};

         // 线性数组容量为100000，系统栈空间溢出
//        int[] arr = new int[100000];
//        for (int i = 0; i < 100000; i++) {
//            arr[i] = i;
//        }
        log.info("arr sum = {}", sum(arr));
    }
}

