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
 * Clase Graph (Grafo)
 */
public class Graph {
	/**
	 * Atributos de la clase Graph.
	 */
	private int numVert;
	private int numEdges;
	private boolean isDirected;
	private SimpleLinkedList adjList;
	
	/**
	 * Constructor de la clase Graph.
	 */
	public Graph() {
		this.numVert = 0;
		this.numEdges = 0;
		this.adjList = new SimpleLinkedList();
	}
	
	/**
	 * Obtiene el numero de vertices que tiene el grafo.
	 * @return numVert
	 */
	public int getNumVert() {
		return this.numVert;
	}
	
	/**
	 * Asigna el numero de vertices que tiene el grafo.
	 * @param numVert
	 */
	public void setNumVert(int numVert) {
		this.numVert = numVert;
	}
	
	/**
	 * Obtiene el numero de aristas.
	 * @return numEdges
	 */
	public int getNumEdges() {
		return this.numEdges;
	}
	
	/**
	 * Asigna el n�mero de aristas.
	 * @param numEdges
	 */
	public void setNumEdges(int numEdges) {
		this.numEdges = numEdges;
	}
	
	/**
	 * Devuelve el tipo de grafo.
	 * @return True si es dirigido, False si no es dirigido.
	 */
	public boolean isDirected() {
		return isDirected;
	}
	
	/**
	 * Asigna el tipo de grafo que sera.
	 * @param isDirected
	 */
	public void setDirected(boolean isDirected) {
		this.isDirected = isDirected;
	}
	
	/**
	 * Agrega el vertice
	 * @param vertex
	 */
	public void addVertex(Vertex vertex) {
		SimpleLinkedList SLL = new SimpleLinkedList();
		SLL.insertEnd(vertex);
		adjList.insertEnd(SLL);	
		numVert++;
	}
	
	/**
	 * Agrega aristas
	 * @param src
	 * @param dest
	 * @param weight
	 * @param bool
	 */
	public void addEdges(Vertex src, Vertex dest, int weight, boolean bool) {
		Node current = adjList.getFlag();
		while(current != null) {
			SimpleLinkedList temp = (SimpleLinkedList) current.getData();
			if(temp.getFlag().getData().equals(src) && temp.get(dest) == null) {
				src.addEdge(dest, weight);
				temp.insertEnd(dest);
				break;
			}
			current = current.getNext();
		}
		if(!isDirected() && !bool) {
			addEdges(dest, src, weight, true);
		}
		numEdges++;
	}
	
	/**
	 * Borra Vertices
	 * @param vertex
	 */
	public void removeVertex(Vertex vertex) {
		Node current = adjList.getFlag();
		while(current != null) {
			SimpleLinkedList vert = (SimpleLinkedList) current.getData();
			Vertex toRemove = (Vertex) vert.get(vertex);
			Vertex head = (Vertex) vert.getFlag().getData();
			if(toRemove != null && toRemove != head) {
				vert.deleteNode(vertex);   				// Elimina la arista que conecta al vertice que se eliminara
				head.removeEdge(head.getEdge(vertex));  // Elimina la arista del registro del vertice que la contenia
				this.numEdges--;
			}
			if(head.equals(vertex)) {
				adjList.deleteNode(vert);
			}
			current = current.getNext();
		}
		this.numVert--;
	}
	
	/**
	 * Borra Aristas
	 * @param src
	 * @param dest
	 */
	public void removeEdge(Vertex src, Vertex dest) {
		Node current = adjList.getFlag();
		while(current != null) {
			SimpleLinkedList vertex = (SimpleLinkedList) current.getData();
			Vertex head = (Vertex) vertex.getFlag().getData();
			if(vertex.get(dest) != null && vertex.get(dest)!= head) {
				vertex.deleteNode(dest);
				head.removeEdge(head.getEdge(dest));
				this.numEdges--;
			}
			else {
				current = current.getNext();
			}
		}		
	}
	
