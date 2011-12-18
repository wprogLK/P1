
public class Queue<T>
{
	Node rear;
	Node front;
	int size;
	
	public Queue()
	{
		this.rear = null;
		this.front = null;
		this.size = 0;
	}
	
	public T dequeue() throws EmptyQueueException
	{
		if (this.size == 0)
			throw new EmptyQueueException("Queue is empty!");
		
		T ret = front.element;
		
		if (this.size == 1)
		{
			this.rear = null;
			this.front = null;
		}
		else
		{
			Node newFront = front.prev;
			front.prev = null;
			newFront.next = null;
			this.front = newFront;
		}
		
		this.size--;
		
		return ret;
	}
	
	public void enqueue(T element)
	{
		Node newNode = new Node(element, this.rear, null);
		
		if (this.size == 0)
		{
			this.rear = newNode;
			this.front = newNode;
		}
		else
		{
			this.rear.prev = newNode;
			this.rear = newNode;
		}
		
		this.size++;
	}
	
	public boolean isEmpty()
	{
		return (this.size == 0);
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		for (Node current = this.rear; current != null; current = current.next)
			sb.append(current.element + ",");
		
		sb.append("]");
		return sb.toString();
	}
	
	private class Node
	{
		T element;
		Node next;
		Node prev;
		
		public Node(T element, Node next, Node prev)
		{
			this.element = element;
			this.next = next;
			this.prev = prev;
		}
	}
}
