package com.leetcode.ryan.algorithm.medium.medium71;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date July 13,2017
 */
@Slf4j
public class Solution {

    private static String simplifyPath(String path) {
        Deque<String> validPath = new ArrayDeque<>();
        Set<String> skipStrings = new HashSet<>(Arrays.asList("", ".", ".."));
        String[] splitDir = path.split("/");
        for (String dir : splitDir) {
            if ("..".equals(dir) && !validPath.isEmpty()) validPath.pop();
            else if (!skipStrings.contains(dir)) validPath.push(dir);
        }
        String res = "";
        for (String dir : validPath) res = "/" + dir + res;
        return res.isEmpty() ? "/" : res;
    }

    public static void main(String[] args) {
        // String path = "/a/./b/../../c/";
        String path = "/abc/...";
        log.info("simple path = {}", simplifyPath(path));
    }
}