	/**
	 * Busqueda en anchura donde el parametro src es el vertice por el que se empieza la busqueda.
	 * @param src
	 */
	public void breadthFirst(Vertex src) {
		// Crea una lista con los nodos no visitados, en un principio ninguno ha sido visitado
		// Por lo tanto, la iguala a la lista de adyacencia.
        SimpleLinkedList visited = this.adjList;
        
        // Crea la cola para el recorrido breadth first
        SimpleLinkedList queue = new SimpleLinkedList();

        // Establece el nodo del principio como visitado y lo agrega a la cola
        ((Vertex) ((SimpleLinkedList) visited.getFlag().getData()).get(src)).setVisited(true);
        queue.insertEnd(src);
 
        while (queue.getSize() != 0){
            // Saca el vertice de la cola y lo imprime
            src = (Vertex) queue.getFlag().getData();
            queue.deleteNode(src);
            System.out.print(src.getData() +" ");
 
            // Obtiene los vertices siguientes al src que obtuvimos
            // y si este no ha sido visitado lo establece como visitado
            // y lo agrega a la cola
            Node i = (Node) adjList.getFlag();
            while (i != null) {
                Vertex vert =  (Vertex) ((SimpleLinkedList) i.getData()).getFlag().getData();
                if (!vert.isVisited()) {
                    vert.setVisited(true);
                    queue.insertEnd(vert);
                }
                i = i.getNext();
            }
        }
	}
	
	/**
	 * Obtiene el vertice que se pasa por parametro como un string.
	 * @param name
	 * @return Nombre
	 */
	public Vertex getVertex(String name) {
		Node current = getGraphVertexList().getFlag();
		Vertex vertex;
		while(current != null) {
			vertex = (Vertex) current.getData();
			if(vertex.getName().equalsIgnoreCase(name)) {
				return vertex;
			}
			current = current.getNext();
		}
		return null;
	}
	
	/**
	 * Obtiene lista de vertices del grafo
	 * @return lista
	 */
	public SimpleLinkedList getGraphVertexList() {
		SimpleLinkedList res = new SimpleLinkedList();
		Node current = adjList.getFlag();
		while(current != null) {
			SimpleLinkedList temp = (SimpleLinkedList) current.getData();
			res.insertEnd(((Vertex) temp.getFlag().getData()));
			current = current.getNext();
		}
		return res;
	}
	
	/**
	 * Obtiene lista de aristas del grafo
	 * @return lista
	 */
	public SimpleLinkedList getGraphEdgesList() {
		SimpleLinkedList res = new SimpleLinkedList();
		Node current = adjList.getFlag();
		while(current != null) {
			SimpleLinkedList temp = (SimpleLinkedList) current.getData();
			Node current2 = temp.getFlag();
			Vertex src = (Vertex) current2.getData();
			while(current2.getNext() != null) {
				Vertex dest = (Vertex) current2.getNext().getData();
				res.insertEnd(src.getEdge(dest));
				current2 = current2.getNext();
			}
			current = current.getNext();
		}
		return res;
	}
	
	/**
	 * Obtiene grado entrante del vertice
	 * @param name
	 * @return nombre
	 */
	public int getInputDegree(String name) {
		int inputDegree = 0;
		Node current = this.adjList.getFlag();
		while(current != null) {
			SimpleLinkedList temp = (SimpleLinkedList) current.getData();
			Node current2 = temp.getFlag().getNext();
			while(current2 != null) {
				Vertex src = (Vertex) current2.getData();
				if(src.getName().equalsIgnoreCase(name)) {
					inputDegree++;
				}
				current2 = current2.getNext();
			}
			current = current.getNext();
			
		}
		return inputDegree;
	}
	
	/**
	 * Obtiene el grado saliente del vertice
	 * @param name
	 * @return nombre
	 */
	public int getOutputDegree(String name) {
		int outputDegree = 0;
		Node current = this.adjList.getFlag();
		while(current != null) {
			SimpleLinkedList temp = (SimpleLinkedList) current.getData();
			Vertex src = (Vertex) temp.getFlag().getData();
			if(src.getName().equalsIgnoreCase(name)) {
				outputDegree = src.getEdgesList().getSize();
				break;
			}
			current = current.getNext();
		}
		return outputDegree;
	}
	
	/**
	 * Retorna True si es conexo.
	 * @return boolean
	 */
	public boolean isRelated() {
		return true;
	}
	
