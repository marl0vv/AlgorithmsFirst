package org.gladmik;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HashTableTest {

    @Test
    void givenHashTable_whenPut_returnSlot() {
        // Arrange
        HashTable table = new HashTable(17, 3);

        // Act
        int slot = table.seekSlot("test");
        int puttedValueSlot = table.put("test");

        // Assert
        assertThat(table.slots).contains("test");
        assertThat(table.slots[slot]).isEqualTo("test");
        assertThat(slot).isEqualTo(puttedValueSlot);
    }


    @Test
    void givenHashTable_whenPutCollision_returnSlot() {
        // Arrange
        HashTable table = new HashTable(17, 3);

        // Act
        //Collision
        int slot1 = table.hashFun("Aa");
        int slot2 = table.hashFun("BB");

        int puttedValue1Slot = table.put("Aa");
        int puttedValue2Slot = table.put("BB");

        // Assert
        assertThat(table.slots).contains("Aa", "BB");
        assertThat(puttedValue1Slot).isEqualTo(4);
        assertThat(puttedValue2Slot).isEqualTo(7);
        assertThat(slot1).isEqualTo(slot2);
    }


    @Test
    void givenHashTable_whenPutSameValue_returnSameSlot() {
        // Arrange
        HashTable table = new HashTable(17, 3);

        // Act
        int puttedValue1Slot = table.put("Inserted string");
        int puttedValue2Slot = table.put("Inserted string");

        // Assert
        assertThat(table.slots).contains("Inserted string");
        assertThat(puttedValue1Slot).isEqualTo(1);
        assertThat(puttedValue2Slot).isEqualTo(1);
    }

    @Test
    void givenHashTable_whenPutFullTable_returnNegativeOne() {
        // Arrange
        HashTable table = new HashTable(17, 3);
        for (int i = 0; i < 17; i++) {
            table.put(Integer.toString(i));
        }
        // Act

        int puttedValueSlot = table.put("test");

        // Assert
        assertThat(puttedValueSlot).isEqualTo(-1);
    }

    @Test
    void givenEmptyHashTable_whenFind_returnNegativeOne() {
        // Arrange
        HashTable table = new HashTable(17, 3);

        // Act
        int foundValueSlot = table.find("test");

        // Assert
        assertThat(foundValueSlot).isEqualTo(-1);
    }

    @Test
    void givenHashTable_whenFind_returnSlot() {
        // Arrange
        HashTable table = new HashTable(17, 3);
        int puttedValueSlot = table.put("test");

        // Act
        int foundValueSlot = table.find("test");

        // Assert
        assertThat(foundValueSlot).isEqualTo(puttedValueSlot);
    }

    @Test
    void givenHashTable_whenFindCollision_returnSlot() {
        // Arrange
        HashTable table = new HashTable(17, 3);
        int puttedValue1Slot = table.put("Aa");
        int puttedValue2Slot = table.put("BB");

        // Act
        //Collision
        int slot1 = table.hashFun("Aa");
        int slot2 = table.hashFun("BB");

        int foundValue1Slot = table.find("Aa");
        int foundValue2Slot = table.find("BB");

        // Assert
        assertThat(table.slots).contains("Aa", "BB");
        assertThat(foundValue1Slot).isEqualTo(puttedValue1Slot);
        assertThat(foundValue2Slot).isEqualTo(puttedValue2Slot);
        assertThat(slot1).isEqualTo(slot2);
    }
}
