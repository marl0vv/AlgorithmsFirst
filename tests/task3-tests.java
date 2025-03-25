package org.gladmik;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * <p> Created on: 24.03.2025
 *
 * @author Mikhail Gladkikh
 */
class DynArrayTest {

    @Test
    void givenArray_whenMakeArray_thenReturnArrayWithBiggerCapacity() {
        // Arrange
        DynArray<Integer> array = new DynArray<>(Integer.class);

        assertThat(array.capacity).isEqualTo(16);

        // Act
        array.makeArray(20);

        // Assert
        assertThat(array.capacity).isEqualTo(20);
    }

    @Test
    void givenArray_whenMakeArrayCapacityLessThatCount_thenThrowException() {
        // Arrange
        DynArray<Integer> array = new DynArray<>(Integer.class);

        assertThat(array.capacity).isEqualTo(16);

        // Act
        for (int i = 0; i < 20; i++) {
            array.append(i);
        }

        // Assert
        assertThrows(IllegalArgumentException.class, () -> array.makeArray(16));
    }

    @Test
    void givenArray_whenMakeArrayCapacityLessThatMinimumCapacityValue_thenCapacityIsMinimumValue() {
        // Arrange
        DynArray<Integer> array = new DynArray<>(Integer.class);

        assertThat(array.capacity).isEqualTo(16);

        // Act
        array.makeArray(10);

        // Assert
        assertThat(array.capacity).isEqualTo(16);
    }

    @Test
    void givenArray_whenNewCapacityEqualsCapacity_thenReturnUnchangedArray() {
        // Arrange
        DynArray<Integer> array = new DynArray<>(Integer.class);

        assertThat(array.capacity).isEqualTo(16);

        // Assert
        array.makeArray(16);
    }

    @Test
    void givenArray_whenGetItem_thenReturnItem() {
        // Arrange
        DynArray<Integer> array = new DynArray<>(Integer.class);
        array.append(42);
        array.append(98);
        array.append(53);

        // Assert
        assertThat(array.getItem(0)).isEqualTo(42);
        assertThat(array.getItem(1)).isEqualTo(98);
        assertThat(array.getItem(2)).isEqualTo(53);
    }

