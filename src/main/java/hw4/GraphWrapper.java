package hw4;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class GraphWrapper{
	private Graph<String, String> g;
	
	/**
	 * @effects Constructs a new GraphWrapper
	 */
	public GraphWrapper()
	{
		this.g = new Graph<String, String>();
	}
	
	/**
	 * 
	 * @param nodeData the name of the node that is to be added
	 * 
	 * @requires nodeData != null
	 * @modifies this.g.nodes
	 * @effects Creates a Node n and adds it to the respective place in the this.g.nodes
	 * 
	 */
	public void addNode(String nodeData)
	{
		this.g.addNode(nodeData);
	}
	
	/**
	 * 
	 * @param parentNode
	 * @param childNode
	 * @param edgeLabel
	 * 
	 * @requires parentNode != null && childNode != null && edgeLabel != null
	 * @modifies edges of one node in this.g.nodes
	 * @effects Adds a new Edge to the edges ArrayList in this.g and places it in the correct spot if the node exists in this.nodes
	 * 			and the parent node has no duplicate edge already
	 */
	public void addEdge(String parentNode, String childNode, String edgeLabel)
	{
		this.g.addEdge(edgeLabel, parentNode, childNode);
	}
	
	/**
	 * @return Iterator<T> of all the nodes' names in g in lexicographical order
	 */
	public Iterator<String> listNodes()
	{
		ArrayList<Node<String, String>> n_list = this.g.getNodes();

		ArrayList<String> s_list = new ArrayList<String>();
		for(int i = 0; i < n_list.size(); i++)
		{
			s_list.add(n_list.get(i).getName());
		}
		
		Collections.sort(s_list, new Comparator<String>(){

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
			
		});
		
		Iterator<String> itr = s_list.iterator();
		return itr;
	}
	
	/**
	 * 
	 * @param parentNode
	 * 
	 * @requires parentNode != null
	 * @return Iterator<Edge<T,S>> of all the edges' edgeLabels in the given parentNode if in g in lexicographical order
	 */
	public Iterator<String> listChildren(String parentNode)
	{
		ArrayList<Edge<String, String>> E = this.g.getEdges(parentNode);
				
		Collections.sort(E, new Comparator<Edge<String, String>>() {

			@Override
			public int compare(Edge<String, String> o1, Edge<String, String> o2) {
				if(o1.getChild().compareTo(o2.getChild()) == 0){
					return o1.getLabel().compareTo(o2.getLabel());
				}
				else {
					return o1.getChild().compareTo(o2.getChild());
				}
			}
			
		});
		ArrayList<String> a = new ArrayList<String>();
		for(Edge<String, String> e : E) {
			a.add(e.getChild() + "(" + e.getLabel() + ")");
		}
		Iterator<String> itr = a.iterator();
		return itr;
	}
}

