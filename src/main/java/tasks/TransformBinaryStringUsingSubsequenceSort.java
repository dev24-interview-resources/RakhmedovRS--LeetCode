package tasks;

import common.Difficulty;
import common.LeetCode;

import java.util.*;

/**
 * @author Ruslan Rakhmedov
 * @created 2026-07-25
 */
@LeetCode(
        id = 3998,
        name = "Transform Binary String Using Subsequence Sort",
        url = "https://leetcode.com/problems/transform-binary-string-using-subsequence-sort/description/",
        difficulty = Difficulty.MEDIUM
)
public class TransformBinaryStringUsingSubsequenceSort {
    public boolean[] transformStr(String s, String[] strs) {
        int[][] sPrefix = new int[s.length()][2];
        for (int i = 0; i < s.length(); i++) {
            int bit = s.charAt(i) - '0';
            sPrefix[i][bit]++;
            if (i > 0) {
                sPrefix[i][0] += sPrefix[i - 1][0];
                sPrefix[i][1] += sPrefix[i - 1][1];
            }
        }

        int totalOnes = sPrefix[sPrefix.length - 1][1];

        boolean[] ans = new boolean[strs.length];
        for (int i = 0; i < strs.length; i++) {
            int o = 0;
            int q = 0;
            for (int j = 0; j < strs[i].length(); j++) {
                if (strs[i].charAt(j) == '1') {
                    o++;
                } else if (strs[i].charAt(j) == '?') {
                    q++;
                }
            }

            if (o > totalOnes) {
                ans[i] = false;
                continue;
            }

            int req = totalOnes - o;

            int onesUsed = 0;
            int questionsLeft = q;
            boolean ok = true;

            for (int j = 0; j < s.length(); j++) {
                char c = strs[i].charAt(j);

                if (c == '1') {
                    onesUsed++;
                } else if (c == '?') {
                    questionsLeft--;
                    if (req > questionsLeft) {
                        onesUsed++;
                        req--;
                    }
                }

                if (onesUsed > sPrefix[j][1]) {
                    ok = false;
                    break;
                }
            }
            ans[i] = ok && req == 0;
        }
        return ans;
    }
}