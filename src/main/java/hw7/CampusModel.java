package hw7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import hw4.Edge;
import hw4.Graph;
import hw4.Node;
import hw6.Pair;

public class CampusModel {
	/**
	 * @param g Graph to add to
	 * @param namesByID Map of Names as values and id's as keys
	 * @param coordinatesByID Map of pair of coordinates as values and id's as keys
	 * @param IdByNames Map of Names as keys and id's as values
	 * @throws IOException
	 * Creates the Nodes of the given RPI Data
	 */
	public static void parseNodes(Graph<Integer, Double> g, Map<Integer, String> namesByID, Map<Integer, Pair<Integer>> coordinatesByID, Map<String, Integer> IdByNames) 
			throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("data/RPI_map_data_Nodes.csv"));
        String line = null;

        while ((line = reader.readLine()) != null) {
        	String[] a = line.split(",");
        	Integer ID = Integer.parseInt(a[1]);
        	Integer xcoord = Integer.parseInt(a[2]);
        	Integer ycoord = Integer.parseInt(a[3]);
        	Pair<Integer> p = new Pair<Integer>(xcoord, ycoord);
        	g.addNode(new Node<Integer, Double>(ID));
        	namesByID.put(ID, a[0]);
        	
        	if(!a[0].equals("")) {
        		IdByNames.put(a[0], ID);
        	}
        	
        	coordinatesByID.put(ID, p);        	
        }
	}
	
	/**
	 * @param g Graph to store edges in
	 * @param namesByID Map of Names as values and id's as keys
	 * @param coordinatesByID Map of pair of coordinates as values and id's as keys
	 * @throws IOException 
	 * Creates the edges of the given RPI Data
	 */
	public static void parseEdges(Graph<Integer, Double> g, Map<Integer, String> namesByID, Map<Integer, Pair<Integer>> coordinatesByID) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data/RPI_map_data_Edges.csv"));
        String line = null;

        while ((line = reader.readLine()) != null) {
        	String[] a = line.split(",");
        	Integer parentID = Integer.parseInt(a[0]);
        	Integer childID = Integer.parseInt(a[1]);
        	
        	Double distance = Math.sqrt(Math.pow(coordinatesByID.get(childID).getKeyOne() - coordinatesByID.get(parentID).getKeyOne(), 2) +
        			Math.pow(coordinatesByID.get(childID).getKeyTwo() - coordinatesByID.get(parentID).getKeyTwo(), 2));
        	
        	g.addEdge(new Edge<Integer, Double>(distance, parentID, childID));
        	g.addEdge(new Edge<Integer, Double>(distance, childID, parentID));
        }
	}
}
