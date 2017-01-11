package queues;
import exceptionclasses.*;

/**
 * <p>Title: CircularArrayQueue Class</p>
 *
 * <p>Description: Provides basic Queue functionality, including the ability
 * to enqueue and dequeue items to/from the queue, get the front-most item, 
 * determine whether or not the queue is empty, determine the queue's size, 
 * and to get a String representation of the items in the queue.</p>
 *
 * @author Feazan Yaseen, Zain Butt
 */
public class CircularArrayQueue<E> implements QueueADT<E>
{

	protected int front;
	protected int rear;
	protected E[] contents;
	protected int count;

	/**
	 * default constructor -- creates an empty queue.
	 */
	@SuppressWarnings("unchecked")
	public CircularArrayQueue()
	{
		front = 0;
		rear = 0;
		contents = (E[]) (new Object[100]);
		count = 0;
	}

	/**
	 * parameterized constructor --
	 * creates an empty queue that is initially capable of storing 
	 * 'size' items.
	 * @param size the initial size of the queue as specified by the user
	 */
	@SuppressWarnings("unchecked")
	public CircularArrayQueue(int size)
	{
		front = 0;
		rear = 0;
		if (size > 0)
			contents = (E[]) (new Object[size]);
		else
			contents = (E[]) (new Object[100]);
		count = 0;
	}

	/**
	 * enqueue --
	 * stores a new item at the rear of the queue; if the queue becomes
	 * full, it's size is automatically increased to accommodate additional items.
	 * @param newItem a reference to the item to be stored at the rear of the queue
	 */
	public void enqueue(E newItem)
	{
		if(count == contents.length)
			expandCapacity();
		
			contents[rear] = newItem;
			rear = (rear + 1) % contents.length;
			count++;
	}

	/**
	 * deQueue -- removes the front-most item from the queue.
	 * @return a reference to the object which was stored at the front of the
	 * queue
	 * @throws QueueException if the queue is empty
	 */
	public E dequeue()
	{
		if(isEmpty())
			throw new EmptyCollectionException("CircularArrayQueue");

		E item = contents[front];
		contents[front] = null; 
		front = (front +1) % contents.length;

		count--;			

		return item;
	}


	/**
	 * front --
	 * returns the item stored at the front of the queue; the queue 
	 * is not modified.
	 * @return a reference to the object which is stored at the front of the queue
	 * @throws QueueException if the queue is empty
	 */
	public E front()
	{
		if (isEmpty())
			throw new EmptyCollectionException("CircularArrayQueue");
		else
			return contents[front];
	}

	/**
	 * isEmpty -- determines whether or not the queue is empty.
	 * @return true if the queue is empty; false otherwise
	 */
	public boolean isEmpty()
	{
		return count == 0;
	}

	/**
	 * count -- returns the count of the number of items in the queue.
	 * @return count
	 */
	public int size()
	{
		return count;
	}

	/**
	 * expandCapacity --
	 * a private method called upon by the enqueue method when the queue 
	 * becomes full; the queue size is doubled to accommodate the storage of
	 * additional items.
	 */
	@SuppressWarnings("unchecked")
	private void expandCapacity()
	{

		E[] temp = (E[]) (new Object[(contents.length * 2)]); 
		for ( int i = 0; i < contents.length; i++)
		{
			temp[i] = contents[front];
			front = (front + 1) % contents.length;

		}
		front = 0;
		rear = count;
		contents = temp; 

	}

	/**
	 * toString method - returns a String representing the current state of the queue.
	 * @return a String containing all items in the queue
	 */
	public String toString()
	{
		String str = "";
		int current = front; 

		for (int i = 0; i < count; i++)
		{
			str += contents[current].toString() + "\n";
			current = (current + 1) % contents.length;
		}
		return str;
	}
}
