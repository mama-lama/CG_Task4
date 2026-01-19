package com.cgvsu.math.vectors;

import com.cgvsu.math.vectors.Vector;

public class Vector3f implements Vector<Vector3f> {
    private float x;
    private float y;
    private float z;

    // ---------------------- КОНСТРУКТОРЫ ----------------------------
    public Vector3f() {
        this(0, 0, 0);
    }
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vector3f(float[] components) {
        if (components.length != 3) {
            throw new IllegalArgumentException("Vector3f требует 3 компонента");
        }
        this.x = components[0];
        this.y = components[1];
        this.z = components[2];
    }

    // ----------------------- ГЕТЕРЫ ---------------------------------
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public float getZ() {
        return z;
    }

    // ----------------------- СЕТТЕРЫ -----------------------------------
    public void setX(float newX) {
        this.x = newX;
    }
    public void setY(float newY) {
        this.y = newY;
    }
    public void setZ(float newZ) {
        this.z = newZ;
    }
    public void set(float newX, float newY, float newZ) {
        this.x = newX;
        this.y = newY;
        this.z = newZ;
    }

    // ----------------- ОПЕРАЦИИ НАД ВЕКТОРАМИ ----------------------
    // Сложение
    @Override
    public Vector3f add(Vector3f other) {
        return new Vector3f(this.x + other.x, this.y + other.y, this.z + other.z);
    }
    // Вычитание
    @Override
    public Vector3f sub(Vector3f other) {
        return new Vector3f(this.x - other.x, this.y - other.y, this.z - other.z);
    }
    // Умножение
    @Override
    public Vector3f mult(float scalar) {
        return new Vector3f(this.x * scalar, this.y * scalar, this.z * scalar);
    }
    // Деление
    @Override
    public Vector3f div(float scalar) {
        checkDivisionByZero(scalar);
        return new Vector3f(this.x / scalar, this.y / scalar, this.z / scalar);
    }

    // -------------------------- ДРУГИЕ МЕТОДЫ --------------------
    // Длина вектора
    @Override
    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }
    // Нормализация вектора
    @Override
    public Vector3f normalize() {
        float len = length();
        if (Math.abs(len) < 1e-7) {
            return new Vector3f();
        }
        return this.div(len);
    }
    // Скалярное произведение
    @Override
    public float scalarProd(Vector3f other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }
    // Векторное произведение
    public Vector3f vectorProd(Vector3f other) {
        return new Vector3f(this.y * other.z - this.z * other.y, this.z * other.x - this.x * other.z, this.x * other.y - this.y * other.x);
    }

    // -------------------------- ВЫВОД ВЕКТОРА --------------------------
    @Override
    public String toString() {
        return "(" + x + "; " + y + "; " + z + ")";
    }

    // -------------------------- ОШИБКИ ----------------------------------
    private static void checkDivisionByZero(float scalar) {
        if (Math.abs(scalar) < 1e-7f) {
            throw new ArithmeticException("Деление на ноль не допускается! Получено: " + scalar);
        }
    }
}
