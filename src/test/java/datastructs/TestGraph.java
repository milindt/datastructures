package datastructs;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestGraph {

    @Test
    void testGraphHasVertises() {
        Graph g = new Graph(1, 2, 3, 4);
        assertThat(g.getVertices().stream().
                map(Graph.Node::getValue)).
                contains(1, 2, 3, 4);
    }

    @Test
    void testGraphHasGetVertexNode() {
        Graph g = new Graph(1);
        assertThat(g.getVertex(1)).
                isNotNull().
                isInstanceOf(Graph.Node.class);
        assertThat(g.getVertex(1).getValue()).
                isEqualTo(1);
    }

    @Test
    void testGetVertexNodeFailsForAbsentNode() {
        Graph g = new Graph(1);
        assertThatThrownBy(() -> g.getVertex(2)).
                isInstanceOf(RuntimeException.class);
    }

    @Test
    void testGraphHasEdges() {
        Graph g = new Graph(1, 2, 3, 4);
        g.addEdge(1, 2);
        assertThat(g.hasEdge(1, 2)).isTrue();
        assertThat(g.hasEdge(2, 1)).isTrue();
    }

    @Test
    void testGraphDoesNotHaveInvalidEdges() {
        Graph g = new Graph(1, 2, 3, 4);
        g.addEdge(1, 2);
        assertThat(g.hasEdge(1, 3)).isFalse();
        assertThat(g.hasEdge(1, 4)).isFalse();
        assertThat(g.hasEdge(2, 3)).isFalse();
        assertThat(g.hasEdge(2, 4)).isFalse();
        assertThat(g.hasEdge(3, 1)).isFalse();
        assertThat(g.hasEdge(3, 2)).isFalse();
        assertThat(g.hasEdge(3, 4)).isFalse();
        assertThat(g.hasEdge(4, 1)).isFalse();
        assertThat(g.hasEdge(4, 2)).isFalse();
        assertThat(g.hasEdge(4, 3)).isFalse();
    }

    @Test
    void testGraphDoesNotHaveVerticesWithEdgesToSelf() {
        Graph g = new Graph(1, 2, 3, 4);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 1);
        g.getVertices().stream().
                map(Graph.Node::getValue).
                map(v-> assertThat(g.hasEdge(v, v)).
                        isFalse());
    }

    @Test
    void testFindPathDfsWorksIfPathExists() {
        Graph g = new Graph(1, 2, 3, 4, 5, 6);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 5);
        g.addEdge(2, 4);
        g.addEdge(4, 6);
        assertThat(g.findPathDfs(1, 6)).
                isNotEmpty().
                containsSubsequence(1, 2, 4, 6);
    }

    @Test
    void testFindPathDfsReturnsEmptyIfPathDoesntExists() {
        Graph g = new Graph(1, 2, 3, 4);
        g.addEdge(1, 2);
        g.addEdge(3, 4);
        assertThat(g.findPathDfs(1, 4)).isEmpty();
    }

    @Test
    void testFindPathBfsWorksIfPathExists() {
        Graph g = new Graph(1, 2, 3, 4, 5, 6, 7);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(4, 6);
        g.addEdge(6, 7);
        assertThat(g.findPathBfs(1, 7)).
                isNotEmpty().
                containsSubsequence(1, 3, 4, 6, 7);
    }

    @Test
    void testEdgeCanHaveCost() {
        Graph g = new Graph(1, 2);
        g.addEdge(1 ,2, 9);
        assertThat(g.hasEdge(1, 2)).isTrue();
        assertThat(g.getCost(1, 2)).isEqualTo(9);
    }

}
