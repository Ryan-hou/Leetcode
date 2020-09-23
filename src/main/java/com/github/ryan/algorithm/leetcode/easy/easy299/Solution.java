package com.github.ryan.algorithm.leetcode.easy.easy299;

public class Solution {

    public String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;
        int[] smap = new int[10]; // number 0 ~ 9
        int[] gmap = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            char sch = secret.charAt(i);
            char gch = guess.charAt(i);
            if (sch == gch) {
                bulls++;
            } else {
                smap[sch - '0']++;
                gmap[gch - '0']++;
            }
        }

        for (int i = 0; i < 10; i++) {
            if (smap[i] != 0 && gmap[i] != 0) {
                cows += Math.min(smap[i], gmap[i]);
            }
        }

        return bulls + "A" + cows + "B";
    }
}
