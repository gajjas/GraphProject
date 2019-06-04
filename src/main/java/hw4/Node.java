package hw4;

import java.util.ArrayList;

/**
 * 
 * Node represents an mutable Node in a Directed Graph. A node is a vertex in a graph and can have infinite 
 * edges pointing to and away from it.
 * @param <T>
 * @param <N>
 * 
 */
public class Node<T, S> {
	
	T name;
	ArrayList<Edge<T, S>> edges;
	
	//Abstraction Function
	//name is the name that is given to the Node
	//ArrayList<Edges> edges are the edges that go out of the nodes in the graph if any
	//
	//Representation invariant for every Node n:
	//All the edges in e are not repeating where if two edges have the same parent node and child node as each other
	//there would only be one edge that resembles both same edges
	//edges is sorted lexicographically by edgeLabel
	//name != null
	//
	
	/**
	 * @param Name
	 * @effects Constructs a new Node with this.edges.length = 0
	 */
	public Node(T Name)
	{
		this.name = Name;
		this.edges = new ArrayList<Edge<T, S>>();
	}
	
	/**
	 * @return The Number of edges going out of this node or this.edges.length
	 */
	public int NumOfEdges()
	{
		return edges.size();
	}
	
	/**
	 * 
	 * @param e the edge that is to be added
	 * 
	 * @requires e != null
	 * @modifies this.edges
	 * @effects Adds a new Edge to the edges ArrayList and places it in the correct spot if not already in it
	 * 			If node with the same name is found, does not add edge, no duplicate edges
	 * 
	 */
	public void addEdge(Edge<T, S> e)
	{
		if(this.NumOfEdges() == 0)
		{
			this.edges.add(e);
		}
		else
		{
			for(int i = 0; i < this.edges.size(); i++)
			{
				if (this.edges.get(i).getParent().equals(e.getParent()) && this.edges.get(i).getChild().equals(e.getChild()) && this.edges.get(i).getLabel().equals(e.getLabel())) 
				{
					return;
				}
			}
			this.edges.add(e);
		}
	}
	
	/**
	 * 
	 * @param parentNode The parent node of the edge that is to be added
	 * @param childNode	The child node of the edge that is to be added
	 * @param edgeLabel The label for the edge that is to be added
	 * 
	 * @requires parentNode != null && childNode != null && edgeLabel != null
	 * @modifies this.edges
	 * @effects Adds a new Edge to the edges ArrayList and places it in the correct spot if not already in it
	 * 			If node with the same name is found, does not add edge, no duplicate edges
	 * 
	 */
	public void addEdge(S edgeLabel ,T parentNode, T childNode)
	{
		Edge<T,S> e = new Edge<T,S>(edgeLabel, parentNode, childNode);
		this.addEdge(e);
	}
	
	/**
	 * @return The name of this node (this.name)
	 */
	public T getName()
	{
		return this.name;
	}
	
	/**
	 * @return the list of edges coming off of the node or this.edges
	 */
	public ArrayList<Edge<T, S>> getEdges()
	{
		return new ArrayList<Edge<T, S>>(this.edges);
	}
	
	/**
	 * @param n A node object
	 * @return true if the names of the nodes are equal and false otherwise
	 * 		!!! THIS DOES NOT CHECK NODE DATA FOR EDGES !!!
	 */
	public boolean equals(Node<T, S> n) {
		if(this == n){
			return true;
		}
		if(n.getName() == this.name){
			return true;
		}
		return false;
		
	}
	
	/**
	 * Checks that the representation invariant holds (if any).
	 */
	// Throws a RuntimeException if the rep invariant is violated.
	public void checkRep()
	{
		if(this.name == null)
		{
			throw new RuntimeException("Name of node is null");
		}
		
		if(this.edges.size() == 0 || this.edges.size() == 1)
		{
			return;
		}
		return;
	}
	
}

