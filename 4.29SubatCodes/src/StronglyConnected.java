/**
 * Mert CACINA
 */
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StronglyConnected {
	
	public List<Set<Node<Integer>>> strongConnect (Graph g){
		
		
		/*
		 * Created to keep track of the nodes
		 * that we visit.
		 */
		
		Set<Node<Integer>> visitedNodes = new HashSet<>();
		
		/*
		 * Our stack for the usage of DFS.
		 */
		Deque<Node<Integer>> stack = new ArrayDeque<>();
		
		
		/*
		 * Recursively doing the first DFS on the nodes
		 * to obtain finishing times.
		 */
		
        for (Node<Integer> Node : g.getNodes()) {
            if (visitedNodes.contains(Node)) {
                continue;
            }
            dfsFirst(Node, visitedNodes, stack);
        }

        
        /*
         * Reversing the graph.
         */
        Graph reverseGraph = reverseGraph(g);
        /*
         * Clearing the visitedNodes.
         */
        visitedNodes.clear();
        
        /*
         * Result list that we will use in the main mehtod.
         */
        
        List<Set<Node<Integer>>> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            Node Node = reverseGraph.getNodeT(stack.poll());
            if(visitedNodes.contains(Node)){
                continue;
            }
            Set<Node<Integer>> set = new HashSet<>();
            dfsWithReverse(Node, visitedNodes, set);
            result.add(set);
        }
        return result;
    }
	
	/*
	 * Reverse graph function that takes an graph
	 * an gives its reversed (TRANSPOSE) version
	 * as an output.
	 */
     Graph reverseGraph(Graph graph) {
        
        for (Edge edge : graph.getEdges()) {
        	//Turning the edges other way.
        	Node temp = edge.a;
        	edge.a=edge.b;
        	edge.b=temp;
        	
        }
        
        return graph;
        
    }
    
    /*
     * DFS search algorithm
     */
     void dfsFirst(Node<Integer> Node,
            Set<Node<Integer>> visitedNodes, Deque<Node<Integer>> stack) {
        visitedNodes.add(Node);
        for (Node<Integer> v : Graph.getNeighbors(Node)) {
            if (visitedNodes.contains(v)) {
                continue;
            }
            dfsFirst(v, visitedNodes, stack);
        }
        stack.offerFirst(Node);
    }
    
    /*
     * DFS search algorithm for the reversed graph.
     */
     void dfsWithReverse(Node<Integer> Node,
                                        Set<Node<Integer>> visitedNodes, Set<Node<Integer>> set) {
        visitedNodes.add(Node);
        set.add(Node);
        for (Node<Integer> v : Graph.getNeighbors(Node)) {
            if (visitedNodes.contains(v)) {
                continue;
            }
            dfsWithReverse(v, visitedNodes, set);
        }
    }

    

	
	
	
}
