/**
 * @author Conor Pasley
 * Student number: R00205997
 * Implementation of the interface Graph with adjacency matrix.
 */
import java.util.LinkedList;


public class GraphAdjMatrix implements Graph{
	// ATTRIBUTES: 
    private boolean directed;
    private int edges;
    private int[][] graphMatrix;
    private int vertices;
 
    
    // CONSTRUCTOR: Creates a directed/undirected graph with V vertices and no edges
    public GraphAdjMatrix(int V, boolean directed) {
        this.graphMatrix = new int[V][V]; // creates 2d list of int length v
        //sets each cell of the matrix to 0
        for(int i = 0; i< V; i++){
            for(int j = 0; i < V;i++){
                this.graphMatrix[i][j] = 0;
            }
        }
        this.directed = directed;
        this.vertices = V;
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
        //if the weight is greater than 0
        if(w > 0){
            try{
                //If the edge doesnt exist
                if(graphMatrix[v1][v2] == 0) edges++;

                //set the weight of the edge 
                graphMatrix[v1][v2] = w;

                //set the weight of the edge in the opposite direction if undirected
                if(!directed) graphMatrix[v2][v1] = w;

            } catch (IndexOutOfBoundsException e){
                if(v1 > this.vertices)
                    System.out.println("Vertices id "+ v1 + " is out of bounds");
                else
                    System.out.println("Vertices id "+ v2 + " is out of bounds");
            }
        } else System.out.println("Weight must be greater than 0");
    }
    
   // 4. IMPLEMENTATION METHOD removeEdge:
   public void removeEdge (int v1, int v2){
        //TO-DO
        try{
            //set the weight to 0
            graphMatrix[v1][v2] = 0;
            if(!directed) graphMatrix[v2][v1] = 0; 
            this.edges--;

        } catch (IndexOutOfBoundsException e){
            if(v1 > this.vertices)
                System.out.println("Vertices id "+ v1 + " is out of bounds");
            else
                System.out.println("Vertices id "+ v2 + " is out of bounds");
        }
   }

    // 5. IMPLEMENTATION METHOD hasEdge:
    public boolean hasEdge(int v1, int v2) {
        //TO-DO
        try{
            //returns true if weight is greater than 0
            return graphMatrix[v1][v2] > 0;
        } catch(IndexOutOfBoundsException e){
            if(v1 > this.vertices)
                System.out.println("Vertices id "+ v1 + " is out of bounds");
            else
                System.out.println("Vertices id "+ v2 + " is out of bounds");
            return false;
        }
    }
    
    // 6. IMPLEMENTATION METHOD getWeightEdge:
	public int getWeightEdge(int v1, int v2) {
		//TO-DO
        try{
            //returns the weight of the edge
            return graphMatrix[v1][v2];
        } catch (IndexOutOfBoundsException e){
            if(v1 > this.vertices)
                System.out.println("Vertices id "+ v1 + " is out of bounds");
            else
                System.out.println("Vertices id "+ v2 + " is out of bounds");
            return -1;
        }
    }

	// 7. IMPLEMENTATION METHOD getNeighbors:
	public LinkedList getNeighbors(int v){
        try{
            LinkedList list = new LinkedList<Integer>(); //creates empty LinkedList
            for(int j = 0; j < this.vertices; j++){
                if(this.graphMatrix[v][j] != 0) list.add(j); // adds each edge of vertex
            }
            return list;
        } catch (IndexOutOfBoundsException e){
            System.out.println("Vertex is out of bounds");
            return null;
        }
	}
   	
	// 8. IMPLEMENTATION METHOD getDegree:
	public int getDegree(int v) {
        //TO-DO
        int outDegree = 0;
        int inDegree = 0;
        try{
            //checking the column of the vertex v 
            for(int i = 0; i < this.vertices; i++){
                if(this.graphMatrix[i][v] > 0) inDegree ++; 
            }
            if(directed){
            //checking the row of the vertex v 
                for(int j = 0; j < this.vertices; j++){
                    if(this.graphMatrix[v][j] > 0) outDegree ++; 
                } 
            }

            return inDegree + outDegree;
        } catch (IndexOutOfBoundsException e){
            System.out.println("Vertices is out of bounds");
            return -1;
        }
	}
    
	// 9. IMPLEMENTATION METHOD toString:
   	public String toString(){
        String string = "| V ||";
        for(int i = 0; i < this.vertices; i++){
            string +=" " + i + " |";
        }
        string += "\n";
        string += "=========================\n";
        for(int i = 0; i < this.vertices ; i++){
            string += "| " + i + " ||";
            for(int j = 0; j < this.vertices;j++){
                string +=  " " + this.graphMatrix[i][j] + " |";   
            }
            string += "\n";
        }

        return string;
    }    
}