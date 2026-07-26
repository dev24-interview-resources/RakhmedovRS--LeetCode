package tasks;

import common.Difficulty;
import common.LeetCode;
import common.TreeNode;

import java.util.*;

/**
 * @author Ruslan Rakhmedov
 * @created 2026-07-25
 */
@LeetCode(
        id = 3997,
        name = "Count Dominant Nodes in a Binary Tree",
        url = "https://leetcode.com/problems/count-dominant-nodes-in-a-binary-tree/description/",
        difficulty = Difficulty.MEDIUM
)
public class CountDominantNodesInBinaryTree {
    public int countDominantNodes(TreeNode root) {
        int[] ans = new int[]{0};
        dfs(root, ans);
        return ans[0];
    }

    private int dfs(TreeNode node, int[] ans) {
        if (node == null) {
            return -1;
        }

        int leftMax = dfs(node.left, ans);
        int rightMax = dfs(node.right, ans);
        if (node.val >= leftMax && node.val >= rightMax) {
            ans[0]++;
        }
        return Math.max(node.val, Math.max(leftMax, rightMax));
    }
}