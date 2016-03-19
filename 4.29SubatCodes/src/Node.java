/**
 * Mert CACINA
 * Project-1
 */


public class Node<T> implements Comparable<Node<T>> {
     
    T data;
    boolean visited;
    public Integer index = null;
    public double distance = Double.POSITIVE_INFINITY;
    public Node<T> predecessor = null;
    public int discovery, finishing; 
    
    
    public Node(T data) {
        this.data = data;
    }
     
    public Node() {
         
    }
     
    public boolean isVisited() {
        return visited;
    }
     
    public void visit() {
        visited = true;
    }
     
    public void unvisit() {
        visited = false;
    }
     
    public int compareTo(Node<T> ob) {
        String tempA = this.toString();
        String tempB = ob.toString();
         
        return tempA.compareTo(tempB);
    }
     
    public String toString() {
        return data.toString();
    }
    
    
     
}
