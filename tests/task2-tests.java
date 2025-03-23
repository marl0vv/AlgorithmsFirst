package org.gladmik;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.gladmik.LinkedList2.merge;

class LinkedList2Test {

    private List<Node> toList(LinkedList2 list) {
        List<Node> nodes = new ArrayList<>();
        Node current = list.head;
        while (current != null) {
            nodes.add(current);
            current = current.next;
        }
        return nodes;
    }

    @Test
    void givenList_whenFindAndElementInAList_thenReturnFoundNode() {
        // Arrange
        Node n1 = new Node(41);
        Node n2 = new Node(65);
        Node n3 = new Node(12);

        LinkedList2 list = new LinkedList2();
        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);

        // Act
        Node foundNode = list.find(12);

        // Assert
        assertThat(foundNode).isEqualTo(n3);
    }

    @Test
    void givenList_whenFindAndElementNotInAList_thenReturnNull() {
        // Arrange
        Node n1 = new Node(41);

        LinkedList2 list = new LinkedList2();

        list.addInTail(n1);

        // Act
        Node foundNode = list.find(12);

        // Assert
        assertThat(foundNode).isNull();
    }

    @Test
    void givenEmptyList_whenFind_thenReturnNull() {
        LinkedList2 list = new LinkedList2();

        Node foundNode = list.find(12);

        assertThat(foundNode).isNull();
    }

    @Test
    void givenList_whenFindAllNodesSeveralSoughtNodesExist_thenReturnArrayListWithSoughtNodes() {
        // Arrange
        Node n1 = new Node(12);
        Node n2 = new Node(55);
        Node n3 = new Node(12);
        Node n4 = new Node(20);
        Node n5 = new Node(12);

        LinkedList2 list = new LinkedList2();
        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);
        list.addInTail(n4);
        list.addInTail(n5);

        // Act
        ArrayList<Node> nodes = list.findAll(12);

        // Assert
        assertThat(nodes).containsExactly(n1, n3, n5);
    }

    @Test
    void givenList_whenFindAllNodesOneSoughtNodeExists_thenReturnArrayListWithOneNode() {
        // Arrange
        Node n1 = new Node(12);
        Node n2 = new Node(55);
        Node n3 = new Node(12);

        LinkedList2 list = new LinkedList2();
        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);

        // Act
        ArrayList<Node> nodes = list.findAll(55);

        // Assert
        assertThat(nodes).containsExactly(n2);
    }

    @Test
    void givenList_whenFindAllNodesNoSoughtNodeExists_thenReturnEmptyArrayList() {
        // Arrange
        Node n1 = new Node(12);
        Node n2 = new Node(55);
        Node n3 = new Node(12);

        LinkedList2 list = new LinkedList2();
        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);

        // Act
        ArrayList<Node> nodes = list.findAll(42);

        // Assert
        assertThat(nodes).isEmpty();
    }

    @Test
    void givenEmptyList_whenFindAll_thenReturnEmptyArrayList() {
        // Arrange
        LinkedList2 list = new LinkedList2();

        // Act
        ArrayList<Node> nodes = list.findAll(42);

        // Assert
        assertThat(nodes).isEmpty();
    }

    @Test
    void givenList_whenRemoveElementInTheMiddle_thenReturnTrue() {
        // Arrange
        Node n1 = new Node(12);
        Node n2 = new Node(55);
        Node n3 = new Node(12);

        LinkedList2 list = new LinkedList2();
        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);

        // Act
        boolean successfulDeletion = list.remove(55);

        // Assert
        assertThat(successfulDeletion).isTrue();
        assertThat(list.count()).isEqualTo(2);
        assertThat(toList(list)).containsExactly(n1, n3);
        assertThat(list.find(55)).isNull();
        assertThat(list.head).isEqualTo(n1);
        assertThat(list.tail).isEqualTo(n3);
        assertThat(n1.next).isEqualTo(n3);
        assertThat(n3.prev).isEqualTo(n1);
    }

    @Test
    void givenListWithManyElements_whenRemoveElementInTheHead_thenReturnTrue() {
        // Arrange
        Node n1 = new Node(12);
        Node n2 = new Node(55);
        Node n3 = new Node(86);

        LinkedList2 list = new LinkedList2();
        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);

        // Act
        boolean successfulDeletion = list.remove(12);

        // Assert
        assertThat(successfulDeletion).isTrue();
        assertThat(list.count()).isEqualTo(2);
        assertThat(toList(list)).containsExactly(n2, n3);
        assertThat(list.find(12)).isNull();
        assertThat(list.head).isEqualTo(n2);
        assertThat(list.tail).isEqualTo(n3);
        assertThat(n2.prev).isNull();
    }

    @Test
    void givenListWithOneElement_whenRemoveElementInTheHead_thenReturnTrue() {
        // Arrange
        Node n1 = new Node(12);

        LinkedList2 list = new LinkedList2();
        list.addInTail(n1);

        // Act
        boolean successfulDeletion = list.remove(12);

        // Assert
        assertThat(successfulDeletion).isTrue();
        assertThat(list.count()).isZero();
        assertThat(list.find(12)).isNull();
        assertThat(list.head).isNull();
        assertThat(list.tail).isNull();
    }

    @Test
    void givenListWithManyElements_whenRemoveElementInTheTail_thenReturnTrue() {
        // Arrange
        Node n1 = new Node(65);
        Node n2 = new Node(55);
        Node n3 = new Node(12);

        LinkedList2 list = new LinkedList2();
        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);

        // Act
        boolean successfulDeletion = list.remove(12);

        // Assert
        assertThat(successfulDeletion).isTrue();
        assertThat(list.count()).isEqualTo(2);
        assertThat(toList(list)).containsExactly(n1, n2);
        assertThat(list.find(12)).isNull();
        assertThat(list.head).isEqualTo(n1);
        assertThat(list.tail).isEqualTo(n2);
        assertThat(n2.next).isNull();
    }

    @Test
    void givenEmptyList_whenRemoveElement_thenReturnFalse() {
        // Arrange
        LinkedList2 list = new LinkedList2();
        // Act
        boolean successfulDeletion = list.remove(12);

        // Assert
        assertThat(list.count()).isZero();
        assertThat(successfulDeletion).isFalse();

        assertThat(list.head).isNull();
        assertThat(list.tail).isNull();
    }

    @Test
    void givenListWithThreeElements_whenCount_thenReturnThree() {
        // Arrange
        LinkedList2 list = new LinkedList2();
        list.addInTail(new Node(12));
        list.addInTail(new Node(55));
        list.addInTail(new Node(128));

        // Act
        int count = list.count();

        // Assert
        assertThat(count).isEqualTo(3);
    }

    @Test
    void givenListWithZeroElements_whenCountElementsAndCountInvoked_thenReturnZero() {
        // Arrange
        LinkedList2 list = new LinkedList2();

        // Act
        int count = list.count();

        // Assert
        assertThat(count).isZero();
    }

    @Test
    void givenList_whenClear_thenReturnEmptyList() {
        // Arrange
        LinkedList2 list = new LinkedList2();
        list.addInTail(new Node(12));
        list.addInTail(new Node(55));

        // Act
        list.clear();

        // Assert
        assertThat(list.count()).isZero();
        assertThat(list.head).isNull();
        assertThat(list.tail).isNull();

    }

    @Test
    void givenList_whenRemoveAll_thenReturnListWithoutDeletedValues() {
        // Arrange
        Node n1 = new Node(55);
        Node n2 = new Node(36);
        Node n3 = new Node(55);
        Node n4 = new Node(46);
        Node n5 = new Node(55);

        LinkedList2 list = new LinkedList2();
        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);
        list.addInTail(n4);
        list.addInTail(n5);

        // Act
        list.removeAll(55);

        // Assert
        assertThat(list.count()).isEqualTo(2);
        assertThat(toList(list)).containsExactly(n2, n4);

        assertThat(list.head).isEqualTo(n2);
        assertThat(list.tail).isEqualTo(n4);
        assertThat(n2.next).isEqualTo(n4);
        assertThat(n4.prev).isEqualTo(n2);
    }

    @Test
    void givenEmptyList_whenRemoveAll_thenReturnListWithoutDeletedValues() {
        // Arrange
        LinkedList2 list = new LinkedList2();

        // Act
        list.removeAll(55);

        // Assert
        assertThat(list.count()).isZero();
    }

    @Test
    void givenList_whenInsertN7AfterN2_thenReturnListWithN7AfterN2() {
        // Arrange
        Node n1 = new Node(12);
        Node n2 = new Node(55);
        Node n3 = new Node(128);
        LinkedList2 list = new LinkedList2();
        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);

        Node n7 = new Node(35);

        // Act
        list.insertAfter(n2, n7);

        // Assert
        assertThat(list.count()).isEqualTo(4);
        assertThat(toList(list)).containsExactly(n1, n2, n7, n3);
        assertThat(list.tail).isEqualTo(n3);

    }

    @Test
    void givenList_whenInsertAfterTail_thenReturnListWithNewNodeAtTail() {
        // Arrange
        Node n1 = new Node(12);
        Node n2 = new Node(55);
        Node n3 = new Node(128);
        LinkedList2 list = new LinkedList2();
        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);

        Node n7 = new Node(35);

        // Act
        list.insertAfter(n3, n7);

        // Assert
        assertThat(list.count()).isEqualTo(4);
        assertThat(toList(list)).containsExactly(n1, n2, n3, n7);
        assertThat(list.tail).isEqualTo(n7);

        assertThat(n3.next).isEqualTo(n7);
        assertThat(n7.prev).isEqualTo(n3);
    }

    @Test
    void givenList_whenInsertN7AfterNull_thenReturnListWithN7AtHead() {
        // Arrange
        Node n1 = new Node(12);
        Node n2 = new Node(55);
        Node n3 = new Node(128);
        LinkedList2 list = new LinkedList2();
        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);

        Node n7 = new Node(35);

        // Act
        list.insertAfter(null, n7);

        // Assert
        assertThat(list.count()).isEqualTo(4);
        assertThat(toList(list)).containsExactly(n7, n1, n2, n3);
        assertThat(list.head).isEqualTo(n7);

        assertThat(n7.next).isEqualTo(n1);
        assertThat(n1.prev).isEqualTo(n7);
    }

    @Test
    void givenEmptyList_whenInsertN7AfterNull_thenReturnListWithN7AtHead() {
        // Arrange
        LinkedList2 list = new LinkedList2();

        Node n7 = new Node(35);

        // Act
        list.insertAfter(null, n7);

        // Assert
        assertThat(list.count()).isEqualTo(1);
        assertThat(toList(list)).containsExactly(n7);
        assertThat(list.head).isEqualTo(n7);
        assertThat(list.tail).isEqualTo(n7);

    }

    @Test
    void givenList_whenInsertNull_thenReturnUnchangedList() {
        // Arrange
        Node n1 = new Node(12);
        Node n2 = new Node(55);
        Node n3 = new Node(128);
        LinkedList2 list = new LinkedList2();
        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);

        // Act
        list.insertAfter(null, null);

        // Assert
        assertThat(list.count()).isEqualTo(3);
        assertThat(toList(list)).containsExactly(n1, n2, n3);
        assertThat(list.tail).isEqualTo(n3);

    }

    @Test
    void givenList_whenReverse_thenReturnReversedList() {
        // Arrange
        Node n1 = new Node(12);
        Node n2 = new Node(55);
        Node n3 = new Node(128);
        LinkedList2 list = new LinkedList2();
        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);

        // Act
        list.reverse();

        // Assert
        assertThat(list.count()).isEqualTo(3);
        assertThat(toList(list)).containsExactly(n3, n2, n1);
        assertThat(list.head).isEqualTo(n3);
        assertThat(list.tail).isEqualTo(n1);
    }

    @Test
    void givenEmptyList_whenReverse_thenReturnEmptyList() {
        // Arrange
        LinkedList2 list = new LinkedList2();

        // Act
        list.reverse();

        // Assert
        assertThat(list.count()).isZero();
    }

    @Test
    void givenListWithACycle_whenFindCycle_thenReturnTrue() {
        // Arrange
        Node n1 = new Node(12);
        Node n2 = new Node(55);
        Node n3 = new Node(128);
        LinkedList2 list = new LinkedList2();
        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);
        n3.next = n2;

        // Act
        boolean hasCycle = list.findCycle();

        // Assert
        assertThat(hasCycle).isTrue();
    }

    @Test
    void givenListWithoutACycle_whenFindCycle_thenReturnFalse() {
        // Arrange
        Node n1 = new Node(12);
        Node n2 = new Node(55);
        Node n3 = new Node(128);
        LinkedList2 list = new LinkedList2();
        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);

        // Act
        boolean hasCycle = list.findCycle();

        // Assert
        assertThat(hasCycle).isFalse();
    }

    @Test
    void givenEmptyList_whenFindCycle_thenReturnFalse() {
        // Arrange
        LinkedList2 list = new LinkedList2();

        // Act
        boolean hasCycle = list.findCycle();

        // Assert
        assertThat(hasCycle).isFalse();
    }

    @Test
    void givenList_whenSort_thenReturnSortedList() {
        // Arrange
        Node n1 = new Node(128);
        Node n2 = new Node(55);
        Node n3 = new Node(12);
        LinkedList2 list = new LinkedList2();
        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);

        // Act
        list.sort();

        int[] values = {12, 55, 128};

        // Assert
        Node current = list.head;
        for (int i : values) {
            assertThat(current.value).isEqualTo(i);
            current = current.next;
        }
    }

    @Test
    void givenEmptyList_whenSort_thenReturnEmptyList() {
        // Arrange
        LinkedList2 list = new LinkedList2();

        // Act
        list.sort();
    }

    @Test
    void givenTwoSameSizeLists_whenMerge_thenReturnMergedSortedList() {
        // Arrange
        Node n1 = new Node(128);
        Node n2 = new Node(55);
        Node n3 = new Node(12);
        LinkedList2 list1 = new LinkedList2();
        list1.addInTail(n1);
        list1.addInTail(n2);
        list1.addInTail(n3);

        Node n4 = new Node(24);
        Node n5 = new Node(73);
        Node n6 = new Node(5);
        LinkedList2 list2 = new LinkedList2();
        list2.addInTail(n4);
        list2.addInTail(n5);
        list2.addInTail(n6);

        // Act
        LinkedList2 mergedList = merge(list1, list2);

        // Assert
        assertThat(mergedList.count()).isEqualTo(6);
        assertThat(mergedList.head.value).isEqualTo(5);
        assertThat(mergedList.tail.value).isEqualTo(128);

        int[] values = {5, 12, 24, 55, 73, 128};
        Node current = mergedList.head;
        for (int i : values) {
            assertThat(current.value).isEqualTo(i);
            if (current.next != null) {
                assertThat(current.next.prev).isEqualTo(current);
            }
            current = current.next;
        }
    }

    @Test
    void givenTwoDifferentSizeLists_whenMerge_thenReturnMergedSortedList() {
        // Arrange
        Node n1 = new Node(128);
        Node n2 = new Node(55);
        Node n3 = new Node(12);
        LinkedList2 list1 = new LinkedList2();
        list1.addInTail(n1);
        list1.addInTail(n2);
        list1.addInTail(n3);

        Node n4 = new Node(24);
        LinkedList2 list2 = new LinkedList2();
        list2.addInTail(n4);

        // Act
        LinkedList2 mergedList = merge(list1, list2);

        // Assert
        assertThat(mergedList.count()).isEqualTo(4);
        assertThat(mergedList.head.value).isEqualTo(12);
        assertThat(mergedList.tail.value).isEqualTo(128);

        int[] values = {12, 24, 55, 128};
        Node current = mergedList.head;
        for (int i : values) {
            assertThat(current.value).isEqualTo(i);
            if (current.next != null) {
                assertThat(current.next.prev).isEqualTo(current);
            }
            current = current.next;
        }
    }
    @Test
    void givenTwoEmptyLists_whenMerge_thenReturnEmptyList() {
        // Arrange
        LinkedList2 list1 = new LinkedList2();
        LinkedList2 list2 = new LinkedList2();

        // Act
        LinkedList2 mergedList = merge(list1, list2);

        // Assert
        assertThat(mergedList.count()).isZero();
    }
}
