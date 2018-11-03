package graphStructure;

/**
 * 
 * @author David Azofeifa
 * @author Daniel Sing
 * @author Manuel Bojorge
 * @author Jose Antonio Cespedes Downing
 * 
 * Clase Edge (Arista)
 */
public class Edge {
	/**
	 * Atributos de la clase Edge
	 */
	private Vertex src;
	private Vertex dest;
	private int weight;
	
	
	/**
	 * Constructor de la clase Edge que recibe el vertice de procedencia y el vertice al que va, a su vez de su peso.
	 * @param src
	 * @param dest
	 * @param weight
	 */
	public Edge(Vertex src, Vertex dest, int weight) {
		this.src = src;
		this.dest = dest;
		this.setWeight(weight);
	}
	
	/**
	 * Devuelve el peso de la arista
	 * @return weight
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * Asigna el peso de la arista.
	 * @param weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * Obtiene el vertice de origen.
	 * @return src
	 */
	public Vertex getSource() {
		return src;
	}
	
	/**
	 * Obtiene el vertice de destino.
	 * @return dest
	 */
	public Vertex getDest() {
		return dest;
	}
	
}
