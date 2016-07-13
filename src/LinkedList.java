/** 
 *  Date: 03/02/2015
 *  Description:
 *  A generic linked list class that implements generic Iterable interface
 *  to connect country nodes 
 *
 *  @author Wai Hei (Sunny) Chan
 */

//package part02;
import java.util.Iterator;
public class LinkedList<T> implements Iterable<T> 
{
    // variables
    private Node<T> head;
    private int nodeCount;
    
    // constructor
    public LinkedList()
    {
        this.head = null;
        this.nodeCount = 0;
    }

    // add new node to the linked list
    public void add(T newData)
    {
        Node<T> current = this.head; 
        // Check if list is empty, if so, head link to first node
        if(this.isEmpty() == true)
        {
            this.head = new Node<T>(newData);
            this.head.setNext(null);
        }
        // if not empty, tranverse to the end of list and add to end 
        else
        {
            while(current.getNext() != null)
                current = current.getNext();
            Node<T> newEndNode = new Node<T>(newData);
            current.setNext(newEndNode);
        }
        this.nodeCount++;
    }
    
    public void insertAtIndex(T newData, int index)
    { 
        Node<T> before = this.head; 
        Node<T> after = this.head.getNext(); 
        // if adding to the end of the list
        System.out.println("node count: " + nodeCount);
        System.out.println("index: " + index);
        if(index-1 == this.nodeCount)
            add(newData);
        else if(index == 1)
        {
            Node<T> newEndNode = new Node<T>(newData);
            newEndNode.setNext(before);
            this.head = newEndNode;
        }
        else 
        {
            if(indexValid(index))
            {
                for(int i=0; i<index-1; i++)
                {
                    before = before.getNext();
                    after = after.getNext();
                }
                Node<T> newEndNode = new Node<T>(newData);
                before.setNext(newEndNode);
                newEndNode.setNext(after);
            }
            else
            {
                System.out.println("Wrong input of index");
                return;
            }
        }
        this.nodeCount++; 
    }

    // validate the index parameter of insertAtIndex()
    private boolean indexValid(int index)
    {
        if(index < this.nodeCount)
            return true;
        return false;
    }

    public int getNodeCount()
    {
        return nodeCount;
    }

      // Check's if head is pointing to any node
    public boolean isEmpty()
    {
        if (this.head == null)  // alternatively, check this.length == 0;
            return true;
        return false;
    }

    // find the request country in the linked list 
    public T contains(T checkData)
    {
        Node<T> current = this.head; 

        while(current!=null)
        {
            T info = current.getData();
            if(((Country)info).equals(checkData))
                return current.getData();;
            current = current.getNext();
        }
        return null;
        
        /*
         * Sunny's previous attempt
        while(current.getData().equals(checkData) == false)
        {
            if(current.getNext() == null)
                return null;
            current = current.getNext();
        }
        return current.getData();
        */
    }

    public String toString()
    {
        Node<T> current = this.head; 
        String string;
        string = "\n";
        while(current.getNext() != null)
        {
            string += current.getData() + "\n";
            current = current.getNext();
        }
        string += current.getData() + "\n";
        return string;
    }


    /**
     * create an iterator object htat starts at the beginning of the list
     */
    public Iterator<T> iterator()
    {
        return new ListIterator();
    }

    /**
     * Inner class of LinkedList used to traverse the collection of 
     * objects in the list
     *
     * This defines the generic iterable interface
     */
    private class ListIterator implements Iterator<T>
    {
        private Node<T> current;

        // contructor setting iterator to head of list
        public ListIterator()
        {
            current = head;
        }

        /**
         * check if there is next node in the list
         */
        public boolean hasNext()
        {
            if(current == null)
                return false;
            return true;
        }

        public T next()
        {
            // throw error if empty
            if(!hasNext())
                throw new java.util.NoSuchElementException();
            // if there is data, get it
            T data = current.getData();
            // increment for next call
            current = current.getNext();
            return data;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

    }
}
