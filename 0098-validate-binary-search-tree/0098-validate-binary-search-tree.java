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
    private ArrayList<Integer> traversal = new ArrayList<>();
    
    public void inOrderTraversal(TreeNode root) {
        if (root.left != null) {
            inOrderTraversal(root.left);
        }
        traversal.add(root.val);
        if (root.right != null) {
            inOrderTraversal(root.right);
        }
    }
    
    public boolean check() {
        Integer[] traversalArray = new Integer[traversal.size()];
        traversalArray = traversal.toArray(traversalArray);
        for (int i = 0; i < traversalArray.length - 1; ++i) {
            if (traversalArray[i + 1] <= traversalArray[i]) {
                return false;
            }
        }
        return true;
    }
    public boolean isValidBST(TreeNode root) {
        // boolean isValid = true;
        // if (root.left != null) {
        //     isValid = isValid && (root.val > root.left.val && isValidBST(root.left));
        // }
        // if (root.right != null) {
        //     isValid = isValid && (root.val < root.right.val && isValidBST(root.right));
        // }
        // return isValid;
        inOrderTraversal(root);
        return check();
    }
}