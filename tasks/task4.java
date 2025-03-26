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

     /*
        Time complexity - O(1) because getting and removing first element
        Space complexity - O(1) because no additional space used
     */
      public T pop()
      {
       if (values.isEmpty()) {
            return null;
        }
        T value = values.getFirst();
        values.remove(values.getFirst());
        return value;


      }
	  
      /*
        Time complexity - O(1) because just adding a new node to a linked list
        Space complexity - O(1) because no additional space used
     */
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