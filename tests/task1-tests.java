import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.gladmik.LinkedList.sumOfCorrespondingElements;

/**
 * <p> Created on: 21.03.2025
 *
 * @author Mikhail Gladkikh
 */
class LinkedListTest {

    @Test
    void givenList_whenClear_thenReturnEmptyList() {
        // Arrange
        LinkedList list = new LinkedList();
        list.addInTail(new Node(12));
        list.addInTail(new Node(55));

        // Act
        list.clear();

        // Assert
        assertThat(list.head).isEqualTo(null);
        assertThat(list.tail).isEqualTo(null);
    }

    @Test
    void givenList_whenHasThreeElementsAndCountInvoked_thenReturnThree() {
        // Arrange
        LinkedList list = new LinkedList();
        list.addInTail(new Node(12));
        list.addInTail(new Node(55));
        list.addInTail(new Node(128));

        // Act
        int count = list.count();

        // Assert
        assertThat(count).isEqualTo(3);
    }

    private List<Node> toList(LinkedList list) {
        List<Node> nodes = new ArrayList<>();
        Node current = list.head;
        while (current != null) {
            nodes.add(current);
            current = current.next;
        }
        return nodes;
    }

    @Test
    void givenList_whenInsertN7AfterN2_thenReturnListWithN7AfterN2() {
        // Arrange
        Node n1 = new Node(12);
        Node n2 = new Node(55);
        Node n3 = new Node(128);
        LinkedList list = new LinkedList();
        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);

        Node n7 = new Node(35);

        // Act
        list.insertAfter(n2, n7);

