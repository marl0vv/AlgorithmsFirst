import java.util.*;


 class Node<T>
  {
    public T value;
    public Node<T> next, prev;

    public Node(T _value)
    {
      value = _value;
      next = null;
      prev = null;
    }
  }

 public class OrderedList<T>
  {
    public Node<T> head, tail;
    private boolean _ascending;

	// Didn't understand what I should do for the first task because it is already implemented.
    // If asc = false then it is descending
    public OrderedList(boolean asc)
    {
      head = null;
      tail = null;
      _ascending = asc;
    }

    public int compare(T v1, T v2)
    {
		//So I'm not sure if I need to modify string itself or should I only compare them without whitespaces
        if (v1 instanceof String string1 && v2 instanceof String string2) {
            String s1 = string1.trim();
            String s2 = string2.trim();
            int cmp = s1.compareTo(s2);
            return _ascending ? cmp : -cmp;
        }

        // We could put extends Comparable to class declaration, but I'm not sure that I can change signature of
        // a class because server could not process it.
        int cmp = ((Comparable<T>) v1).compareTo(v2);
        // If we want descending order then we just reverse value from standard function
        return _ascending ? cmp : -cmp;
		
		
    }

    public void add(T value)
    {
        // Case 1: Null insert value
        if (value == null) return;

        Node<T> newNode = new Node<>(value);

        // Case 2: Empty list
        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }

        // Case 3: Insert at head
        if (compare(value, head.value) <= 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            return;
        }

        // Case 4: Insert at tail
        if (compare(value, tail.value) >= 0) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            return;
        }

        // Case 4: Insert in the middle
        Node<T> current = head;
        while (current != null) {
            if (compare(value, current.value) >= 0 && compare(value, current.next.value) <= 0) {
                // Connect next node of the current and new node to each other
                current.next.prev = newNode;
                newNode.next = current.next;
                // Connect newNode to a currentNode
                current.next = newNode;
                newNode.prev = current;
                return;
            }
            current = current.next;
        }
		
		
    }

	// In the case when first element is already bigger/lesser than
	// the element we need to fine, algorithm will stop and it will be O(1)
	// But in general it is O(n) because still can go through all elements
	// if sought element is the last one
    public Node<T> find(T val)
    {
        Node<T> current = head;
        while (current != null) {
            if (compare(val, current.value) < 0) {
                return null;
            }
            if (compare(val, current.value) == 0) {
                return current;
            }
            current = current.next;
        }
        return null;
		
		
    }

    public void delete(T val)
    {
        Node<T> current = this.head;

        while (current != null) {
            if (compare(val, current.value) == 0) {
                // case 1: deleted value is at the head
                if (current == this.head) {
                    this.head = current.next;
                    if (this.head != null) {
                        this.head.prev = null;
                    } else {
                        this.tail = null;
                    }
                }
                // case 2: deleted value is at the tail
                else if (current == this.tail) {
                    this.tail = current.prev;
                    this.tail.next = null;
                }
                // case 3: deleted value is in the middle
                else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                return;
            }
            current = current.next;
        }
		
		
    }

    public void clear(boolean asc)
    {
        _ascending = asc;
        this.head = null;
        this.tail = null;
		
		
    }

    public int count()
    {
       int count = 0;
        Node<T> current = this.head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
		
		
    }

    ArrayList<Node<T>> getAll()  
    {
        ArrayList<Node<T>> r = new ArrayList<Node<T>>();
        Node<T> node = head;
        while(node != null)
        {
            r.add(node);
            node = node.next;
        }
        return r;
		
		
    }
  }