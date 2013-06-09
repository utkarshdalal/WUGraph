package dict;

import list.DListNode;

/* Entry.java */



/**
 *  A class for dictionary entries.
 *
 *  DO NOT CHANGE THIS FILE.  It is part of the interface of the
 *  Dictionary ADT.
 **/

public class Entry {

  protected Object key;
  protected Object value;
  public DListNode e1;
  public DListNode e2;
  public DListNode listVertex;
  
  /** key() returns the key of this Entry
   * @return key
   */

  public Object key() {
    return key;
  }
  
  /** value() returns the value of this Entry 
   * @return value
   */

  public Object value() {
    return value;
  }
  
  /** changeWeight updates the value of this Entry when an edge with the same key but a different weight is added.
   * @param w is the value that value will be changed to.
   */
  
  public void changeWeight(int w){
	  value = w;
  }

}
