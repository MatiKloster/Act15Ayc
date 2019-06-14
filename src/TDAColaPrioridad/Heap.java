package TDAColaPrioridad;

public class Heap{
	private Pesado [] ar;
	private int size;
	private int maxElements=50;
	
	public Heap(){
		this.ar=new Pesado[maxElements];
		this.size=0;
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return this.size==0;
	}

	public Pesado min() throws Exception {
		if(isEmpty())
			throw new Exception("La cola con prioridad esta vacia");
		else
			return ar[1];
	}

	
	public Pesado insert(Pesado p) {
		if(size==maxElements-1){
			Pesado[] aux=new Pesado[maxElements*2];
			if (maxElements - 1 >= 0) System.arraycopy(ar, 1, aux, 1, maxElements - 1);
			this.ar=aux;
			maxElements*=2;
		}
		ar[++size]=p;
		int i=size;
		boolean sigo=true;
		while(i>1 && sigo){
			Pesado elemActual=ar[i];
			Pesado elemPadre=ar[(i/2)];
			if(elemActual.getPeso() < elemPadre.getPeso()){
				ar[i]=ar[i/2];
				ar[i/2]= elemActual;
				i/=2;
			}
			else{
				sigo=false;
			}
		}
		return p;
	}

	public Pesado removeMin() throws Exception {
		Pesado ent=min();
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
		return ent;
	}
}
