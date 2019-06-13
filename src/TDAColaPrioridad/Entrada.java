package	TDAColaPrioridad;
/**
 * Class Entrada
 * @author Matias Kloster
 * @author Luciano Baschiera
 * @param <K>
 * @param <V>
 */
public class Entrada<K,V> implements Entry<K, V> {
	private K key;
	private V value;
	/**
	 * Crea una entrada con clave k y valor v
	 * @param k Clave a ingresar
	 * @param v Valor a ingresar
	 */
	public Entrada(K k, V v) {
		key=k;
		value=v;
	}

	/**
	 * Retorna la clave de la entrada
	 */
	public K getKey() {
		return key;
	}
	/**
	 * Retorna el valor de la entrada
	 */
	public V getValue() {
		return value;
	}
	/**
	 * Establece la clave de la entrada
	 * @param k Clave a establecer
	 */
	public void setKey(K k){
		key=k;
	}
	/**
	 * Establece el valor de la entrada
	 * @param v Valor a establecer
	 */
	public void setValue(V v){
		value=v;
	}
}