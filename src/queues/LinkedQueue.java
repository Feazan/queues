package queues;
import exceptionclasses.EmptyCollectionException;

/**
 * <p>Title: The LinkedQueue Class</p>
 *
 * <p>Description: Defines the properties and behaviors of a linked queue.</p>
 *
 * @author Feazan Yaseen, Johnny Sandoval
 */
public class LinkedQueue<E> implements QueueADT<E>
{
	protected Node<E> front, rear; //references to the first and last nodes

	/**
	 * default constructor - creates an empty queue
	 */
	public LinkedQueue()
	{
		front = rear = null;
	}
	
	/**
	 * enqueue method - adds the specified item to the rear of the queue
	 * @param newItem a reference to the item to be added to the queue
	 */
	public void enqueue (E newItem)
	{
		if(isEmpty())
			front = rear = new Node<E>(newItem);
		else 
		{
			rear.setNext(new Node<E>(newItem));
			rear = rear.getNext();
		}
	}

	/**
	 * dequeue method - removes the item at the front of the queue
	 * @return a reference to the item removed from the front of the queue
	 * @throws an EmptyCollectionException if the queue is empty
	 */
	public E dequeue()
	{
		if(isEmpty())
			throw new EmptyCollectionException("LinkedQueue ");
		E item = front.getItem();
		front = front.getNext();
		if(front == rear)
			rear = null;
			
		return item;
	}

	/**
	 * front method - returns a reference to the item at the front of the queue
	 * without removing it from the queue
	 * @return a reference to the item at the front of the queue
	 * @throws an EmptyCollectionException if the queue is empty  
	 */
	public E front() 
	{
		if(isEmpty())
			throw new EmptyCollectionException("LinkedQueue ");
		E result = front.getItem();
		return result;
	}

	/**
	 * isEmpty method - determines whether or not the queue is empty
	 * @return true if the queue is empty; false if the queue is not empty
	 */
	public boolean isEmpty()
	{
		if( front == null && rear == null)
			return true;
		else 
			return false;
	}

	/**
	 * size method - returns a count of the number of items in the queue
	 * @return the number of items in the queue
	 */
	public int size()
	{
		Node<E> current = front;
		int count = 0;
		while(current != null)
		{
			count++;
			current=current.getNext();
		}
		return count;
	}
	
	/**
	 * search method - Looks for an item in the Queue
	 * @param The item being searched
	 * @return The numerical distance from front of Queue
	 */
	public int search(E theItem)
	{
		int pos = 1; 
		Node<E> current = front;
		while(current != null)
		{
			if(current.getItem().equals(theItem))
				return pos;
			else
				pos++;
			current = current.getNext();
		}
		return -1;
	}
	
	/**
	 * removeLast method - Removes last item in the Queue
	 * @return The last item in the Queue
	 * @throws an EmptyCollectionException if the queue is empty
	 */
	public E removeLast()
	{
		if(isEmpty())
			throw new EmptyCollectionException("LinkedQueue");
		E item = rear.getItem();  
		Node<E> current = front;
		if (current != rear) // Queue is not size 1
        {
            while(current.getNext() != rear)
		    {
			    current = current.getNext();
		    }
            current.setNext(null);
		    rear = current;		
        }
        else  // Queue is size 1
        {
            front = rear = null;
        }
		return item;
	}
	
	/**
	 * toString method - returns a String representing the state of the queue
	 * @return a string containing all items in the queue
	 */
	public String toString()
	{
		Node<E> current = front;
		String str = "";
		while(current != null)
		{
			str = str + "\n" + current.getItem();
			current = current.getNext();
		}
		
		return str;
	}
}
