package org.gladmik;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p> Created on: 04.04.2025
 *
 * @author Mikhail Gladkikh
 */
class OrderedListTest {

    private <T> List<T> toList(OrderedList<T> list) {
        List<T> values = new ArrayList<>();
        Node<T> current = list.head;
        while (current != null) {
            values.add(current.value);
            current = current.next;
        }
        return values;
    }


    @Test
    void givenList_whenAddNull_thenUnchangedList() {
        // Arrange
        OrderedList<Integer> list = new OrderedList<>(true);

        // Act
        list.add(null);

        // Assert
        assertThat(list.count()).isZero();
        assertThat(toList(list)).containsExactly();
        assertThat(list.head).isNull();
        assertThat(list.tail).isNull();
    }

    @Test
    void givenEmptyList_whenAdd_thenListWithOneElement() {
        // Arrange
        OrderedList<Integer> list = new OrderedList<>(true);

        // Act
        list.add(12);

        // Assert
        assertThat(list.count()).isEqualTo(1);
        assertThat(toList(list)).containsExactly(12);
        assertThat(list.head.value).isEqualTo(12);
        assertThat(list.tail.value).isEqualTo(12);
    }

    @Test
    void givenAscendingList_whenAddAtHead_thenListWithNewNodeAtHead() {
        // Arrange
        OrderedList<Integer> list = new OrderedList<>(true);
        list.add(10);
        list.add(20);

        // Act
        list.add(5);

        // Assert
        assertThat(list.count()).isEqualTo(3);
        assertThat(toList(list)).containsExactly(5, 10, 20);
        assertThat(list.head.value).isEqualTo(5);
        assertThat(list.tail.value).isEqualTo(20);
        assertThat(list.head.next.value).isEqualTo(10);
        assertThat(list.tail.prev.value).isEqualTo(10);
    }

    @Test
    void givenAscendingStringList_whenAddAtHead_thenListWithNewNodeAtHead() {
        // Arrange
        OrderedList<String> list = new OrderedList<>(true);
        list.add("bbb    ");
        list.add("ccc    ");

        // Act
        list.add("aaa    ");

        // Assert
        assertThat(list.count()).isEqualTo(3);
        assertThat(toList(list)).containsExactly("aaa    ", "bbb    ", "ccc    ");
        assertThat(list.head.value).isEqualTo("aaa    ");
        assertThat(list.tail.value).isEqualTo("ccc    ");
        assertThat(list.head.next.value).isEqualTo("bbb    ");
        assertThat(list.tail.prev.value).isEqualTo("bbb    ");
    }

    @Test
    void givenAscendingList_whenAddAtTail_thenListWithNewNodeAtTail() {
        // Arrange
        OrderedList<Integer> list = new OrderedList<>(true);
        list.add(50);
        list.add(60);

        // Act
        list.add(70);

        // Assert
        assertThat(list.count()).isEqualTo(3);
        assertThat(toList(list)).containsExactly(50, 60, 70);
        assertThat(list.tail.value).isEqualTo(70);
        assertThat(list.tail.prev.value).isEqualTo(60);
        assertThat(list.tail.prev.next.value).isEqualTo(70);
    }

    @Test
    void givenAscendingList_whenAddInTheMiddle_thenListWithNewNodeAtTheMiddle() {
        // Arrange
        OrderedList<Integer> list = new OrderedList<>(true);
        list.add(20);
        list.add(50);
        list.add(70);

        // Act
        list.add(35);

        // Assert
        assertThat(list.count()).isEqualTo(4);
        assertThat(toList(list)).containsExactly(20, 35, 50, 70);
        assertThat(list.head.next.value).isEqualTo(35);
        assertThat(list.head.next.next.value).isEqualTo(50);
        assertThat(list.head.next.next.prev.value).isEqualTo(35);
    }

    @Test
    void givenAscendingList_whenAddDuplicates_thenListWithNewDuplicatedNodes() {
        // Arrange
        OrderedList<Integer> list = new OrderedList<>(true);
        list.add(20);
        list.add(50);
        list.add(70);

        // Act
        list.add(20);
        list.add(50);
        list.add(70);

        // Assert
        assertThat(list.count()).isEqualTo(6);
        assertThat(toList(list)).containsExactly(20, 20, 50, 50, 70, 70);
    }

