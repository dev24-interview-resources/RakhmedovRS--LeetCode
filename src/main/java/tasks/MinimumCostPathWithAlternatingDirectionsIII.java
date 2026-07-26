package tasks;

import common.Difficulty;
import common.LeetCode;

import java.util.*;

/**
 * @author Ruslan Rakhmedov
 * @created 2026-07-25
 */
@LeetCode(
        id = 4003,
        name = "Minimum Cost Path with Alternating Directions III",
        url = "https://leetcode.com/problems/minimum-cost-path-with-alternating-directions-iii/description/",
        difficulty = Difficulty.HARD
)
public class MinimumCostPathWithAlternatingDirectionsIII {
    public long minCost(int rows, int columns, int[][] penalty) {
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[3], b[3]));
        Long[][][] memo = new Long[rows][columns][2];
        pq.add(new long[]{0, 0, 1, 1L});
        while (!pq.isEmpty()) {
            long[] curr = pq.remove();
            int row = (int) curr[0];
            int column = (int) curr[1];
            int action = (int) curr[2];
            long cost = curr[3];

            if (row < 0 || row >= rows || column < 0 || column >= columns) {
                continue;
            }

            if (memo[row][column][action % 2] != null && cost >= memo[row][column][action % 2]) {
                continue;
            }

            memo[row][column][action % 2] = cost;
            if (row + 1 == rows && column + 1 == columns) {
                break;
            }
            action++;

            //stay
            pq.add(new long[]{row, column, action, cost + penalty[row][column]});

            if (action % 2 == 0) {
                pq.add(new long[]{row + 1, column, action, cost + (long) (row + 2) * (column + 1)});
                pq.add(new long[]{row, column + 1, action, cost + (long) (row + 1) * (column + 2)});

                //penalty
                pq.add(new long[]{row - 1, column, action, cost + penalty[row][column] + (long) (row) * (column + 1)});
                pq.add(new long[]{row, column - 1, action, cost + penalty[row][column] + (long) (row + 1) * (column)});
            } else {
                pq.add(new long[]{row - 1, column, action, cost + (long) (row) * (column + 1)});
                pq.add(new long[]{row, column - 1, action, cost + (long) (row + 1) * (column)});

                //penalty
                pq.add(new long[]{row + 1, column, action, cost + penalty[row][column] + (long) (row + 2) * (column + 1)});
                pq.add(new long[]{row, column + 1, action, cost + penalty[row][column] + (long) (row + 1) * (column + 2)});
            }
        }

        Long even = memo[rows - 1][columns - 1][0];
        Long odd = memo[rows - 1][columns - 1][1];
        if (even == null && odd == null) {
            return -1;
        } else if (even != null && odd != null) {
            return Math.min(even, odd);
        } else if (even != null) {
            return even;
        } else {
            return odd;
        }
    }
}