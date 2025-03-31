import java.util.*;

public class Deque<T>
{
    public final List<T> elements;

    public Deque()
    {
     elements = new LinkedList<>();


    }

    public void addFront(T item)
    {
     elements.addFirst(item);


    }

    public void addTail(T item)
    {
     elements.add(item);


    }

    public T removeFront()
    {
        if (elements.isEmpty()) {
            return null;
        }
        return elements.removeFirst();


    }

    public T removeTail()
    {
        if (elements.isEmpty()) {
            return null;
        }
        return elements.removeLast();


    }
        
    public int size()
    {
        return elements.size();


    }
}