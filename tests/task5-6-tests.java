package org.gladmik;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueueWithFixedArrayTest {
    @Test
    void givenQueue_whenEnqueue_makeQueueWithAddedElements() {
        // Arrange
        QueueWithFixedArray<Integer> queue = new QueueWithFixedArray<>(Integer.class, 3);

        // Act
        queue.enqueue(5);
        queue.enqueue(1);

        // Assert
        assertThat(queue.values).containsExactly(5, 1, null);
        assertThat(queue.isFull()).isTrue();
    }

    @Test
    void givenQueue_whenEnqueueNull_makeQueueWithAddedElements() {
        // Arrange
        QueueWithFixedArray<Integer> queue = new QueueWithFixedArray<>(Integer.class, 3);

        // Act
        queue.enqueue(5);
        queue.enqueue(null);

        // Assert
        assertThat(queue.values).containsExactly(5, null, null);
        assertThat(queue.isFull()).isTrue();
        assertThat(queue.isEmpty()).isFalse();
    }

    @Test
    void givenEmptyQueue_whenSize_returnZero() {
        // Arrange
        QueueWithFixedArray<Integer> queue = new QueueWithFixedArray<>(Integer.class, 10);

        // Assert
        assertThat(queue.getSize()).isZero();
    }

    @Test
    void givenQueue_whenSize_returnSize() {
        // Arrange
        QueueWithFixedArray<Integer> queue = new QueueWithFixedArray<>(Integer.class, 10);

        queue.enqueue(87);
        queue.enqueue(12);
        queue.enqueue(42);

        // Act & Assert
        assertThat(queue.getSize()).isEqualTo(3);
    }

    @Test
    void givenQueue_whenDequeue_returnDeletedElement() {
        // Arrange
        QueueWithFixedArray<Integer> queue = new QueueWithFixedArray<>(Integer.class, 3);

        queue.enqueue(87);
        queue.enqueue(12);

        // Act
        Integer deletedElement = queue.dequeue();

        // Assert
        assertThat(deletedElement).isEqualTo(87);
        assertThat(queue.getSize()).isEqualTo(1);
        assertThat(queue.values).containsExactly(null, 12, null);
    }

    @Test
    void givenEmptyQueue_whenDequeue_returnNull() {
        // Arrange
        QueueWithFixedArray<Integer> queue = new QueueWithFixedArray<>(Integer.class, 3);

        // Act & Assert
        assertThat(queue.dequeue()).isNull();
        assertThat(queue.getSize()).isZero();
        assertThat(queue.values).containsExactly(null, null, null);
    }

    @Test
    void givenQueue_whenDequeueAllElements_returnNull() {
        // Arrange
        QueueWithFixedArray<Integer> queue = new QueueWithFixedArray<>(Integer.class, 3);

        queue.enqueue(87);
        queue.enqueue(12);

        int[] elements = {87, 12};

        // Act & Assert

        for (int i : elements) {
            assertThat(queue.dequeue()).isEqualTo(i);
        }

        assertThat(queue.dequeue()).isNull();
        assertThat(queue.getSize()).isZero();
        assertThat(queue.values).containsExactly(null, null, null);
    }
}
