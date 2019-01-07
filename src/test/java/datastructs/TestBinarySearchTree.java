package datastructs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestBinarySearchTree {

    @Test
    void testBinaryTreeExtendsTree() {
        BinarySearchTree bst = new BinarySearchTree(1);
        assertThat(bst).isInstanceOf(Tree.class);
    }

    @Test
    @DisplayName("BST search does not return absent nodes")
    void testBinaryTreeSearchCantFindAbsentNodes() {
        BinarySearchTree bst = new BinarySearchTree(1);
        assertThat(bst.find(2)).isNull();
    }

    @Test
    @DisplayName("BST support binary search")
    void testBinaryTreeCanFindANode() {
        BinarySearchTree bst = getSampleBinarySearchTree();
        assertThat(bst.find(3)).isEqualTo(3);
        assertThat(bst.find(7)).isEqualTo(7);
    }

    @Test
    @DisplayName("BST can add node a node")
    void testBinaryTreeCanInsertANode() {
        BinarySearchTree bst = getSampleBinarySearchTree();
        bst.insert(10);
        assertThat(bst.find(10)).isEqualTo(10);
    }

    @Test
    @DisplayName("BST can add node multiple nodes")
    void testBinaryTreeCanInsertMultipleNodesCorrectly() {
        BinarySearchTree bst = getSampleBinarySearchTree();
        bst.insert(10);
        bst.insert(8);
        bst.insert(9);
        assertThat(bst.inorder()).
                containsSequence(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    /**
    * @return
    *<br>------6
    *<br>------^
    *<br>----4---7
    *<br>----^
    *<br>--2---5
    *<br>--^
    *<br>1---3
    */
    private BinarySearchTree getSampleBinarySearchTree() {
        BinarySearchTree bst = new BinarySearchTree(6);
        bst.getRoot().addLeft(4);
        bst.getRoot().addRight(7);
        bst.getRoot().getLeft().addLeft(2);
        bst.getRoot().getLeft().addRight(5);
        bst.getRoot().getLeft().getLeft().addLeft(1);
        bst.getRoot().getLeft().getLeft().addRight(3);
        return bst;
    }


}