        // Assert
        assertThat(toList(list)).containsExactly(n1, n2, n7, n3);
        assertThat(list.tail).isEqualTo(n3);
        assertThat(list.count()).isEqualTo(4);
    }

    @Test
    void givenList_whenInsertN7AfterTail_thenReturnListWithN7AtTail() {
        // Arrange
        Node n1 = new Node(12);
        Node n2 = new Node(55);
        Node n3 = new Node(128);
        LinkedList list = new LinkedList();
        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);

        Node n7 = new Node(35);

        // Act
        list.insertAfter(n3, n7);

        // Assert
        assertThat(toList(list)).containsExactly(n1, n2, n3, n7);
        assertThat(list.tail).isEqualTo(n7);
        assertThat(list.count()).isEqualTo(4);
    }

    @Test
    void givenList_whenInsertN7AfterNull_thenReturnListWithN7AfterHead() {
        // Arrange
        Node n1 = new Node(12);
        Node n2 = new Node(55);
        Node n3 = new Node(128);
        LinkedList list = new LinkedList();
        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);

        Node n7 = new Node(35);

        // Act
        list.insertAfter(null, n7);

        // Assert
        assertThat(toList(list)).containsExactly(n7, n1, n2, n3);
        assertThat(list.tail).isEqualTo(n3);
        assertThat(list.count()).isEqualTo(4);
    }

    @Test
    void givenEmptyList_whenInsertN7AfterNull_thenReturnListWithN7AtHead() {
        // Arrange
        LinkedList list = new LinkedList();

        Node n7 = new Node(35);

        // Act
        list.insertAfter(null, n7);

        // Assert
        assertThat(toList(list)).containsExactly(n7);
        assertThat(list.head).isEqualTo(n7);
        assertThat(list.tail).isEqualTo(n7);
        assertThat(list.count()).isEqualTo(1);
    }

    @Test
    void givenList_whenInsertNull_thenReturnUnchangedList() {
        // Arrange
        Node n1 = new Node(12);
        Node n2 = new Node(55);
        Node n3 = new Node(128);
        LinkedList list = new LinkedList();
        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);

        // Act
        list.insertAfter(null, null);

        // Assert
        assertThat(toList(list)).containsExactly(n1, n2, n3);
        assertThat(list.tail).isEqualTo(n3);
        assertThat(list.count()).isEqualTo(3);
    }

    @Test
    void givenList_whenFindAllNodesWithValue12_thenReturnArrayListWithNodesWhichValuesAre12() {
        // Arrange
        Node n1 = new Node(12);
        Node n2 = new Node(55);
        Node n3 = new Node(12);
        Node n4 = new Node(20);
        Node n5 = new Node(12);

        LinkedList list = new LinkedList();
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
    void givenList_whenRemoveNodeWithValueInTheMiddle_thenReturnTrue() {
        // Arrange
        Node n1 = new Node(12);
        Node n2 = new Node(55);
        Node n3 = new Node(36);
        Node n4 = new Node(55);

        LinkedList list = new LinkedList();
        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);
        list.addInTail(n4);

        // Act
        boolean removalSuccess = list.remove(55);

        // Assert
        assertThat(toList(list)).containsExactly(n1, n3, n4);
        assertThat(list.count()).isEqualTo(3);
        assertThat(removalSuccess).isTrue();
    }

    @Test
    void givenList_whenRemoveNodeWithValueInTheHead_thenReturnTrue() {
        // Arrange
        Node n1 = new Node(12);
        Node n2 = new Node(55);
        Node n3 = new Node(12);

        LinkedList list = new LinkedList();
        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);

        // Act
        boolean removalSuccess = list.remove(12);

        // Assert
        assertThat(toList(list)).containsExactly(n2, n3);
        assertThat(list.head).isEqualTo(n2);
        assertThat(list.count()).isEqualTo(2);
        assertThat(removalSuccess).isTrue();
    }

    @Test
    void givenList_whenRemoveNodeWithValueInTheTail_thenReturnTrue() {
        // Arrange
        Node n1 = new Node(42);
        Node n2 = new Node(55);
        Node n3 = new Node(12);

        LinkedList list = new LinkedList();
        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);

        // Act
        boolean removalSuccess = list.remove(12);

        // Assert
        assertThat(toList(list)).containsExactly(n1, n2);
        assertThat(list.tail).isEqualTo(n2);
        assertThat(list.count()).isEqualTo(2);
        assertThat(removalSuccess).isTrue();
    }

    @Test
    void givenList_whenRemoveButNodeIsNotInTheList_thenReturnFalse() {
        // Arrange
        Node n1 = new Node(42);
        Node n2 = new Node(55);
        Node n3 = new Node(12);

        LinkedList list = new LinkedList();
        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);

        // Act
        boolean removalSuccess = list.remove(64);

        // Assert
        assertThat(toList(list)).containsExactly(n1, n2, n3);
        assertThat(list.count()).isEqualTo(3);
        assertThat(removalSuccess).isFalse();
    }

    @Test
    void givenListWithOneElement_whenRemove_thenReturnTrue() {
        // Arrange
        Node n1 = new Node(42);

        LinkedList list = new LinkedList();
        list.addInTail(n1);

        // Act
        boolean removalSuccess = list.remove(42);

        // Assert
        assertThat(toList(list)).containsExactly();
        assertThat(list.count()).isEqualTo(0);
        assertThat(removalSuccess).isTrue();
    }

    @Test
    void givenEmptyList_whenRemove_thenReturnFalse() {
        // Arrange
        LinkedList list = new LinkedList();

        // Act
        boolean removalSuccess = list.remove(42);

        // Assert
        assertThat(toList(list)).containsExactly();
        assertThat(list.count()).isEqualTo(0);
        assertThat(removalSuccess).isFalse();
    }

    @Test
    void givenList_whenRemoveAll_thenListShouldBeWithoutDeletedValues() {
        // Arrange
        Node n1 = new Node(55);
        Node n2 = new Node(55);
        Node n3 = new Node(36);
        Node n4 = new Node(46);
        Node n5 = new Node(55);

        LinkedList list = new LinkedList();
        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);
        list.addInTail(n4);
        list.addInTail(n5);

        // Act
        list.removeAll(55);

        // Assert
        assertThat(toList(list)).containsExactly(n3, n4);
        assertThat(list.count()).isEqualTo(2);
    }

    @Test
    void givenTwoListWithEqualSize_whenSumOfCorrespondingElements_thenReturnListWithCorrespondingSums() {
        // Arrange
        Node l1n1 = new Node(55);
        Node l1n2 = new Node(55);
        Node l1n3 = new Node(36);

        LinkedList list1 = new LinkedList();
        list1.addInTail(l1n1);
        list1.addInTail(l1n2);
        list1.addInTail(l1n3);

        Node l2n1 = new Node(62);
        Node l2n2 = new Node(18);
        Node l2n3 = new Node(54);

        LinkedList list2 = new LinkedList();
        list2.addInTail(l2n1);
        list2.addInTail(l2n2);
        list2.addInTail(l2n3);

        // Act
        LinkedList listWithCorrespondingSums = sumOfCorrespondingElements(list1, list2);


        int[] expectedValues = {117, 73, 90};
        // Assert
        Node current = listWithCorrespondingSums.head;
        for (int i : expectedValues) {
            assertThat(current.value).isEqualTo(i);
            current = current.next;
        }
    }

    @Test
    void givenListWithDifferentSizes_whenSumOfCorrespondingElements_thenReturnEmptyList() {
        // Arrange
        Node l1n1 = new Node(55);
        Node l1n2 = new Node(55);

        LinkedList list1 = new LinkedList();
        list1.addInTail(l1n1);
        list1.addInTail(l1n2);

        Node l2n1 = new Node(62);

        LinkedList list2 = new LinkedList();
        list2.addInTail(l2n1);

        // Act
        LinkedList listWithCorrespondingSums = sumOfCorrespondingElements(list1, list2);

        // Assert
        assertThat(toList(listWithCorrespondingSums)).containsExactly();

    }
}
