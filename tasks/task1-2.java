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

     /*
     * Задание на курсе - 1
     * Номер задачи - 8
     * Краткое название - "Список сумм элементов двух связных списков"
     * Пространственная сложность - O(n)
     * Временная сложность - O(n)
     * Рефлексия:
     * Наверное, прозвучит смешно, но думать - сложно.
     * Я попробовал продумывать решение задач по методичке "Карнеги-Меллона" и сначала писать тесты, а потом писать код.
     * Сначала прописывал словесно в тетради, что хотел сделать и рисовал какие-то схемки или псевдокод, писал тест,
     * а потом шёл реализовывать функцию. Это реально тяжелее, но в результате конечный алгоритм
     * вырисовывается гораздо быстрее и код сразу делает то, что нужно, даже если я где-то забывал граничные условия.
     * Раньше моим подходом было сразу ринуться писать какой-то код, пытаясь примерно нащупать что-то похожее на то, что нужно.
     * Обычно это заканчивалось тем, что код вообще не делал то, что нужно и приходилось дебагом пытаться прощупать, что не так.
     * Наверное, возьму на вооружение.
     */
     public static LinkedList sumOfCorrespondingElements(LinkedList list1, LinkedList list2) {
        LinkedList outputList = new LinkedList();

        if (list1.count() == list2.count()) {
            Node current1 = list1.head;
            Node current2 = list2.head;
            while (current1 != null) {
                outputList.addInTail(new Node(current1.value + current2.value));
                current1 = current1.next;
                current2 = current2.next;
            }
        }

        return outputList;
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