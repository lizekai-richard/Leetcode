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
    private HashMap<Integer, List<Pair<Integer, Integer> > > columnToNode;
    private List<List<Integer>> traversal;
    private int minColumn = Integer.MAX_VALUE;
    private int maxColumn = Integer.MIN_VALUE;
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        traversal = new ArrayList<>();
        columnToNode = new HashMap<>();
        
        if (root == null) {
            return traversal;
        }
        
        Queue<Pair<TreeNode, Pair<Integer, Integer> > > queue = new LinkedList<>();
        queue.add(new Pair<TreeNode, Pair<Integer, Integer>>(root, new Pair<>(0, 0)));
        while (!queue.isEmpty()) {
            Pair<TreeNode, Pair<Integer, Integer> > e = queue.poll();
            TreeNode node = e.getKey();
            Pair<Integer, Integer> coor = e.getValue();
            int row = coor.getKey();
            int column = coor.getValue();
            if (!columnToNode.containsKey(column)) {
                columnToNode.put(column, new ArrayList<>());
            }
            columnToNode.get(column).add(new Pair<>(row, node.val));
            
            minColumn = Math.min(minColumn, column);
            maxColumn = Math.max(maxColumn, column);
            
            if (node.left != null) {
                queue.add(new Pair<TreeNode, Pair<Integer, Integer>>(node.left, new Pair<>(row + 1, column - 1)));
            }
            if (node.right != null) {
                queue.add(new Pair<TreeNode, Pair<Integer, Integer>>(node.right, new Pair<>(row + 1, column + 1)));
            }
        }
        
        for (int i = minColumn; i <= maxColumn; ++i) {
            List<Pair<Integer, Integer> > list = columnToNode.get(i);
            Collections.sort(list, (p1, p2) -> {
                if (p1.getKey() == p2.getKey()) {
                    return p1.getValue() - p2.getValue();
                } else {
                    return p1.getKey() - p2.getKey();
                }
            });
            
            List<Integer> valueList = new ArrayList<>();
            for (Pair<Integer, Integer> p: list) {
                valueList.add(p.getValue());
            }
                
            traversal.add(valueList);
        }
        return traversal;
    }
}