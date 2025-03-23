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

    /*
     * Задание на курсе - 2
     * Номер задачи - 9
     * Краткое название - "Развернуть двусвязный список"
     * Временная сложность - O(n)
     * Пространственная сложность - O(1)
     * Рефлексия:
     * Очень простая и интересная схема с тем, что нужно просто изменить prev и next и перейти к prev и так пройтись по
     * всем узлам. Потратил очень много времени, потому что не мог понять, как именно должны измениться связи.
     * Пришлось порисовать схемки и потратить какое-то время.
     */
    public void reverse() {
        Node oldHead = this.head;
        Node previous = null;
        Node current = this.head;

        while (current != null) {
            previous = current.prev;
            current.prev = current.next;
            current.next = previous;
            current = current.prev;
        }

        if (previous != null) {
            this.head = previous.prev;
        }
        this.tail = oldHead;
    }

    /*
     * Задание на курсе - 2
     * Номер задачи - 10
     * Краткое название - "Найти циклы в списке"
     * Временная сложность - O(n)
     * Пространственная сложность - O(1)
     * Рефлексия:
     * Потратил пару часов, чтобы соорудить какого-то монстра из вложенных циклов. Потом понял, что должна быть какой-то
     * более элегантный вариант и пошёл гуглить. Нашёл алгоритм с быстрыми и медленными указателями. Разобрал, изучил.
     * Очень понравилась идея. Всегда себя чувствую немного неловко, когда гуглю идеи алгоритмов или решений.
     * Ощущается, как будто сдался и считерил. Но, наверное, сидеть больше 5 часов и ждать пока придёт озарение тоже
     * сомнительная идея.
     */
    public boolean findCycle() {
        Node previous = this.head;
        Node current = this.head;
        while (current != null && current.next != null) {
            previous = previous.next;
            current = current.next.next;

            if (previous == current) {
                return true;
            }
        }

        return false;
    }

    /*
     * Задание на курсе - 2
     * Номер задачи - 11
     * Краткое название - "Отсортировать двусвязный список"
     * Временная сложность - O(n*n)
     * Пространственная сложность - O(1)
     * Рефлексия:
     * Решил особо сильно не усложнять. Сделал пузырьковую сортировку, потому что мог её сходу выдать.
     * И меняю только значения узлов, а не сами узлы, чтобы не менять связи между узлами.
     */
    public void sort() {
        Node outerCurrent = this.head;
        while (outerCurrent != null) {
            Node innerCurrent = this.head;
            while (innerCurrent.next != null) {
                if (innerCurrent.value > innerCurrent.next.value) {
                    int temp = innerCurrent.value;
                    innerCurrent.value = innerCurrent.next.value;
                    innerCurrent.next.value = temp;
                }
                innerCurrent = innerCurrent.next;
            }
            outerCurrent = outerCurrent.next;
        }
    }

    /*
     * Задание на курсе - 2
     * Номер задачи - 11
     * Краткое название - "Отсортировать двусвязный список"
     * Временная сложность - O(n), если не учитывать сортировку
     * Пространственная сложность - O(1)
     * Рефлексия:
     * Все первые идеи по итогу сводились к тому, чтобы тупо отсортировать итоговый список.
     * Потом подробнее почитал про dummy node, подумал про то, что нужно сразу собирать новый список, ставя соответствующие
     * узлы на нужные места. По итогу получилась такая конструкция, где по одному проверяем, какой элемент нужно добавлять
     * и собираем из этого итоговый список.
     */
    public static LinkedList2 merge(LinkedList2 list1, LinkedList2 list2) {
        list1.sort();
        list2.sort();

        if (list1.head == null) {
            return list2;
        }
        if (list2.head == null) {
            return list1;
        }

        Node dummyHead = new Node(0);
        Node currentMergedList = dummyHead;

        Node currentList1 = list1.head;
        Node currentList2 = list2.head;
        while (currentList1 != null && currentList2 != null) {
            // traverse both lists one by one and add Nodes in a sorted manner to a new List
            if (currentList1.value <= currentList2.value) {
                currentMergedList.next = currentList1;
                currentList1.prev = currentMergedList;
                currentList1 = currentList1.next;
            } else {
                currentMergedList.next = currentList2;
                currentList2.prev = currentMergedList;
                currentList2 = currentList2.next;
            }
            currentMergedList = currentMergedList.next;
        }

        // if one list is null and other is not
        // then put all leftover nodes from the other to the new List
        if (currentList1 != null) {
            currentMergedList.next = currentList1;
            currentList1.prev = currentMergedList;
        } else {
            currentMergedList.next = currentList2;
            currentList2.prev = currentMergedList;
        }

        LinkedList2 mergedList = new LinkedList2();
        mergedList.head = dummyHead.next;
        mergedList.head.prev = null;
        Node tail = mergedList.head;
        while (tail.next != null) {
            tail = tail.next;
        }
        mergedList.tail = tail; // Set the tail
        return mergedList;
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