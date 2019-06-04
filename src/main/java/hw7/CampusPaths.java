package hw7;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import hw4.Edge;
import hw6.Pair;

public class CampusPaths{
	private static Campus m = new Campus();
	/**
	 * Lists the building in stored in campus
	 */
	private static void listBuildings() {
		TreeMap<String, Integer> names = m.getBuildings();
		for(String n : names.keySet()) {
			System.out.println(n +"," + names.get(n));
		}
	}
	
	/**
	 * @param building1 First building to start with 
	 * @param building2	Second Building to end with (Destination)
	 * List the directions or path to the Destination or building 2 from building 1
	 */
	private static void Dpaths(String building1, String building2){
		TreeMap<String, Integer> names = m.getBuildings();
		Set<Integer> IDS = m.IDSet();
		Map<Integer, String> id = m.getBuildingMap();
		
		try {
			if(names.keySet().contains(building1) && !building1.equals("")) {
				try {
					if(names.keySet().contains(building2) && !building2.equals("")) {
						if(building1.equals(building2))
						{
							System.out.print("Path from " + building1 + " to " + building2 + ":\n");
							System.out.print("Total distance: 0.000 pixel units.\n");
							return;
						}
						
						ArrayList<Edge<Integer, Double>> p = m.findPath(names.get(building1),names.get(building2));
						printPath(p, building1, building2);
					}
					else if(IDS.contains(Integer.parseInt(building2))){
						if(id.get(Integer.parseInt(building2)).equals("")) {
							System.out.println("Unknown building: [" + building2 + "]");
						}
						else {
							if(names.get(building1) == Integer.parseInt(building2)){
								System.out.print("Path from " + building1 + " to " + id.get(Integer.parseInt(building2)) + ":\n");
								System.out.print("Total distance: 0.000 pixel units.\n");
								return;
							}
							
							ArrayList<Edge<Integer, Double>> p = m.findPath(names.get(building1), Integer.parseInt(building2));
							printPath(p, building1, id.get(Integer.parseInt(building2)));
						}
					}
					else {
						System.out.println("Unknown building: [" + building2 + "]");
						return;
					}
				}
				catch(NumberFormatException e) {
					System.out.println("Unknown building: [" + building2 + "]");
					return;
				}
			}
			else if(IDS.contains(Integer.parseInt(building1)) && !id.get(Integer.parseInt(building1)).equals("")){
				try {
					if(names.keySet().contains(building2) && !building2.equals("")) {
						if(Integer.parseInt(building1) == names.get(building2)){
							System.out.print("Path from " + id.get(Integer.parseInt(building1)) + " to " + building2 + ":\n");
							System.out.print("Total distance: 0.000 pixel units.\n");
							return;
						}
						ArrayList<Edge<Integer, Double>> p = m.findPath(Integer.parseInt(building1),names.get(building2));
						printPath(p, id.get(Integer.parseInt(building1)), building2);
					}
					else if(IDS.contains(Integer.parseInt(building2))){
						if(id.get(Integer.parseInt(building2)).equals("")) {
							System.out.println("Unknown building: [" + building2 + "]");
						}
						else {
							if(Integer.parseInt(building1) == Integer.parseInt(building2)){
								System.out.print("Path from " + id.get(Integer.parseInt(building1)) + " to " + id.get(Integer.parseInt(building2)) + ":\n");
								System.out.print("Total distance: 0.000 pixel units.\n");
								return;
							}
							ArrayList<Edge<Integer, Double>> p = m.findPath(Integer.parseInt(building1),Integer.parseInt(building2));
							printPath(p, id.get(Integer.parseInt(building1)), id.get(Integer.parseInt(building2)));
						}
					}
					else {
						System.out.println("Unknown building: [" + building2 + "]");
						return;
					}
				}
				catch(NumberFormatException e) {
					System.out.println("Unknown building: [" + building2 + "]");
					return;
				}
			}
			else {
				System.out.println("Unknown building: [" + building1 + "]");
				try {
					if(names.keySet().contains(building2) && !building2.equals("")) {
						return;
					}
					else if(IDS.contains(Integer.parseInt(building2)) && !id.get(Integer.parseInt(building1)).equals("")){
						return;
					}
					else {
						if(building1.equals(id.get(Integer.parseInt(building2)))) {
							return;
						}
						if(building1.equals(building2)) {
							return;
						}
						System.out.println("Unknown building: [" + building2 + "]");
						return;
					}
				}
				catch(NumberFormatException e) {
					if(building1.equals(building2)) {
						return;
					}
					if(id.containsKey(Integer.parseInt(building1))){
						if(id.get(Integer.parseInt(building1)).equals(building2)) {
							return;
						}
					}
					System.out.println("Unknown building: [" + building2 + "]");
					return;
				}
			}
		} 
		catch(NumberFormatException e) {
			System.out.println("Unknown building: [" + building1 + "]");
			try {
				if(names.keySet().contains(building2) && !building2.equals("")) {
					return;
				}
				else if(IDS.contains(Integer.parseInt(building2)) && !id.get(Integer.parseInt(building1)).equals("")){
					return;
				}
				else {
					if(building1.equals(id.get(Integer.parseInt(building2)))) {
						return;
					}
					if(building1.equals(building2)) {
						return;
					}
					System.out.println("Unknown building: [" + building2 + "]");
					return;
				}
			}
			catch(NumberFormatException b) {
				if(building1.equals(building2)) {
					return;
				}
				System.out.println("Unknown building: [" + building2 + "]");
				return;
			}
		}	
	}
	
