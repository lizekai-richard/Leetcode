import java.util.ArrayList;

/**
 * Definition of a binary search tree
 * @param <S> the type of key
 * @param <T> the type of value
 */
public class TreeNode<S extends Comparable<S>, T> {
    private TreeNode<S, T> leftTree = null;
    private TreeNode<S, T> rightTree = null;
    private TreeNode<S, T> parentTree = null;
    private S key;
    private T value;

    private final ArrayList<S> inOrderTraversal = new ArrayList<>();

    public TreeNode (S key, T value, TreeNode<S, T> parent) {
        this.key = key;
        this.value = value;
        this.parentTree = parent;
    }

    /**
     * Computes the height of current node
     */
    public int height() {
        int leftHeight = -1;
        int rightHeight = -1;
        if (leftTree != null) {
            leftHeight = leftTree.height();
        }
        if (rightTree != null) {
            rightHeight = rightTree.height();
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Searches for the maximum key
     */
    public TreeNode<S, T> searchMax() {
        if (rightTree != null) {
            return rightTree.searchMax();
        }
        return this;
    }

    /**
     * Searches for the minimum key
     */
    public TreeNode<S, T> searchMin() {
        if (leftTree != null) {
            return leftTree.searchMin();
        }
        return this;
    }

    /**
     * Searches for a key
     */
    public TreeNode<S, T> search(S key) {
        if (this.key == key) {
            return this;
        }

        if (key.compareTo(this.key) < 0) {
            if (leftTree != null) {
                return leftTree.search(key);
            } else {
                return null;
            }
        }

        else {
            if (rightTree != null) {
                return rightTree.search(key);
            } else {
                return null;
            }
        }
    }

    /**
     * Inserts a new node
     */
    public void insert(S key, T value) {
        if (key.compareTo(this.key) < 0) {
            if (leftTree != null) {
                leftTree.insert(key, value);
            } else {
                leftTree = new TreeNode<>(key, value, this);
            }
        } else {
            if (rightTree != null) {
                rightTree.insert(key, value);
            } else {
                rightTree = new TreeNode<>(key, value, this);
            }
        }
    }

    /**
     * In-order traversal
     */
    private void traverse(S key) {
        inOrderTraversal.add(key);
    }

    public void inOrderTraverse() {
        if (leftTree != null) {
            leftTree.inOrderTraverse();
        }
        traverse(key);
        if (rightTree != null) {
            rightTree.inOrderTraverse();
        }
    }

    /**
     * Finds the successor
     */
    public TreeNode<S, T> successor() {
        if (rightTree != null) {
            return rightTree.searchMin();
        }

        TreeNode<S, T> parent = parentTree;
        TreeNode<S, T> child = this;
        while (parent != null && child == parent.rightTree) {
            child = parent;
            parent = parent.parentTree;
        }
        return parent;
    }

    /**
     * Deletes a node
     */
    private void deleteNoChild(TreeNode<S, T> node) {
        if (node == node.parentTree.leftTree) {
            node.parentTree.leftTree = null;
        } else {
            node.parentTree.rightTree = null;
        }
    }
    private void deleteOneChild(TreeNode<S, T> node) {
        if (node.leftTree != null) {
            if (node == node.parentTree.leftTree) {
                node.parentTree.leftTree = node.leftTree;
            } else {
                node.parentTree.rightTree = node.leftTree;
            }
        } else {
            if (node == node.parentTree.leftTree) {
                node.parentTree.leftTree = node.rightTree;
            } else {
                node.parentTree.rightTree = node.rightTree;
            }
        }
    }

    private void deleteTwoChild(TreeNode<S, T> node) {
        TreeNode<S, T> successor = node.successor();
        node.key = successor.key;
        node.value = successor.value;
        successor.delete();
    }

    public void delete() {
        if (leftTree == null && rightTree == null) {
            deleteNoChild(this);
        } else if (leftTree != null || rightTree != null) {
            deleteOneChild(this);
        } else {
            deleteTwoChild(this);
        }
    }

}