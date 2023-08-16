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
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        
        List<List<Integer>> nodes = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            int cnt = 0;
            for (TreeNode u: queue) {
                tmp.add(u.val);
                ++cnt;
            }
            nodes.add(tmp);
            for (int i = 0; i < cnt; ++i) {
                TreeNode u = queue.poll();
                if (u.left != null) queue.add(u.left);
                if (u.right != null) queue.add(u.right);
            }
        }
        
        return nodes;
    }
}