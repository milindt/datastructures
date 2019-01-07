package datastructs;

public class BinarySearchTree extends Tree {

    public BinarySearchTree(Integer root) {
        super(root);
    }

    public Integer find(Integer value) {
        return find(this.root, value);
    }

    private Integer find(Node node, Integer value) {
      if (node == null) return null;
      if(value < node.getValue()) {
          return find(node.getLeft(), value);
      } else if(value > node.getValue()) {
          return find(node.getRight(), value);
      } else {
          return node.getValue();
      }
    }

    public void insert(Integer value) {
        insert(this.root, value);
    }

    private Node insert(Node node, Integer value) {
        if(node == null) {
            node = new Node(value);
        } else if(value < node.getValue()) {
            node.addLeft(insert(node.getLeft(), value));
        } else if(value > node.getValue()) {
            node.addRight(insert(node.getRight(), value));
        }
        //Linking won't change
        return node;
    }
}
