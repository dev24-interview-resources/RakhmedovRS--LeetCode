package tasks;

import common.Difficulty;
import common.LeetCode;

import java.util.*;

/**
 * @author Ruslan Rakhmedov
 * @created 2026-08-02
 */
@LeetCode(
        id = 4012,
        name = "Count of Unfinished Tasks After Each Shift",
        url = "https://leetcode.com/problems/count-of-unfinished-tasks-after-each-shift/description/",
        difficulty = Difficulty.MEDIUM
)
public class CountOfUnfinishedTasksAfterEachShift {
    public int[] countTasks(int[] tasks, int[] shifts) {
        long[] leftToRight = new long[tasks.length];
        long[] rightToLeft = new long[tasks.length];
        leftToRight[0] = tasks[0];
        rightToLeft[rightToLeft.length - 1] = tasks[tasks.length - 1];
        for (int i = 1; i < tasks.length; i++) {
            leftToRight[i] += leftToRight[i - 1] + tasks[i];
        }

        for (int i = tasks.length - 2; i >= 0; i--) {
            rightToLeft[i] += rightToLeft[i + 1] + tasks[i];
        }

        long progress = 0;
        long total = leftToRight[leftToRight.length - 1];
        int[] ans = new int[shifts.length];
        for (int i = 0; i < shifts.length; i++) {
            progress += shifts[i];
            if (progress >= total) {
                progress = 0;
                continue;
            }

            int left = 0;
            int right = leftToRight.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (leftToRight[mid] <= progress) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            ans[i] = tasks.length - left;
        }
        return ans;
    }
}