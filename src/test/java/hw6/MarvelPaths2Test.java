package hw6;

import static org.junit.Assert.*;

import org.junit.Test;

public final class MarvelPaths2Test{
	@Test
	public void testPair() {
		String a = "asdf";
		String b = "asdff";
		
		Pair<String> p = new Pair<String>(a, b);
	}
	
	@Test
	public void testHashCode() {
		String a = "asdf";
		String b = "asdff";
		
		Pair<String> p = new Pair<String>(a, b);
		
		assertEquals(p.hashCode(), a.hashCode()+b.hashCode());
	}
	
	@Test
	public void testEquals() {
		String a = "asdf";
		String b = "asdff";
		String c = "asd";
		
		Pair<String> p = new Pair<String>(a, b);
		Pair<String> p2 = new Pair<String>(a, b);
		Pair<String> p3 = new Pair<String>(a, c);
		Object p1 = new Object();
		
		assertEquals(p.equals(p2), true);
		assertEquals(p.equals(p3), false);
		assertEquals(p.equals(p), true);
		assertEquals(p.equals(p1), false);
	}
	
	@Test
	public void testgetKey() {
		String a = "asdf";
		String b = "asdff";
		
		Pair<String> p = new Pair<String>(a, b);
		
		assertEquals(p.getKeyOne(), a);
		assertEquals(p.getKeyTwo(), b);
	}
	
	@Test
	public void testcreateNewGraph() {
		String file1 = "data/testf1.csv";
		String file2 = "data/testf2.csv";
		
		
		MarvelPaths2 m1 = new MarvelPaths2();
		MarvelPaths2 m2 = new MarvelPaths2();

		m1.createNewGraph(file1);
		m2.createNewGraph(file2);
	}
	
	@Test
	public void testFindPath() {
		String file1 = "data/testf1.csv";
		String file2 = "data/testf2.csv";
		
		
		MarvelPaths2 m1 = new MarvelPaths2();
		MarvelPaths2 m2 = new MarvelPaths2();
		

		m1.createNewGraph(file1);
		m2.createNewGraph(file2);
			
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
		expectedf2_2 = "path from " + "IronMan" + " to "+ "IronMan" + ":\ntotal cost: 0.000\n";
		assertEquals(f2str2.equals(expectedf2_2),true);	
		
		
		String f1str = m1.findPath("KILLRAVEN/JONATHAN R", "3-D MAN/CHARLES CHAN");
		System.out.println(f1str);
		String expectedf1_1 = "path from KILLRAVEN/JONATHAN R to 3-D MAN/CHARLES CHAN:\n"+
			"KILLRAVEN/JONATHAN R to 24-HOUR MAN/EMMANUEL with weight 1.000\n"+
			"24-HOUR MAN/EMMANUEL to 3-D MAN/CHARLES CHAN with weight 0.500\n"+
			"total cost: 1.500\n";
		assertEquals(f1str.equals(expectedf1_1),true);
		
	}
}