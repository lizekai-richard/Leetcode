import java.util.ArrayList;
import java.util.List;
import java.util.Random;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class ClosestBinarySearchTreeValueII {
    double targetValue;
    ArrayList<Integer> valList = new ArrayList<>();
    
    public void inOrderTraversal(TreeNode root, ArrayList<Integer> valList) {
        if(root == null) {
            return ;
        }
        inOrderTraversal(root.left, valList);
        valList.add(root.val);
        inOrderTraversal(root.right, valList);
    }
    
    public double dist(int i) {
        return Math.abs(valList.get(i) - this.targetValue);
    }
    
    public void swap(int i, int j) {
        int temp = valList.get(i);
        valList.set(i, valList.get(j));
        valList.set(j, temp);
    }
    
    public int partition(int left, int right, int pivot) {
        double pivotDist = dist(pivot);
        swap(right, pivot);
        int storeIndex = left;
        for (int i = left; i <= right; i++) {
            if (dist(i) < pivotDist) {
                swap(storeIndex, i);
                storeIndex++;
            }
        }
        
        swap(storeIndex, right);
        return storeIndex;
    }
    
    
    public void quickSelect(int left, int right, int k) {
        if(left >= right) {
            return ;
        }
        
        Random rng = new Random();
        int pivotIndex = left + rng.nextInt(right - left);
        
        pivotIndex = partition(left, right, pivotIndex);
        
        if(k < pivotIndex) {
            quickSelect(left, pivotIndex - 1, k);
        } else if(k > pivotIndex) {
            quickSelect(pivotIndex + 1, right, k);
        } else {
            return ;
        }
    }
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        this.targetValue = target;
        inOrderTraversal(root, valList);
        
        quickSelect(0, valList.size() - 1, k);
        return valList.subList(0, k);
    }
}