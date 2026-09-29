package tasks;

import common.Difficulty;
import common.LeetCode;

import java.util.*;

/**
 * @author Ruslan Rakhmedov
 * @created 2026-09-28
 */
@LeetCode(
        id = 2267,
        name = "Check if There Is a Valid Parentheses String Path",
        url = "https://leetcode.com/problems/check-if-there-is-a-valid-parentheses-string-path/description/",
        difficulty = Difficulty.HARD
)
public class CheckIfThereIsValidParenthesesStringPath {
    public boolean hasValidPath(char[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        Boolean[][][] memo = new Boolean[rows][columns][rows + columns];
        return dfs(0, 0, rows, columns, 0, grid, memo);
    }

    private boolean dfs(int row, int column, int rows, int columns, int balance, char[][] grid, Boolean[][][] memo) {
        if (row >= rows || column >= columns) {
            return false;
        }

        if (balance < 0) {
            return false;
        }

        if (grid[row][column] == '(') {
            balance++;
        } else {
            balance--;
        }

        if (balance < 0) {
            return false;
        }

        if (row == rows - 1 && column == columns - 1) {
            return balance == 0;
        }

        if (memo[row][column][balance] != null) {
            return memo[row][column][balance];
        }

        boolean isValid = dfs(row + 1, column, rows, columns, balance, grid, memo)
                || dfs(row, column + 1, rows, columns, balance, grid, memo);

        memo[row][column][balance] = isValid;
        return isValid;
    }
}