    @Test
    void givenArray_whenGetItemInvalidPosition_thenReturnException() {
        // Arrange
        DynArray<Integer> array = new DynArray<>(Integer.class);
        array.append(42);
        array.append(98);
        array.append(53);

        // Assert
        assertThrows(IndexOutOfBoundsException.class, () -> array.getItem(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> array.getItem(3));
    }

    @Test
    void givenArray_whenAppendCapacityIsEnough_thenReturnAppendedArray() {
        // Arrange
        DynArray<Integer> array = new DynArray<>(Integer.class);

        // Act
        array.append(6);

        // Assert
        assertThat(array.count).isEqualTo(1);
        assertThat(array.capacity).isEqualTo(16);
        assertThat(array.array[0]).isEqualTo(6);
    }

    @Test
    void givenArray_whenAppendCapacityIsNotEnough_thenReturnAppendedArray() {
        // Arrange
        DynArray<Integer> array = new DynArray<>(Integer.class);

        // Act
        for (int i = 0; i < 20; i++) {
            array.append(i);
        }

        // Assert
        assertThat(array.count).isEqualTo(20);
        assertThat(array.capacity).isEqualTo(32);
        for (int i = 0; i < 20; i++) {
            assertThat(array.array[i]).isEqualTo(i);
        }
    }

    @Test
    void givenArray_whenAppendNull_thenReturnAppendedArray() {
        // Arrange
        DynArray<Integer> array = new DynArray<>(Integer.class);

        // Act
        array.append(null);

        // Assert
        assertThat(array.count).isEqualTo(1);
        assertThat(array.capacity).isEqualTo(16);
        assertThat(array.array[0]).isNull();
    }

    @Test
    void givenArray_whenInsertCapacityIsNotExceeded_thenReturnArrayWithInsertedElement() {
        // Arrange
        DynArray<Integer> array = new DynArray<>(Integer.class);
        array.append(6);
        array.append(12);
        array.append(17);

        // Act
        array.insert(52, 1);

        // Assert
        assertThat(array.count).isEqualTo(4);
        assertThat(array.capacity).isEqualTo(16);
        assertThat(array.array[1]).isEqualTo(52);
        int[] values = {6, 52, 12, 17};

        for (int i = 0; i < 4; i++) {
            assertThat(array.array[i]).isEqualTo(values[i]);
        }
    }

    @Test
    void givenArray_whenInsertCapacityIsExceeded_thenReturnArrayWithInsertedElement() {
        // Arrange
        DynArray<Integer> array = new DynArray<>(Integer.class);
        for (int i = 0; i < 16; i++) {
            array.append(i);
        }
        assertThat(array.capacity).isEqualTo(16);

        // Act
        array.insert(52, 0);

        // Assert
        assertThat(array.count).isEqualTo(17);
        assertThat(array.capacity).isEqualTo(32);
        assertThat(array.array[0]).isEqualTo(52);

        for (int i = 0; i < 16; i++) {
            assertThat(array.array[i + 1]).isEqualTo(i);
        }
    }

    @Test
    void givenArray_whenInsertIndexIsIllegalPosition_thenReturnException() {
        // Arrange
        DynArray<Integer> array = new DynArray<>(Integer.class);
        array.append(1);


        // Act && Assert
        assertThrows(IndexOutOfBoundsException.class, () -> array.insert(52, 2));
        assertThrows(IndexOutOfBoundsException.class, () -> array.insert(52, -1));
    }

    @Test
    void givenArray_whenInsertManyElements_thenReturnArrayWithInsertedElement() {
        // Arrange
        DynArray<Integer> array = new DynArray<>(Integer.class);
        array.append(6);
        array.append(12);
        array.append(17);

        // Act
        array.insert(52, 0); // in the start
        array.insert(42, 3); // in the end
        array.insert(79, 1); // in the middle

        // Assert
        assertThat(array.count).isEqualTo(6);
        assertThat(array.capacity).isEqualTo(16);
        int[] values = {52, 79, 6,  12, 42, 17};

        for (int i = 0; i < 6; i++) {
            assertThat(array.array[i]).isEqualTo(values[i]);
        }
    }

    @Test
    void givenArray_whenRemoveTailCapacityNotChanged_thenReturnArrayWithoutRemovedElement() {
        // Arrange
        DynArray<Integer> array = new DynArray<>(Integer.class);
        array.append(6);
        array.append(12);
        array.append(17);

        // Act
        array.remove(2);

        // Assert
        assertThat(array.count).isEqualTo(2);
        assertThat(array.capacity).isEqualTo(16);
        assertThrows(IndexOutOfBoundsException.class, () -> array.getItem(2));
    }

    @Test
    void givenArray_whenRemoveMiddleCapacityNotChanged_thenReturnArrayWithoutRemovedElement() {
        // Arrange
        DynArray<Integer> array = new DynArray<>(Integer.class);
        array.append(6);
        array.append(12);
        array.append(17);
        array.append(75);

        // Act
        array.remove(2);

        // Assert
        assertThat(array.count).isEqualTo(3);
        assertThat(array.capacity).isEqualTo(16);
        assertThrows(IndexOutOfBoundsException.class, () -> array.getItem(3));

        int[] values = {6, 12, 75};
        for (int i = 0; i < 3; i++) {
            assertThat(array.array[i]).isEqualTo(values[i]);
        }
    }

    @Test
    void givenArray_whenRemoveCapacityChanged_thenReturnArrayWithoutRemovedElement() {
        // Arrange
        DynArray<Integer> array = new DynArray<>(Integer.class);
        for (int i = 0; i < 17; i++) {
            array.append(i);
        }
        assertThat(array.capacity).isEqualTo(32);

        // Act
        array.remove(2);
        array.remove(6);

        // Assert
        assertThat(array.count).isEqualTo(15);
        assertThat(array.capacity).isEqualTo(21);
        assertThrows(IndexOutOfBoundsException.class, () -> array.getItem(16));
    }

    @Test
    void givenArray_whenRemoveManyElementsCapacityChangesToMinimum_thenReturnArrayWithMinimumCapacity() {
        // Arrange
        DynArray<Integer> array = new DynArray<>(Integer.class);
        for (int i = 0; i < 17; i++) {
            array.append(i);
        }
        assertThat(array.capacity).isEqualTo(32);

        // Act
        for (int i = 16; i >= 0; i--) {
            array.remove(i);
        }

        // Assert
        assertThat(array.count).isZero();
        assertThat(array.capacity).isEqualTo(16);
        assertThrows(IndexOutOfBoundsException.class, () -> array.getItem(16));
    }

    @Test
    void givenArray_whenRemoveIndexIsIllegalPosition_thenReturnException() {
        // Arrange
        DynArray<Integer> array = new DynArray<>(Integer.class);
        array.append(1);


        // Act && Assert
        assertThrows(IndexOutOfBoundsException.class, () -> array.remove(2));
        assertThrows(IndexOutOfBoundsException.class, () -> array.remove(-1));
    }
}
