package linkedListStructure;

/**
 * 
 * @author David Azofeifa
 * @author Daniel Sing
 * @author Manuel Bojorge
 * @author Jose Antonio Cespedes Downing
 * 
 * Clase SimpleLinkedList (Lista Simplemente Enlazada)
 */
public class SimpleLinkedList {
	/**
	 * Atributos de la clase SimpleLinkedList.
	 */
	private Node head;
	private int size;
	
    /**
     * Constructor de la clase SimpleLinkedList.
     */
    public SimpleLinkedList(){
        this.head = null;
        this.size = 0;
    }
    
    /**
     * Obtiene el primer nodo de la lista.
     * @return head.
     */
    public Node getFlag(){
        return this.head;
    }
    
    /**
     * Obtiene el tamano de la lista.
     * @return size
     */
    public int getSize(){
        return this.size;
    }
    
    /**
     * Obtiene un elemento de la lista.
     * @param obj
     * @return current
     */
    public Object get(Object obj){
        Node current = head;
        while(current != null){
            if(current.getData() == obj){
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }
    
    /**
     * Inserta al final
     * @param obj
     */
    public void insertEnd(Object obj){
        Node newNode = new Node(obj, null);
        if(head == null){
            head = newNode;
        }
        else{
            Node current = head;
            while(current.getNext() != null){
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }
    
    /**
     * Borra el nodo pasando como parametro su informacion.
     * @param obj
     */
    public void deleteNode(Object obj){
        try{
            Node current = head;
            if(current.getNext() == null && current.getData() == obj){
                head = null; 
                size--;
            }
            else if(current.getNext() != null && current.getData() == obj){
                head = current.getNext();
                current.setNext(null);
                size--;
            }
            else{
                while(current.getNext() != null){
                    if(current.getNext().getData() == obj){
                        current.setNext(current.getNext().getNext());
                        size--;
                    }
                    else{
                        current = current.getNext();
                    }
                }
            }
        }catch(NullPointerException ex){
        }
    }
}
