package com.cgvsu.math.vectors;

import com.cgvsu.math.vectors.Vector3f;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Vector3fTest {
    // Конструктор и гетеры
    @Test
    public void testConstructorAndGetters() {
        System.out.println("Vector3f: Тестирование конструктора и геттеров...");
        Vector3f v = new Vector3f(1.0F, 2.0F, 3.0F);
        assertEquals(1.0, v.getX(), 0.0001);
        assertEquals(2.0, v.getY(), 0.0001);
        assertEquals(3.0, v.getZ(), 0.0001);
    }

    // Сложение
    @Test
    public void testAddition() {
        System.out.println("Vector3f: Тестирование сложения векторов...");
        Vector3f v1 = new Vector3f(1, 2, 3);
        Vector3f v2 = new Vector3f(4, 5, 6);
        Vector3f result = v1.addition(v2);
        assertEquals(5.0, result.getX(), 0.0001);
        assertEquals(7.0, result.getY(), 0.0001);
        assertEquals(9.0, result.getZ(), 0.0001);
    }

    // Вычитание
    @Test
    public void testSubtraction() {
        System.out.println("Vector3f: Тестирование вычитания векторов...");
        Vector3f v1 = new Vector3f(5, 6, 7);
        Vector3f v2 = new Vector3f(1, 2, 3);
        Vector3f result = v1.subtraction(v2);
        assertEquals(4.0, result.getX(), 0.0001);
        assertEquals(4.0, result.getY(), 0.0001);
        assertEquals(4.0, result.getZ(), 0.0001);
    }

    // Произведение
    @Test
    public void testMultiplicationByScalar() {
        System.out.println("Vector3f: Тестирование умножения вектора на скаляр...");
        Vector3f v = new Vector3f(1, 2, 3);
        Vector3f result = v.multiplication(2);
        assertEquals(2.0, result.getX(), 0.0001);
        assertEquals(4.0, result.getY(), 0.0001);
        assertEquals(6.0, result.getZ(), 0.0001);
    }

    // Деление
    @Test
    public void testDivisionByScalar() {
        System.out.println("Vector3f: Тестирование деления вектора на скаляр...");
        Vector3f v = new Vector3f(2, 4, 6);
        Vector3f result = v.division(2);
        assertEquals(1.0, result.getX(), 0.0001);
        assertEquals(2.0, result.getY(), 0.0001);
        assertEquals(3.0, result.getZ(), 0.0001);
    }

    // Длина
    @Test
    public void testLength() {
        System.out.println("Vector3f: Тестирование длины вектора...");
        Vector3f v = new Vector3f(2, 3, 6);
        assertEquals(7.0, v.length(), 0.0001);
    }

    // Нормализация
    @Test
    public void testNormalize() {
        System.out.println("Vector3f: Тестирование нормализации вектора...");
        Vector3f v = new Vector3f(1, 0, 0);
        Vector3f normalized = v.normalize();
        assertEquals(1.0, normalized.getX(), 0.0001);
        assertEquals(0.0, normalized.getY(), 0.0001);
        assertEquals(0.0, normalized.getZ(), 0.0001);
        assertEquals(1.0, normalized.length(), 0.0001);
    }

    // Векторное произведение
    @Test
    public void testVectorProduct() {
        System.out.println("Vector3f: Векторного скалярного произведения...");
        Vector3f v1 = new Vector3f(1, 0, 0);
        Vector3f v2 = new Vector3f(0, 1, 0);
        Vector3f result = v1.vectorProduct(v2);

        assertEquals(0.0, result.getX(), 0.0001);
        assertEquals(0.0, result.getY(), 0.0001);
        assertEquals(1.0, result.getZ(), 0.0001);
    }

    // Скалярное произведение
    @Test
    public void testScalarProduct() {
        System.out.println("Vector3f: Тестирование скалярного произведения...");
        Vector3f v1 = new Vector3f(1, 2, 3);
        Vector3f v2 = new Vector3f(4, 5, 6);
        double result = v1.scalarProduct(v2);
        assertEquals(32.0, result, 0.0001);
    }

    // Вывод
    @Test
    public void testToString() {
        System.out.println("Vector3f: Тестирование вывода вектора...");
        Vector3f v = new Vector3f(1.5F, 2.5F, 3.5F);
        assertEquals("(1.5; 2.5; 3.5)", v.toString());
    }
}
