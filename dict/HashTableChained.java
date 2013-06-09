package dict;
/* HashTableChained.java */


import dict.Entry;
import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {
	
	list.SList [] hash;
	int length;
	int size;


  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/
	

  public HashTableChained(int sizeEstimate) {
	  hash = new list.SList[sizeEstimate];
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
	  hash = new list.SList[101];
	  size = 101;
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
	  int val = Math.abs((((4*code + 13) % 16908799) % hash.length));
	  return val;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
	  return length;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
	  if (length == 0){
		  return true;
	  }
	  else{
		  return false;
	  }
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
	  if ((double) (length/size) >= 0.75){
		  HashTableChained temp = new HashTableChained (size * 2);
		  rehash(temp);
		  this.hash = temp.hash;
		  size = temp.size;
		  length = temp.length;
	  }
	  Entry i = new Entry();
	  i.key = key;
	  i.value = value;
	  int hashval = key.hashCode();
	  hashval = compFunction(hashval);
	  if (hash[hashval] != null){
		  hash[hashval].insertBack(i);
	  }
	  else{
		  list.SList s = new list.SList();
		  s.insertBack(i);
		  hash[hashval] = s;
	  }
	  this.length++;
	  return i;
  }
  
  /**
   *  rehash updates a HashTableChained object with size double the size of this.size with this hash[]'s elements.
   *  It takes a HashTableChained t as a parameter (which is ideally a new HashTableChained()) and returns t updated with all the 
   *  current hash table's elements.
   **/
  
  public void rehash(HashTableChained t){
	  for (list.SList i : this.hash){
		  SListNode temp = (SListNode) i.front();
		  while (temp.isValidNode()){
			  try{
				  t.insert(((Entry) temp.item()).key(), ((Entry) temp.item()).value());
				  temp = (SListNode) temp.next();
			  }
			  catch (InvalidNodeException e){
				  System.out.println(e);
			  }
		  }
	  }
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/
  
  public Entry find(Object key) {
	  int hashval = compFunction(key.hashCode());
	  list.SList currl = hash[hashval];
	  if (currl == null){
		  return null;
	  }
	  else{
		  list.SListNode currn = null;
		  try{
			  currn = (SListNode) currl.front();
			  while (true){
				  if (((Entry) currn.item()).key().equals(key)){
					  return (Entry) currn.item();
				  }
				  else{
					  currn = (SListNode) currn.next();
				  }
			  }
		  }
		  catch (InvalidNodeException e){
			  return null;
		  }		  
	  }	  
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
	  int hashval = compFunction(key.hashCode());
	  list.SList currl = hash[hashval];
	  if (currl == null){
		  return null;
	  }
	  else{
		  SListNode currn = (SListNode) currl.front();
		  try{
			  while(true){
				  if (((Entry)currn.item()).key().equals(key)){
					  Entry e = (Entry) currn.item();
					  currn.remove();
					  this.length--;
					  return e;
				  }
				  else{
					  currn = (SListNode) currn.next();
				  }
			  }
		  }
		  catch (InvalidNodeException e){
			  return null;
		  }
		  
	  }
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
	  this.hash = new list.SList[hash.length];
	  this.length = 0;
  }

}
