import java.util.*;

public class Queue<T>
{
    public final List<T> values;

      public Queue()
      {
        this.values = new LinkedList<>();


      } 

      public void enqueue(T item)
      {
        values.add(item);


      }

      public T dequeue()
      {
        if (values.isEmpty()) {
            return null;
        }
        return values.removeFirst(); 


      }

      public int size()
      {
        return values.size();

        
      }

}