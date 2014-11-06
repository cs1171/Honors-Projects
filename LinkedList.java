/**
 * Christopher Soto
 * COSC 1437, Lab02, LinkedList
 * LinkedList class which holds all linked list
 * methods/constructors
 **/
class LinkedList
{
  // node class
  public class Node
  {
    FingerPrint value;
    Node next;
    
    // default Node constructor
    public Node()
    {
      next = null;
      value = null;
    }
    
    /**
     * Constructor accepting FingerPrint object
     * and next node in list
     * @param fp FingerPrint object
     * @param n next node in list
     **/
    public Node(FingerPrint fp, Node n)
    {
      value = fp;
      next  = n;
    }
    
    /**
     * Constructor accepting FingerPrint object
     * @param fp FingerPrint object
     **/
    public Node(FingerPrint fp)
    {
      value = fp;
      next = null;
    }
  }
  
  public Node first;
  public Node last;
  
  // default constructor
  public LinkedList()
  {
    first = null;
    last = null;        
  }
  
  /**
   * Constructor that creates a linked list
   * using an array of FingerPrint objects
   * @param fpd array of FingerPrint objects
   **/
  public LinkedList(FingerPrint[] fpd)
  {
    for(FingerPrint fp:fpd)
    {
      first = new Node(fp, first);
      last = new Node(fp, last);
    }
    
//    for(int i = 0;i < fpd.length;i++)
//    {
//      first = new Node(fpd[i], first);
//      last = new Node(fpd[i], last);
//    }
    
  }
  
  /**
   * Method to check if linked list is empty
   * @return true if empty, false if not empty
   */
  public boolean isEmpty()
  {        
    return first == null;       
  }
  
  /**
   * Find method searches LinkedList for specific
   * FingerPrint object
   * @return true if found, false if not
   **/
  public boolean find(FingerPrint fp)
  {
    if(isEmpty())
      return false;
    
    Node temp = first;
    
    while(temp!= null)
    {
      if(temp.value.equals(fp))
        return true;
      temp = temp.next;
    }
    
    return false;
  }
  
  /**
   * Size method to get size of linked list
   * @return int counter, length of list
   **/
  public int size()
  {
    int counter = 0;
    Node p = first;     
    
    while (p != null)
    {
      counter++;
      p = p.next;
    }
    
    return counter;
  }
  
  /**
   * Add method to add nodes to end of list
   * @param fp FingerPrint object to add to list    
   **/
  public void add(FingerPrint fp)
  {
    if (isEmpty()) 
    {
      first = new Node(fp);
      last = first;
    }
    else
    {
      last.next = new Node(fp);
      last = last.next;
    }      
  }
  
  /**
   * Add method to add a FingerPrint object
   * to linked list at a specific position
   * @param fp FingerPrint object to add to list
   * @param index position to add the object to list
   * @exception IndexOutOfBoundsException if index is
   * out of bounds.  
   */
  public void add(int index, FingerPrint fp)
  {
    // check if index is out of bounds
    if (index < 0  || index > size()) 
    {
      String message = String.valueOf(index);
      throw new IndexOutOfBoundsException(message);
    }
    
    // if index is 0, adds to start of list
    if (index == 0)
    {
      first = new Node(fp, first);
      if (last == null)
        last = first;
      return;
    }
    
    // Set a reference pred to point to the node that
    // will be the predecessor of the new node
    Node pred = first;        
    for (int k = 1; k <= index - 1; k++)        
    {
      pred = pred.next;           
    }
    
    // Splice in a node containing the new element
    pred.next = new Node(fp, pred.next);  
    
    // Checks/sets new last element
    if (pred.next.next == null)
      last = pred.next;         
  }
  
  /**
   * toString method to turn LinkedList
   * into a readable String
   * @return string form of linked list
   **/
  public String toString()
  {
    String strBuilder = "";
    Node p = first;
    
    while (p != null)
    {
      strBuilder+=""+p.value + "-> "; 
      p = p.next;
    }
    
    return strBuilder.toString(); 
  }
  
  /**
   * Remove method to remove node from specific point
   * in linked list
   * @param index the index of the element to remove 
   * @return the removed element
   * @exception IndexOutOfBoundsException if index is 
   * out of bounds. 
   **/
  public FingerPrint remove(int index)
  {
    if (index < 0 || index >= size())
    {  
      String message = String.valueOf(index);
      throw new IndexOutOfBoundsException(message);
    }
    
    FingerPrint temp;
    
    // removing first element of list
    if (index == 0)
    {
      temp = first.value;    
      first = first.next;
      
      // check if new first is null/erase list
      if (first == null)
        last = null;             
    }
    else
    {
      // To remove an element other than the first,
      // find the predecessor of the element to
      // be removed.
      Node pred = first;
      
      // Move pred forward index - 1 times
      for (int k = 1; k <= index -1; k++)
        pred = pred.next;
      
      // Store the data to return
      temp = pred.next.value;
      
      // Route link around the node to be removed
      pred.next = pred.next.next;  
      
      // Check if pred is now last
      if (pred.next == null)
        last = pred;
    }
    
    return temp;
  }  
  
  /**
   * The remove method removes an element.
   * @param element the element to remove.
   * @return true if the remove succeeded, 
   * false otherwise.
   **/
  public boolean remove(FingerPrint element)
  {
    if (isEmpty()) 
      return false;      
    
    if (element.equals(first.value))
    {
      // Removal of first item in the list
      first = first.next;
      if (first == null)
        last = null;       
      return true;
    }
    
    // Find the predecessor of the element to remove
    Node pred = first;
    while (pred.next != null && 
           !pred.next.value.equals(element))
    {
      pred = pred.next;
    }
    
    // pred.next == null OR pred.next.value is element
    if (pred.next == null)
      return false;
    
    // pred.next.value  is element
    pred.next = pred.next.next;    
    
    // Check if pred is now last
    if (pred.next == null)
      last = pred;
    
    return true;       
  }
  
  /**
   * Method to clear linked list
   * sets first node to null
   **/
  public void makeEmpty()
  {
    first = null;
  }
  
  /**
   * Get method, retreives node value at index i
   * @param i  index of node in linkedlist
   * @return value of node at index
   **/
  public FingerPrint get(int index)
  {
    int counter = 0;
    Node p = first;     
    
    while (counter != (index-1))
    {
      counter++;
      p = p.next;
    }
    
    return p.value;
  }
}
