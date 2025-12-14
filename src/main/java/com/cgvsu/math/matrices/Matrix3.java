package com.cgvsu.math.matrices;

public class Matrix3 {
    private double[][] data = new double[3][3];

    // -------------------------- Конструкторы ---------------------------
    public Matrix3() {}
    public Matrix3(double[][] data) {
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
    public void set(int row, int col, double value) {
        data[row][col] = value;
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
