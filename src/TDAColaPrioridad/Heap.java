package TDAColaPrioridad;
import business.Pesado;
public class Heap{
	private Pesado[] ar;
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
		int hIzq=2*i, hDer=2*i+1;
		int menor;
		if ((hIzq <= size) && (ar[hIzq].getPeso() < ar[i].getPeso()))
			menor=hIzq;
		else menor=i;
		if ((hDer <= size) && (ar[hDer].getPeso() < menor))
			menor = hDer;
		if (menor!=i){
			Pesado aux= ar[menor];
			ar[menor]=ar[i];
			ar[i]=aux;
			heapify(menor);
		}
	}
}
