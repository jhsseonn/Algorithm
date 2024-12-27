package BOJ.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 14,052kb / 104ms
 */
public class Main_1991_S1_트리_순회 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N;

    public static void main(String[] args) throws IOException {
        BinaryTree tree = new BinaryTree();
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char parent = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            tree.addNode(parent, left, right);
        }

        tree.preorderTraversal(tree.root);
        sb.append("\n");
        tree.inorderTraversal(tree.root);
        sb.append("\n");
        tree.postorderTraversal(tree.root);

        System.out.print(sb);
    }

    static class BinaryTree {
        private HashMap<Character, TreeNode> nodeMap = new HashMap<>();
        TreeNode root;

        public void addNode(char parent, char left, char right) {
            TreeNode parentNode = nodeMap.getOrDefault(parent, new TreeNode(parent));
            nodeMap.put(parent, parentNode);

            if (root == null) {
                root = parentNode;
            }

            if (left != '.') {
                TreeNode leftNode = new TreeNode(left);
                parentNode.left = leftNode;
                nodeMap.put(left, leftNode);
            }

            if (right != '.') {
                TreeNode rightNode = new TreeNode(right);
                parentNode.right = rightNode;
                nodeMap.put(right, rightNode);
            }
        }

        public void preorderTraversal(TreeNode node) {
            if (node == null) {
                return;
            }
            sb.append(node.value);
            preorderTraversal(node.left);
            preorderTraversal(node.right);
        }

        public void inorderTraversal(TreeNode node) {
            if (node == null) {
                return;
            }
            inorderTraversal(node.left);
            sb.append(node.value);
            inorderTraversal(node.right);
        }

        public void postorderTraversal(TreeNode node) {
            if (node == null) {
                return;
            }
            postorderTraversal(node.left);
            postorderTraversal(node.right);
            sb.append(node.value);
        }
    }

    static class TreeNode {
        char value;
        TreeNode left;
        TreeNode right;

        public TreeNode(char value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}
