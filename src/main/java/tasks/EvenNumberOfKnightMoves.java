package tasks;

import common.Difficulty;
import common.LeetCode;

import java.util.*;

/**
 * @author Ruslan Rakhmedov
 * @created 2026-07-25
 */
@LeetCode(
        id = 3996,
        name = "Even Number of Knight Moves",
        url = "https://leetcode.com/problems/even-number-of-knight-moves/description/",
        difficulty = Difficulty.EASY
)
public class EvenNumberOfKnightMoves {
    public boolean canReach(int[] start, int[] target) {
        Integer[][] memo = new Integer[8][8];
        dfs(0, memo, start, target);
        return memo[target[0]][target[1]] != null && memo[target[0]][target[1]] % 2 == 0;
    }

    private void dfs(int steps, Integer[][] memo, int[] curr, int[] target) {
        if (curr[0] < 0 || curr[0] >= memo.length || curr[1] < 0 || curr[1] >= memo.length) {
            return;
        }

        if (curr[0] == target[0] && curr[1] == target[1] && steps % 2 == 0) {
            memo[curr[0]][curr[1]] = steps;
            return;
        }

        if (memo[curr[0]][curr[1]] != null && memo[curr[0]][curr[1]] % 2 == steps % 2) {
            return;
        }

        memo[curr[0]][curr[1]] = steps;

        dfs(steps + 1, memo, new int[]{curr[0] - 1, curr[1] - 2}, target);
        dfs(steps + 1, memo, new int[]{curr[0] - 2, curr[1] - 1}, target);
        dfs(steps + 1, memo, new int[]{curr[0] - 2, curr[1] + 1}, target);
        dfs(steps + 1, memo, new int[]{curr[0] - 1, curr[1] + 2}, target);
        dfs(steps + 1, memo, new int[]{curr[0] + 1, curr[1] + 2}, target);
        dfs(steps + 1, memo, new int[]{curr[0] + 2, curr[1] + 1}, target);
        dfs(steps + 1, memo, new int[]{curr[0] + 2, curr[1] - 1}, target);
        dfs(steps + 1, memo, new int[]{curr[0] + 1, curr[1] - 2}, target);
    }
}