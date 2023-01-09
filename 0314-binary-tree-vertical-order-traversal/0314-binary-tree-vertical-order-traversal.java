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
    private HashMap<Integer, List<Integer>> columnToNode;
    private List<List<Integer>> traversal;
    private int minColumn = Integer.MAX_VALUE;
    private int maxColumn = Integer.MIN_VALUE;
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        traversal = new ArrayList<>();
        columnToNode = new HashMap<>();
        
        if (root == null) {
            return traversal;
        }
        
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<TreeNode, Integer>(root, 0));
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> e = queue.poll();
            TreeNode node = e.getKey();
            int column = e.getValue();
            
            if (!columnToNode.containsKey(column)) {
                columnToNode.put(column, new ArrayList<>());
            }
            columnToNode.get(column).add(node.val);
            
            minColumn = Math.min(minColumn, column);
            maxColumn = Math.max(maxColumn, column);
            
            if (node.left != null) {
                queue.add(new Pair<TreeNode, Integer>(node.left, column - 1));
            }
            if (node.right != null) {
                queue.add(new Pair<TreeNode, Integer>(node.right, column + 1));
            }
        }
        
        for (int i = minColumn; i <= maxColumn; ++i) {
            traversal.add(columnToNode.get(i));
        }
        return traversal;
    }
}