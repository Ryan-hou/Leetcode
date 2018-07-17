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

        // 左移（左移一位相当于乘以2，可能会出现溢出）：左边最高位丢弃，末尾补0
        // 4
        System.out.println("2左移1位，值为： " + (a << 1) + ",补码为: " + Integer.toBinaryString(a << 1));
        // -4
        System.out.println("-2左移1位，值为： " + (b << 1) + ",补码为: " + Integer.toBinaryString(b << 1));

        // >>>: unsigned right shift
        // always fills 0 irrespective of the sign of the number.
        // 1
        System.out.println("2逻辑右移1位，值为： " + (a >>> 1) + ", 补码为： " + Integer.toBinaryString(a >>> 1));
        // 2^31 - 1
        System.out.println("-2逻辑右移1位，值为： " + (b >>> 1) + ", 补码为： " + Integer.toBinaryString(b >>> 1));

        // >>: signed shift right
        // uses the sign bit (left most bit) to fill the trailing positions after shift.
        // If the number is negative, then 1 is used as a filler and if the number is positive, then 0 is used as a filler.
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

    // 2的幂用2进制表示，只有一位为1，其余位为0
    public static boolean isPowerOfTwo(int val) {

        return val < 1 ? false : (val & (val - 1)) == 0;
        // 在netty中使用了下面方式判断，需要注意在netty中val保证了大于0：
        // DefaultEventExecutorChooserFactory.newChooser
        // return (val & -val) == val;
    }

    // n是2的幂
    public static int modByBitOper(int val, int n) {
        // val % n
        return val & (n - 1);
        // 在 hashMap 和 netty 等中都有使用：
        // first = tab[(n - 1) & hash]
        // return executors[idx.getAndIncrement() & executors.length - 1];
    }

    public static boolean isOddNum(int n) {
        return (n & 1) == 1;
    }

    // 不用临时变量交换两个数
    // 异或操作性质: a ^ a = 0, a ^ 0 = a
    // a ^ b ^ a = b
    public void swapWithNoTemp(int[] pair) {
        assert pair != null && pair.length == 2;

        pair[0] = pair[0] ^ pair[1];
        pair[1] = pair[0] ^ pair[1]; // pair[1] = pair[0] ^ pair[1] ^ pair[1] = pair[0]
        pair[0] = pair[1] ^ pair[0]; // pair[0] = pair[0] ^ pair[0] ^ pair[1] = pair[1]
    }

    // 判断一个数是否小于2的整数次幂
    // netty: PoolArena isTiny()
    // normCapacity < 512
    public static boolean isTiny(int normCapacity) {
        return (normCapacity & 0xFFFFFE00) == 0;
    }

    public static void main(String[] args) {
//        bitwiseOper();
//        bitShiftOper();

//        int a = Integer.MAX_VALUE;
//        System.out.println(a + 1); // 下溢
    }
}
