import java.util.*;

public class Stack<T>
{

     public final java.util.List<T> values;

      public Stack()
      {
       this.values = new java.util.LinkedList<>();


      } 

      public int size() 
      {
       return values.size();

       
      }

      public T pop()
      {
       if (values.isEmpty()) {
            return null;
        }
        T value = values.getFirst();
        values.remove(values.getFirst());
        return value;


      }
	  
      public void push(T val)
      {
       values.addFirst(val);


      }

      public T peek()
      {
       if (values.isEmpty()) {
            return null;
        }
        return values.getFirst();


      }
}