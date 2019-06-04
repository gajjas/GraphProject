package hw4;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;
import org.junit.BeforeClass;

public final class GraphWrapperTest{
	
	//Edge Class Testing
	@Test
	public void testEdgeConstructor()
	{
		new Edge<String, String>("Aasdf", "aasdf", "asdfsdaf" );
		new Edge<String, String>("asdf", "aasdf", "asdfsdaf" );
		String a  = new String("asdfasafw");
		new Edge<String, String>(a, a, a);
	}
	
	@Test
	public void testgetChild()
	{
		Edge<String, String> e = new Edge<String, String>("Aasdf", "aasdf", "asdfsdaf" );
		assertEquals(e.getChild(), "asdfsdaf");
		String a  = new String("asdfasafw");
		e = new Edge<String, String>(a, a, a);
		assertEquals(e.getChild(),a);
	}
	
	@Test
	public void testgetParent()
	{
		Edge<String, String> e = new Edge<String, String>("Aasdf", "aasdf", "asdfsdaf" );
		assertEquals(e.getParent(),"aasdf");
		String a  = new String("asdfasafw");
		e = new Edge<String, String>(a, a, a);
		assertEquals(e.getParent(), "asdfasafw");
	}
	
	@Test
	public void testgetLabel()
	{
		Edge<String, String> e = new Edge<String, String>("Aasdf", "aasdf", "asdfsdaf" );
		assertEquals(e.getLabel(),"Aasdf");
		String a  = new String("asdfasafw");
		e = new Edge<String, String>(a, a, a);
		assertEquals(e.getLabel(), a);
	}
	
	@Test
	public void testequals()
	{
		Edge<String, String> e = new Edge<String, String>("sadf", "asdf", "asdf");
		Edge<String, String> a = new Edge<String, String>("sadf", "asdf", "asdf");
		Edge<String, String> b = new Edge<String, String>("asdf", "asdfss", "asdf");
		Edge<String, String> c = new Edge<String, String>("sadf", "asdfd", "asdf");
		Edge<String, String> d = new Edge<String, String>("sadf", "asdf", "234ds");
		
		assertEquals(e.equals(a), true);
		assertEquals(e.equals(b), false);
		assertEquals(e.equals(c), false);
		assertEquals(e.equals(d), false);
	}
	
	
	
	//Node Class Testing
	@Test
	public void testNodeConstructor() {
		new Node<String, String>("sdf");
		String s = new String("asdfsadf");
		new Node<String, String>(s);
	}
	
	@Test
	public void testAddEdgeOneArg()
	{
		Edge<String, String> e = new Edge<String, String>("asdf", "asdf", "sdfdsa");
		Node<String, String> n = new Node<String, String>("people");
		n.addEdge(e);
		assertEquals(n.NumOfEdges(), 1);
		ArrayList<Edge<String, String>> list = n.getEdges();
		assertEquals(list.get(0).equals(e), true);
		
		e = new Edge<String, String>("asdp", "asasdfdf", "sdfdasdfsa");
		n.addEdge(e);
		assertEquals(n.NumOfEdges(), 2);
		list = n.getEdges();
		assertEquals(list.get(1).equals(e), true);	
		
		e = new Edge<String, String>("asdasdfp", "asasdfdf", "asasdfdf");
		n.addEdge(e);
		assertEquals(n.NumOfEdges(), 3);
	}
	
	@Test
	public void testaddEdgeMultArgs() {
		Edge<String, String> e = new Edge<String, String>("asdf", "asdf", "sdfdsa");
		Node<String, String> n = new Node<String, String>("sdf");
		n.addEdge("asdf", "asdf", "sdfdsa");
		assertEquals(n.NumOfEdges(), 1);
		ArrayList<Edge<String, String>> list = n.getEdges();
		assertEquals(list.get(0).equals(e), true);
		
		e = new Edge<String, String>("asdp", "asasdfdf", "sdfdasdfsa");
		n.addEdge("asdp", "asasdfdf", "sdfdasdfsa");
		assertEquals(n.NumOfEdges(), 2);
		list = n.getEdges();
		assertEquals(list.get(1).equals(e), true);
	}
	
	@Test
	public void testgetName() {
		Node<String, String> a = new Node<String, String>("sdf");
		assertEquals(a.getName().equals("sdf"), true);
		String s = new String("asdfsadf");
		Node<String, String> b = new Node<String, String>(s);
		assertEquals(b.getName().equals(s), true);
	}
	
	@Test
	public void testgetEdges() {
		Node<String, String> a = new Node<String, String>("sdf");
		ArrayList<Edge<String, String>> list = a.getEdges();
		assertEquals(list.size(), 0);
		
		Edge<String, String> e = new Edge<String, String>("asdp", "sdf", "sdfdasdfsa"); 
		a.addEdge(e);
		list = a.getEdges();
		assertEquals(list.size(), 1);
		assertEquals(list.get(0).equals(e), true);
		
		Edge<String, String> b = new Edge<String, String>("aset", "sdf", "sdfdasdfsa4"); 
		a.addEdge(b);
		list = a.getEdges();
		assertEquals(list.size(), 2);
		assertEquals(list.get(1).equals(b), true);
	}
	
	
	
	//Graph Tests
	@Test
	public void testGraphConstructor()
	{
		new Graph<String, String>();
	}
	
