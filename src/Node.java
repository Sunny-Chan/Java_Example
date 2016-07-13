/** 
 *  Date: 03/02/2015
 *  Description:
 *  A node class to store a data of generic type
 *
 *  @author Wai Hei (Sunny) Chan
 */

//package part02;
public class Node<T> 
{
    // variables
    private T data;
    private Node<T> next;

    // constructors
    public Node(T newData)
    {
        this.data= newData;
        this.next = null;
    }

    public Node(T newData, Node<T> newNode)
    {
        this.data= newData;
        setNext(newNode);
    }

    // setter
    public void setNext(Node<T> newNode)
    {
        this.next = newNode;
    }

    // getter
    public Node<T> getNext()
    {
        return this.next;
    }

    public T getData()
    {
        return this.data;
    }

    /**
     * Overwrites toString()
     */
    public String toString()
    {
        return this.data.toString();
    }

}
