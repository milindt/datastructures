package datastructs;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    final Node root;

    public Tree(int root) {
        this.root = new Node(root);
    }
    public Node getRoot() {
        return root;
    }

    public Iterable<Integer> inorder() {
        return inorder(this.root, new ArrayList<Integer>());
    }

    private List<Integer> inorder(Node node, List<Integer> nodesSoFar) {
        if(node==null) {
            return nodesSoFar;
        }
        inorder(node.getLeft(), nodesSoFar);
        nodesSoFar.add(node.getValue());
        inorder(node.getRight(), nodesSoFar);
        return nodesSoFar;
    }

    public Iterable<Integer> preorder() {
        return preorder(this.root, new ArrayList<>());
    }

    private List<Integer> preorder(Node node, ArrayList<Integer> nodesSoFar) {
        if(node == null) {
            return nodesSoFar;
        }
        nodesSoFar.add(node.getValue());
        preorder(node.getLeft(), nodesSoFar);
        preorder(node.getRight(), nodesSoFar);
        return nodesSoFar;
    }

    public Iterable<Integer> postorder() {
        return postorder(this.root, new ArrayList<>());
    }

    private List<Integer> postorder(Node node, ArrayList<Integer> nodesSoFar) {
        if(node == null) {
            return nodesSoFar;
        }
        postorder(node.getLeft(), nodesSoFar);
        postorder(node.getRight(), nodesSoFar);
        nodesSoFar.add(node.getValue());
        return nodesSoFar;
    }

    public class Node {
        public Integer getValue() {
            return value;
        }

        final private Integer value;
        private Node left;
        private Node right;

        public Node(Integer value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public void addLeft(Integer left) {
            this.left = new Node(left);
        }

        public Node addLeft(Node left) {
            this.left = left;
            return this.left;
        }

        public void addRight(Integer right) {
            this.right = new Node(right);
        }

        public Node addRight(Node right) {
            this.right = right;
            return this.right;
        }
    }

}
