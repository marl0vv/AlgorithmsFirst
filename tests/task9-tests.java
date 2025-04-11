package org.gladmik;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * <p> Created on: 09.04.2025
 *
 * @author Mikhail Gladkikh
 */
class NativeDictionaryTest {

    @Test
    void givenDictionary_whenPut_returnDictionaryWithPutElement() {
        // Arrange
        NativeDictionary<Integer> dictionary = new NativeDictionary<>(10, Integer.class);

        // Act
        dictionary.put("one", 1);

        // Assert
        assertThat(dictionary.slots).contains("one");
        assertThat(dictionary.isKey("one")).isTrue();
        assertThat(dictionary.get("one")).isEqualTo(1);
        assertThat(dictionary.values).contains(1);
    }

    @Test
    void givenDictionary_whenPutExistingKey_returnDictionaryWithNewValueAndSameKey() {
        // Arrange
        NativeDictionary<Integer> dictionary = new NativeDictionary<>(10, Integer.class);
        dictionary.put("one", 1);

        // Act
        dictionary.put("one", 64);

        // Assert
        assertThat(dictionary.slots).contains("one");
        assertThat(dictionary.isKey("one")).isTrue();
        assertThat(dictionary.get("one")).isEqualTo(64);
        assertThat(dictionary.values).contains(64)
                                     .doesNotContain(1);

    }

    @Test
    void givenDictionary_whenPutInAFullDictionary_returnDictionaryWithNoNewElements() {
        // Arrange
        NativeDictionary<Integer> dictionary = new NativeDictionary<>(3, Integer.class);
        dictionary.put("one", 1);
        dictionary.put("two", 2);
        dictionary.put("three", 3);

        // Act
        dictionary.put("four", 4);

        // Assert
        assertThat(dictionary.slots).contains("one", "two", "three")
                                    .doesNotContain("four");
        assertThat(dictionary.isKey("one")).isTrue();
        assertThat(dictionary.get("one")).isEqualTo(1);
        assertThat(dictionary.isKey("two")).isTrue();
        assertThat(dictionary.get("two")).isEqualTo(2);
        assertThat(dictionary.isKey("three")).isTrue();
        assertThat(dictionary.get("three")).isEqualTo(3);
        assertThat(dictionary.values).contains(1, 2, 3)
                                     .doesNotContain(4);

    }

    @Test
    void givenDictionary_whenPutCollisionKeys_thenBothAreStored() {
        NativeDictionary<Integer> dictionary = new NativeDictionary<>(5, Integer.class);

        dictionary.put("Aa", 1); // "Aa" and "BB" have same hashCode
        dictionary.put("BB", 2);

        assertThat(dictionary.isKey("Aa")).isTrue();
        assertThat(dictionary.isKey("BB")).isTrue();
        assertThat(dictionary.slots).contains("Aa", "BB");
        assertThat(dictionary.values).contains(1, 2);
        assertThat(dictionary.get("Aa")).isEqualTo(1);
        assertThat(dictionary.get("BB")).isEqualTo(2);
    }

    @Test
    void givenEmptyDictionary_whenGet_returnNull() {
        // Arrange
        NativeDictionary<Integer> dictionary = new NativeDictionary<>(3, Integer.class);

        // Act
        Integer value = dictionary.get("one");

        // Assert
        assertThat(value).isNull();
    }

    @Test
    void givenFullDictionary_whenGetNonIncludedKey_returnNull() {
        // Arrange
        NativeDictionary<Integer> dictionary = new NativeDictionary<>(3, Integer.class);
        dictionary.put("one", 1);
        dictionary.put("two", 2);
        dictionary.put("three", 3);

        // Act
        Integer value = dictionary.get("four");

        // Assert
        assertThat(value).isNull();
    }

    @Test
    void givenDictionary_whenGet_returnValue() {
        // Arrange
        NativeDictionary<Integer> dictionary = new NativeDictionary<>(3, Integer.class);
        dictionary.put("one", 1);

        // Act
        Integer value = dictionary.get("one");

        // Assert
        assertThat(value).isEqualTo(1);
    }

    @Test
    void givenDictionary_whenGetKeyInsertedAfterCollision_thenReturnCorrectValue() {
        // Arrange
        NativeDictionary<Integer> dict = new NativeDictionary<>(3, Integer.class);
        dict.put("Aa", 100);
        dict.put("BB", 200);
        dict.put("CC", 300);

        // Act & Assert
        assertThat(dict.get("Aa")).isEqualTo(100);
        assertThat(dict.get("BB")).isEqualTo(200);
        assertThat(dict.get("CC")).isEqualTo(300);
    }

    @Test
    void givenDictionaryAndHasKey_whenIsKey_returnTrue() {
        // Arrange
        NativeDictionary<Integer> dictionary = new NativeDictionary<>(3, Integer.class);
        dictionary.put("one", 1);

        // Act
        Boolean isKey = dictionary.isKey("one");

        // Assert
        assertThat(isKey).isTrue();
    }

    @Test
    void givenDictionaryAndHasNotKey_whenIsKey_returnFalse() {
        // Arrange
        NativeDictionary<Integer> dictionary = new NativeDictionary<>(3, Integer.class);

        // Act
        Boolean isKey = dictionary.isKey("one");

        // Assert
        assertThat(isKey).isFalse();
    }

    @Test
    void givenDictionary_whenIsKeyNull_returnFalse() {
        // Arrange
        NativeDictionary<Integer> dictionary = new NativeDictionary<>(3, Integer.class);

        // Assert
        assertThatThrownBy(() -> dictionary.isKey(null))
                .isInstanceOf(NullPointerException.class);
    }
}
