package com.github.ryan.leetcode.algorithm.medium.medium353;

import java.util.LinkedList;

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */
public class SnakeGame {

    private int width;
    private int height;
    private int[][] food;
    private LinkedList<Point> snake;
    private int eatCount;


    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0].
     */
    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        eatCount = 0;
        snake = new LinkedList<Point>();
        snake.addFirst(new Point(0, 0));
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body.
     */
    public int move(String direction) {
        Point head = snake.getFirst();
        Point newHead = new Point(head.x, head.y);
        Point tail = snake.removeLast();

        if ("U".equals(direction)) {
            newHead.x -= 1;
        } else if ("D".equals(direction)) {
            newHead.x += 1;
        } else if ("L".equals(direction)) {
            newHead.y -= 1;
        } else if ("R".equals(direction)) {
            newHead.y += 1;
        } else {
            throw new IllegalArgumentException("Invalid input!");
        }

        // corner case: 1) collides with border 2) or collides with itself
        if (newHead.x < 0 || newHead.x >= height
                || newHead.y < 0 || newHead.y >= width
                || snake.contains(newHead)) {
            return -1;
        }

        snake.addFirst(newHead);
        if (eatCount < food.length
                && newHead.x == food[eatCount][0] && newHead.y == food[eatCount][1]) {
            snake.addLast(tail);
            eatCount++;
        }
        return eatCount;
    }

    private static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Point)) return false;
            Point p = (Point) obj;
            return this.x == p.x && this.y == p.y;
        }
    }

}
