package tasks;

import common.Difficulty;
import common.LeetCode;

import java.util.*;

/**
 * @author Ruslan Rakhmedov
 * @created 2026-08-02
 */
@LeetCode(
        id = 4010,
        name = "Maximize Pair Strength Using GCD",
        url = "https://leetcode.com/problems/maximize-pair-strength-using-gcd/description/",
        difficulty = Difficulty.EASY
)
public class MaximizePairStrengthUsingGCD {
    public long maxPairStrength(int[] nums) {
        long max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                max = Math.max(max, ((long) nums[i] * nums[j]) / (long) Math.pow(gcd(nums[i], nums[j]), 2));
            }
        }
        return max;
    }

    public static int gcd(int a, int b) {
        return a == 0 ? b : gcd(b % a, a);
    }
}