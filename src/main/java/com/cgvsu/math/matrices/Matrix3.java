package com.cgvsu.math.matrices;

import com.cgvsu.math.vectors.Vector3f;

public class Matrix3 {
    private float[][] data = new float[3][3];

    // -------------------------- Конструкторы ---------------------------
    public Matrix3() {}
    public Matrix3(float[][] data) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.data[i][j] = data[i][j];
            }
        }
    }

    // ----------------------- ГЕТТЕРЫ И СЕТТЕРЫ ---------------------------
    public double get(int row, int col) {
        return data[row][col];
    }
    public void set(int row, int col, float value) {
        data[row][col] = value;
    }

    // ----------------------- СОЗДАНИЕ МАТРИЦ ---------------------
    // Единичная
    public static Matrix3 identityMatrix() {
        float[][] id = new float[3][3];

        for (int i = 0; i < 3; i++) {
            id[i][i] = 1.0F;
        }

        return new Matrix3(id);
    }
    // Нулевая
    public static Matrix3 zeroMatrix() {
        return new Matrix3();
    }

    // --------------------------- РАБОТА С МАТРИЦАМИ --------------------------
    // Сложение
    public Matrix3 addition(Matrix3 other) {
        float[][] result = new float[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = this.data[i][j] + other.data[i][j];
            }
        }

        return new Matrix3(result);
    }
    // Вычитание
    public Matrix3 subtraction(Matrix3 other) {
        float[][] result = new float[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = this.data[i][j] - other.data[i][j];
            }
        }

        return new Matrix3(result);
    }
    // Умножение на вектор
    public Vector3f multiplication(Vector3f vector) {
        float x = data[0][0] * vector.getX() + data[0][1] * vector.getY() + data[0][2] * vector.getZ();
        float y = data[1][0] * vector.getX() + data[1][1] * vector.getY() + data[1][2] * vector.getZ();
        float z = data[2][0] * vector.getX() + data[2][1] * vector.getY() + data[2][2] * vector.getZ();

        return new Vector3f(x, y, z);
    }
    // Умножение на матрицу
    public Matrix3 multiplication(Matrix3 other) {
        float[][] result = new float[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                float sum = 0;
                for (int k = 0; k < 3; k++) {
                    sum += this.data[i][k] * other.data[k][j];
                }
                result[i][j] = sum;
            }
        }

        return new Matrix3(result);
    }

    // Транспонирование
    public Matrix3 transposition() {
        float[][] result = new float[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[j][i] = this.data[i][j];
            }
        }

        return new Matrix3(result);
    }

    // ------------------------- ВЫВОД МАТРИЦЫ -----------------------------------
    public void print() {
        System.out.println("Matrix 3x3:");

        for (int i = 0; i < 3; i++) {
            System.out.print("[ ");
            for (int j = 0; j < 3; j++) {
                System.out.printf("%6.2f ", data[i][j]);
            }
            System.out.println("]");
        }
    }
}
