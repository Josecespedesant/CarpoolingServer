package graphStructure;

import linkedListStructure.Node;
import linkedListStructure.SimpleLinkedList;

/**
 * 
 * @author David Azofeifa
 * @author Daniel Sing
 * @author Manuel Bojorge
 * @author Jose Antonio Cespedes Downing
 * 
 * Clase Vertex (Vertice)
 */
public class Vertex {
	
	/**
	 * Atributos de la clase Vertex.
	 */
	private Object data;
	private SimpleLinkedList edges;
	private boolean isVisited;
	private String name;
	private int coordX;
	private int coordY;
	
	/**
	 * Constructor de la clase Vertex que recibe como parametro el objeto que guardara.
	 * @param data
	 */
	public Vertex(Object data) {
		this.data = data;
		this.edges = new SimpleLinkedList();
		this.isVisited = false;
		this.setName("");
		this.setCoordX(0);
		this.setCoordY(0);
	}
	
	/**
	 * Constructor de la clase Vertex que recibe como par�metro el objeto que guardara, su nombre y las coordenadas X y Y donde estara.
	 * @param data
	 * @param name
	 * @param coordX
	 * @param coordY
	 */
	public Vertex(Object data, String name, int coordX, int coordY) {
		this.data = data;
		this.edges = new SimpleLinkedList();
		this.isVisited = false;
		this.setName(name);
		this.setCoordX(coordX);
		this.setCoordY(coordY);
	}
	
	/**
	 * Obtiene la informacion que tiene el vertice.
	 * @return data
	 */
	public Object getData() {
		return data;
	}
	
	/**
	 * Asigna la informacion que tendra el vertice.
	 * @param value
	 */
	public void setData(Object value) {
		this.data = value;
	}
	
	/**
	 * Obtiene la lista de aristas que tiene el vertice.
	 * @return
	 */
	public SimpleLinkedList getEdgesList() {
		return this.edges;
	}
	
	/**
	 * Obtiene True si el vertice ha sido visitado, de no ser asi devuelve False
	 * @return True o False
	 */
	public boolean isVisited() {
		return isVisited;
	}
	
	/**
	 * Asigna si el vertice ha sido o no visitado.
	 * @param isVisited
	 */
	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}
	
	/**
	 * Obtiene el nombre del vertice
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Asigna el nombre del vertice.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obtiene la coordenada X del vertice.
	 * @return coordx
	 */
	public int getCoordX() {
		return coordX;
	}

	/**
	 * Asigna la coordenada X del vertice.
	 * @param coordX
	 */
	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	/**
	 * Obtiene la coordenada Y del vertice.
	 * @return coordY
	 */
	public int getCoordY() {
		return coordY;
	}
	
	/**
	 * Asigna la coordenada Y del vertice.
	 * @param coordY
	 */
	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	
	/**
	 * Agrega una arista al vertice. Tiene como parametros el vertice con el que se relacionara y el peso de la misma.
	 * @param dest
	 * @param weight
	 */
	public void addEdge(Vertex dest, int weight) {
		Edge newEdge = new Edge(this, dest, weight);
		this.edges.insertEnd(newEdge);
	}
	
	/**
	 * Obtiene la arista pasando como par�metro el vertice con el que esta conectado.
	 * @param dest
	 * @return edge
	 */
	public Edge getEdge(Vertex dest) {
		Node current = edges.getFlag();
		while(current != null) {
			Edge edge = (Edge) current.getData();
			if(edge.getDest().equals(dest)) {
				return edge;
			}
			current = current.getNext();
		}
		return null;
	}
	
	/**
	 * Borra una arista.
	 * @param edge
	 */
	public void removeEdge(Edge edge) {
		Node current = edges.getFlag();
		while (current != null) {
			if(current.getData().equals(edge)) {
				edges.deleteNode(edge);
				break;
			}
			current = current.getNext();
		}
	}
}
