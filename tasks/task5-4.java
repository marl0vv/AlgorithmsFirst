package org.gladmik;

import java.util.Stack;

/*
 * Задание на курсе - 5
 * Номер задачи - 4
 * Краткое название - "Очередь с помощью двух стеков"
 * Пространственная сложность - O(n)
 * Временная сложность - O(n)
 * Рефлексия: Самым сложным было понять, что конкретно нужно сделать.
 * Пришлось порисовать в тетради немало схемок, пока не дошло, как конкретно добавлять элементы
 */
public class QueueWithStacks<T> {
    public Stack<T> stack1;
    public Stack<T> stack2;

    QueueWithStacks() {
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();
    }
    
    // Временная сложность - O(n)
    public void enqueue(T item) {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        stack1.push(item);
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }

    // Временная сложность - O(1)
    public T dequeue() {
        if (stack1.isEmpty()) {
            return null;
        }
        return stack1.pop();
    }

    public int size() {
        return stack1.size();
    }

    public void rotate(int n) {
        for (int i = 0; i < n; i++) {
            this.enqueue(this.dequeue());
        }
    }
}