	/**
	 * @param p the path to print
	 * Prints the path
	 */
	private static void printPath(ArrayList<Edge<Integer, Double>> p, String one, String two) {
		
		if(p.size() == 0){
			System.out.println("There is no path from "+ one +" to "+ two +".");
			return;
		}
		System.out.println("Path from "+ one +" to " + two + ":");
		Map<Integer, String> i = m.getBuildingMap();
		Map<Integer, Pair<Integer>> an = m.coordinateMap();
		double sum = 0.0;
		
		for(Edge<Integer, Double> d : p) {
			sum += d.getLabel();
			Pair<Integer> CoordOne = an.get(d.getParent());
			Pair<Integer> CoordTwo = an.get(d.getChild());
			if(i.get(d.getChild()).contentEquals("")) {
				System.out.println("\tWalk " + getDirection(CoordOne, CoordTwo) +" to (Intersection " + d.getChild() + ")");
			}
			else {
				System.out.println("\tWalk " + getDirection(CoordOne, CoordTwo) +" to (" + i.get(d.getChild()) + ")");
			}
		}
		System.out.println("Total distance: " + String.format("%.3f", sum) + " pixel units.");
	}
	
	/**
	 * @param a Pair<Integer> that holds the start coordinate
	 * @param b Pair<Integer> that holds the destination coordinate
	 * @return The String direction of the 8 main directions
	 */
	public static String getDirection(Pair<Integer> a, Pair<Integer> b) {
		double deg = Math.toDegrees(Math.atan2(b.getKeyOne() - a.getKeyOne(),  b.getKeyTwo() - a.getKeyTwo())) - 90;

		if(deg < 0)
		{
			deg = 360 + deg;
		}
		
	    if (deg >= 337.5 || (deg < 22.5 && deg >= 0))
	        return "East";
	    else if (deg < 337.5 && deg >= 292.5)
	        return "SouthEast";
	    else if (deg < 292.5 && deg >= 247.5)
	        return "South";
	    else if (deg < 247.5 && deg >= 202.5)
	        return "SouthWest";
	    else if (deg < 202.5 && deg >= 157.5)
	        return "West";
	    else if (deg < 157.5 && deg >= 112.5)
	        return "NorthWest";
	    else if (deg < 112.5 && deg >= 67.5)
	        return "North";
	    else
	        return "NorthEast";
	}
	public static void main(String[] args){
		
		m.createNewGraph();
		
		Scanner sc = new Scanner(System.in);
		String prompt = sc.nextLine();
		while(!prompt.equals("q")) {
			
			if(prompt.equals("b")) {
				listBuildings();
			}
			else if(prompt.equals("r")) {
				System.out.print("First building id/name, followed by Enter: ");
				String building1 = sc.nextLine();
				
				System.out.print("Second building id/name, followed by Enter: ");
				String building2 = sc.nextLine();
				
				Dpaths(building1, building2);
			}
			else if(prompt.equals("m")) {
				System.out.println("b :\nlists all buildings (only buildings) in the "
						+ "form name,id in lexicographic(alphabetical) order of name.");
				System.out.println("r :\nshortest route between two given building or intersections");
				System.out.println("q :\nquits the program");
				System.out.println("m :\nprints menu of functionality");
			}
			else if(prompt.equals("q")) {
				return;
			}
			else {
				System.out.println("Unknown option");
			}
			
			prompt = sc.nextLine();
		}
		return;
	}
}