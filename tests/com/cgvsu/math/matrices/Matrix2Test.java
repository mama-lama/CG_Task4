package com.cgvsu.math.matrices;

import com.cgvsu.math.vectors.Vector2f;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Matrix2Test {

    @Test
    public void testIdentityMatrix() {
        System.out.println("Matrix2: Тестирование создания единичной матрицы...");
        Matrix2 identity = new Matrix2().identity();

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                float expected = (i == j) ? 1.0F : 0.0F;
                assertEquals(expected, identity.get(i, j), 0.0001);
            }
        }
    }

    @Test
    public void testZeroMatrix() {
        System.out.println("Matrix2: Тестирование создания нулевой матрицы...");
        Matrix2 zero = new Matrix2().zero();

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(0.0, zero.get(i, j), 0.0001);
            }
        }
    }

    @Test
    public void testAddition() {
        System.out.println("Matrix2: Тестирование сложения матриц...");
        float[][] data1 = {
                {1, 2},
                {3, 4}
        };
        float[][] data2 = {
                {4, 3},
                {2, 1}
        };

        Matrix2 m1 = new Matrix2(data1);
        Matrix2 m2 = new Matrix2(data2);
        Matrix2 result = m1.add(m2);

        assertEquals(5.0, result.get(0, 0), 0.0001);
        assertEquals(5.0, result.get(0, 1), 0.0001);
        assertEquals(5.0, result.get(1, 0), 0.0001);
        assertEquals(5.0, result.get(1, 1), 0.0001);
    }

    @Test
    public void testSubtraction() {
        System.out.println("Matrix2: Тестирование вычитания...");
        float[][] data1 = {
                {10, 10},
                {10, 10}
        };
        float[][] data2 = {
                {1, 2},
                {3, 4}
        };

        Matrix2 m1 = new Matrix2(data1);
        Matrix2 m2 = new Matrix2(data2);
        Matrix2 result = m1.sub(m2);

        assertEquals(9.0, result.get(0, 0), 0.0001);
        assertEquals(8.0, result.get(0, 1), 0.0001);
        assertEquals(7.0, result.get(1, 0), 0.0001);
        assertEquals(6.0, result.get(1, 1), 0.0001);
    }

    @Test
    public void testMultiplicationWithVector() {
        System.out.println("Matrix2: Тестирование умножения матрицы на вектор...");
        float[][] data = {
                {1, 2},
                {3, 4}
        };

        Matrix2 m = new Matrix2(data);
        Vector2f v = new Vector2f(2, 3);
        Vector2f result = m.mult(v);

        assertEquals(8.0, result.getX(), 0.0001);
        assertEquals(18.0, result.getY(), 0.0001);
    }

    @Test
    public void testMatrixMultiplication() {
        System.out.println("Matrix2: Тестирование умножения матрицы на матрицу...");
        float[][] data1 = {
                {1, 2},
                {3, 4}
        };
        float[][] data2 = {
                {2, 0},
                {1, 2}
        };

        Matrix2 m1 = new Matrix2(data1);
        Matrix2 m2 = new Matrix2(data2);
        Matrix2 result = m1.mult(m2);

        assertEquals(4.0, result.get(0, 0), 0.0001);
        assertEquals(4.0, result.get(0, 1), 0.0001);
        assertEquals(10.0, result.get(1, 0), 0.0001);
        assertEquals(8.0, result.get(1, 1), 0.0001);
    }

    @Test
    public void testTransposition() {
        System.out.println("Matrix2: Тестирование транспозиции...");
        float[][] data = {
                {1, 2},
                {3, 4}
        };

        Matrix2 m = new Matrix2(data);
        Matrix2 transposed = m.trans();

        assertEquals(1.0, transposed.get(0, 0), 0.0001);
        assertEquals(3.0, transposed.get(0, 1), 0.0001);
        assertEquals(2.0, transposed.get(1, 0), 0.0001);
        assertEquals(4.0, transposed.get(1, 1), 0.0001);
    }

    @Test
    public void testMultiplicationWithIdentity() {
        System.out.println("Matrix2: Тестирование умножения матрицы на единичную матрицу...");
        float[][] data = {
                {1, 2},
                {3, 4}
        };

        Matrix2 m = new Matrix2(data);
        Matrix2 identity = new Matrix2().identity();
        Matrix2 result = m.mult(identity);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(m.get(i, j), result.get(i, j), 0.0001);
            }
        }
    }
}