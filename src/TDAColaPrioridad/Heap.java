package TDAColaP;

import java.util.Comparator;

public class Heap<K,V> implements PriorityQueue<K,V> {
	protected Entrada<K,V> [] ar;
	protected Comparator<K> comp;
	protected int size;
	private int maxElements=50;
	
	public Heap(Comparator<K> comp){
		this.ar=(Entrada<K,V>[])new Entrada[maxElements];
		this.comp=comp;
		this.size=0;
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return this.size==0;
	}

	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if(isEmpty())
			throw new EmptyPriorityQueueException("La cola con prioridad esta vacia");
		else
			return ar[1];
	}

	
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if(size==maxElements-1){
			Entrada<K,V>[] aux=(Entrada<K,V>[]) new Entrada[maxElements*2];
			for(int i=1;i<maxElements;i++){
				aux[i]=ar[i];
			}
			this.ar=aux;
			maxElements*=2;
		}
		if(key==null){
			throw new InvalidKeyException("Clave nula");
		}
		Entrada<K,V> ent=new Entrada<K,V>(key,value);
		ar[++size]=ent;
		int i=size;
		boolean sigo=true;
		while(i>1 && sigo){
			Entrada<K,V> elemActual=ar[i];
			Entrada<K,V> elemPadre=ar[(i/2)];
			if(comp.compare(elemActual.getKey(), elemPadre.getKey())<0){
				Entrada<K,V> aux=elemActual;
				ar[i]=ar[i/2];
				ar[i/2]=aux;
				i/=2;
			}
			else{
				sigo=false;
			}
		}
		return ent;
	}

	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		Entry<K,V> ent=min();
		if(size==1){
			ar[size--]=null;
			return ent;
		}
		else{
			ar[1]=ar[size]; ar[size--]=null;
			int i=1;
			boolean seguir=true;
			while(seguir){
				int hDer=(i*2)+1,hIzq=i*2;
				int m=0;
				boolean tieneHIzq=hIzq<=size,tieneHDer=hDer<=size;
				if(!tieneHIzq) seguir=false;
				else{
					if(tieneHDer){
						if(comp.compare(ar[hIzq].getKey(), ar[hDer].getKey())<0) m=hIzq;
						else m=hDer;
					}
					else m=hIzq;
				}
			
				if(seguir && comp.compare(ar[i].getKey(), ar[m].getKey())>0){
					Entrada<K,V> aux=ar[i];
					ar[i]=ar[m];
					ar[m]=aux;
					i=m;
				}
				else seguir=false;
			}
		}
		return ent;
	}
	public void mostrarHeap(){
		for(int i=1;i<=size;i++){
			System.out.println(ar[i].getKey());
		}
	}
}
