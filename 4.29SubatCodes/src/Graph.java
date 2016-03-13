/**
 * Mert CACINA
 * Project-1
 */

import java.io.File;
import java.util.*;
 
public class Graph {
 
    Vector<Node> nodes = new Vector<Node>();
    Vector<Edge> edges = new Vector<Edge>();
    boolean directed = false;
    boolean sortedNeighbors = true;
     
    public double[][] getAdjacencyMatrix() {
        double[][] adjMatrix = new double[nodes.size()][nodes.size()];
         
        for(int i = 0; i < nodes.size(); i++)
            for(int j = 0; j < nodes.size(); j++)
                if(i == j)
                    adjMatrix[i][j] = 0;
              
                 
        for(int i = 0; i < nodes.size(); i++) {
            Node node = nodes.elementAt(i);
            //System.out.println("Current node: " + node);
             
            for(int j = 0; j < edges.size(); j++) {
                Edge edge = edges.elementAt(j);
                 
                if(edge.a == node) {
                    int indexOfNeighbor = nodes.indexOf(edge.b);
                     
                    adjMatrix[i][indexOfNeighbor] = edge.weight;
                }
            }
        }
         
        return adjMatrix;
    }
     
    public void setDirected() {
        directed = true;
    }
     
    public void setUndirected() {
        directed = false;
    }
     
    public boolean isDirected() {
        return directed;
    }
     
    public boolean isSortedNeighbors() {
        return sortedNeighbors;
    }
     
    public void setSortedNeighbors(boolean flag) {
        sortedNeighbors = flag;
    }
     
    public int indexOf(Node a) {
        for(int i = 0; i < nodes.size(); i++)
            if(nodes.elementAt(i).data.equals(a.data))
                return i;
                 
        return -1;
    }
     
    public Vector<Node> getNodes() {
        return nodes;
    }
     
    public Vector<Edge> getEdges() {
        return edges;
    }
     
    public Node getNodeAt(int i) {
        return nodes.elementAt(i);
    }
     
    public void unvisitAllNodes() {
        for(int i = 0; i < nodes.size(); i++)
            nodes.elementAt(i).unvisit();
    }
     
    public Vector<Node> getNeighbors(Node a) {
        Vector<Node> neighbors = new Vector<Node>();
         
        for(int i = 0; i < edges.size(); i++) {
            Edge edge = edges.elementAt(i);
             
            if(edge.a == a)
                neighbors.add(edge.b);
                 
            if(!directed && edge.b == a)
                neighbors.add(edge.a);
        }
         
        if(sortedNeighbors)
            Collections.sort(neighbors);
         
        return neighbors;
    }
     
    public int addNode(Node a) {
        nodes.add(a);
         
        return nodes.size() - 1;
    }
     
    public void addEdge(Edge edge) {
        edges.add(edge);
         
        if(!directed)
            edges.add(new Edge(edge.b, edge.a, edge.weight));
    }
     
    public void printNodes() {
        System.out.println(nodes);
    }
     
    public void printEdges() {
        System.out.println(edges);
    }
    
    
    void loadDot(String filename) throws Exception{
        //BufferedInputStream fr=new BufferedInputStream(new FileInputStream(filename));
        Scanner scanner=new Scanner(new File(filename));
        boolean inGraph=false;
        while (scanner.hasNext()) {
            String token=scanner.next().trim();
            if (token.equals("{")) {
                inGraph=true;
                scanner.useDelimiter(";");
            } else if (!inGraph && token.equals("graph"))
                setUndirected();
            else if (!inGraph && token.equals("digraph"))
                setDirected();
            else if (inGraph && token.length()>0 && !(token.equals("}"))) {
                if (token.lastIndexOf(" ")<0){ //a vertex
                	Node<String> n=new Node<String>(token);
                	System.out.println("adding "+ n);
                	addNode(n);
                }
                else {
                    String[] parts=token.split(" ");
                    String from=parts[0], to=parts[2];
                    
                    System.out.println(from);
                    System.out.println(to);
                    
                    if (!isNode(from) || !isNode(to))
                        throw new Exception("from or to vertex can not be found for line: "+token);
                    
                    Edge edge=new Edge(this.getNode(from), this.getNode(to));
                    if (parts.length>3) {
                        String[] tmp=parts[3].replace("[","").replace("]","").split("=");
                        if (tmp[0].equals("weight") || tmp[0].equals("w"))
                            edge.setWeight(Double.parseDouble(tmp[1]));
                    }
                    edges.add(edge);
                    System.out.println("adding "+ edge);
                   
                     if (!directed) {
                        Edge reverseEdge=new Edge(getNode(to),getNode(from));
                        reverseEdge.setWeight(edge.weight);
                        edges.add(reverseEdge);
                        System.out.println("adding "+ reverseEdge);
                    }
                }
            }
        }
    }

	private Node getNode(String from) {
		for (Node n : this.nodes)
			if(n.data.equals(from))
				return n;
	
		return null;
	}

	private boolean isNode(String from) {
	
			for (Node n : this.nodes)
			if(n.data.equals(from))
				return true;
		return false;
	}
    
	/*
	 * Reverse graph method to use it in the Strongly Connected 
	 * Method.
	 */
		static Graph reverseGraph(Graph graph) {
		Graph tempG = new Graph();
		for(Node u : graph.getNodes()){
			tempG.addNode(u);
		}
		for(Edge e: graph.getEdges()){
			Edge tempEdge = new Edge(e.b,e.a,e.weight);
			tempG.addEdge(tempEdge);
		}
        return tempG;
    }
    
    
 
}

