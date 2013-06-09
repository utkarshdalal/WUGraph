/* Edge.java */	
	/**
	 * The Edge class stores a vertex pair and is used by Kruskal to keep
	 * a representation of an edge. The origin and destination are irrelevant
	 * because the graph manipulated by Kruskal's is undirected.
	 * 
	 */
public class Edge {

	protected Object origin;
	protected Object destination;

	 /**
	   *  Construct an Edge.
	   *
	   *  @param o is the object that represents the starting vertex in the Edge.
	   *  @param d is the object that represents the destination vertex in the Edge.
	   *  
	   **/
     public Edge(Object o, Object d){
    	 origin = o;
    	 destination = d;
     }
	
     
     /**
      * origin() returns the object representing the starting vertex "origin." 
      * @return the vertex origin.
      */
	 public Object origin () {
		 return origin;
	 }

	 /**
      * destination() returns the object representing the "destination" vertex.
      * @return the vertex destination.
      */
	 public Object destination() {
		 return destination;
	 }
}
