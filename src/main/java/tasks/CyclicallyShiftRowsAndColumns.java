package tasks;

import common.Difficulty;
import common.LeetCode;

import java.util.*;

/**
 * @author Ruslan Rakhmedov
 * @created 2026-09-13
 */
@LeetCode(
        id = 4052,
        name = "Cyclically Shift Rows and Columns",
        url = "https://leetcode.com/problems/cyclically-shift-rows-and-columns/description/",
        difficulty = Difficulty.EASY
)
public class CyclicallyShiftRowsAndColumns {
    public int[][] cyclicShift(int n, int[][] grid, int[] rowShift, int[] colShift) {
        for (int i = 0; i < n; i++) {
            int k = rowShift[i] % n;
            int[] shifted = new int[n];
            for (int j = 0; j < n; j++) {
                shifted[j] = grid[i][(j + k) % n];
            }
            grid[i] = shifted;
        }

        int[][] result = new int[n][n];
        for (int j = 0; j < n; j++) {
            int k = colShift[j] % n;
            for (int i = 0; i < n; i++) {
                result[i][j] = grid[(i + k) % n][j];
            }
        }
        return result;
    }
}