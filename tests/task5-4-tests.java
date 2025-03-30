package org.gladmik;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueueWithStacksTest {
    @Test
    void givenQueue_whenEnqueue_makeQueueWithAddedElements() {
        // Arrange
        QueueWithStacks<Integer> queue = new QueueWithStacks<>();

        // Act
        queue.enqueue(5);
        queue.enqueue(1);
        queue.enqueue(65);

        queue.stack1.pop();
        // Assert
        assertThat(queue.stack1).containsExactly(5, 1, 65);
        assertThat(queue.size()).isEqualTo(3);
    }

    @Test
    void givenQueue_whenEnqueueNull_makeQueueWithAddedElements() {
        // Arrange
        QueueWithStacks<Integer> queue = new QueueWithStacks<>();

        // Act
        queue.enqueue(5);
        queue.enqueue(null);
        queue.enqueue(null);

        // Assert
        assertThat(queue.stack1).containsExactly(5, null, null);
        assertThat(queue.size()).isEqualTo(3);
    }

    @Test
    void givenEmptyQueue_whenSize_returnZero() {
        // Arrange
        QueueWithStacks<Integer> queue = new QueueWithStacks<>();

        // Assert
        assertThat(queue.size()).isZero();
    }

    @Test
    void givenQueue_whenSize_returnSize() {
        // Arrange
        QueueWithStacks<Integer> queue = new QueueWithStacks<>();

        queue.enqueue(87);
        queue.enqueue(12);
        queue.enqueue(42);

        // Act & Assert
        assertThat(queue.size()).isEqualTo(3);
    }

    @Test
    void givenQueue_whenDequeue_returnDeletedElement() {
        // Arrange
        QueueWithStacks<Integer> queue = new QueueWithStacks<>();

        queue.enqueue(87);
        queue.enqueue(12);
        queue.enqueue(42);

        // Act & Assert
        assertThat(queue.dequeue()).isEqualTo(87);
        assertThat(queue.size()).isEqualTo(2);
        assertThat(queue.stack1).containsExactly(12, 42);
    }

    @Test
    void givenEmptyQueue_whenDequeue_returnNull() {
        // Arrange
        QueueWithStacks<Integer> queue = new QueueWithStacks<>();

        // Act & Assert
        assertThat(queue.dequeue()).isNull();
        assertThat(queue.size()).isZero();
        assertThat(queue.stack1).containsExactly();
    }

    @Test
    void givenQueue_whenDequeueAllElements_returnNull() {
        // Arrange
        QueueWithStacks<Integer> queue = new QueueWithStacks<>();

        queue.enqueue(87);
        queue.enqueue(12);
        queue.enqueue(42);

        int[] elements = {87, 12, 42};

        // Act & Assert

        for (int i : elements) {
            assertThat(queue.dequeue()).isEqualTo(i);
        }

        assertThat(queue.dequeue()).isNull();
        assertThat(queue.size()).isZero();
        assertThat(queue.stack1).containsExactly();
    }

    @Test
    void givenQueue_whenRotate_returnRotatedQueue() {
        // Arrange
        QueueWithStacks<Integer> queue = new QueueWithStacks<>();

        queue.enqueue(87);
        queue.enqueue(12);
        queue.enqueue(42);

        // Act & Assert

        queue.rotate(5);

        assertThat(queue.stack1).containsExactly(42, 87, 12);
    }
}
