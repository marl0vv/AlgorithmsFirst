package org.gladmik;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p> Created on: 28.03.2025
 *
 * @author Mikhail Gladkikh
 */
class QueueTest {

    @Test
    void givenQueue_whenEnqueue_makeQueueWithAddedElements() {
        // Arrange
        Queue<Integer> queue = new Queue<>();

        // Act
        queue.enqueue(5);
        queue.enqueue(1);
        queue.enqueue(65);

        // Assert
        assertThat(queue.values).containsExactly(5, 1, 65);
        assertThat(queue.size()).isEqualTo(3);
    }

    @Test
    void givenQueue_whenEnqueueNull_makeQueueWithAddedElements() {
        // Arrange
        Queue<Integer> queue = new Queue<>();

        // Act
        queue.enqueue(5);
        queue.enqueue(null);
        queue.enqueue(null);

        // Assert
        assertThat(queue.values).containsExactly(5, null, null);
        assertThat(queue.size()).isEqualTo(3);
    }

    @Test
    void givenEmptyQueue_whenSize_returnZero() {
        // Arrange
        Queue<Integer> queue = new Queue<>();

        // Assert
        assertThat(queue.size()).isZero();
    }

    @Test
    void givenQueue_whenSize_returnSize() {
        // Arrange
        Queue<Integer> queue = new Queue<>();

        queue.enqueue(87);
        queue.enqueue(12);
        queue.enqueue(42);

        // Act & Assert
        assertThat(queue.size()).isEqualTo(3);
    }

    @Test
    void givenQueue_whenDequeue_returnDeletedElement() {
        // Arrange
        Queue<Integer> queue = new Queue<>();

        queue.enqueue(87);
        queue.enqueue(12);
        queue.enqueue(42);

        // Act & Assert
        assertThat(queue.dequeue()).isEqualTo(87);
        assertThat(queue.size()).isEqualTo(2);
        assertThat(queue.values).containsExactly(12, 42);
    }

    @Test
    void givenEmptyQueue_whenDequeue_returnNull() {
        // Arrange
        Queue<Integer> queue = new Queue<>();

        // Act & Assert
        assertThat(queue.dequeue()).isNull();
        assertThat(queue.size()).isZero();
        assertThat(queue.values).containsExactly();
    }

    @Test
    void givenQueue_whenDequeueAllElements_returnNull() {
        // Arrange
        Queue<Integer> queue = new Queue<>();

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
        assertThat(queue.values).containsExactly();
    }
}
