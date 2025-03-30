package org.gladmik;

import java.util.*;

public class Queue<T> {

    public final List<T> values;

    public Queue() {
        this.values = new LinkedList<>();
    }

    public void enqueue(T item) {
        this.values.add(item);
    }

    public T dequeue() {
        if (this.values.isEmpty()) {
            return null;
        }
        return this.values.removeFirst(); // null если очередь пустая
    }

    public int size() {
        return this.values.size();
    }

    /*
     * Задание на курсе - 5
     * Номер задачи - 3
     * Краткое название - "Вращение очереди по кругу"
     * Пространственная сложность - O(1)
     * Временная сложность - O(n)
     */
    public void rotate(int n) {
        for (int i = 0; i < n; i++) {
            this.enqueue(this.dequeue());
        }
    }

    /*
     * Задание на курсе - 5
     * Номер задачи - 5
     * Краткое название - "Вернуть очередь в обратном порядке"
     * Пространственная сложность - O(1)
     * Временная сложность - O(n)
     * Рефлексия: Первой идеей было сохранять в ArrayList, а потом циклом в обратном порядке пройтись, но
     * стеком удобнее
     */
    public void reverse() {
        Stack<T> stack = new Stack<>();
        while (!this.values.isEmpty()) {
            stack.push(this.dequeue());
        }
        while (!stack.isEmpty()) {
            this.enqueue(stack.pop());
        }
    }
}