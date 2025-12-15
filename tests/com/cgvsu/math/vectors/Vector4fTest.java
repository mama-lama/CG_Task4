package com.cgvsu.math.vectors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Vector4fTest {
    // Конструктор и гетеры
    @Test
    public void testConstructorAndGetters() {
        System.out.println("Vector4f: Тестирование конструктора и геттеров...");
        Vector4f v = new Vector4f(1.0F, 2.0F, 3.0F, 4.0F);
        assertEquals(1.0, v.getX(), 0.0001);
        assertEquals(2.0, v.getY(), 0.0001);
        assertEquals(3.0, v.getZ(), 0.0001);
        assertEquals(4.0, v.getW(), 0.0001);
    }

    // Сложение
    @Test
    public void testAddition() {
        System.out.println("Vector4f: Тестирование сложения векторов...");
        Vector4f v1 = new Vector4f(1, 2, 3, 4);
        Vector4f v2 = new Vector4f(5, 6, 7, 8);
        Vector4f result = v1.addition(v2);

        assertEquals(6.0, result.getX(), 0.0001);
        assertEquals(8.0, result.getY(), 0.0001);
        assertEquals(10.0, result.getZ(), 0.0001);
        assertEquals(12.0, result.getW(), 0.0001);
    }

    // Вычитание
    @Test
    public void testSubtraction() {
        System.out.println("Vector4f: Тестирование вычитания векторов...");
        Vector4f v1 = new Vector4f(5, 6, 7, 8);
        Vector4f v2 = new Vector4f(1, 2, 3, 4);
        Vector4f result = v1.subtraction(v2);

        assertEquals(4.0, result.getX(), 0.0001);
        assertEquals(4.0, result.getY(), 0.0001);
        assertEquals(4.0, result.getZ(), 0.0001);
        assertEquals(4.0, result.getW(), 0.0001);
    }

    // Произведение
    @Test
    public void testMultiplicationByScalar() {
        System.out.println("Vector4f: Тестирование умножения вектора на скаляр...");
        Vector4f v = new Vector4f(1, 2, 3, 4);
        Vector4f result = v.multiplication(2);

        assertEquals(2.0, result.getX(), 0.0001);
        assertEquals(4.0, result.getY(), 0.0001);
        assertEquals(6.0, result.getZ(), 0.0001);
        assertEquals(8.0, result.getW(), 0.0001);
    }

    // Деление
    @Test
    public void testDivisionByScalar() {
        System.out.println("Vector4f: Тестирование деления вектора на скаляр...");
        Vector4f v = new Vector4f(2, 4, 6, 8);
        Vector4f result = v.division(2);

        assertEquals(1.0, result.getX(), 0.0001);
        assertEquals(2.0, result.getY(), 0.0001);
        assertEquals(3.0, result.getZ(), 0.0001);
        assertEquals(4.0, result.getW(), 0.0001);
    }

    // Длина
    @Test
    public void testLength() {
        System.out.println("Vector2: Тестирование длины вектора...");
        Vector4f v = new Vector4f(2, 2, 4, 1);
        assertEquals(5.0, v.length(), 0.0001);
    }

    // Нормализация
    @Test
    public void testNormalize() {
        System.out.println("Vector4f: Тестирование нормализации вектора...");
        Vector4f v = new Vector4f(2, 0, 0, 0);
        Vector4f normalized = v.normalize();

        assertEquals(1.0, normalized.getX(), 0.0001);
        assertEquals(0.0, normalized.getY(), 0.0001);
        assertEquals(0.0, normalized.getZ(), 0.0001);
        assertEquals(0.0, normalized.getW(), 0.0001);
        assertEquals(1.0, normalized.length(), 0.0001);
    }

    // Скалярное произведение
    @Test
    public void testScalarProduct() {
        System.out.println("Vector2: Тестирование скалярного произведения...");
        Vector4f v1 = new Vector4f(1, 2, 3, 4);
        Vector4f v2 = new Vector4f(5, 6, 7, 8);
        double result = v1.scalarProduct(v2);
        assertEquals(70.0, result, 0.0001);
    }

    // Вывод
    @Test
    public void testToString() {
        System.out.println("Vector2: Тестирование вывода вектора...");
        Vector4f v = new Vector4f(1.5F, 2.5F, 3.5F, 4.5F);
        assertEquals("(1.5; 2.5; 3.5; 4.5)", v.toString());
    }
}
