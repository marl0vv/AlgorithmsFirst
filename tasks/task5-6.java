package org.gladmik;

/*
 * Задание на курсе - 5
 * Номер задачи - 6
 * Краткое название - "Циклическая буферная очередь"
 * Пространственная сложность - O(1)
 * Временная сложность - O(1)
 * Рефлексия: Затупил поначалу. Не очень понял про "эффективное управление указателями" и "круговой буфер". 
 * Сначала сделал обычный сдвиг типа как было с динамическим массивом. Потом когда увидел рекомендации, до меня дошло,
 * как оно должно выглядеть.
 */
public class QueueWithFixedArray<T> {

    public T[] values;
    public int size = 0;
    public int capacity;
    int head = 0;
    int tail = 0;

    QueueWithFixedArray(Class<T> clazz, int capacity) {
        values = (T[]) java.lang.reflect.Array.newInstance(clazz, capacity);
        this.capacity = capacity;
    }

    public void enqueue(T item) {
        if (isFull()) {
            return;
        }
        values[tail] = item;
        size++;
        tail = (tail + 1) % capacity;
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        T deletedValue = values[head];
        values[head] = null;

        head = (head + 1) % capacity;
        size--;

        return deletedValue;
    }

    public boolean isEmpty() {
        return tail == head;
    }
    
    public boolean isFull() {
        return ((tail + 1) % capacity) == head;
    }

    public int getSize() {
        return size;
    }
}
