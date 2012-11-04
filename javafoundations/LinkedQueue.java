//********************************************************************
//LinkedQueue.java       Java Foundations
//
//Represents a linked implementation of a queue.
//********************************************************************

package javafoundations;

import javafoundations.exceptions.*;

public class LinkedQueue<T> implements Queue<T>
{
	private int count;
	private LinearNode<T> front, rear;

	//-----------------------------------------------------------------
	//Creates an empty queue.
	//-----------------------------------------------------------------
	public LinkedQueue()
	{
		count = 0;
		front = rear = null;
	}

	//-----------------------------------------------------------------
	//Adds the specified element to the rear of this queue.
	//-----------------------------------------------------------------
	public void enqueue (T element)
	{
		LinearNode<T> node = new LinearNode<T>(element);

		if (count == 0)
			front = node;
		else
			rear.setNext(node);

		rear = node;
		count++;
	}

	//-----------------------------------------------------------------
	//Removes the first element in the queue and returns it.
	//-----------------------------------------------------------------
	public T dequeue () throws EmptyCollectionException
	{
		if (count == 0)
			throw new EmptyCollectionException ("Dequeue operation failed. " +
				"The queue is empty.");
		
		T result = front.getElement();
		front = front.getNext();
		count--;
		
		return result;
	}
	
	//-----------------------------------------------------------------
	//Reads the first element in the queue and returns it.
	//-----------------------------------------------------------------
	public T first () throws EmptyCollectionException
	{
		if (count == 0)
			throw new EmptyCollectionException ("First operation failed. " +
				"The queue is empty.");
		
		T result = front.getElement();
		
		return result;
	}
	
	//-----------------------------------------------------------------
	//Checks if the queue is empty.  Returns true if empty, false
	//otherwise.
	//-----------------------------------------------------------------
	public boolean isEmpty ()
	{
		if (count == 0)
			return true;
		else
			return false;
	}
	
	//-----------------------------------------------------------------
	//Returns the current number of elements in the queue.
	//-----------------------------------------------------------------
	public int size ()
	{
		return count;
	}
	
	//-----------------------------------------------------------------
	//Returns a string representation of the queue.
	//-----------------------------------------------------------------
	public String toString()
	{
		String result = "<front of queue>\n";
		LinearNode current = front;

		while (current != null)
		{
		result += current.getElement() + "\n";
		current = current.getNext();
		}

		return result + "<end of queue>";
	}
}
