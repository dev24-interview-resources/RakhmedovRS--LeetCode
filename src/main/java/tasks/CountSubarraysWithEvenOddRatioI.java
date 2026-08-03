package tasks;

import common.Difficulty;
import common.LeetCode;

import java.util.*;

/**
 * @author Ruslan Rakhmedov
 * @created 2026-08-02
 */
@LeetCode(
        id = 4011,
        name = "Count Subarrays With Even Odd Ratio I",
        url = "https://leetcode.com/problems/count-subarrays-with-even-odd-ratio-i/description/",
        difficulty = Difficulty.MEDIUM
)
public class CountSubarraysWithEvenOddRatioI {
    public int countRatioSubarrays(int[] nums, int a, int b) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int even = 0;
            int odd = 0;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] % 2 == 0) {
                    even++;
                } else {
                    odd++;
                }

                if (odd == 0) {
                    continue;
                }

                if (even * b <= odd * a) {
                    ans++;
                }
            }
        }
        return ans;
    }
}