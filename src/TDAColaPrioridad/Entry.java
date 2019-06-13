package TDAColaPrioridad;
/**
 * Interface Entry
 * @author Matias
 *
 * @param <K>
 * @param <V>
 */
public interface Entry<K, V> {
	/**
	 * Devuelve la clave de la entrada
	 * @return clave de la entrada
	 */
	public K getKey();
	/**
	 * Devuelve el valor de la entrada
	 * @return valor de la entrada
	 */
	public V getValue();
}
