package com.cgvsu.math.matrices;

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
