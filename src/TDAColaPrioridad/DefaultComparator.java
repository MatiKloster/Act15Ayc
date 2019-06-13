package TDAColaP;

public class DefaultComparator<E extends Comparable<E>> implements java.util.Comparator<E>{
	public int compare(E a, E b) {
		return ((Comparable<E>) a).compareTo(b);
	}

}
