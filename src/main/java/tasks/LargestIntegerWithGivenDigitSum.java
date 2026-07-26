package tasks;

import common.Difficulty;
import common.LeetCode;

import java.util.*;

/**
 * @author Ruslan Rakhmedov
 * @created 2026-07-25
 */
@LeetCode(
        id = 4000,
        name = "Largest Integer With Given Digit Sum",
        url = "https://leetcode.com/problems/largest-integer-with-given-digit-sum/description/",
        difficulty = Difficulty.EASY
)
public class LargestIntegerWithGivenDigitSum {
    public int largestInteger(int n, int s) {
        int ans = 0;
        while (n-- > 0) {
            if (s >= 9) {
                ans *= 10;
                ans += 9;
                s -= 9;
            } else if (s >= 0) {
                ans *= 10;
                ans += s;
                s -= s;
            }
        }
        return n <= 0 && s == 0 ? ans : -1;
    }
}