package tasks;

import common.Difficulty;
import common.LeetCode;

import java.util.*;

/**
 * @author Ruslan Rakhmedov
 * @created 2026-09-13
 */
@LeetCode(
        id = 4044,
        name = "Count Good Cyclic Rotations",
        url = "https://leetcode.com/problems/count-good-cyclic-rotations/description/",
        difficulty = Difficulty.MEDIUM
)
public class CountGoodCyclicRotations {
    public int countGoodRotations(int[] nums) {
        long leftSum = 0L;
        for (int i = 0; i < nums.length / 2; i++) {
            leftSum += nums[i];
        }

        long rightSum = 0L;
        for (int i = nums.length / 2; i < nums.length; i++) {
            rightSum += nums[i];
        }

        int ans = 0;
        int left = nums.length / 2 - 1;
        int right = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (leftSum > rightSum) {
                ans++;
            }

            leftSum -= nums[left];
            leftSum += nums[right];
            rightSum -= nums[right];
            rightSum += nums[left];

            left = (left - 1 + nums.length) % nums.length;
            right = (right - 1 + nums.length) % nums.length;
        }

        return ans;
    }
}