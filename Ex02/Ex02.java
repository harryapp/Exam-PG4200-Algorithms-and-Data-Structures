package ex02;

import java.util.Objects;



public class StudentMap implements MyMap<String, Student> {


    private final TreeNode[] roots = new TreeNode[10];
    protected int size;

    protected class TreeNode {
        public String key;
        public Student value;

        public TreeNode left;
        public TreeNode right;
    }

    public int selectIndex(String key) {
        return Integer.parseInt(String.valueOf(key.charAt(0)));

    }

    @Override
    public void put(String key, Student value) {
        Objects.requireNonNull(key);
        roots[selectIndex(key)] = put(key, value, roots[selectIndex(key)]);
    }

    private TreeNode put(String key, Student value, TreeNode subtree) {

        if (subtree == null) {
            TreeNode node = new TreeNode();
            node.key = key;
            node.value = value;
            size++;
            return node;
        }

        int cmp = key.compareTo(subtree.key);

        if (cmp < 0) {
            subtree.left = put(key, value, subtree.left);
            return subtree;
        }

        if (cmp > 0) {
            subtree.right = put(key, value, subtree.right);
            return subtree;
        }

        assert cmp == 0;
        subtree.value = value;

        return subtree;
    }

    @Override
    public void delete(String key) {
        Objects.requireNonNull(key);
        roots[selectIndex(key)] = delete(key, roots[selectIndex(key)]);
    }

    protected TreeNode delete(String key, TreeNode subtreeRoot) {

        if (subtreeRoot == null) {
            return null;
        }

        int cmp = key.compareTo(subtreeRoot.key);

        if (cmp < 0) {
            subtreeRoot.left = delete(key, subtreeRoot.left);
            return subtreeRoot;
        }

        if (cmp > 0) {
            subtreeRoot.right = delete(key, subtreeRoot.right);
            return subtreeRoot;
        }

        assert cmp == 0;

        size--;

        if (subtreeRoot.left == null) {
            return subtreeRoot.right;
        }

        if (subtreeRoot.right == null) {
            return subtreeRoot.left;
        }

        assert subtreeRoot.left != null && subtreeRoot.right != null;

        TreeNode tmp = subtreeRoot;
        subtreeRoot = min(tmp.right);
        subtreeRoot.right = deleteMin(tmp.right);
        subtreeRoot.left = tmp.left;

        return subtreeRoot;
    }

    private TreeNode min(TreeNode subtreeRoot) {
        if (subtreeRoot.left == null) {
            return subtreeRoot;
        }
        return min(subtreeRoot.left);
    }

    private TreeNode deleteMin(TreeNode subtreeRoot) {

        if (subtreeRoot.left == null) {
            return subtreeRoot.right;
        }

        subtreeRoot.left = deleteMin(subtreeRoot.left);

        return subtreeRoot;
    }

    @Override
    public Student get(String key) {
        Objects.requireNonNull(key);
        return get(key, roots[selectIndex(key)]);
    }

    private Student get(String key, TreeNode subtreeRoot) {

        if (subtreeRoot == null) {
            return null;
        }

        int cmp = key.compareTo(subtreeRoot.key);

        if (cmp == 0) {
            return subtreeRoot.value;
        } else if (cmp > 0) {
            //look at greater values in the right subtree
            return get(key, subtreeRoot.right);
        } else if (cmp < 0) {
            //look at smaller values in the left subtree
            return get(key, subtreeRoot.left);
        }

        return null;
    }

    public Student getByName(String search_name){
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    public int getMaxTreeDepth(int index) {
        TreeNode root = roots[index];

        if (root == null) {
            return 0;
        }

        return depth(root);
    }

    protected int depth(TreeNode node) {

        int leftDepth = 0;
        int rightDepth = 0;

        if (node.left != null) {
            leftDepth = depth(node.left);
        }
        if (node.right != null) {
            rightDepth = depth(node.right);
        }

        return 1 + Math.max(leftDepth, rightDepth);
    }

}
