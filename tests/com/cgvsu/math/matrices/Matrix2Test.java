package com.cgvsu.math.matrices;

import com.cgvsu.math.vectors.Vector2f;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Matrix2Test {
    // Единичная матрица
    @Test
    public void testIdentityMatrix() {
        System.out.println("Matrix2: Тестирование создания единичной матрицы...");
        Matrix2 identity = Matrix2.identityMatrix();

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                float expected = (i == j) ? 1.0F : 0.0F;
                assertEquals(expected, identity.get(i, j), 0.0001);
            }
        }
    }

    // Нулевая матрица
    @Test
    public void testZeroMatrix() {
        System.out.println("Matrix2: Тестирование создания нулевой матрицы...");
        Matrix2 zero = Matrix2.zeroMatrix();

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(0.0, zero.get(i, j), 0.0001);
            }
        }
    }

    // Сложение
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
        Matrix2 result = m1.addition(m2);

        assertEquals(5.0, result.get(0, 0), 0.0001); // 1 + 4 = 5
        assertEquals(5.0, result.get(0, 1), 0.0001); // 2 + 3 = 5
        assertEquals(5.0, result.get(1, 0), 0.0001); // 3 + 2 = 5
        assertEquals(5.0, result.get(1, 1), 0.0001); // 4 + 1 = 5
    }

    // Вычитание
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
        Matrix2 result = m1.subtraction(m2);

        assertEquals(9.0, result.get(0, 0), 0.0001); // 10 - 1 = 9
        assertEquals(8.0, result.get(0, 1), 0.0001); // 10 - 2 = 8
        assertEquals(7.0, result.get(1, 0), 0.0001); // 10 - 3 = 7
        assertEquals(6.0, result.get(1, 1), 0.0001); // 10 - 4 = 6
    }

    // Умножение на вектор
    @Test
    public void testMultiplicationWithVector() {
        System.out.println("Matrix2: Тестирование умножения матрицы на вектор...");
        float[][] data = {
                {1, 2},
                {3, 4}
        };

        Matrix2 m = new Matrix2(data);
        Vector2f v = new Vector2f(2, 3);
        Vector2f result = m.multiplication(v);

        assertEquals(8.0, result.getX(), 0.0001);  // 1*2 + 2*3 = 8
        assertEquals(18.0, result.getY(), 0.0001); // 3*2 + 4*3 = 18
    }

    // Умножение на матрицу
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
        Matrix2 result = m1.multiplication(m2);

        assertEquals(4.0, result.get(0, 0), 0.0001);  // 1*2 + 2*1 = 4
        assertEquals(4.0, result.get(0, 1), 0.0001);  // 1*0 + 2*2 = 4
        assertEquals(10.0, result.get(1, 0), 0.0001); // 3*2 + 4*1 = 10
        assertEquals(8.0, result.get(1, 1), 0.0001);  // 3*0 + 4*2 = 8
    }

    // Транспозиция
    @Test
    public void testTransposition() {
        System.out.println("Matrix2: Тестирование транспозиции...");
        float[][] data = {
                {1, 2},
                {3, 4}
        };

        Matrix2 m = new Matrix2(data);
        Matrix2 transposed = m.transposition();

        assertEquals(1.0, transposed.get(0, 0), 0.0001); // Остается
        assertEquals(3.0, transposed.get(0, 1), 0.0001); // 2 ↔ 3
        assertEquals(2.0, transposed.get(1, 0), 0.0001); // 3 ↔ 2
        assertEquals(4.0, transposed.get(1, 1), 0.0001); // Остается
    }

    // Умножение на единичную матрицу
    @Test
    public void testMultiplicationWithIdentity() {
        System.out.println("Matrix2: Тестирование умножения матрицы на единичную матрицу...");
        float[][] data = {
                {1, 2},
                {3, 4}
        };

        Matrix2 m = new Matrix2(data);
        Matrix2 identity = Matrix2.identityMatrix();
        Matrix2 result = m.multiplication(identity);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(m.get(i, j), result.get(i, j), 0.0001);
            }
        }
    }
}
