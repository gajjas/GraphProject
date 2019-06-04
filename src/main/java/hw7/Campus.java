package hw7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import hw4.Edge;
import hw4.Graph;
import hw6.Pair;
import hw6.MarvelPaths2;

//Holds the map of the Campus including Building and Intersections as Nodes
public class Campus {
	private Graph<Integer, Double> g;
	private Map<Integer, String> namesByID;
	private Map<Integer, Pair<Integer>> coordinateByID;
	private Map<String, Integer> IdByNames;
	
	/**
	 * @param filename given to us by the controller
	 * Constructs the Campus object and creates the Map of RPI
	 */
	public void createNewGraph() {
		try{
			this.g = new Graph<Integer, Double>();
			this.namesByID = new HashMap<Integer, String>();
			this.coordinateByID = new HashMap<Integer, Pair<Integer>>();
			this.IdByNames = new HashMap<String, Integer>();
			
			CampusModel.parseNodes(this.g, this.namesByID, this.coordinateByID, this.IdByNames);
			CampusModel.parseEdges(this.g, this.namesByID, this.coordinateByID);
		}
		catch(IOException e){
    		e.printStackTrace();
    	}
	}

	/**
	 * @return sorted Map of TreeMap<String, Integer> of the buildings 
	 */
	public TreeMap<String, Integer> getBuildings() {
		TreeMap<String, Integer> p = new TreeMap<String, Integer>();
		p.putAll(IdByNames);
		return p;
	}
	
	/**
	 * @return the Map<Integer, String> namesByID
	 */
	public Map<Integer, String> getBuildingMap() {
		return this.namesByID;
	}
	
	/**
	 * @return Map<Integer, Pair<Integer>> coordinateByID
	 */
	public Map<Integer, Pair<Integer>> coordinateMap(){
		return this.coordinateByID;
	}

	/**
	 * @return returns a set of building names in the RPI map
	 */
	public Set<String> NameSet() {
		return IdByNames.keySet();
	}
	
	/**
	 * @return a set of IDs in the RPI map
	 */
	public Set<Integer> IDSet() {
		return namesByID.keySet();
	}

	/**
	 * @param integer first ID start node
	 * @param integer2 second ID destination Node
	 * @return a path in the form ArrayList<Integer, Double>
	 */
	public ArrayList<Edge<Integer, Double>> findPath(Integer integer, Integer integer2) {
		return MarvelPaths2.Dijkstra(integer, integer2, this.g);
	}
}
