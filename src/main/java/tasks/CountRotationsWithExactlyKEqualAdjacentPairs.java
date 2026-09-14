package tasks;

import common.Difficulty;
import common.LeetCode;

import java.util.*;

/**
 * @author Ruslan Rakhmedov
 * @created 2026-09-13
 */
@LeetCode(
        id = 4043,
        name = "Count Rotations With Exactly K Equal Adjacent Pairs",
        url = "https://leetcode.com/problems/count-rotations-with-exactly-k-equal-adjacent-pairs/description/",
        difficulty = Difficulty.EASY
)
public class CountRotationsWithExactlyKEqualAdjacentPairs {
    public int countRotations(String s, int k) {
        List<Character> list = new ArrayList<>();
        for (char ch : s.toCharArray()) {
            list.add(ch);
        }

        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int seen = 0;
            for (int j = 1; j < list.size(); j++) {
                if (list.get(j - 1).equals(list.get(j))) {
                    seen++;
                }
            }
            if (seen == k) {
                ans++;
            }
            list.addFirst(list.removeLast());
        }
        return ans;
    }
}