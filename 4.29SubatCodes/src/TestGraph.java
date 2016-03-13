/**
 * Mert CACINA
 * Project-1
 */


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
public class TestGraph {

	private static int time;
	static String printSome = "";

	public static void main(String[] args) throws Exception {
		

		
		Graph g=new Graph();
		
		
		g.setDirected();
		
		/*
		Node<Integer> n1=new Node<Integer>(1);
		Node<Integer> n2=new Node<Integer>(2);
		Node<Integer> n3=new Node<Integer>(3);
		Node<Integer> n4=new Node<Integer>(4);
		Node<Integer> n5=new Node<Integer>(5);
		Node<Integer> n6=new Node<Integer>(6);
		Node<Integer> n7=new Node<Integer>(7);
		
		
		Edge e1=new Edge(n1,n2,1); 
		Edge e2=new Edge(n1,n3,1);
		Edge e3=new Edge(n3,n2,1); 
		Edge e4=new Edge(n3,n5,1);
		Edge e5=new Edge(n5,n4,1); 
		Edge e6=new Edge(n5,n6,1);
		Edge e7=new Edge(n7,n6,1); 
		
		
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		g.addNode(n5);
		g.addNode(n6);
		g.addNode(n7);
		
		
		
		g.addEdge(e1);
		g.addEdge(e2);
		g.addEdge(e3);
		g.addEdge(e4);
		g.addEdge(e5);
		g.addEdge(e6);
		g.addEdge(e7);
		*/
		
		 g.loadDot("data/24subat.dot");
		 
		
		
		for(Node n: g.getNodes())
			System.out.println(n);
		
		for(Edge e: g.getEdges())
			System.out.println(e);
		
	
		for(int i=0;i<g.getNodes().size();i++)
			System.out.println(i+":"+g.getNodeAt(i));
		
	
		//BFS(g,g.getNodeAt(0));
		
		DFS(g);
		
		//System.out.println("************");
		
		
		System.out.println("data\tpred\tdisc\tfinish\tvisited");
		for(Node<Integer> n: g.getNodes()){
			System.out.println(n.data+"\t"+n.predecessor+"\t"+n.discovery+"\t"
		+n.finishing+"\t"+n.visited);
		}
	
		//System.out.println("pat from/to " +n1+"/"+ n6);
		path(g,g.getNodeAt(0) ,g.getNodeAt(4));
		System.out.println("\n"+ printSome); // Added this to see the graph with parentheses and more clearly
		
	}

	
	
	public static void produceMatrix(Graph g){
	double d[][]=g.getAdjacencyMatrix();
		
		
		for(int i =0 ;i<d.length;i++)
			System.out.print("\t"+g.nodes.get(i));
		System.out.println();
		int r=0;
		for(double arr[]: d){
			System.out.print(g.nodes.get(r++) +"\t");
			for(double x : arr){
				System.out.print(x+"\t");
			}
			System.out.println();
		}
		
	}
	
	
	
	private static void path(Graph g, Node<Integer> n1,  Node<Integer> n2) {
		
		
		if(n1.equals(n2))
			System.out.print(n1+"");
		else
		if(n2.predecessor==null)
			System.err.println("no path");
		else{
			path(g,n1,n2.predecessor);
			System.out.print("->"+n2);
		}
				
	}

	
	private static void DFS(Graph g){
		for(Node u : g.getNodes()){
			u.unvisit();
			u.predecessor=null;
		}
		time =0;
		for (Node u : g.getNodes())
			if(!u.isVisited())
				DFSVisit(g,u);
	}
	
	
	/*
	 * I am adding some extra codes to write 
	 * the graph with parentheses.
	 * 
	 * Mert
	 */
	
	private static void DFSVisit(Graph g, Node u) {
		time ++;
		u.discovery=time;
		u.visit();
		printSome = printSome + "(" + u +" ";
		for(Node v: g.getNeighbors(u)){
			if(!v.isVisited()){
				v.predecessor=u;
				DFSVisit(g, v);
			}
		}
		printSome = printSome + " " + u + ")";
		time++;
		u.finishing=time;
	}



	private static void BFS(Graph g, Node<Integer> s) {

		for(Node<Integer> n: g.getNodes())
			if(!n.equals(s)){
				n.unvisit();
				n.distance=Double.POSITIVE_INFINITY;
				n.predecessor=null;
			}
		
		s.distance=0;
		s.predecessor=null;
		
		Queue<Node> queue= new LinkedList<Node>();
		
		s.visit();
		queue.add(s);
		
		
		
		while(!queue.isEmpty()){
			Node u=queue.poll();
			
			//System.out.println("Node "+u+" visited");
			
			for(Node v : g.getNeighbors(u)){
				
				if(!v.isVisited()){
					v.visit();
					v.distance=u.distance+1;
					v.predecessor=u;
					queue.add(v);
				}
				
			}			
			u.visit();
			
		}
		
		
		
	}
	
	
	
	
}
