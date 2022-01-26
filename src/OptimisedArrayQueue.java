import java.util.Arrays;
import static java.lang.String.join;
import static java.lang.String.format;

public class OptimisedArrayQueue<T>
{
	private int front = -1;
	private int rear = -1;
	final int maxSize;
	final Object[] queue;
	final T defaultVal;

	public OptimisedArrayQueue(int maxSize)
	{
		this.maxSize = maxSize;
		this.queue = new Object[maxSize];
		defaultVal = null;
	}

	public OptimisedArrayQueue(int maxSize , T defaultVal)
	{
		this.maxSize = maxSize;
		this.defaultVal = defaultVal;
		this.queue = new Object[maxSize];
		Arrays.fill(this.queue , defaultVal);
	}

	private static int index(int cursor , int length)
	{
		return cursor % length;
	}

	public boolean enqueue(T elem)
	{
		if (this.isFull()) return false;

		this.rear++;
		this.queue[index(rear , maxSize)] = elem;
		return true;
	}

	@SuppressWarnings("unchecked")
	public T dequeue()
	{
		String err = "Cannot dequeue an empty queue";
		if (this.isEmpty()) throw new RuntimeException(err);

		// front always point to the element *before* head
		// so front++ points to the dequeuing elem.
		this.front++;

		int frontIndex = index(front , maxSize);

		// the type cast throws compiler warning.
		// disabled with annotation.
		T res = (T)this.queue[frontIndex];

		// restoring this elem to $defaultVal.
		this.queue[frontIndex] = defaultVal;

		return res;
	}

	public boolean isFull()
	{
		return (rear - front == maxSize);
	}

	public boolean isEmpty()
	{
		return front == rear;
	}

	public int size()
	{
		return rear - front;
	}

	@SuppressWarnings("unchecked")
	public T peek()
	{
		String err = "Cannot dequeue an empty queue";
		if (this.isEmpty()) throw new RuntimeException(err);

		int frontIndex = index(front , maxSize);

		if (frontIndex + 1 == maxSize) return (T)this.queue[0];
		return (T)this.queue[frontIndex + 1];
	}

	@Override
	public String toString()
	{
		return toString(" , ");
	}

	public String toString(String sep)
	{
		String[] body = new String[this.maxSize];
		for (int i = 0 ; i < maxSize ; i++)
		{
			Object o = this.queue[i];

			body[i] = (o == null ? "" : o.toString());
		}

		return format("[%s]" , join(sep , body));
	}
}
