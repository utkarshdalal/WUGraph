/* Kruskal.java */

import dict.HashTableChained;
import list.*;
import graph.*;
import set.*;

/**
 * The Kruskal class contains the method minSpanTree(), which implements
 * Kruskal's algorithm for computing a minimum spanning tree of a graph.
 * 
 */

public class Kruskal {

  /**
   * minSpanTree() returns a WUGraph that represents the minimum spanning tree
   * of the WUGraph g.  The original WUGraph g is NOT changed.
   * 
   * Copies all vertices in g to the empty WUGraph that is going to be returned
   * and calls neighbors on each one immediately after. Stores all edges, including
   * duplicate edges, in a queue. O(2V) => O(|V|).
   * Sorts the queue with all possible edges based on their weights
   * from smallest to greatest using quicksort. O(|E| log |E|).
   * 
   * Start at beginning of the queue adding the edges into the WUGraph 
   * that is going to be returned if: 1) the edge doesn't exist in this graph and 2)
   * the two vertices that make up the edge are not part of the same set. 
   * For every edge you added join the two vertices that make up the edge into a set 
   * and continue until there is only one set left. 
   *  
   * Running Time: O(|V| + |E| log |E|)
   * @param g is WUGraph analyzed with Kruskal's algorithm but NOT changed.
   * @return WUGraph that contains the minimum spanning tree of the WUGraph g.
   * 
   */
  public static WUGraph minSpanTree(WUGraph g){
	  WUGraph minTree = new WUGraph();
	  LinkedQueue edges = new LinkedQueue();
	  Object[] allVertices =  g.getVertices();
	  HashTableChained vertices = new HashTableChained();
	  int numVertices = allVertices.length;
	  
	  if (numVertices == 0){
		  return minTree;
	  }
	  
	  else{
		  for (int i = 0; i< numVertices; i++) {
			  vertices.insert(allVertices[i], i);
			  
			  minTree.addVertex(allVertices[i]);
			  Neighbors neigh = g.getNeighbors(allVertices[i]);	 // 2 V 
			  
			  if(neigh != null){
				  for (int j = 0; j < neigh.neighborList.length; j++){ //the duplicates are in the queue
					  Edge current = new Edge(allVertices[i], neigh.neighborList[j]);
					  Entry e = new Entry(neigh.weightList[j], current);
					  edges.enqueue(e);
				  }
			  }
		  }
		  
		  ListSorts.quickSort(edges); //E log(E)
		  DisjointSets tree = new DisjointSets(numVertices);
		  

		  //add min edges and unite sets based on Kruskals
		  while (!edges.isEmpty()){
			  try {
				  Entry counter = (Entry) edges.dequeue();
				  Object vertex1 = ((Edge) counter.value()).origin(); //IN QUEUE key = weight, value = edge pair
				  int index1 = (Integer) vertices.find(vertex1).value(); //IN HASH key = vertex, value = int
				  Object vertex2 = ((Edge) counter.value()).destination();
				  int index2 = (Integer) vertices.find(vertex2).value();
				  int k = (Integer) counter.key();
				  
				  int root1 = tree.find(index1);
				  int root2 = tree.find(index2);
			
			  	  
				  if (root1 != root2 && (!minTree.isEdge(vertex1, vertex2)) ){ //if dequeued edge is not in minTree and not self edge
					  minTree.addEdge(vertex1, vertex2, k);
					  tree.union(root1, root2);
				  }
				} catch (QueueEmptyException e) {
					e.printStackTrace();
				}
				  
		  }
		  
		  return minTree;
	  }
  }
  
  
}
