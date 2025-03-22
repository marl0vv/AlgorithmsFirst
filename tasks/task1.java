import java.util.*;

public class LinkedList
{
     public Node head;
     public Node tail;

     public LinkedList()
     {
       head = null;
       tail = null;
     }

    public void addInTail(Node item) {
        if (this.head == null)
            this.head = item;
        else
            this.tail.next = item;
        this.tail = item;
    }

    public Node find(int value) {
        Node node = this.head;
        while (node != null) {
            if (node.value == value)
                return node;
            node = node.next;
        }
        return null;

        
    }

    public ArrayList<Node> findAll(int _value) {
        ArrayList<Node> nodes = new ArrayList<Node>();

        Node current = this.head;
        while (current != null) {
            if (current.value == _value) {
                nodes.add(current);
            }
            current = current.next;
        }
        return nodes;


     }

     public boolean remove(int _value)
     {
        Node current = this.head;
        while (current != null) {
            // case when value is not in a list
            if (current.equals(this.tail) && current.value != _value) {
                return false;
            }

            // case when value is in the head of a list
            if (current.equals(this.head) && current.value == _value) {
                this.head = current.next;
                // if deleted  element was the only element, then tail should be null
                if (current.equals(this.tail)) {
                    this.tail = null;
                }
                return true;
            }

            if (current.next.value == _value) {
                if (current.next.next != null) {
                    current.next = current.next.next;
                } else {
                    current.next = null;
                    this.tail = current;
                }
                return true;
            }

            current = current.next;
        }
        return false;


     }

     public void removeAll(int _value)
     {
        while (remove(_value)) {
        }


     }

     public void clear()
     {
        this.head = null;
        this.tail = null;


     }

     public int count()
     {
        int count = 0;
        Node current = this.head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;


     }

     public void insertAfter(Node _nodeAfter, Node _nodeToInsert)
     {
        if (_nodeToInsert == null) {
            return;
        }

        if (_nodeAfter == null) {
            Node swap = this.head;
            this.head =_nodeToInsert;
            this.head.next = swap;
            if (this.tail == null) {
                this.tail = _nodeToInsert;
            }
        } else {
            Node current = this.head;
            while (current != _nodeAfter) {
                current = current.next;
            }
            if (current.next == null) {
                current.next = _nodeToInsert;
                this.tail = _nodeToInsert;
            } else {
                Node swap = current.next;
                current.next = _nodeToInsert;
                current.next.next = swap;
            }
        }
     }


}

class Node
{
     public int value;
     public Node next;
     public Node(int _value) 
     {  
       value = _value;
       next = null;
     }
}