    @Test
    void givenAscendingList_whenAddDescendingSequence_thenCorrectList() {
        // Arrange
        OrderedList<Integer> list = new OrderedList<>(true);

        // Act
        list.add(287);
        list.add(64);
        list.add(35);

        // Assert
        assertThat(list.count()).isEqualTo(3);
        assertThat(toList(list)).containsExactly(35, 64, 287);
    }

    @Test
    void givenDescendingList_whenAddAtTail_thenListWithNewNodeAtTail() {
        // Arrange
        OrderedList<Integer> list = new OrderedList<>(false);
        list.add(20);
        list.add(10);

        // Act
        list.add(5);

        // Assert
        assertThat(list.count()).isEqualTo(3);
        assertThat(toList(list)).containsExactly(20, 10, 5);
        assertThat(list.tail.value).isEqualTo(5);
        assertThat(list.tail.prev.value).isEqualTo(10);
        assertThat(list.tail.prev.next.value).isEqualTo(5);
    }


    @Test
    void givenDescendingList_whenAddAtHead_thenListWithNewNodeAtHead() {
        // Arrange
        OrderedList<Integer> list = new OrderedList<>(false);
        list.add(60);
        list.add(50);

        // Act
        list.add(70);

        // Assert
        assertThat(list.count()).isEqualTo(3);
        assertThat(toList(list)).containsExactly(70, 60, 50);
        assertThat(list.head.value).isEqualTo(70);
        assertThat(list.head.next.value).isEqualTo(60);
        assertThat(list.head.next.prev.value).isEqualTo(70);
    }

    @Test
    void givenDescendingList_whenAddInTheMiddle_thenListWithNewNodeAtTheMiddle() {
        // Arrange
        OrderedList<Integer> list = new OrderedList<>(false);
        list.add(70);
        list.add(50);
        list.add(20);

        // Act
        list.add(35);

        // Assert
        assertThat(list.count()).isEqualTo(4);
        assertThat(toList(list)).containsExactly(70, 50, 35, 20);
        assertThat(list.tail.prev.value).isEqualTo(35);
        assertThat(list.tail.prev.next.value).isEqualTo(20);
    }

    @Test
    void givenDescendingList_whenAddDuplicates_thenListWithNewDuplicatedNodes() {
        // Arrange
        OrderedList<Integer> list = new OrderedList<>(false);
        list.add(70);
        list.add(50);
        list.add(20);

        // Act
        list.add(70);
        list.add(50);
        list.add(20);

        // Assert
        assertThat(list.count()).isEqualTo(6);
        assertThat(toList(list)).containsExactly(70, 70, 50, 50, 20, 20);
    }

    @Test
    void givenDescendingList_whenAddAscendingSequence_thenCorrectList() {
        // Arrange
        OrderedList<Integer> list = new OrderedList<>(false);

        // Act
        list.add(35);
        list.add(64);
        list.add(287);

        // Assert
        assertThat(list.count()).isEqualTo(3);
        assertThat(toList(list)).containsExactly(287, 64, 35);
    }

    @Test
    void givenList_whenDeleteElementInTheMiddle_thenListWithoutDeletedElement() {
        // Arrange
        OrderedList<Integer> list = new OrderedList<>(true);
        list.add(32);
        list.add(76);
        list.add(91);

        // Act
        list.delete(76);

        // Assert
        assertThat(list.count()).isEqualTo(2);
        assertThat(toList(list)).containsExactly(32, 91);
        assertThat(list.find(55)).isNull();
        assertThat(list.head.value).isEqualTo(32);
        assertThat(list.tail.value).isEqualTo(91);
        assertThat(list.head.next).isEqualTo(list.tail);
        assertThat(list.tail.prev).isEqualTo(list.head);
    }

