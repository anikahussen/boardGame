package project1_boardGame;

import java.util.*;



/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * A basic doubly linked list implementation.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class DoublyLinkedList<E> {

  //---------------- nested Node class ----------------
  /**
   * Node of a doubly linked list, which stores a reference to its
   * element and to both the previous and next node in the list.
   */
  private static class Node<E>  {

    /** The element stored at this node */
    private E element;               // reference to the element stored at this node

    /** A reference to the preceding node in the list */
    private Node<E> prev;            // reference to the previous node in the list

    /** A reference to the subsequent node in the list */
    private Node<E> next;            // reference to the subsequent node in the list
    
    
    /** A reference to board position color */
    Random r = new Random();
    ArrayList <String> colors = new ArrayList <String>(Arrays.asList("g", "y", "p", "r", "o", "p", "b", "i", "bl", "sf"));
    String color = colors.get(r.nextInt(colors.size()));
    
    /** Booleans checking player occupations  */
	ArrayList <Player> occupyingPlayers = new ArrayList<Player>();
    
    
    /** Counting occupancy **/
    private int currentOccupy = 0;
    

    
    

    /**
     * Creates a node with the given element and next node.
     *
     * @param e  the element to be stored
     * @param p  reference to a node that should precede the new node
     * @param n  reference to a node that should follow the new node
     */
    public Node(E e, Node<E> p, Node<E> n) {
      element = e;
      prev = p;
      next = n;
      color = colors.get(r.nextInt(8));
      
    }

    // public accessor methods

    /**
     * Returns the occupy status of the node.
     */
    public int getOccupy() { return currentOccupy; }
    
    /**
     * Sets occupy status of the node.
     */
    public void setOccupy(Player x) { 
    	occupyingPlayers.add(x);
    		currentOccupy++;
    }
    
    /**
     * Remove player from board node space.
     */
    public void remOccupy(Player x) { 
    			occupyingPlayers.remove(x);
    			currentOccupy--;
        		
    }
    
    
    
    /**
     * Returns the color associated with the node.
     */
    public String getColor() { return color; }
     
    
    /**
     * Returns the element stored at the node.
     * @return the element stored at the node
     */
    public E getElement() { return element; }

    /**
     * Returns the node that precedes this one (or null if no such node).
     * @return the preceding node
     */
    public Node<E> getPrev() { return prev; }

    /**
     * Returns the node that follows this one (or null if no such node).
     * @return the following node
     */
    public Node<E> getNext() { return next; }

    // Update methods
    /**
     * Sets the node's previous reference to point to Node n.
     * @param p    the node that should precede this one
     */
    public void setPrev(Node<E> p) { prev = p; }

    /**
     * Sets the node's next reference to point to Node n.
     * @param n    the node that should follow this one
     */
    public void setNext(Node<E> n) { next = n; }
  } //----------- end of nested Node class -----------

  // instance variables of the DoublyLinkedList
  /** Sentinel node at the beginning of the list */
  private Node<E> header;                    // header sentinel

  /** Sentinel node at the end of the list */
  private Node<E> trailer;                   // trailer sentinel
  
 
  
  

  /** Number of elements in the list (not including sentinels) */
  private int size = 0;                      // number of elements in the list

  /** Constructs a new empty list. */
  public DoublyLinkedList() {
    header = new Node<>(null, null, null);      // create header
    trailer = new Node<>(null, header, null);   // trailer is preceded by header
    header.setNext(trailer);                    // header is followed by trailer
  }

  // public accessor methods
  
  /** 
   * Get Score
   */
  public E getScore(int position) {
	  Node<E> walk = header.getNext();
	  for (int i = 0; i < size; i++) {
		  if (i==position) {
			  return walk.getElement();
		  }
		  else {
			  walk = walk.getNext();
		  }
		  
	  }        
	  return null; //error
	   
	 } 
  
  
  
  /**
   * Returns the number of elements in the linked list.
   * @return number of elements in the linked list
   */
  public int size() { return size; }

  /**
   * Tests whether the linked list is empty.
   * @return true if the linked list is empty, false otherwise
   */
  public boolean isEmpty() { return size == 0; }

  /**
   * Returns (but does not remove) the first element of the list.
   * @return element at the front of the list (or null if empty)
   */
  public E first() {
    if (isEmpty()) return null;
    return header.getNext().getElement();   // first element is beyond header
  }

  /**
   * Returns (but does not remove) the last element of the list.
   * @return element at the end of the list (or null if empty)
   */
  public E last() {
    if (isEmpty()) return null;
    return trailer.getPrev().getElement();    // last element is before trailer
  }
  
  
  
 

  // public update methods
  /** 
   * Move and occupy
   */
  public void move(int c, int m, Player x) {
	  Node<E> walk = header.getNext();
	  if (c >=size) {
		  walk.setOccupy(x);
	  }
	  for (int i = 0; i < size; i++) {
		  if (i == c) {
			  walk.remOccupy(x);
		  }
		  if (i == c+m) {
			  walk.setOccupy(x);
			  break;
		  }
		  if ((i != c) && (i != c+m)) {
			  walk = walk.getNext();
		  }
	  }
	      
	      
  }
  
  
  
  /** 
   * Reset to front
   */
  public void front(Player x) {
	  Node<E>  walk = header.getNext();
	  for (int k=0; k<size; k++) {
		  if (k==0) {
			  walk.setOccupy(x);
		  } 
		  else {
			  walk = walk.getNext();
		  }
	  }
  }
  
  /** 
   * Reset
   */
  
  public void reset() {
	  Node<E>  walk = header.getNext();
	  walk.currentOccupy = 4;
	  while (walk != trailer) {
		  walk.currentOccupy = 0;
		  walk = walk.getNext();
	  }
	      
  }
  
  /** 
   * Check for occupancy
   */
  
  public boolean checkOccupy(int place) {
	  Node<E> walk = header.getNext();
	  for (int i = 0; i < size; i++) {
		  if (i==place) {
			  if (walk.getOccupy() > 0) {
				  
				  return true;
			  }
		  }
		  else {
			  walk = walk.getNext();
		  }
		  
	  }        
	  return false;  //error
	   
	 } 
  
  /**Check who occupy a current position**/
  public ArrayList<Player> whoOccupy(int pos){
	  ArrayList <Player> ocP = new ArrayList <Player>();
	  Node<E> walk = header.getNext();
	  for (int i = 0; i < size; i++) {
		  if (i==pos) {
			  for (int j=0; j<walk.occupyingPlayers.size(); j++) {
				  ocP.add(walk.occupyingPlayers.get(j));
			  }
		  }
		  else {
			  walk = walk.getNext();
		  }
	  }
	  return ocP;
  }
  
  //Draw a board 
  public void drawBoard(ArrayList<Player> occ) {
	    System.out.println("");
	    String boardprint = "";
	    
	   
	    //create a walk node
	    //make a variable that represents index of the node
	    //do a while loop to loop through game board
	    //do an inner for loop to loop through each player
	    //for each player, check if getPos == index 
	    //if they are equal, add player to board tile 
	    //if they aren't, do nothing
	    //print full board tile 
	    //increment index
	    
	    Node <E> walk = header.getNext().getNext();
	    int indexing = 0; 
	    boardprint+="|Start : ";
	    boardprint+="[";
	    boardprint+=walk.getColor();
	    boardprint+="]";
	    for (int p = 0; p<occ.size(); p++) {
	    	
    		if (occ.get(p).getPos() == indexing) {
    			boardprint += "(";
    			boardprint+=occ.get(p).getidNum();
    			boardprint += ")";
    		}
    		
    	}
	    boardprint += " |";
    	
    	indexing ++;
	    

	    while (walk!=trailer.getPrev().getPrev()) {
	    	walk=walk.getNext();
	    	boardprint += walk.getElement();
	    	boardprint += ": ";
	    	boardprint+="[";
		    boardprint+=walk.getColor();
		    boardprint+="]";
	    	for (int p = 0; p<occ.size(); p++) {
	    		
	    		if (occ.get(p).getPos() == indexing) {
	    			boardprint += "(";
	    			boardprint+=occ.get(p).getidNum();
	    			boardprint += ")";
	    		}
	    		
	    		
	    	}
	    	boardprint += " |";
	    	indexing ++;
	    }
	    
	    if (walk == trailer.getPrev().getPrev()) {
	    	
	    	boardprint+="|END : ";
	    	boardprint+="[";
		    boardprint+=walk.getColor();
		    boardprint+="]";
	    	for (int p = 0; p<occ.size(); p++) {
	    		if (occ.get(p).getPos() == indexing) {
	    			boardprint += "(";
	    			boardprint+=occ.get(p).getidNum();
	    			boardprint += ")";
	    		}
	    		if (occ.get(p).getPos() > size-1) {
	    			boardprint += "(";
	    			boardprint+=occ.get(p).getidNum();
	    			boardprint += ")";
	    		}
	    		
	    	}
	    	boardprint+="| ";
	    }	
	    
	    		System.out.println(boardprint);	
	    		System.out.println("");
	    
	    
	    
  }
	    		
	    		
	    	
	    
	   
	      
	      
	    
	    
	    
	     
  
  
  
  
  /**
   * Adds an element to the front of the list.
   * @param e   the new element to add
   */
  public void addFirst(E e) {
    addBetween(e, header, header.getNext());    // place just after the header
  }

  /**
   * Adds an element to the end of the list.
   * @param e   the new element to add
   */
  public void addLast(E e) {
    addBetween(e, trailer.getPrev(), trailer);  // place just before the trailer
    
  }

  /**
   * Removes and returns the first element of the list.
   * @return the removed element (or null if empty)
   */
  public E removeFirst() {
    if (isEmpty()) return null;                  // nothing to remove
    return remove(header.getNext());             // first element is beyond header
  }

  /**
   * Removes and returns the last element of the list.
   * @return the removed element (or null if empty)
   */
  public E removeLast() {
    if (isEmpty()) return null;                  // nothing to remove
    return remove(trailer.getPrev());            // last element is before trailer
  }

  // private update methods
  /**
   * Adds an element to the linked list in between the given nodes.
   * The given predecessor and successor should be neighboring each
   * other prior to the call.
   *
   * @param predecessor   node just before the location where the new element is inserted
   * @param successor     node just after the location where the new element is inserted
   */
  private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
    // create and link a new node
    Node<E> newest = new Node<>(e, predecessor, successor);
    predecessor.setNext(newest);
    successor.setPrev(newest);
    size++;
  }

  /**
   * Removes the given node from the list and returns its element.
   * @param node    the node to be removed (must not be a sentinel)
   */
  private E remove(Node<E> node) {
    Node<E> predecessor = node.getPrev();
    Node<E> successor = node.getNext();
    predecessor.setNext(successor);
    successor.setPrev(predecessor);
    size--;
    return node.getElement();
  }

  
  
  
  /**
   * Produces a string representation of the contents of the list.
   * This exists for debugging purposes only.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder("(");
    Node<E> walk = header.getNext();
    while (walk != trailer) {
      sb.append(walk.getElement());
      walk = walk.getNext();
      if (walk != trailer)
        sb.append(", ");
    }
    sb.append(")");
    return sb.toString();
  }
} //----------- end of DoublyLinkedList class -----------

