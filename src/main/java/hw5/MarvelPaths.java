package hw5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import hw4.Graph;
import hw4.Node;
import hw4.Edge;

/**
 * 
 * MarvelPaths represents a wrapper for Graph that is immutable. This represents a graph given by data in a csv file.
 *
 */
public class MarvelPaths{
	private Graph<String,String> g;
	
	//Graph g is the graph that the MarvelPath holds and adds its data to
	
	/**
	 * Constructor for MarvelPaths
	 * Call the graph constructor for g
	 */
	public MarvelPaths(){
		this.g = new Graph<String, String>();
	}
	
	/**
	 * @requires graph != null
	 * @param graph Graph given to the class to set this.g = graph
	 * Overloaded Constructor for MarvelPaths class set this.g to the graph given
	 */
	public MarvelPaths(Graph<String, String> graph) {
		this.g = graph;
	}
	
	/**
	 * @requires file != null
	 * @param file - the csv file to interpret
	 * @modifies this.g
	 * @effects	adds all the nodes and edges that is interpreted from the given csv file 
	 */
	public void createNewGraph(String file)
	{
		try{
			ArrayList<Edge<String, String>> edges = new ArrayList<Edge<String, String>>();
    		Map<String, Set<String>> charsInBooks = new HashMap<String, Set<String>>();
    		Set<String> chars = new HashSet<String>();
    		MarvelParser.readData(file,charsInBooks,chars,edges);
    		
    		Iterator<String> it = chars.iterator();
    		
    		while(it.hasNext()) {
    			g.addNode(it.next());
    		}
    		
    		for(Edge<String, String> i : edges) {
    			g.addEdge(i);
    		}
    	} 
		catch (IOException e){
    		e.printStackTrace();
    	}
	}
	
	/**
	 * @requires node1 != null, node2 != null
	 * @param node1 the starting node
	 * @param node2 the nodes to find
	 * @return String of the finished path respective to whether the node was found or not
	 */
	public String findPath(String node1, String node2) {
		String answer = "";
		
		//Checking if nodes are in the graph
		boolean Node1InGraph = g.NodeInGraph(node1);
		boolean Node2InGraph = g.NodeInGraph(node2);
		
		if(Node1InGraph == false && Node2InGraph == false) {
			if(node1.equals(node2)) {
				answer = "unknown character " + node1 + "\n";
			}
			else {
				answer = "unknown character " + node1 + "\nunknown character " + node2 + "\n";
			}
			return answer;
		}
		else if(Node1InGraph == false && Node2InGraph == true) {
			answer = "unknown character " + node1 + "\n";
			return answer;
		}
		else if(Node1InGraph == true && Node2InGraph == false) {
			answer = "unknown character " + node2 + "\n";
			return answer;
		}
		
		if(node1.equals(node2)) {
			answer = "path from " + node1 + " to " + node2 + ":\n";
			return answer;
		}
		
		//Implement BFS
		Map<Node<String, String>, ArrayList<Edge<String, String>>> M = new HashMap<Node<String, String>, ArrayList<Edge<String, String>>>();
		Queue<Node<String, String>> Q = new LinkedList<Node<String, String>>();
		ArrayList<Edge<String, String>> path = new ArrayList<Edge<String, String>>();
		
		Node<String, String> n = g.getNode(node1);
		Q.add(n);
		M.put(n, new ArrayList<Edge<String, String>>());
		while(Q.size() != 0) {
			n = Q.remove();
			if(n.getName().equals(node2)) {
				path = M.get(n);
				answer = "path from "+ node1 +" to " +node2+":\n";
				for(Edge<String, String> e: path) {
					answer = answer + e.getParent() + " to " + e.getChild() + " via " + e.getLabel() + "\n";
				}
				return answer;
			}
			ArrayList<Edge<String, String>> ed = n.getEdges();
			Collections.sort(ed, new Comparator<Edge<String, String>>() {
				@Override public int compare(Edge<String, String> e1, Edge<String, String> e2) {
					int val =e1.getChild().compareTo(e2.getChild()); 
					if(val == 0) { 
						return e1.getLabel().compareTo(e2.getLabel()); 
					} 
					else { 
						return val; 
					} 
				}
			});
			 
			
			for(Edge<String, String> e: ed) {
				Node<String, String> m = g.getNode(e.getChild());
				if(!M.containsKey(m)) {
					ArrayList<Edge<String, String>> p = M.get(n);
					ArrayList<Edge<String, String>> p1 = new ArrayList<Edge<String, String>>(p);
					p1.add(e);
					M.put(m, p1);
					Q.add(m);	
				}
			}
		}
		
		answer = "path from " + node1 + " to "  + node2 + ":\nno path found\n";
		return answer;
	}
}