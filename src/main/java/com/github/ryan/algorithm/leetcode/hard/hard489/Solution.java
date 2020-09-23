package com.github.ryan.algorithm.leetcode.hard.hard489;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    private Robot robot;
    private Set<Pair<Integer, Integer>> visited;
    // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};


    // always turn right
    public void cleanRoom(Robot robot) {
        this.robot = robot;
        visited = new HashSet<>();
        dfs(0, 0, 0);
    }

    private void dfs(int x, int y, int dir) {
        visited.add(new Pair(x, y));
        robot.clean();
        for (int i = 0; i < dirs.length; i++) {
            int newd = (dir + i) % 4;
            int nextx = x + dirs[newd][0];
            int nexty = y + dirs[newd][1];
            if (!visited.contains(new Pair(nextx, nexty)) && robot.move()) {
                dfs(nextx, nexty, newd);
                goBack();
            }

            robot.turnRight();
        }
    }

    private void goBack() {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }

    // This is the robot's control interface.
    // You should not implement it, or speculate about its implementation
    interface Robot {
        // Returns true if the cell in front is open and robot moves into the cell.
        // Returns false if the cell in front is blocked and robot stays in the current cell.
        boolean move();

        // Robot will stay in the same cell after calling turnLeft/turnRight.
        // Each turn will be 90 degrees.
        void turnLeft();

        void turnRight();

        // Clean the current cell.
        void clean();
    }

}
