package com.cgvsu.math.matrices;

import com.cgvsu.math.vectors.Vector3f;
import com.cgvsu.math.matrices.Matrix3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Matrix3Test {

    @Test
    public void testIdentityMatrix() {
        System.out.println("Matrix3: Тестирование создания единичной матрицы...");
        Matrix3 identity = new Matrix3().identity();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                float expected = (i == j) ? 1.0F : 0.0F;
                assertEquals(expected, identity.get(i, j), 0.0001);
            }
        }
    }

    @Test
    public void testZeroMatrix() {
        System.out.println("Matrix3: Тестирование создания нулевой матрица...");
        Matrix3 zero = new Matrix3().zero();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(0.0, zero.get(i, j), 0.0001);
            }
        }
    }

    @Test
    public void testAddition() {
        System.out.println("Matrix3: Тестирование сложения матриц...");
        float[][] data1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        float[][] data2 = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};

        Matrix3 m1 = new Matrix3(data1);
        Matrix3 m2 = new Matrix3(data2);
        Matrix3 result = m1.add(m2);

        assertEquals(10.0, result.get(0, 0), 0.0001);
        assertEquals(10.0, result.get(1, 1), 0.0001);
        assertEquals(10.0, result.get(2, 2), 0.0001);
        assertEquals(10.0, result.get(0, 1), 0.0001);
    }

    @Test
    public void testSubtraction() {
        System.out.println("Matrix3: Тестирование вычитания...");
        float[][] data1 = {{10, 10, 10}, {10, 10, 10}, {10, 10, 10}};
        float[][] data2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        Matrix3 m1 = new Matrix3(data1);
        Matrix3 m2 = new Matrix3(data2);
        Matrix3 result = m1.sub(m2);

        assertEquals(9.0, result.get(0, 0), 0.0001);
        assertEquals(8.0, result.get(0, 1), 0.0001);
        assertEquals(1.0, result.get(2, 2), 0.0001);
    }

    @Test
    public void testMultiplicationWithVector() {
        System.out.println("Matrix3: Тестирование умножения матрицы на вектор...");
        float[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix3 m = new Matrix3(data);
        Vector3f v = new Vector3f(1, 2, 3);

        Vector3f result = m.mult(v);

        assertEquals(14.0, result.getX(), 0.0001);
        assertEquals(32.0, result.getY(), 0.0001);
        assertEquals(50.0, result.getZ(), 0.0001);
    }

    @Test
    public void testMatrixMultiplication() {
        System.out.println("Matrix3: Тестирование умножения матрицы на матрицу...");
        float[][] data1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        float[][] data2 = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};

        Matrix3 m1 = new Matrix3(data1);
        Matrix3 m2 = new Matrix3(data2);
        Matrix3 result = m1.mult(m2);

        assertEquals(30.0, result.get(0, 0), 0.0001);
        assertEquals(69.0, result.get(1, 1), 0.0001);
        assertEquals(90.0, result.get(2, 2), 0.0001);
    }

    @Test
    public void testTransposition() {
        System.out.println("Matrix3: Тестирование транспозиции...");
        float[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix3 m = new Matrix3(data);
        Matrix3 transposed = m.trans();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(m.get(i, j), transposed.get(j, i), 0.0001);
            }
        }
    }

    @Test
    public void testMultiplicationWithIdentity() {
        System.out.println("Matrix3: Тестирование умножения матрицы на единичную матрицу...");
        float[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix3 m = new Matrix3(data);
        Matrix3 identity = new Matrix3().identity();
        Matrix3 result = m.mult(identity);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(m.get(i, j), result.get(i, j), 0.0001);
            }
        }
    }
}