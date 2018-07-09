package com.leetcode.ryan.personal.bit;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: BitBasicOper
 * @date July 04,2018
 */
public class BitBasicOper {

    public static void bitShiftOper() {

        int a = 2, b = -2;

        System.out.println("2 的补码： " + Integer.toBinaryString(a));
        System.out.println("－2 的补码： " + Integer.toBinaryString(b));

        // 左移：符号位不变，末尾补0
        // 4
        System.out.println("2左移1位，值为： " + (a << 1) + ",补码为: " + Integer.toBinaryString(a << 1));
        // -4
        System.out.println("-2左移1位，值为： " + (b << 1) + ",补码为: " + Integer.toBinaryString(b << 1));

        // 逻辑右移（无符号右移）：不考虑符号位，高位补0
        // 1
        System.out.println("2逻辑右移1位，值为： " + (a >>> 1) + ", 补码为： " + Integer.toBinaryString(a >>> 1));
        // 2^31 - 1
        System.out.println("-2逻辑右移1位，值为： " + (b >>> 1) + ", 补码为： " + Integer.toBinaryString(b >>> 1));

        // 算术🈶️右移：考虑符号位
        // 1
        System.out.println("2算术右移1位，值为： " + (a >> 1) + "， 补码为：" + Integer.toBinaryString(a >> 1));
        // -1
        System.out.println("-2算术右移1位，值为： " + (b >> 1) + "， 补码为：" + Integer.toBinaryString(b >> 1));

    }

    public static void bitwiseOper() {
        int a = -2;
        // 1111 1111 1111 1111 1111 1111 1111 1110
        System.out.println("-2的补码： " + Integer.toBinaryString(a));
        // ~a
        System.out.println("~(-2): " + ~a);

        int b = 1;
        // &:  bitwise AND operation
        System.out.println("a & b: " + (a & b));
        // ｜: bitwise inclusive OR operation
        System.out.println("a | b: " + (a | b));
        // ^: bitwise exclusive OR operation
        System.out.println("a ^ b: " + (a ^ b));
    }

    public static void main(String[] args) {
        bitwiseOper();
    }
}
