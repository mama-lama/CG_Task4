package com.cgvsu.math.matrices;

import com.cgvsu.math.vectors.Vector4f;
import com.cgvsu.math.matrices.Matrix4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Matrix4Test {

    @Test
    public void testIdentityMatrix() {
        System.out.println("Matrix4: Тестирование создания единичной матрицы...");
        Matrix4 identity = new Matrix4().identity();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                float expected = (i == j) ? 1.0F : 0.0F;
                assertEquals(expected, identity.get(i, j), 0.0001);
            }
        }
    }

    @Test
    public void testZeroMatrix() {
        System.out.println("Matrix4: Тестирование создания нулевой матрицы...");
        Matrix4 zero = new Matrix4().zero();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(0.0, zero.get(i, j), 0.0001);
            }
        }
    }

    @Test
    public void testAddition() {
        System.out.println("Matrix4: Тестирование сложения матриц...");
        float[][] data1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        float[][] data2 = {
                {16, 15, 14, 13},
                {12, 11, 10, 9},
                {8, 7, 6, 5},
                {4, 3, 2, 1}
        };

        Matrix4 m1 = new Matrix4(data1);
        Matrix4 m2 = new Matrix4(data2);
        Matrix4 result = m1.add(m2);

        assertEquals(17.0, result.get(0, 0), 0.0001);
        assertEquals(17.0, result.get(3, 3), 0.0001);
        assertEquals(17.0, result.get(0, 1), 0.0001);
    }

    @Test
    public void testSubtraction() {
        System.out.println("Matrix4: Тестирование вычитания...");
        float[][] data1 = {
                {10, 10, 10, 10},
                {10, 10, 10, 10},
                {10, 10, 10, 10},
                {10, 10, 10, 10}
        };
        float[][] data2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        Matrix4 m1 = new Matrix4(data1);
        Matrix4 m2 = new Matrix4(data2);
        Matrix4 result = m1.sub(m2);

        assertEquals(9.0, result.get(0, 0), 0.0001);
        assertEquals(8.0, result.get(0, 1), 0.0001);
        assertEquals(-6.0, result.get(3, 3), 0.0001);
    }

    @Test
    public void testMultiplicationWithVector() {
        System.out.println("Matrix4: Тестирование умножения матрицы на вектор...");
        float[][] data = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        Matrix4 m = new Matrix4(data);
        Vector4f v = new Vector4f(1, 2, 3, 4);
        Vector4f result = m.mult(v);

        assertEquals(30.0, result.getX(), 0.0001);
        assertEquals(70.0, result.getY(), 0.0001);
        assertEquals(110.0, result.getZ(), 0.0001);
        assertEquals(150.0, result.getW(), 0.0001);
    }

    @Test
    public void testMatrixMultiplication() {
        System.out.println("Matrix4: Тестирование умножения матрицы на матрицу...");
        float[][] data1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        float[][] data2 = {
                {16, 15, 14, 13},
                {12, 11, 10, 9},
                {8, 7, 6, 5},
                {4, 3, 2, 1}
        };

        Matrix4 m1 = new Matrix4(data1);
        Matrix4 m2 = new Matrix4(data2);
        Matrix4 result = m1.mult(m2);

        assertEquals(80.0, result.get(0, 0), 0.0001);
        assertEquals(214.0, result.get(1, 1), 0.0001);
        assertEquals(386.0, result.get(3, 3), 0.0001);
    }

    @Test
    public void testTransposition() {
        System.out.println("Matrix4: Тестирование транспозиции...");
        float[][] data = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        Matrix4 m = new Matrix4(data);
        Matrix4 transposed = m.trans();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(m.get(i, j), transposed.get(j, i), 0.0001);
            }
        }
    }

    @Test
    public void testMultiplicationWithIdentity() {
        System.out.println("Matrix4: Тестирование умножения матрицы на единичную матрицу...");
        float[][] data = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        Matrix4 m = new Matrix4(data);
        Matrix4 identity = new Matrix4().identity();
        Matrix4 result = m.mult(identity);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(m.get(i, j), result.get(i, j), 0.0001);
            }
        }
    }
}