package tasks;

import common.Difficulty;
import common.LeetCode;

import java.util.*;

/**
 * @author Ruslan Rakhmedov
 * @created 2026-09-13
 */
@LeetCode(
        id = 4046,
        name = "Minimum Cost Path With At Most K Turns",
        url = "https://leetcode.com/problems/minimum-cost-path-with-at-most-k-turns/description/",
        difficulty = Difficulty.MEDIUM
)
public class MinimumCostPathWithAtMostKTurns {
    enum Direction {
        LEFT,
        RIGHT,
        UP,
        DOWN;
    }

    class Position {
        Direction direction;
        int row;
        int column;
        int sum;
        int turnsLeft;

        public Position(Direction direction, int row, int column, int sum, int turnsLeft) {
            this.direction = direction;
            this.row = row;
            this.column = column;
            this.sum = sum;
            this.turnsLeft = turnsLeft;
        }
    }

    public static int manhattanDistance(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    public int minCost(int[][] grid, int k) {
        int rows = grid.length;
        int columns = grid[0].length;

        PriorityQueue<Position> positions = new PriorityQueue<>((p1, p2) -> {
            if (p1.sum == p2.sum) {
                return manhattanDistance(new int[]{p1.row, p1.column}, new int[]{rows - 1, columns - 1}) - manhattanDistance(new int[]{p2.row, p2.column}, new int[]{rows - 1, columns - 1});
            }

            return Long.compare(p1.sum, p2.sum);
        });

        positions.add(new Position(Direction.DOWN, 0, 0, grid[0][0], k));
        positions.add(new Position(Direction.RIGHT, 0, 0, grid[0][0], k));

        Integer[][][][] memo = new Integer[rows][columns][4][k + 1];

        while (!positions.isEmpty()) {
            Position current = positions.remove();
            Direction direction = current.direction;
            int row = current.row;
            int column = current.column;
            int sum = current.sum;
            int turnsLeft = current.turnsLeft;

            if (row < 0 || row >= rows || column < 0 || column >= columns || turnsLeft < 0) {
                continue;
            }

            if (memo[row][column][direction.ordinal()][turnsLeft] != null && memo[row][column][direction.ordinal()][turnsLeft] <= sum) {
                continue;
            }

            memo[row][column][direction.ordinal()][turnsLeft] = sum;

            if (row == rows - 1 && column == columns - 1) {
                return sum;
            }

            for (Direction dir : Direction.values()) {
                int nextRow = row;
                int nextColumn = column;
                if (dir == Direction.DOWN) {
                    nextRow++;
                } else if (dir == Direction.LEFT) {
                    nextColumn--;
                } else if (dir == Direction.RIGHT) {
                    nextColumn++;
                } else {
                    nextRow--;
                }

                int turns = turnsLeft;
                if (direction != dir) {
                    turns--;
                }

                if (nextRow >= 0 && nextRow < rows && nextColumn >= 0 && nextColumn < columns) {
                    positions.add(new Position(dir, nextRow, nextColumn, sum + grid[nextRow][nextColumn], turns));
                }
            }
        }
        return -1;
    }
}