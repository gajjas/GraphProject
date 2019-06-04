package hw6;

/* Pair used to hold two data objects. */
public class Pair<S>{
	private S key1;
	private S key2;
	
	//Abstraction Function:
	//key1 is the first object used in the Pair
	//key2 is the second Object used in the pair
	//
	//Representation invariant for every Pair p:
	//no key values can be null
	//
	
	/**
	 * @param n1 ParentNode
	 * @param n2 ChildNode
	 * @requires n1 != null and n2 != null
	 * Constructor for the pair class
	 */
	public Pair(S n1, S n2) {
		key1 = n1;
		key2 = n2;
	}
	
	/**
	 * @return the hashCode for the Pair which is the key1.getName().hashCode()+key2.getName().hashCode()
	 */
	public int hashCode() {
		return this.key1.hashCode() + this.key2.hashCode();
	}
	
	/**
	 * @return true if the pairs are the same and false otherwise
	 */
	public boolean equals(Object o1) {
		if(this == o1) {
			return true;
		}
		
		if(!(o1 instanceof Pair)) {
			return false;
		}
		
		Pair<?> p = (Pair<?>) o1;
		if(p.getKeyOne().equals(this.key1) && p.getKeyTwo().equals(this.key2)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @return Node<String, String> that is the key for the first key value (key1)
	 */
	public S getKeyOne() {
		return this.key1;
	}
	
	/**
	 * @return Node<String, String> that is the key for the second key value (key2)
	 */
	public S getKeyTwo() {
		return this.key2;
	}
}
