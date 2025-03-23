import java.util.*;

public class LinkedList2
{
    public Node head;
    public Node tail;

    public LinkedList2()
    {
        head = null;
        tail = null;
    }

    public void addInTail(Node _item)
    {
        if (head == null) {
            this.head = _item;
            this.head.next = null;
            this.head.prev = null;
        } else {
            this.tail.next = _item;
            _item.prev = tail;
        }
        this.tail = _item;
    }

    public Node find(int _value)
    {
        Node current = this.head;
        while (current != null) {
            if (current.value == _value) {
                return current;
            }
            current = current.next;
        }
        return null;


    }

    public ArrayList<Node> findAll(int _value)
    {
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
            if (current.value == _value) {
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
            this.head = _nodeToInsert;
            this.head.next = swap;
            // if list was empty then tail points at null
            if (this.tail == null) {
                this.tail = _nodeToInsert;
            } else {
                swap.prev = this.head;
            }
        } else {
            Node current = this.head;
            while (current != _nodeAfter) {
                current = current.next;
            }
            if (current.next == null) {
                current.next = _nodeToInsert;
                this.tail = _nodeToInsert;
                this.tail.prev = current;
            } else {
                Node swap = current.next;
                current.next = _nodeToInsert;
                _nodeToInsert.prev = current;
                current.next.next = swap;
                swap.prev = _nodeToInsert;
            }
        }


    }
}

class Node
{
    public int value;
    public Node next;
    public Node prev;

    public Node(int _value)
    {
        value = _value;
        next = null;
        prev = null;
    }
}