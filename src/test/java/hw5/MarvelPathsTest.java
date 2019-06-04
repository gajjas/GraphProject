package hw5;

import static org.junit.Assert.*;

import org.junit.Test;

import hw4.Graph;

import org.junit.BeforeClass;

public final class MarvelPathsTest{
	
	@Test
	public void testMarvelPathsConstructor() {
		MarvelPaths m = new MarvelPaths();
	}
	
	@Test
	public void testMarvelPathsOverloadConstructor() {
		Graph<String, String> g = new Graph<String, String>();
		MarvelPaths m = new MarvelPaths(g);
	}
	
	@Test
	public void testcreateNewGraph() {
		String file1 = "data/testf1.csv";
		String file2 = "data/testf2.csv";
		
		Graph<String, String> a = new Graph<String, String>();
		Graph<String, String> b = new Graph<String, String>();
		
		MarvelPaths m1 = new MarvelPaths(a);
		MarvelPaths m2 = new MarvelPaths(b);

		m1.createNewGraph(file1);
		m2.createNewGraph(file2);
		
		assertEquals(a.getNumOfNodes(), 11);
		assertEquals(a.getNumOfEdges(), 50);
		
		assertEquals(b.getNumOfNodes(), 12);
		assertEquals(b.getNumOfEdges(), 17);
	}
	
	@Test
	public void testfindPath() {
		String file1 = "data/testf1.csv";
		String file2 = "data/testf2.csv";
		
		Graph<String, String> a = new Graph<String, String>();
		Graph<String, String> b = new Graph<String, String>();
		
		MarvelPaths m1 = new MarvelPaths(a);
		MarvelPaths m2 = new MarvelPaths(b);


		m1.createNewGraph(file1);
		m2.createNewGraph(file2);

		
		String f1str = m1.findPath("KILLRAVEN/JONATHAN R", "24-HOUR MAN/EMMANUEL");
		String expectedf1_1 = "path from KILLRAVEN/JONATHAN R to 24-HOUR MAN/EMMANUEL:\nKILLRAVEN/JONATHAN R to 24-HOUR MAN/EMMANUEL via AA2 35\n";
		assertEquals(f1str.equals(expectedf1_1),true);
		
		String f2str1 = m2.findPath("IronMan", "CaptainAmerica");
		String expectedf2_1 = "path from " + "IronMan" + " to "+ "CaptainAmerica" + ":\nno path found\n";
		assertEquals(f2str1.equals(expectedf2_1),true);
		
		String f2str2 = m2.findPath("IronMan", "asdf");
		String expectedf2_2 = "unknown character " + "asdf\n";
		assertEquals(f2str2.equals(expectedf2_2),true);
		
		f2str2 = m2.findPath("asdf", "IronMan");
		expectedf2_2 = "unknown character " + "asdf\n";
		assertEquals(f2str2.equals(expectedf2_2),true);
		
		f2str2 = m2.findPath("asdf", "sdfasdf");
		expectedf2_2 = "unknown character " + "asdf\n"+ "unknown character sdfasdf\n";
		assertEquals(f2str2.equals(expectedf2_2),true);
		
		f2str2 = m2.findPath("asdf", "asdf");
		expectedf2_2 = "unknown character " + "asdf\n";
		assertEquals(f2str2.equals(expectedf2_2),true);		
		
		f2str2 = m2.findPath("IronMan", "IronMan");
		expectedf2_2 = "path from " + "IronMan" + " to "+ "IronMan" + ":\n";
		assertEquals(f2str2.equals(expectedf2_2),true);	
	}
}