package linkedListStructure;

/**
 * 
 * @author David Azofeifa
 * @author Daniel Sing
 * @author Manuel Bojorge
 * @author Jose Antonio Cespedes Downing
 * 
 * Clase Node (Nodo)
 */
public class Node {
	/**
	 * Atributos de la clase Node.
	 */
    private Node next;
    private Object data;
    
    /**
     * Constructor de la clase Node.
     * @param data
     */
    public Node(Object data){
        this.data = data;
    }
    
    /**
     * Constructor de la clase Node que a su vez se le pasa por parametro el siguiente Nodo.
     * @param data
     * @param next
     */
    public Node(Object data, Node next){
        this.data = data;
        this.next = next;
    }
    
    /**
     * Asigna el siguiente nodo.
     * @param next
     */
    public void setNext(Node next){
        this.next = next;
    }
    
    /**
     * Devuelve el siguiente nodo.
     * @return next
     */
    public Node getNext(){
        return this.next;
    }
    
    /**
     * Asigna la informacion que guardara el nodo.
     * @param data
     */
    public void setData(Object data){
        this.data = data;
    }
    
    /**
     * Obtiene la informacion del nodo.
     * @return data
     */
    public Object getData(){
        return this.data;
    }
}

