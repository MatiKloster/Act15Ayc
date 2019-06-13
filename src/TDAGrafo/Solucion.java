package TDAGrafo;

public class Solucion {
	protected Integer valor;
	
	public Solucion() {
		this.valor = 0;
	}
	public Solucion(Integer valor){
		this.valor=valor;
	}
	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}
	public Solucion sumar(Integer valor){
		this.valor+=valor;
		return new Solucion(this.valor);
	}
	
}
