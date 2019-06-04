package hw4;

/**
 * 
 * Edge represents an mutable directed-edge in a Directed Graph. A directed edge connects two nodes, one ParentNode that the edge
 * points from and one ChildNode that the edge points two.
 * @param <T>
 * 
 */
public class Edge<T, S>{
	
	private S edgeLabel;
	private T ParentNode;
	private T ChildNode;
	
	//Abstraction Function:
	//edgeLabel is the label or length of the edge
	//ParentNode is the name of the node this edge comes out from
	//ChildNode is the name of the node that this edge points to
	//
	//Representation invariant for every Edge e:
	//ParentNode, ChildNode, and edgeLabel in e are not null
	
	/**
	 * 
	 * @param length The label of that edge
	 * @param parentNode The Node name that the edge will point from
	 * @param childNode The Node name that the edge will point to
	 * @modifies this.edgeLabel, this.ParentNode, this.ChildNode
	 * @effects Constructs a new Edge with the specified parameters of parentNode and childNode and label
	 * 
	 */
	public Edge(S label, T parentNode, T childNode)
	{
		this.edgeLabel = label;
		this.ParentNode = parentNode;
		this.ChildNode = childNode;
		this.checkRep();
	}
	
	/**
	 * @return The parentNode that this edge is pointing from(this.ParentNode)
	 */
	public T getParent()
	{
		return this.ParentNode;
	}
	
	/**
	 * @return The childNode that this edge is pointing to (this.ChildNode)
	 */
	public T getChild()
	{
		return this.ChildNode;
	}
	
	/**
	 * @return The edgeLabel of this (this.edgeLabel)
	 */
	public S getLabel()
	{
		return this.edgeLabel;
	}
	
	/**
	 * 
	 * @param e An Edge to compare with
	 * @requires e != null
	 * @return boolean true if this.ParentNode == e.getParent() && this.ChildNode == e.getChild()
	 * 
	 */
	 public boolean equals(Edge<T, S> e){
		 if(this == e){
			 return true;
		 }
		 if(this.ParentNode.equals(e.getParent()) && this.ChildNode.equals(e.getChild())){
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
		if(this.ParentNode == null || this.ChildNode == null || this.edgeLabel == null)
		{
			throw new RuntimeException("Representaion Variant is violated for Edge!!!");
		}
	}
	
}
