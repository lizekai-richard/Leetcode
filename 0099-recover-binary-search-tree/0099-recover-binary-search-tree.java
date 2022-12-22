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
    
    private ArrayList<TreeNode> traversal = new ArrayList<>();
    
    public void inOrderTraversal(TreeNode root) {
        if (root.left != null) {
            inOrderTraversal(root.left);
        }
        traversal.add(root);
        if (root.right != null) {
            inOrderTraversal(root.right);
        }
    }
    
    
    public void recoverTree(TreeNode root) {
        inOrderTraversal(root);
        TreeNode[] traversalArray = new TreeNode[traversal.size()];
        traversalArray = traversal.toArray(traversalArray);
        
        TreeNode u = null;
        TreeNode v = null;
        boolean swappedFirstOccurence = false;
        for (int i = 0; i < traversalArray.length - 1; ++i) {
            if (traversalArray[i].val > traversalArray[i + 1].val) {
                v = traversalArray[i + 1];
                if (!swappedFirstOccurence) {
                    u = traversalArray[i];
                    swappedFirstOccurence = true;
                } else {
                    break;
                }
            }
        }
        
        int temp = u.val;
        u.val = v.val;
        v.val = temp;
    }
}