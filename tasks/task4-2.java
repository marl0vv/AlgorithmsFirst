package org.gladmik;

public class Stack<T extends Comparable<T>> {

    public final java.util.List<T> values;
    public final java.util.Deque<T> minimumValues;
    int elementsSum = 0;

    public Stack() {
        this.values = new java.util.LinkedList<>();
        this.minimumValues = new java.util.ArrayDeque<>();
    }

    public int size() {
        return values.size();
    }

    /*
        Time complexity - O(1) because getting and removing first element
        Space complexity - O(1) because no additional space used
     */
    public T pop() {
        if (values.isEmpty()) {
            return null;
        }
        T value = values.getFirst();
        values.remove(values.getFirst());

        if (minimumValues.isEmpty() || value.compareTo(minimumValues.peek()) == 0) {
            minimumValues.pop();
        }

        if (value instanceof Number number) {
            elementsSum -= number.intValue();
        }
        return value;


    }

    /*
        Time complexity - O(1) because just adding a new node to a linked list
        Space complexity - O(1) because no additional space used
     */
    public void push(T val) {
        values.addFirst(val);

        if (val != null && (minimumValues.isEmpty() || val.compareTo(minimumValues.peek()) <= 0)) {
            minimumValues.push(val);
        }

        if (val instanceof Number number) {
            elementsSum += number.intValue();
        }
    }

    public T peek() {
        if (values.isEmpty()) {
            return null;
        }
        return values.getFirst();
    }

    /*
     * Задание на курсе - 4
     * Номер задачи - 4-5
     * Краткое название - "Сбалансированная строка скобок"
     * Пространственная сложность - O(n), в случае если все скобки открывающие
     * Временная сложность - O(n), потому что итерируемся по строке n элементов
     * Рефлексия:
     * Мне как-то уже попадалась подобная задачка на Литкоде, но тогда я её решил написав
     * кучу if-else блоков. Использовать словарь - хорошая идея, код намного короче.
     */
    public static boolean isBalancedBracketString(String inputString) {
        Stack<Character> stack = new Stack<>();
        Set<Character> openBrackets = Set.of('(', '{', '[');
        Map<Character, Character> bracketMap = Map.of(')', '(',
                                                      '}', '{',
                                                      ']', '[');
        for (int i = 0; i < inputString.length(); i++) {
            Character currentBracket = inputString.charAt(i);
            if (openBrackets.contains(currentBracket)) {
                stack.push(currentBracket);
            } else if (stack.size() > 0 && stack.peek().equals(bracketMap.get(currentBracket))) {
                stack.pop();
            } else {
                return false;
            }

        }

        return stack.size() <= 0;
    }

    /*
     * Задание на курсе - 4
     * Номер задачи - 6
     * Краткое название - "Найти минимальный элемент"
     * Пространственная сложность - O(n)
     * Временная сложность - O(1)
     * Рефлексия:
     * Сначала не понял зачем вообще нужен второй стек и хотел просто хранить минимальное значение в переменной.
     * Потом догнал, что тогда мы не сможем понять, какое минимальное значение стало в стеке, если мы удалим элемент.
     * В принципе, понял, как это реализовать, но возникли сложности с тем, что мы не можем внутри класса без проблем в конструкторе
     * добавить объект этого же класса. Потому что тогда будет бесконечная рекурсия и словим StackOverflow.
     * Единственное, что пришло в голову - делать другой класс, в котором уже реализовывать функционал получения минимального значения.
     * Решил, чтобы не плодить лишнего кода использовать библиотечный deque.
     */
    public T getMinimum() {
        if (minimumValues.isEmpty()) {
            return null;
        }
        return minimumValues.peek();
    }

    /*
     * Задание на курсе - 4
     * Номер задачи - 7
     * Краткое название - "Найти среднее арифметическое"
     * Пространственная сложность - O(1)
     * Временная сложность - O(1)
     */
    public Double getAverage() {
        return (double) elementsSum / this.size();
    }

    /*
     * Задание на курсе - 4
     * Номер задачи - 8
     * Краткое название - "Постфиксная запись выражения"
     * Пространственная сложность - O(n)
     * Временная сложность - O(n)
     * Рефлексия: Из описания задачи примерно сразу понял, что нужно делать, но сделал ошибку с тем, что делал
     * a.pop() + b.pop(). Ну и операции соответственно были все в if-else. Поправил это и код сразу читабельнее стал.
     */
    public static int evaluate(String expression) {
        if (expression == null || expression.isEmpty()) {
            return 0;
        }
        Stack<String> allElements = new Stack<>();
        Stack<Integer> numbers = new Stack<>();

        String[] expressionElements = expression.split("\\s+");
        for (int i = expressionElements.length - 1; i >= 0; i--) {
            allElements.push(expressionElements[i]);
        }
        Map<String, Runnable> commands = new HashMap<>();
        commands.put("+", () -> numbers.push(numbers.pop() + numbers.pop()));
        commands.put("*", () -> numbers.push(numbers.pop() * numbers.pop()));

        while (allElements.size() > 0) {
            String element = allElements.pop();
            if (element.equals("=")) {
                return numbers.pop();
            } else if (commands.containsKey(element)) {
                commands.get(element).run();
            } else {
                numbers.push(Integer.parseInt(element));
            }
        }

        return 0;
    }
}