    @Test
    void givenListWithManyElements_whenDeleteElementInTheHead_thenListWithoutDeletedElement() {
        // Arrange
        OrderedList<Integer> list = new OrderedList<>(true);
        list.add(12);
        list.add(53);
        list.add(87);

        // Act
        list.delete(12);

        // Assert
        assertThat(list.count()).isEqualTo(2);
        assertThat(toList(list)).containsExactly(53, 87);
        assertThat(list.find(12)).isNull();
        assertThat(list.head.value).isEqualTo(53);
        assertThat(list.tail.value).isEqualTo(87);
        assertThat(list.head.next).isEqualTo(list.tail);
        assertThat(list.tail.prev).isEqualTo(list.head);
    }

    @Test
    void givenListWithOneElement_whenDeleteElementInTheHead_thenListWithoutDeletedElement() {
        // Arrange
        OrderedList<Integer> list = new OrderedList<>(true);
        list.add(54);

        // Act
        list.delete(54);

        // Assert
        assertThat(list.count()).isZero();
        assertThat(list.find(54)).isNull();
        assertThat(list.head).isNull();
        assertThat(list.tail).isNull();
    }

    @Test
    void givenListWithManyElements_whenRemoveElementInTheTail_thenReturnTrue() {
        // Arrange
        OrderedList<Integer> list = new OrderedList<>(true);
        list.add(34);
        list.add(65);
        list.add(167);

        // Act
        list.delete(167);

        // Assert
        assertThat(list.count()).isEqualTo(2);
        assertThat(toList(list)).containsExactly(34, 65);
        assertThat(list.find(167)).isNull();
        assertThat(list.head.value).isEqualTo(34);
        assertThat(list.tail.value).isEqualTo(65);
        assertThat(list.tail.next).isNull();
    }

    @Test
    void givenEmptyList_whenRemoveElement_thenReturnFalse() {
        // Arrange
        OrderedList<Integer> list = new OrderedList<>(true);

        // Act
        list.delete(12);

        // Assert
        assertThat(list.count()).isZero();

        assertThat(list.head).isNull();
        assertThat(list.tail).isNull();
    }

    @Test
    void givenList_whenClear_thenReturnEmptyList() {
        // Arrange
        OrderedList<Integer> list = new OrderedList<>(true);
        list.add(12);
        list.add(64);

        // Act
        list.clear(true);

        // Assert
        assertThat(list.count()).isZero();
        assertThat(list.head).isNull();
        assertThat(list.tail).isNull();
    }

    @Test
    void givenEmptyList_whenFind_thenReturnNull() {
        // Arrange
        OrderedList<Integer> list = new OrderedList<>(true);

        // Act
        Node<Integer> node = list.find(112);

        // Assert
        assertThat(node).isNull();
    }

    @Test
    void givenListWithSoughtElement_whenFindTail_thenReturnTail() {
        // Arrange
        OrderedList<Integer> list = new OrderedList<>(true);
        list.add(42);
        list.add(56);
        list.add(112);

        // Act
        Node<Integer> node = list.find(112);

        // Assert
        assertThat(node).isNotNull();
        assertThat(node.value).isNotNull()
                              .isEqualTo(112);
        assertThat(node).isEqualTo(list.tail);
    }

    @Test
    void givenListWithSoughtElement_whenFindHead_thenReturnHead() {
        // Arrange
        OrderedList<Integer> list = new OrderedList<>(true);
        list.add(42);
        list.add(56);
        list.add(112);

        // Act
        Node<Integer> node = list.find(42);

        // Assert
        assertThat(node).isNotNull();
        assertThat(node.value).isNotNull()
                              .isEqualTo(42);
        assertThat(node).isEqualTo(list.head);
    }

    @Test
    void givenListWithoutSoughtElementAscending_whenFind_thenReturnNull() {
        // Arrange
        OrderedList<Integer> list = new OrderedList<>(true);
        list.add(42);
        list.add(56);
        list.add(112);

        // Act
        Node<Integer> node = list.find(30);

        // Assert
        assertThat(node).isNull();
    }

    @Test
    void givenListWithoutSoughtElementDescending_whenFind_thenReturnNull() {
        // Arrange
        OrderedList<Integer> list = new OrderedList<>(false);
        list.add(42);
        list.add(56);
        list.add(112);

        // Act
        Node<Integer> node = list.find(156);

        // Assert
        assertThat(node).isNull();
    }
}
