package org.gladmik;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.gladmik.BalancedString.isBalancedBracketString;
import static org.gladmik.PostfixExpression.evaluate;

/**
 * <p> Created on: 26.03.2025
 *
 * @author Mikhail Gladkikh
 */
class StackTest {

    @Test
    void givenStack_whenSize_thenReturnSize() {
        // Arrange
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(1);

        // Act
        int size = stack.size();

        // Assert
        assertThat(size).isEqualTo(3);
    }

    @Test
    void givenEmptyStack_whenSize_thenReturnZero() {
        // Arrange
        Stack<Integer> stack = new Stack<>();

        // Act
        int size = stack.size();

        // Assert
        assertThat(size).isZero();
    }

    @Test
    void givenStack_whenSizeAfterRemove_thenReturnSize() {
        // Arrange
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(1);

        assertThat(stack.size()).isEqualTo(3);
        stack.pop();

        //Act
        int size = stack.size();

        // Assert
        assertThat(size).isEqualTo(2);
    }

    @Test
    void givenStack_whenPush_thenStackMustBeCorrect() {
        // Arrange
        Stack<Integer> stack = new Stack<>();

        //Act
        stack.push(1);
        stack.push(2);
        stack.push(1);

        // Assert
        List<Integer> elements = List.of(1, 2, 1);

        assertThat(stack.values).isEqualTo(elements);
        assertThat(stack.size()).isEqualTo(3);
        assertThat(stack.peek()).isEqualTo(1);
    }

    @Test
    void givenStack_whenPushNull_thenStackMustBeCorrect() {
        // Arrange
        Stack<Integer> stack = new Stack<>();

        //Act
        stack.push(1);
        stack.push(null);
        stack.push(null);

        // Assert
        List<Integer> elements = new LinkedList<>(Arrays.asList(null, null, 1));

        assertThat(stack.values).isEqualTo(elements);
        assertThat(stack.size()).isEqualTo(3);
        assertThat(stack.peek()).isNull();
    }

    @Test
    void givenStack_whenPop_thenReturnPoppedElement() {
        // Arrange
        Stack<Integer> stack = new Stack<>();
        stack.push(32);
        stack.push(65);
        stack.push(17);

        //Act
        stack.pop();

        // Assert
        List<Integer> elements = new LinkedList<>(Arrays.asList(65, 32));

        assertThat(stack.values).isEqualTo(elements);
        assertThat(stack.size()).isEqualTo(2);
        assertThat(stack.peek()).isEqualTo(65);
    }

    @Test
    void givenEmptyStack_whenPop_thenReturnNull() {
        // Arrange
        Stack<Integer> stack = new Stack<>();

        //Act
        Integer popResult = stack.pop();

        // Assert
        List<Integer> elements = Collections.emptyList();

        assertThat(popResult).isNull();
        assertThat(stack.values).isEqualTo(elements);
        assertThat(stack.size()).isZero();
    }

    @Test
    void givenEmptyStack_whenPeek_thenReturnNull() {
        // Arrange
        Stack<Integer> stack = new Stack<>();

        //Act
        Integer peekResult = stack.peek();

        // Assert
        List<Integer> elements = Collections.emptyList();

        assertThat(peekResult).isNull();
        assertThat(stack.values).isEqualTo(elements);
        assertThat(stack.size()).isZero();
    }

    @Test
    void givenStack_whenPeek_thenReturnLastElement() {
        // Arrange
        Stack<Integer> stack = new Stack<>();
        stack.push(32);
        stack.push(65);
        stack.push(17);

        //Act
        Integer peekResult = stack.peek();

        // Assert
        List<Integer> elements = new LinkedList<>(Arrays.asList(17, 65, 32));

        assertThat(peekResult).isEqualTo(17);
        assertThat(stack.values).isEqualTo(elements);
        assertThat(stack.size()).isEqualTo(3);
    }

    @Test
    void givenStack_whenPopEverythingAndThenPopAgain_thenReturnEmptyStack() {
        // Arrange
        Stack<Integer> stack = new Stack<>();
        stack.push(32);
        stack.push(65);
        stack.push(17);

        //Act
        while (stack.size() > 0) {
            stack.pop();
        }
        stack.pop();

        // Assert
        List<Integer> elements = Collections.emptyList();

        assertThat(stack.values).isEqualTo(elements);
        assertThat(stack.size()).isZero();
    }

    @ParameterizedTest
    @CsvSource({
            "'(())', true",
            "'{{}}', true",
            "'[[]]', true",
            "'(((())))', true",
            "'[[[[]]]]', true",
            "'{{{{}}}}', true",
            "'(({[]}))', true",
            "'', true",
            "'(()((())()))', true",
            "'(()()(()', false",
            "'[()(){()', false",
            "'())', false",
            "'(((()))', false",
            "'(', false",
            "')', false",
            "')(', false",
            "'())(', false",
            "'))((', false",
            "'((())', false"
    })
    void givenString_whenIsBalanced_ReturnTrueOrFalse(String input, boolean expected) {
        assertThat(isBalancedBracketString(input)).isEqualTo(expected);
    }

    @Test
    void givenStack_whenGetMinimum_returnMinimumValue() {
        // Arrange
        Stack<Integer> stack = new Stack<>();
        stack.push(32);
        stack.push(65);
        stack.push(17);

        // Act & Assert
        assertThat(stack.getMinimum()).isEqualTo(17);
    }

    @Test
    void givenEmptyStack_whenGetMinimum_returnMinimumValue() {
        // Arrange
        Stack<Integer> stack = new Stack<>();

        // Act & Assert
        assertThat(stack.getMinimum()).isNull();
    }

    @Test
    void givenStack_whenGetMinimumThenRemoveElementAndGetMinimum_returnMinimumValue() {
        // Arrange
        Stack<Integer> stack = new Stack<>();

        stack.push(32);
        stack.push(65);
        stack.push(17);

        assertThat(stack.getMinimum()).isEqualTo(17);

        // Act & Assert
        stack.pop();

        assertThat(stack.getMinimum()).isEqualTo(32);
    }

    @Test
    void givenStack_whenGetAverage_thenReturnAverage() {
        // Arrange
        Stack<Integer> stack = new Stack<>();
        stack.push(32);
        stack.push(65);
        stack.push(17);

        // Act & Assert
        assertThat(stack.getAverage()).isEqualTo((32 + 65 + 17) / 3.0);
    }

    @Test
    void givenEmptyStack_whenGetAverage_thenReturnZero() {
        // Arrange
        Stack<Integer> stack = new Stack<>();

        // Act & Assert
        assertThat(stack.getAverage()).isNaN();
    }

    @Test
    void givenExpressionString_whenEvaluate_thenReturnResult() {
        // Arrange
        String expression = "8 2 + 5 * 9 + =";

        // Act
        int result = evaluate(expression);

        // Assert
        assertThat(result).isEqualTo(59);
    }

    @Test
    void givenEmptyExpressionString_whenEvaluate_thenReturnZero() {
        // Act
        int result = evaluate(null);

        // Assert
        assertThat(result).isZero();
    }
}
