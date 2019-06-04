package hw6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import hw4.Edge;
import hw4.Graph;
import hw4.Node;
import hw5.MarvelParser;

public class MarvelPaths2{
    private Graph<String, Double> g;
    private Map<Pair<String>, Double> m;

    public void createNewGraph(String filename) {
        try{
            this.g = new Graph<String, Double>();
            this.m = new HashMap<Pair<String>, Double>();
            ArrayList<Edge<String, String>> edges = new ArrayList<Edge<String, String>>();
            Map<String, Set<String>> charsInBooks = new HashMap<String, Set<String>>();
            Set<String> chars = new HashSet<String>();
            MarvelParser.readData(filename,charsInBooks,chars,edges);

            for(Edge<String, String> e :edges) {
                Pair<String> p = new Pair<String>(e.getParent(), e.getChild());
                if(this.m.keySet().contains(p)) {
                    double a = this.m.get(p);
                    a += 1;
                    m.put(p, a);
                }
                else {
                    m.put(p, (double)1);
                }
            }

            for(String s : chars) {
                g.addNode(s);
            }

            for(Pair<String> i : this.m.keySet()) {
                Edge<String, Double> e = new Edge<String, Double>(m.get(i), i.getKeyOne(), i.getKeyTwo());
                g.addEdge(e);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public String findPath(String CHAR1, String CHAR2) {
        String answer = "";

        //Checking if nodes are in the graph
        boolean Node1InGraph = this.g.NodeInGraph(CHAR1);
        boolean Node2InGraph = this.g.NodeInGraph(CHAR2);

        if(Node1InGraph == false && Node2InGraph == false) {
            if(CHAR1.equals(CHAR2)) {
                answer = "unknown character " + CHAR1 + "\n";
            }
            else {
                answer = "unknown character " + CHAR1 + "\nunknown character " + CHAR2 + "\n";
            }
            return answer;
        }
        else if(Node1InGraph == false && Node2InGraph == true) {
            answer = "unknown character " + CHAR1 + "\n";
            return answer;
        }
        else if(Node1InGraph == true && Node2InGraph == false) {
            answer = "unknown character " + CHAR2 + "\n";
            return answer;
        }

        if(CHAR1.equals(CHAR2)) {
            answer = "path from " + CHAR1 + " to " + CHAR2 + ":\n";
            answer = answer + "total cost: 0.000\n";
            return answer;
        }
        
        ArrayList<Edge<String, Double>> minPath = Dijkstra(CHAR1, CHAR2, this.g);
        answer = "path from " + CHAR1 + " to " + CHAR2 + ":\n";
        double sum = (double)1;
        sum--;
        for(Edge<String, Double> edge: minPath) {
            if(edge.getLabel() != 0){
                sum += (double) (1/edge.getLabel());
                answer = answer + edge.getParent() + " to " + edge.getChild() + String.format(" with weight %.3f\n", (double) (1/(edge.getLabel())));
            }
            else {
                answer = answer + edge.getParent() + " to " + edge.getChild() + " with weight 0.000\n";
            }
        }
        answer = answer + String.format("total cost: %.3f\n", sum);
        return answer;
    }
    
    /**
     * @param ID1 start node
     * @param ID2 destination node
     * @param g graph used to find the path
     * @return Shortest path as an ArrayList<Edge<T, Double>>
     */
    public static <T> ArrayList<Edge<T, Double>> Dijkstra(T ID1, T ID2, Graph<T, Double> g){
        //Implement Dijkstra's
        Node<T, Double> start = g.getNode(ID1);
        Node<T, Double> dest = g.getNode(ID2);

        Queue<ArrayList<Edge<T, Double>>> active = new PriorityQueue<ArrayList<Edge<T, Double>>>(new Comparator<ArrayList<Edge<T, Double>>>(){
            @Override
            public int compare(ArrayList<Edge<T, Double>> arg0, ArrayList<Edge<T, Double>> arg1) {
                double sum1 = (double) 1;
                double sum2 = (double) 1;

                sum1--;
                sum2--;
                for(Edge<T, Double> e : arg0) {
                    sum1 += e.getLabel();
                }

                for(Edge<T, Double> e : arg1) {
                    sum2 += e.getLabel();
                }

                if(sum1 < sum2) {
                    return -1;
                }
                else if(sum2 < sum1){
                    return 1;
                }
                return 0;
            }

        });

        Set<T> finished = new HashSet<T>();
        Edge<T, Double> e = new Edge<T, Double>(0.0, start.getName(), start.getName());
        ArrayList<Edge<T, Double>> path = new ArrayList<Edge<T, Double>>();
        path.add(e);
        active.add(path);

        while(!(active.isEmpty())) {
            ArrayList<Edge<T, Double>> minPath = active.poll();
            Node<T, Double> minDest = g.getNode(minPath.get(minPath.size()-1).getChild());

            if(minDest.getName().equals(dest.getName())) {
            	return new ArrayList<Edge<T, Double>>(minPath.subList(1, minPath.size()));
            }

            if(finished.contains(minDest.getName())){
                continue;
            }

            for(Edge<T, Double> E : minDest.getEdges()) {
                if(!(finished.contains(E.getChild()))) {
                    ArrayList<Edge<T, Double>> newPath = new ArrayList<Edge<T, Double>>(minPath);
                    newPath.add(E);
                    active.add(newPath);
                }
            }
            finished.add(minDest.getName());
        }
        
        return new ArrayList<Edge<T, Double>>();
    }
}