	/**
	 * Imprime el grafo
	 */
    public void printGraph(){
    	Node current = adjList.getFlag();
        while(current != null){
        	Node temp = ((SimpleLinkedList) current.getData()).getFlag();
        	Vertex head = (Vertex) temp.getData();
            System.out.println("Adjacency list of vertex "+ head.getData());
            System.out.print("Head ");
        	while(temp.getNext() != null) {
        		Vertex vert = (Vertex) temp.getNext().getData();
        		System.out.print("--> " + vert.getData());
        		System.out.print("(" + head.getEdge(vert).getWeight() + ")");
        		temp = temp.getNext();
        	}
            System.out.println("\n");
            current = current.getNext();
        }
    }
    /**
     * Metodo que determina si un grafo es fuertemente conexo.
     * @return True si es fuertemente conexo.
     */
    public boolean esFuertementeConexo() {
    	Node current = adjList.getFlag();
    	
    	while(current != null) {
    		SimpleLinkedList temp = (SimpleLinkedList) current.getData();
    		Node current2 = temp.getFlag().getNext();
    		if(current2==null) {
    			return false;
    		}
    		current = current.getNext();	
    	}
    	current = adjList.getFlag();
    	while(current != null) {
    		if(esFuertementeConexo(current) == false) {
    			return false;
    		};
    		current = current.getNext();
    	}return true;	
   
    }
    
    /**
     * Metodo auxiliar que añade los nodos que pueden ser visitados desde el nodo entrante
     * @param v
     * @return true o false
     */
    private boolean esFuertementeConexo(Node v) {
    	Node[] vertArr = new Node[numVert];
    	SimpleLinkedList lista = (SimpleLinkedList) v.getData();
    	Node current = lista.getFlag().getNext();
    	int z = 0;
    	
    	while(current!=null) {
    		vertArr[z] = current;
    		z++;
    		current = current.getNext();
    	}
    	
        	
    	for(int i=0; (numVert)>i; i++) {
    		if(vertArr[i] == null) {
    			return false;
    		}
    		else {
    			esFuertementeConexo(vertArr[i],vertArr, z);
    		}
    	}
    	return true;
    }
    
    /**
     * Metodo auxiliar que revisa las listas de adyacencia de los nodos visitados
     * @param v
     * @param vertArr
     * @param z
     */
    private void esFuertementeConexo(Node v, Node[] vertArr, int z) {
    	Node current2 = adjList.getFlag();
    	Node x = null;
    	while(current2 != null) {
    		if(((SimpleLinkedList) current2.getData()).getFlag().equals(v)) {
    			x=current2;
    		}
    		current2 = current2.getNext();
    	}
    	SimpleLinkedList listav = (SimpleLinkedList) x.getData();
    	Node current = listav.getFlag().getNext();
    	
    	while(current!=null) {
    		for(int i=0; (numVert)>i;i++) {
    			if(!(vertArr[i].equals(current))){
    				vertArr[z] = current;
    				z++;
    			};
    		}
    		current = current.getNext();
    	}
    }
    
    
    /**
     * Metodo que determina si un grafo es debilmente conexo.
     * @return True si es debilmente conexo.
     */
    public boolean esDebilmenteConexo() {
    	SimpleLinkedList lista = new SimpleLinkedList();
    	Node current = adjList.getFlag();

    	while(current != null) {
    		SimpleLinkedList temp = (SimpleLinkedList) current.getData();
    		Vertex v = (Vertex) temp.getFlag().getData();
    		if(v.getEdgesList().getSize() > 0) {
    			if(lista.get(v) == null) {
    				if(lista.getSize()==0) {
    					esConexo(v, lista);
    				}
    				else {
    					esConexo1(v, lista);
    				}
    			}
    		}
    		current = current.getNext();
    	}
    	if (lista.getSize() != getGraphVertexList().getSize())
    		return false;
    	return true;
    	}
    	
    	/**
    	 * Metodo auxiliar que insertaba en la lista cuando esta esta vacia
    	 * @param v
    	 * @param lista
    	 */
    	private void esConexo(Vertex v, SimpleLinkedList lista) {
    		Node current = v.getEdgesList().getFlag();
    		
    		if (lista.get(v) == null) {
    			lista.insertEnd(v);
    			}
    		
    		while(current!=null) {
    			Edge e = (Edge) v.getEdgesList().get(current.getData());
    			if(!(e.getDest().equals(v))) {
    				if(lista.get(e.getDest()) == null) {
    					esConexo(e.getDest(), lista);
    				}
    			}
    			current = current.getNext();
    			
    		}
    	}
    	
    	/**
    	 * Metodo auxiliar que verifica las aristas
    	 * @param v
    	 * @param lista
    	 */
    	public void esConexo1 (Vertex v, SimpleLinkedList lista){
    		Node current = v.getEdgesList().getFlag();
    		while(current != null) {
    			Edge e = (Edge) v.getEdgesList().get(current.getData());
    			if(lista.get(e.getDest()) != null) {
    				esConexo(v,lista);
    				break;
    			}
    		}
    		current = current.getNext();
    	}
}
