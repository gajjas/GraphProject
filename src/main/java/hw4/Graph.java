package hw4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Graph represents a mutable directed-graph. A graph is a collection of nodes and edges. 
 * @param <T, S>
 * 
 */
public class Graph<T, S>{
	
	private Map<T,Node<T,S>> nodes;
	private int edges;
	
	//Abstraction Function:
	//Map<String, Node> nodes are a list of all the nodes in the graph
	//edges are the number of edges in the graph
	//
	//Representation invariant for every Graph g:
	//There can never be any duplicate Nodes in nodes
	//edges are the number of edges in the node
	//
	
	/**
	 * @effects Constructs a new Graph with this.nodes.length = 0 or empty list
	 */
	public Graph()
	{
		this.edges = 0;
		this.nodes = new HashMap<T,Node<T, S>>();
	}
	
	/**
	 * @return int the number of edges in the graph or this.edges
	 */
	public int getNumOfEdges()
	{
		return this.edges;
	}
	
	/**
	 * @return int number of nodes currently in the graph or this.nodes.size()
	 */
	public int getNumOfNodes()
	{
		return this.nodes.size();
	}
	
	/**
	 * 
	 * @param the node n that is to be added
	 * 
	 * @requires n != null
	 * @modifies this.nodes
	 * @effects Adds Node n  to the respective place in the this.nodes if not in nodes
	 * 
	 */
	public void addNode(Node<T,S> n)
	{
		if(!nodes.containsKey(n.getName())) {
			nodes.put(n.getName(), n);
		}	
	}
	
	/**
	 * 
	 * @param name the name of the node that is to be added
	 * 
	 * @requires name != null
	 * @modifies this.nodes
	 * @effects Creates a Node n and adds it to the respective place in the this.nodes
	 * 
	 */
	public void addNode(T name)
	{
		Node<T, S> n = new Node<T, S>(name);
		this.addNode(n);
	}
	
	/**
	 * 
	 * @param e the edge that is to be added
	 * 
	 * @requires e != null
	 * @modifies edges of one node in this.nodes
	 * @effects Adds a Edge e to the edges ArrayList and places it in the correct spot if the node exists in this.nodes
	 * 			and the parent node has no duplicate edge already
	 * 
	 */
	public void addEdge(Edge<T, S> e)
	{
		if(this.nodes.containsKey(e.getParent()) && this.nodes.containsKey(e.getChild())) {
			int a = this.nodes.get(e.getParent()).NumOfEdges();
			this.nodes.get(e.getParent()).addEdge(e);
			int b = nodes.get(e.getParent()).NumOfEdges();
			if(a != b) {
				this.edges++;
			}
		}
		
	}
	
	/**
	 * 
	 * @param parentNode The parent node of the edge that is to be added
	 * @param childNode	The child node of the edge that is to be added
	 * @param edgeLabel The label for the edge that is to be added
	 * 
	 * @requires parentNode != null && childNode != null && edgeLabel != null
	 * @modifies edges of one node in this.nodes
	 * @effects Adds a new Edge to the edges ArrayList and places it in the correct spot if the node exists in this.nodes
	 * 			and the parent node has no duplicate edge already
	 * 
	 */
	public void addEdge(S edgeLabel ,T parentNode, T childNode)
	{
		Edge<T, S> e = new Edge<T, S>(edgeLabel , parentNode, childNode);
		this.addEdge(e);
	}
	
	/**
	 * @param name Name of Node to look for
	 * @return true if nod is in the graph, false otherwise
	 */
	public boolean NodeInGraph(T name) {
		if(this.nodes.containsKey(name)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @return ArrayList<String> of the nodes in this graph
	 */
	public ArrayList<Node<T, S>> getNodes()
	{
		ArrayList<Node<T, S>> a = new ArrayList<Node<T, S>>();
		for(T s:this.nodes.keySet()) {
			a.add(nodes.get(s));
		}
		return a;
	}
	
	/**
	 * @param name The name of the node to get the array from
	 * @return the ArrayList<Edges> for the given node
	 */
	public ArrayList<Edge<T, S>> getEdges(T name){
		ArrayList<Edge<T, S>> edg = this.nodes.get(name).getEdges();
		return edg;
	}
	
	/**
	 * @requires name != null
	 * @param name the name of the node
	 * @return null if there is no node in the graph, the Node if the node is found
	 */
	public Node<T, S> getNode(T name) {
		if(this.NodeInGraph(name)) {
			return this.nodes.get(name);
		}
		return null;	
	}   
	
	/**
	 * Checks that the representation invariant holds (if any).
	 */
	// Throws a RuntimeException if the rep invariant is violated.
	public void checkRep()
	{
		int count = 0;
		for(int i = 0; i < this.nodes.size();i++)
		{
			count = this.nodes.get(i).NumOfEdges() + count;
		}
		
		if(count != this.edges)
		{
			throw new RuntimeException("edges in Graph are not correct");
		}
		
		if(this.nodes.size() == 0 || this.nodes.size() == 1)
		{
			return;
		}
		
	}
	
}
