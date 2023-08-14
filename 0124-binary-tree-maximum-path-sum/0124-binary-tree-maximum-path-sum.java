/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int maxPathSum = -1000;
    
    public int dp(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftSubTree = Math.max(dp(root.left), 0);
        int rightSubTree = Math.max(dp(root.right), 0);
        
        maxPathSum = Math.max(maxPathSum, leftSubTree + rightSubTree + root.val);
        return Math.max(leftSubTree + root.val, rightSubTree + root.val);
        
    }
    public int maxPathSum(TreeNode root) {
        int s = dp(root);
        return maxPathSum;
    }
}