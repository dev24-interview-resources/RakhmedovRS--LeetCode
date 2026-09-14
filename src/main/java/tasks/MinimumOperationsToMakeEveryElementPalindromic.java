package tasks;

import common.Difficulty;
import common.LeetCode;

import java.util.*;

/**
 * @author Ruslan Rakhmedov
 * @created 2026-09-13
 */
@LeetCode(
        id = 4053,
        name = "Minimum Operations to Make Every Element Palindromic",
        url = "https://leetcode.com/problems/minimum-operations-to-make-every-element-palindromic/description/",
        difficulty = Difficulty.MEDIUM
)
public class MinimumOperationsToMakeEveryElementPalindromic {
    public long minOperations(int[] nums) {
        long[] even = new long[66000];
        long[] odd = new long[66000];
        int[] e = new int[]{0};
        int[] o = new int[]{0};
        for (int len = 1; len <= 9; len++) {
            int[] number = new int[len];
            for (int start = 1; start < 10; start++) {
                number[0] = start;
                number[number.length - 1] = start;
                dfs(1, number.length - 2, number, e, even, o, odd);
            }
        }

        Arrays.sort(even);
        Arrays.sort(odd);

        long ans = 0;
        for (long num : nums) {
            long[] palindromes;
            if (num % 2 == 0) {
                palindromes = even;
            } else {
                palindromes = odd;
            }

            int pos = Arrays.binarySearch(palindromes, num);

            if (pos >= 0) {
                continue;
            }

            pos = -pos - 1;

            long best = Long.MAX_VALUE;
            if (pos < palindromes.length) {
                best = Math.min(best, (palindromes[pos] - num) / 2);
            }
            if (pos > 0) {
                best = Math.min(best, (num - palindromes[pos - 1]) / 2);
            }
            ans += best;
        }

        return ans;
    }

    private void dfs(int left, int right, int[] number, int[] e, long[] even, int[] o, long[] odd) {
        if (left > right) {
            long val = 0;
            for (int d : number) {
                val *= 10;
                val += d;
            }
            if (val % 2 == 0) {
                even[e[0]++] = val;
            } else {
                odd[o[0]++] = val;
            }
            return;
        }

        for (int d = 0; d < 10; d++) {
            number[left] = d;
            number[right] = d;
            dfs(left + 1, right - 1, number, e, even, o, odd);
        }
    }
}