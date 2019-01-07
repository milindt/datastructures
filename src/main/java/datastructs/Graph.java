package datastructs;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    private final Set<Node> vertices;

    /*public Graph(Integer... vertices) {
        this.vertices = Arrays.asList(vertices).
                stream().map(Node::new).
                collect(Collectors.toSet());
    }*/

    public Graph(Integer... vertices) {
        this.vertices = new HashSet<>();
        for(Integer i:vertices) {
            this.vertices.add(new Node(i));
        }
    }

    public Set<Node> getVertices() {
        return this.vertices;
    }

    public void addEdge(Integer vertextOne, Integer vertexTwo) {
        addEdgeFromTo(vertextOne, vertexTwo);
        addEdgeFromTo(vertexTwo, vertextOne);
    }

    private void addEdgeFromTo(Integer vertextOne, Integer vertexTwo) {
        getVertex(vertextOne).addEdgeTo(getVertex(vertexTwo));
    }

    public boolean hasEdge(Integer vertextOne, Integer vertextTwo) {
        return getVertex(vertextOne).getEdgesTo().contains(
                        getVertex(vertextTwo));
    }

    public Node getVertex(Integer vertextOne) {
        return this.vertices.stream().
                filter(x -> x.getValue().equals(vertextOne)).
                findFirst().orElseThrow(RuntimeException::new);
    }

    public List<Integer> findPathDfs(Integer start, Integer end) {
        return findPathDfs(getVertex(start), getVertex(end), new LinkedHashSet<>());
    }

    private List<Integer> findPathDfs(Node start, Node end, LinkedHashSet<Node> visitedNodes) {
        if(visitedNodes.contains(start)) {
            return Collections.emptyList();
        } else {
            visitedNodes.add(start);
        }
        if(start.equals(end)) {
            return toValues(visitedNodes);
        } else {
            return start.getEdgesTo().stream().
                    flatMap(st -> findPathDfs(st, end, visitedNodes).stream()).
                    collect(Collectors.toList());
        }
    }

    public List<Integer> findPathBfs(Integer start, Integer end) {
        return findPathBfs(getVertex(start), getVertex(end), new LinkedHashSet<>());
    }

    public List<Integer> findPathBfs(Node start, Node end, LinkedHashSet<Node> visitedNodes) {
        List<Integer> pathBfs = searchBreadth(start, end, visitedNodes);
        return !pathBfs.isEmpty()?pathBfs:
                start.getEdgesTo().stream().
                        filter(v-> !visitedNodes.contains(v)).
                        flatMap(v -> findPathBfs(v, end, visitedNodes).stream()).
                        collect(Collectors.toList());
    }

    private List<Integer> searchBreadth(Node start, Node end, LinkedHashSet<Node> visitedNodes) {
        if(visitedNodes.contains(start)) {
            return Collections.emptyList();
        } else {
            visitedNodes.add(start);
        }
        if(start.equals(end)) {
            return toValues(visitedNodes);
        } else if(start.getEdgesTo().contains(end)){
            visitedNodes.add(end);
            return toValues(visitedNodes);
        } else {
            return Collections.emptyList();
        }
    }

    private List<Integer> toValues(LinkedHashSet<Node> visitedNodes) {
        return visitedNodes.stream().map(Node::getValue).collect(Collectors.toList());
    }

    public void addEdge(Integer start, Integer end, Integer cost) {
        addEdgeFromToWithCost(start, end, cost);
        addEdgeFromToWithCost(end, start, cost);
    }

    private void addEdgeFromToWithCost(Integer vertextOne, Integer vertexTwo, Integer cost) {
        getVertex(vertextOne).addEdgeTo(getVertex(vertexTwo), cost);
    }

    public Integer getCost(Integer startVertex, Integer endVertex) {
        return getVertex(startVertex).getCost(getVertex(endVertex));
    }

    public class Node {

        private final Integer value;
        private final Map<Node, Integer> edgesTo;

        public Node(Integer value) {
            this.value = value;
            this.edgesTo = new HashMap<>();
        }

        public Integer getValue() {
            return value;
        }

        public void addEdgeTo(Node vertex) {
            addEdgeTo(vertex, 0);
        }

        public void addEdgeTo(Node vertex, Integer cost) {
            this.edgesTo.put(vertex, cost);
        }

        public Integer getCost(Node endVertex) {
            return this.edgesTo.get(endVertex);
        }

        public Set<Node> getEdgesTo() {
            return this.edgesTo.keySet();
        }
    }
}
