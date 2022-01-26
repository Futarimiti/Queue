import java.util.Arrays;
import static java.lang.String.join;
import static java.lang.String.format;

public class ArrayQueue<T>
{
	private int front = -1;
	private int rear = -1;
	final int maxSize;
	final Object[] queue;
	final T defaultVal;

	public ArrayQueue(int maxSize)
	{
		this.maxSize = maxSize;
		this.queue = new Object[maxSize];
		defaultVal = null;
	}

	public ArrayQueue(int maxSize , T defaultVal)
	{
		this.maxSize = maxSize;
		this.defaultVal = defaultVal;
		this.queue = new Object[maxSize];
		Arrays.fill(this.queue , defaultVal);
	}

	public boolean enqueue(T elem)
	{
		if (this.isFull()) return false;

		this.rear++;
		this.queue[rear] = elem;
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

		// the type cast throws compiler warning.
		// disabled with annotation.
		T res = (T)this.queue[front];

		// restoring this elem to $defaultVal.
		this.queue[front] = defaultVal;

		return res;
	}

	public boolean isFull()
	{
		return this.rear == this.maxSize - 1;
	}

	public boolean isEmpty()
	{
		return this.rear == this.front;
	}

	public int size()
	{
		return this.rear - this.front;
	}

	@SuppressWarnings("unchecked")
	public T peek()
	{
		String err = "Cannot dequeue an empty queue";
		if (this.isEmpty()) throw new RuntimeException(err);

		return (T)this.queue[front + 1]; // suppresed warning
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
