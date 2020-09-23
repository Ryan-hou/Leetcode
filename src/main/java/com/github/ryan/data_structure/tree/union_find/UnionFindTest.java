package com.github.ryan.data_structure.tree.union_find;

import java.util.Random;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: UnionFindTest
 * @date February 11,2018
 */
public class UnionFindTest {

    public static void main(String[] args) {
        int n = 1000000;
//        UnionFind1 uf1 = new UnionFind1(n);
//        testUF(uf1,"uf1", n);
//        UnionFind2 uf2 = new UnionFind2(n);
//        testUF(uf2, "uf2", n);
        UnionFind3 uf3 = new UnionFind3(n);
        testUF(uf3, "uf3", n);
        UnionFind4 uf4 = new UnionFind4(n);
        testUF(uf4, "uf4", n);
        UnionFind5 uf5 = new UnionFind5(n);
        testUF(uf5, "uf5", n);
    }

    private static void testUF(UnionFind uf, String ufName, int n) {
        Random r = new Random();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            int a = Math.abs(r.nextInt()) % n;
            int b = Math.abs(r.nextInt()) % n;
            uf.union(a, b);
        }
        for (int i = 0; i < n; i++) {
            int a = Math.abs(r.nextInt()) % n;
            int b = Math.abs(r.nextInt()) % n;
            uf.isConnected(a, b);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(ufName + ", " + 2*n + " ops, " + (endTime - startTime) + " ms.");
    }

}
