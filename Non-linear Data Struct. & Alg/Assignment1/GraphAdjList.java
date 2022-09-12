/**
 * @author Conor Pasley
 * Student number: R00205997
*/

import java.util.LinkedList;
	/**
	* Graph implementation that uses Adjacency Lists to store edges. It
	* contains one linked-list for every vertex i of the graph. The list for i
	* contains one instance of VertexAdjList for every vertex that is adjacent to i.
	* For directed graphs, if there is an edge from vertex i to vertex j then there is
	* a corresponding element in the adjacency list of node i (only). For
	* undirected graphs, if there is an edge between vertex i and vertex j, then there is a
	* corresponding element in the adjacency lists of *both* vertex i and vertex j. The
	* edges are not sorted; they contain the adjacent nodes in *order* of
	* edge insertion. In other words, for a graph, the node at the head of
	* the list of some vertex i corresponds to the edge involving i that was
	* added to the graph least recently (and has not been removed, yet). 
	*/

	public class GraphAdjList  implements Graph {

	// ATTRIBUTES: 
		private boolean directed;
		private int edges;
		private LinkedList<Edge>[] graphList;
		private int vertices;

	 /*
	  * CONSTRUCTOR: Creates a directed/undirected graph with V vertices and no edges.
	  * It initializes the array of adjacency edges so that each list is empty.
	  */
	    
	public GraphAdjList(int V, boolean directed) {
		this.vertices = V;
		this.directed = directed; 
		graphList = new LinkedList[V]; // creates empty linked list 
		for(int i = 0; i < V; i++){
			graphList[i] = new LinkedList<>(); 
			// graphList.add(new LinkedList<Edge>()); // adds an empty linked list to each index of graphList 
		}

	 }	 
	// 1. IMPLEMENTATION METHOD numVerts: 
	public int numVerts() { 
		return this.vertices;		  
     
     }

	// 2. IMPLEMENTATION METHOD numEdges:
	public int numEdges() { 
		return this.edges;
	}
	    
	//  3. IMPLEMENTATION METHOD addEdge:
	public void addEdge(int v1, int v2, int w) {
		try{
			int len = this.graphList[v1].size(); //gets the size of the v1 linked list 
			int vertex;
			boolean duplicate = false; // True if the edge is already in the list 
			int replaceIndex = 0; // index of edge to be replaced
			for(int i = 0; i < len; i++){
				vertex = this.graphList[v1].get(i).getVertex(); //gets each Edge in the vertex 1 list 
				//if the vertex is already in the list then it must be updated
				if(vertex == v2){ 
					duplicate = true;
					replaceIndex = i;
					break;
				}
			}
			Edge e1 = new Edge(v2, w);
			if(duplicate) this.graphList[v1].set(replaceIndex, e1); //Replaces edge 
			
			else {
				this.graphList[v1].add(e1); // adds edge to end of the list 
				this.edges++; // increment the amount of edges
			}  
			
			//if it is an undirected graph and its not a loop
			if(!directed && v1 != v2){
				Edge e2 = new Edge(v1, w);
				//if it is a duplicate then we must find the index of the Edge already in the list 
				if(duplicate){
					len = this.graphList[v2].size(); //gets the size of the v2 linked list  
					//finds the index of the edge to be replaced
					for(int i = 0; i < len; i++){
						vertex = this.graphList[v2].get(i).getVertex();
						if(vertex == v1){
							replaceIndex = i;
						}
					}
					this.graphList[v2].set(replaceIndex , e2); // replaces the edge
					
				} else this.graphList[v2].add(e2); // adds edge to end of the list	
			}
		} catch (IndexOutOfBoundsException e){
			System.out.println("Vertex is out of bounds");
		}
	}
	
	// 4. IMPLEMENTATION METHOD removeEdge: 
	public void removeEdge(int v1, int v2) {	
		try{	
			int vertex;
			int len = this.graphList[v1].size(); //gets the size of the v1 linked list 
			//finds the index of the edge to be removed and removes it 
			for(int i = 0; i < len;i++){
				vertex = this.graphList[v1].get(i).getVertex();
				if(vertex == v2){
					this.graphList[v1].remove(i);
					this.edges--;
					break;
				}
			}

		//if the graph is undirected
			if(!directed){
				len = this.graphList[v2].size(); //gets the size of the v2 linked list 
				//finds the index of the edge to be removed and removes it 
				for(int i = 0; i < len;i++){
					vertex = this.graphList[v2].get(i).getVertex();
					if(vertex == v1){
						this.graphList[v2].remove(i);
						break;
					}
				}
			}
		} catch (IndexOutOfBoundsException e){
			System.out.println("Vertex is out of bounds");
		}
	}
	 
	// 5. IMPLEMENTATION METHOD hasEdge:
	public boolean hasEdge(int v1, int v2) {
		try{
			int len = this.graphList[v1].size(); //gets the size of the v1 linked list 
			int vertex;
			//loops through list of v1 and checks if edge is in the list 
			for(int i = 0; i < len;i++){ 
				vertex = this.graphList[v1].get(i).getVertex();
				if(vertex == v2){
					return true;
				}
			}

		} catch (IndexOutOfBoundsException e){
			System.out.println("Vertex is out of bounds");
		}

		return false;
	}

	// 6. IMPLEMENTATION METHOD getWeightEdge:
	public int getWeightEdge(int v1, int v2) {
		try{
			int len = this.graphList[v1].size();//gets the size of the v1 linked list 
			int vertex;
			//loops through the list of v1 and finds the vertex v2
			for(int i = 0; i < len;i++){
				vertex = this.graphList[v1].get(i).getVertex();
				//gets weight of vertex if found 
				if(vertex == v2){
					return this.graphList[v1].get(i).getWeight();
				}
			}
		} catch (IndexOutOfBoundsException e){
			System.out.println("Vertex is out of bounds");
		}
		return 0;
	}

	// 7. IMPLEMENTATION METHOD getNeighbors:
	public LinkedList getNeighbors(int v) {
		try{		
			int len = this.graphList[v].size(); // get size of list v
			LinkedList neighborList = new LinkedList<Integer>(); // create new empty linked list 

			//loop through linked list v and add each vertex to the new list
			for(int i = 0; i < len; i++) 
				neighborList.add(this.graphList[v].get(i).getVertex());

			return neighborList;

		} catch (IndexOutOfBoundsException e){
			System.out.println("Vertex is out of bounds");
			return null;
		}	
	}

    // 8. IMPLEMENTATION METHOD getDegree:
	public int getDegree(int v) {
		try{
			if(v < 0 || v > this.vertices){
				System.out.println("Vertex is out of bounds!");
				return -1;
			}
			int count = 0;
			int size; 
			//if graph is directed
			if(directed){
				//loop from 0 - amount of v. Counts the number of edges for each vertex. 
				for(int i = 0; i < this.vertices; i++){
					size = this.graphList[i].size();
					if(i == v){
						count += size;
						continue;
					}
					for(int j = 0; j< size;j++){
						int vertex = this.graphList[i].get(j).getVertex();
						if(vertex == v) {
							count++; 	
							break;
						}
					}
				}
			} else{
				//undirected
				count = this.graphList[v].size();//gets the size of the v linked list 
			}

			return count;

		} catch (IndexOutOfBoundsException e){
			System.out.println("Vertex is out of bounds");
			return -1;
		}
	}

	// 9. IMPLEMENTATION METHOD toString:
	public String toString() {
		int size;
		String string = "";
		string += "| v1 | --> |( v2 )( w )|\n"; 
		string += "=========================\n";
		for(int i = 0; i < this.vertices; i++){
			size = this.graphList[i].size();//size of next linked list
			string += "| " + i + "  | --> ";
			for(int j = 0 ; j < size; j++){
				string += "|( ";
				string += this.graphList[i].get(j).getVertex();
				string += " )";
				string += "( ";
				string += this.graphList[i].get(j).getWeight();
				string += " )|--> ";
			}
			string+="\n";
		}
		return string;
	 }
}