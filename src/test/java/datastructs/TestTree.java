package datastructs;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


public class TestTree {

    /*Tree tree;

    @BeforeEach
    void setUp() {
        tree = new Tree();
    }

    @AfterEach
    void tearDown() {
        tree = null;
    }*/

    @Test
    void testTreeHasRoot() {
       Tree tree = new Tree(1);
       assertThat(tree.getRoot().getValue()).isEqualTo(1);
    }

    @Test
    void testTreeRootNodeHasLeft() {
        assertThat(new Tree(1).getRoot().getLeft()).isNull();
    }

    @Test
    void testTreeRootNodeHasRight() {
        assertThat(new Tree(1).getRoot().getRight()).isNull();
    }

    @Test
    void testCanAddLeftToTreeRoot() {
        Tree tree = new Tree(1);
        tree.getRoot().addLeft(2);
        assertThat(tree.getRoot().getLeft().getValue()).isEqualTo(2);
    }

    @Test
    void testCanAddRihtToTreeRoot() {
        Tree tree = new Tree(1);
        tree.getRoot().addRight(3);
        assertThat(tree.getRoot().getRight().getValue()).isEqualTo(3);
    }

    @Test
    void testCanAddLeftToTreeRootsLeft() {
        Tree tree = new Tree(1);
        tree.getRoot().addLeft(2);
        assertThat(tree.getRoot().getLeft().getValue()).isEqualTo(2);
    }

    @Test
    void testCanAddRightToTreeRootsLeft() {
        Tree tree = new Tree(1);
        tree.getRoot().addRight(3);
        assertThat(tree.getRoot().getRight().getValue()).isEqualTo(3);
    }

    @Test
    @DisplayName("Supports In-order traversal")
    void testCanTraverseInOrder() {
        /*      1
         *      ^
         *    2   3
         *    ^
         *  4  5
         * * * * * * */
        Tree tree = new Tree(1);
        tree.getRoot().addLeft(2);
        tree.getRoot().addRight(3);
        tree.getRoot().getLeft().addLeft(4);
        tree.getRoot().getLeft().addRight(5);

        Iterable<Integer> inOrderElement = tree.inorder();
        assertThat(inOrderElement).containsSequence(4, 2, 5, 1, 3);
    }

    @Test
    @DisplayName("Supports Pre-order traversal")
    void testCanTraversePreOrder() {
        /*      1
         *      ^
         *    2   3
         *    ^
         *  4  5
         * * * * * * */
        Tree tree = new Tree(1);
        tree.getRoot().addLeft(2);
        tree.getRoot().addRight(3);
        tree.getRoot().getLeft().addLeft(4);
        tree.getRoot().getLeft().addRight(5);

        Iterable<Integer> preOrderElement = tree.preorder();
        assertThat(preOrderElement).containsSequence(1, 2, 4, 5, 3);
    }

    @Test
    @DisplayName("Supports Post-order traversal")
    void testCanTraversePostOrder() {
        /*      1
         *      ^
         *    2   3
         *    ^
         *  4  5
         * * * * * * */
        Tree tree = new Tree(1);
        tree.getRoot().addLeft(2);
        tree.getRoot().addRight(3);
        tree.getRoot().getLeft().addLeft(4);
        tree.getRoot().getLeft().addRight(5);

        Iterable<Integer> postOrderElement = tree.postorder();
        assertThat(postOrderElement).containsSequence(4, 5, 2, 3, 1);
    }
}
