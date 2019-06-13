package TDAColaPrioridad;

public class testMio {
	public static void main(String[] args){
		Heap<Integer,Integer> h=new Heap<Integer,Integer>(new DefaultComparator<Integer>());
		try {
			h.insert(7, 7);
			h.insert(2, 2);
			h.insert(8, 8);
			h.insert(1, 1);
			h.insert(6, 6);
			h.insert(9, 9);
			System.out.println(h.removeMin().getKey());
			System.out.println(h.removeMin().getKey());
			System.out.println(h.removeMin().getKey());
			System.out.println(h.removeMin().getKey());
			System.out.println(h.removeMin().getKey());
			System.out.println(h.removeMin().getKey());
		} catch (InvalidKeyException | EmptyPriorityQueueException e) {
			e.printStackTrace();
		}
	}
}
