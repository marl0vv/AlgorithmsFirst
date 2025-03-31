package org.gladmik;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * <p> Created on: 31.03.2025
 *
 * @author Mikhail Gladkikh
 */
class DequeTest {

    @Test
    void givenDeque_whenAddFront_returnDequeWithAddedElements() {
        // Arrange
        Deque<Integer> deque = new Deque<>();

        // Act & Assert
        assertThat(deque.size()).isZero();
        deque.addFront(5);
        assertThat(deque.elements.getFirst()).isEqualTo(5);
        assertThat(deque.elements).containsExactly(5);
        assertThat(deque.size()).isEqualTo(1);

        deque.addFront(46);
        assertThat(deque.elements.getFirst()).isEqualTo(46);
        assertThat(deque.elements).containsExactly(46, 5);
        assertThat(deque.size()).isEqualTo(2);

        deque.addFront(12);
        assertThat(deque.elements.getFirst()).isEqualTo(12);
        assertThat(deque.elements).containsExactly(12, 46, 5);
        assertThat(deque.size()).isEqualTo(3);
    }

    @Test
    void givenDeque_whenAddFrontNull_returnDequeWithAddedElements() {
        // Arrange
        Deque<Integer> deque = new Deque<>();

        // Act
        deque.addFront(null);

        // Assert
        assertThat(deque.elements.getFirst()).isNull();
        assertThat(deque.elements).containsExactly((Integer) null);
        assertThat(deque.size()).isEqualTo(1);
    }

    @Test
    void givenDeque_whenAddTail_returnDequeWithAddedElements() {
        // Arrange
        Deque<Integer> deque = new Deque<>();

        // Act & Assert
        assertThat(deque.size()).isZero();
        deque.addTail(5);
        assertThat(deque.elements.getLast()).isEqualTo(5);
        assertThat(deque.elements).containsExactly(5);
        assertThat(deque.size()).isEqualTo(1);

        deque.addTail(46);
        assertThat(deque.elements.getLast()).isEqualTo(46);
        assertThat(deque.elements).containsExactly(5, 46);
        assertThat(deque.size()).isEqualTo(2);

        deque.addTail(12);
        assertThat(deque.elements.getLast()).isEqualTo(12);
        assertThat(deque.elements).containsExactly(5, 46, 12);
        assertThat(deque.size()).isEqualTo(3);
    }

    @Test
    void givenDeque_whenAddTailNull_returnDequeWithAddedElements() {
        // Arrange
        Deque<Integer> deque = new Deque<>();

        // Act
        deque.addTail(null);

        // Assert
        assertThat(deque.elements.getFirst()).isNull();
        assertThat(deque.elements).containsExactly((Integer) null);
        assertThat(deque.size()).isEqualTo(1);
    }

    @Test
    void givenDeque_whenRemoveFront_returnDequeWithAddedElements() {
        // Arrange
        Deque<Integer> deque = new Deque<>();
        deque.addTail(5);
        deque.addTail(46);
        deque.addTail(12);

        // Act
        Integer deletedElement = deque.removeFront();

        // Assert
        assertThat(deletedElement).isEqualTo(5);
        assertThat(deque.elements).containsExactly(46, 12);
        assertThat(deque.size()).isEqualTo(2);
    }

    @Test
    void givenEmptyDeque_whenRemoveFrontNull_returnDequeWithAddedElements() {
        // Arrange
        Deque<Integer> deque = new Deque<>();

        // Act
        Integer deletedElement = deque.removeFront();

        // Assert
        assertThat(deletedElement).isNull();
    }

    @Test
    void givenDeque_whenRemoveTail_returnDequeWithAddedElements() {
        // Arrange
        Deque<Integer> deque = new Deque<>();
        deque.addTail(5);
        deque.addTail(46);
        deque.addTail(12);

        // Act
        Integer deletedElement = deque.removeTail();

        // Assert
        assertThat(deletedElement).isEqualTo(12);
        assertThat(deque.elements).containsExactly(5, 46);
        assertThat(deque.size()).isEqualTo(2);
    }

    @Test
    void givenEmptyDeque_whenRemoveTail_returnDequeWithAddedElements() {
        // Arrange
        Deque<Integer> deque = new Deque<>();

        // Act
        Integer deletedElement = deque.removeTail();

        // Assert
        assertThat(deletedElement).isNull();
    }
}
