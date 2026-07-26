package tasks;

import common.Difficulty;
import common.LeetCode;

import java.util.*;

/**
 * @author Ruslan Rakhmedov
 * @created 2026-07-25
 */
@LeetCode(
        id = 4001,
        name = "Aggregate Two Time Series",
        url = "https://leetcode.com/problems/aggregate-two-time-series/description/",
        difficulty = Difficulty.MEDIUM
)
public class AggregateTwoTimeSeries {
    public List<List<Integer>> aggregateTimeSeries(int[][] series1, int[][] series2) {
        int S1 = 0;
        int S2 = 0;
        List<List<Integer>> ans = new ArrayList<>();
        while (S1 < series1.length || S2 < series2.length) {
            if (S1 < series1.length && S2 < series2.length) {
                int s1Time = series1[S1][0];
                int s2Time = series2[S2][0];
                ans.add(Arrays.asList(Math.min(s1Time, s2Time), series1[S1][1] + series2[S2][1]));
                if (s1Time == s2Time) {
                    S1++;
                    S2++;
                } else if (s1Time < s2Time) {
                    S1++;
                } else {
                    S2++;
                }
            } else if (S1 < series1.length) {
                int s1Time = series1[S1][0];
                ans.add(Arrays.asList(s1Time, series1[S1][1]));
                S1++;
            } else {
                int s2Time = series2[S2][0];
                ans.add(Arrays.asList(s2Time, series2[S2][1]));
                S2++;
            }
        }
        return ans;
    }
}