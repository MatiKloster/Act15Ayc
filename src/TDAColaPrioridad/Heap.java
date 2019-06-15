package TDAColaPrioridad;

public class Heap{
	private Pesado [] ar;
	private int size;
	
	public Heap(int cant){
		this.ar=new Pesado[cant];
		this.size=0;
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return this.size==0;
	}

	public Pesado min() throws EmptyPriorityQueueException {
		if(isEmpty())
			throw new EmptyPriorityQueueException("El heap está vacío");
		else
			return ar[1];
	}

	public void insert(Pesado p) {
		ar[++size] = p;
		int i = size;
		boolean sigo = true;
		while (i > 1 && sigo) {
			Pesado elemActual = ar[i];
			Pesado elemPadre = ar[(i / 2)];
			if (elemActual.getPeso() < elemPadre.getPeso()) {
				ar[i] = ar[i / 2];
				ar[i / 2] = elemActual;
				i /= 2;
			} else {
				sigo = false;
			}
		}
	}

	public Pesado removeMin() throws Exception {
		Pesado arco=min();
		if(size==1){
			ar[size--]=null;
			return arco;
		}
		else{
			ar[1]=ar[size]; ar[size--]=null;
			int i=1;
			heapify(i);
		}
		return arco;
	}

	public void heapify(int i){
		boolean seguir=true;
		while(seguir){
			int hDer=(i*2)+1,hIzq=i*2;
			int m=0;
			boolean tieneHIzq=hIzq<=size,tieneHDer=hDer<=size;
			if(!tieneHIzq) seguir=false;
			else{
				if(tieneHDer){
					if(ar[hIzq].getPeso() < ar[hDer].getPeso())
						m=hIzq;
					else
						m=hDer;
				}
				else m=hIzq;
			}

			if(seguir && ar[hIzq].getPeso() > ar[hDer].getPeso()){
				Pesado aux=ar[i];
				ar[i]=ar[m];
				ar[m]=aux;
				i=m;
			}
			else seguir=false;
		}
	}
}
