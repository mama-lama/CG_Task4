package com.cgvsu.math.vectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Vector2fTest {
    // Конструктор и гетеры
    @Test
    @DisplayName("Vector2: Тестирование конструктора и геттеров...")
    public void testConstructorAndGetters() {
        System.out.println("Vector2: Тестирование конструктора и геттеров...");
        Vector2f v = new Vector2f(3.0F, 4.0F);
        assertEquals(3.0, v.getX(), 0.0001);
        assertEquals(4.0, v.getY(), 0.0001);
    }

    // Конструктор без аргументов
    @Test
    @DisplayName("Vector2: Тестирование конструктора без аргументов...")
    public void testDefaultConstructor() {
        System.out.println("Vector2: Тестирование конструктора без аргументов...");
        Vector2f v = new Vector2f();
        assertEquals(0.0, v.getX(), 0.0001);
        assertEquals(0.0, v.getY(), 0.0001);
    }

    // Сложение
    @Test
    @DisplayName("Vector2: Тестирование сложения векторов...")
    public void testAddition() {
        System.out.println("Vector2: Тестирование сложения векторов...");
        Vector2f v1 = new Vector2f(3, 4);
        Vector2f v2 = new Vector2f(1, 2);
        Vector2f result = v1.addition(v2);
        assertEquals(4.0, result.getX(), 0.0001);
        assertEquals(6.0, result.getY(), 0.0001);
    }

    // Вычитание
    @Test
    @DisplayName("Vector2: Тестирование вычитания векторов...")
    public void testSubtraction() {
        System.out.println("Vector2: Тестирование вычитания векторов...");
        Vector2f v1 = new Vector2f(3, 4);
        Vector2f v2 = new Vector2f(1, 2);
        Vector2f result = v1.subtraction(v2);
        assertEquals(2.0, result.getX(), 0.0001);
        assertEquals(2.0, result.getY(), 0.0001);
    }

    // Произведение
    @Test
    @DisplayName("Vector2: Тестирование умножения вектора на скаляр...")
    public void testMultiplicationByScalar() {
        System.out.println("Vector2: Тестирование умножения вектора на скаляр...");
        Vector2f v = new Vector2f(3, 4);
        Vector2f result = v.multiplication(2);
        assertEquals(6.0, result.getX(), 0.0001);
        assertEquals(8.0, result.getY(), 0.0001);
    }

    // Деление
    @Test
    @DisplayName("Vector2: Тестирование деления вектора на скаляр...")
    public void testDivisionByScalar() {
        System.out.println("Vector2: Тестирование деления вектора на скаляр...");
        Vector2f v = new Vector2f(3, 4);
        Vector2f result = v.division(2);
        assertEquals(1.5, result.getX(), 0.0001);
        assertEquals(2.0, result.getY(), 0.0001);
    }

    // Ошибка с делением на нулевой скаляр
    @Test
    @DisplayName("Vector2: Тестирование ошибки деления вектора на нулевой скаляр...")
    public void testDivisionByZeroThrowsException() {
        System.out.println("Vector2: Тестирование ошибки деления вектора на нулевой скаляр...");
        Vector2f v = new Vector2f(3, 4);
        assertThrows(ArithmeticException.class, () -> v.division(0));
    }

    // Длина
    @Test
    @DisplayName("Vector2: Тестирование длины вектора...")
    public void testLength() {
        System.out.println("Vector2: Тестирование длины вектора...");
        Vector2f v = new Vector2f(3, 4);
        assertEquals(5.0, v.length(), 0.0001);
    }

    // Нормализация
    @Test
    @DisplayName("Vector2: Тестирование нормализации вектора...")
    public void testNormalize() {
        System.out.println("Vector2: Тестирование нормализации вектора...");
        Vector2f v = new Vector2f(3, 4);
        Vector2f normalized = v.normalize();
        assertEquals(0.6, normalized.getX(), 0.0001);
        assertEquals(0.8, normalized.getY(), 0.0001);
        assertEquals(1.0, normalized.length(), 0.0001);
    }

    // Ошибка с работой нулевого вектора
    @Test
    @DisplayName("Vector2: Тестирование ошибки с работой нулевого вектора...")
    public void testNormalizeZeroVectorThrowsException() {
        System.out.println("Vector2: Тестирование ошибки с работой нулевого вектора...");
        Vector2f v = new Vector2f(0, 0);
        assertThrows(ArithmeticException.class, v::normalize);
    }

    // Скалярное произведение
    @Test
    @DisplayName("Vector2: Тестирование скалярного произведения...")
    public void testScalarProduct() {
        System.out.println("Vector2: Тестирование скалярного произведения...");
        Vector2f v1 = new Vector2f(1, 2);
        Vector2f v2 = new Vector2f(3, 4);
        double result = v1.scalarProduct(v2);
        assertEquals(11.0, result, 0.0001);
    }

    // Вывод
    @Test
    @DisplayName("Vector2: Тестирование вывода вектора...")
    public void testToString() {
        System.out.println("Vector2: Тестирование вывода вектора...");
        Vector2f v = new Vector2f(1.5F, 2.5F);
        assertEquals("(1.5; 2.5)", v.toString());
    }
}