	@Test
	public void testGraphgetNumOfEdges()
	{
		Graph<String, String> g = new Graph<String, String>();
		assertEquals(g.getNumOfEdges(), 0);
		Node<String, String> n = new Node<String, String>("asdf");
		g.addNode(n);
		n = new Node<String, String>("bcdf");
		g.addNode(n);
		Edge<String, String> e = new Edge<String, String>("23", "asdf", "bcdf");
		g.addEdge(e);
		assertEquals(g.getNumOfEdges(), 1);
	}
	
	@Test
	public void testGraphNumOfNodes()
	{
		Graph<String, String> g = new Graph<String, String>();
		assertEquals(g.getNumOfNodes(), 0);
		Node<String, String> n = new Node<String, String>("asdf");
		g.addNode(n);
		assertEquals(g.getNumOfNodes(), 1);
	}
	
	@Test
	public void testGraphAddNodeNodeArg()
	{
		Graph<String, String> g = new Graph<String, String>();
		Node<String, String> n = new Node<String, String>("node");
		g.addNode(n);
		assertEquals(g.getNumOfNodes(), 1);
		g.addNode(n);
		assertEquals(g.getNumOfNodes(), 1);
	}
	
	@Test
	public void testGraphAddNodeStringArg()
	{
		Graph<String, String> g = new Graph<String, String>();
		g.addNode("2cat");
		assertEquals(g.getNumOfNodes(), 1);
		g.addNode("2cat");
		assertEquals(g.getNumOfNodes(), 1);
	}
	
	@Test
	public void testGraphAddEdgeOneArg()
	{
		Graph<String, String> g = new Graph<String, String>();
		assertEquals(g.getNumOfEdges(), 0);
		Node<String, String> n = new Node<String, String>("asdf");
		g.addNode(n);
		n = new Node<String, String>("bcdf");
		g.addNode(n);
		Edge<String, String> e = new Edge<String, String>("23", "asdf", "bcdf");
		g.addEdge(e);
		assertEquals(g.getNumOfEdges(), 1);
		g.addEdge(new Edge<String, String>("23", "asdf", "asdafadf"));
		assertEquals(g.getNumOfEdges(), 1);
		g.addEdge(new Edge<String, String>("2sdf", "asdfasdasdf", "asdafadf"));
		assertEquals(g.getNumOfEdges(), 1);
		g.addEdge(e);
		assertEquals(g.getNumOfEdges(), 1);
	}
	
	@Test
	public void testGraphAddEdgeMultArgs()
	{
		Graph<String, String> g = new Graph<String, String>();
		assertEquals(g.getNumOfEdges(), 0);
		Node<String, String> n = new Node<String, String>("asdf");
		g.addNode(n);
		n = new Node<String, String>("bcdf");
		g.addNode(n);
		g.addEdge("23", "asdf", "bcdf");
		assertEquals(g.getNumOfEdges(), 1);
		g.addEdge("23", "asdf", "asdafadf");
		assertEquals(g.getNumOfEdges(), 1);
		g.addEdge("2sdf", "asdfasdasdf", "asdafadf");
		assertEquals(g.getNumOfEdges(), 1);
		g.addEdge("23", "asdf", "bcdf");
		assertEquals(g.getNumOfEdges(), 1);
	}
	
	@Test
	public void testGraphGetNodes()
	{
		Graph<String, String> g = new Graph<String, String>();
		g.addNode("asdf");
		boolean list = g.NodeInGraph("asdf");
		assertEquals(list, true);
	}
	
	
	//GraphWrapper Tests
	@Test
	public void testGraphWrapperConstructor()
	{
		new GraphWrapper();
	}
	
	@Test
	public void testGraphWrapperaddNode()
	{
		GraphWrapper g = new GraphWrapper();
		g.addNode("asdf");
		g.addNode("asdfdsaf");
	}
	
	@Test
	public void testGraphWrapperaddEdge()
	{
		GraphWrapper g = new GraphWrapper();
		g.addNode("Rick");
		g.addNode("Morty");
		g.addEdge("Rick", "Morty", "Adventures");
	}
	
	@Test
	public void testGraphWrapperlistNodes()
	{
		GraphWrapper g = new GraphWrapper();
		g.addNode("Rick");
		g.addNode("Morty");
		g.addNode("Bob");
		g.addEdge("Rick", "Morty", "Adventures");
		g.addEdge("Rick", "Rick", "Adventures");
		g.addEdge("Morty", "Morty", "Adventures");
		
		Iterator<String> nodeNames = g.listNodes();
		String a = nodeNames.next();
		while(nodeNames.hasNext())
		{
			String b = nodeNames.next();
			assertEquals(a.compareTo(b) < 0, true);
			a = b;
		}
	}
	
	@Test
	public void testGraphWrapperlistChildren()
	{
		GraphWrapper g = new GraphWrapper();
		g.addNode("Rick");
		g.addNode("Morty");
		g.addEdge("Rick", "Morty", "Adventures");
		g.addEdge("Rick", "Rick", "Science");
		g.addEdge("Morty", "Morty", "dbf");
		
		Iterator<String> childNames = g.listChildren("Rick");
		String a = childNames.next();
		while(childNames.hasNext())
		{
			String b = childNames.next();
			assertEquals(a.compareTo(b) <= 0 && a.compareTo(b) < 0, true);
			a = b;	
		}
	}
	
}