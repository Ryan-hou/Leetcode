package com.github.ryan.algorithm.leetcode.easy.easy733;

public class Solution {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int R;
    private int C;
    private boolean[][] visited;
    private int[][] image;
    private int oldColor;


    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) {
            return image;
        }

        this.image = image;
        R = image.length;
        C = image[0].length;
        visited = new boolean[R][C];
        oldColor = image[sr][sc];

        dfs(sr, sc, newColor);
        return image;
    }

    // flood fill
    private void dfs(int sr, int sc, int newColor) {
        visited[sr][sc] = true;
        image[sr][sc] = newColor;
        for (int d = 0; d < dirs.length; d++) {
            int nextx = sr + dirs[d][0];
            int nexty = sc + dirs[d][1];
            if (inArea(nextx, nexty)
                    && !visited[nextx][nexty]
                    && image[nextx][nexty] == oldColor) {
                dfs(nextx, nexty, newColor);
            }
        }
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

}
