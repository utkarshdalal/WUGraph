/* ListSorts.java */

import list.*;

public class ListSorts {

  
  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
	  while (!qIn.isEmpty()){
		  try {  
			if (((Comparable) ((Entry) qIn.front()).key()).compareTo(pivot) == 0){
				  Object insertion = qIn.dequeue();
				  qEquals.enqueue(insertion);
			  }
			  else if (((Comparable) ((Entry) qIn.front()).key()).compareTo(pivot) < 0){ //qIn front is less than pivot
				  Object insertion = qIn.dequeue();
				  qSmall.enqueue(insertion);
			  }
			  else{ //qIn front is more than pivot
				  Object insertion = qIn.dequeue();
				  qLarge.enqueue(insertion);
			  }
		} catch (QueueEmptyException e) {
			e.printStackTrace();
		}
		  
	  }
	
  }

  

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   *  Implement quickSort(), which sorts a LinkedQueue q as follows.  Choose a
random integer between 1 and q.size().  Find the corresponding item using the
nth() method, and use the item as the pivot in a call to partition().
Recursively quickSort() the smaller and larger partitions, and then put all of
the items back into q in sorted order by using the append() method.

   **/
  public static void quickSort(LinkedQueue q) {
  	  
	  LinkedQueue qLarge = new LinkedQueue();
	  LinkedQueue qEquals = new LinkedQueue();
	  LinkedQueue qSmall = new LinkedQueue();
	  
	  if(!q.isEmpty()){
		 int number = new Integer((int) (q.size() * Math.random()));
		 Entry e = (Entry) q.nth(number);
		 Comparable pivot = (Comparable) e.key();
		 partition(q,pivot,qSmall,qEquals,qLarge);
		 quickSort(qSmall);
		 quickSort(qLarge);
	  } 
	  
	  else {
		 return;
		  
	  }
	  q.append(qSmall);
	  q.append(qEquals);
	  q.append(qLarge);
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   **/
  public static void main(String [] args) {

    LinkedQueue q = makeRandom(10);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    LinkedQueue qq = new LinkedQueue();
    System.out.println(qq.toString());
    quickSort(qq);
    System.out.println(qq.toString());
   
    /**
    Timer stopWatch = new Timer();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");
 **/
  }

}
