package com.cgvsu.math.vectors;

import com.cgvsu.math.MathUtil;

public class Vector3f implements Vector<Vector3f> {
    private float x;
    private float y;
    private float z;

    // ---------------------- КОНСТРУКТОРЫ ----------------------------
    // Без параметров (нулевые координаты)
    public Vector3f() {
        this(0, 0, 0);
    }

    // Передаются x, y и z
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Передается массив компонентов
    public Vector3f(float[] components) {
        checkComponentsLength(components);
        this.x = components[0];
        this.y = components[1];
        this.z = components[2];
    }

    // Обнуление вектора
    public static Vector3f zero() {
        return new Vector3f();
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
        checkNullVector(other);
        return new Vector3f(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    // Вычитание
    @Override
    public Vector3f sub(Vector3f other) {
        checkNullVector(other);
        return new Vector3f(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    // Умножение на скаляр
    @Override
    public Vector3f mult(float scalar) {
        return new Vector3f(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    // Деление на скаляр
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

        if (Math.abs(len) < MathUtil.EPS) {
            return Vector3f.zero();
        }
        return this.div(len);
    }

    // Скалярное произведение
    @Override
    public float scalarProd(Vector3f other) {
        checkNullVector(other);
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    // Векторное произведение
    public Vector3f vectorProd(Vector3f other) {
        checkNullVector(other);
        float fx = this.y * other.z - this.z * other.y;
        float fy = this.z * other.x - this.x * other.z;
        float fz = this.x * other.y - this.y * other.x;

        return new Vector3f(fx, fy, fz);
    }

    // Найти расстояние до вектора
    public float distanceTo(Vector3f other) {
        checkNullVector(other);
        float fx = this.x - other.x;
        float fy = this.y - other.y;
        float fz = this.z - other.z;

        return (float) Math.sqrt(fx * fx + fy * fy + fz * fz);
    }

    // Линейная интерполяция
    public Vector3f linearInterpolation(Vector3f other, float t) {
        checkNullVector(other);
        if (t < 0.0) {
            t = 0.0f;
        } else if (t > 1.0) {
            t = 1.0f;
        }

        float nx = this.x + (other.x - this.x) * t;
        float ny = this.y + (other.y - this.y) * t;
        float nz = this.z + (other.z - this.z) * t;

        return new Vector3f(nx, ny, nz);
    }

    // -------------------------- ВЫВОД ВЕКТОРА --------------------------
    @Override
    public String toString() {
        return "(" + x + "; " + y + "; " + z + ")";
    }

    // -------------------------- ОШИБКИ ----------------------------------
    // Проверка длины массива компонентов
    private static void checkComponentsLength(float[] components) {
        if (components.length != 3) {
            throw new IllegalArgumentException("Vector3f требует 3 компонента. Имеется: " +
                    components.length);
        }
    }

    // Проверка вектора на значения null
    private static void checkNullVector(Vector3f v3) {
        if (v3 == null) {
            throw new NullPointerException("Вектор не может быть null");
        }
    }

    // Проверка деления на ноль
    private static void checkDivisionByZero(float scalar) {
        if (Math.abs(scalar) < MathUtil.EPS) {
            throw new ArithmeticException("Деление на ноль не допускается! Получено: " + scalar);
        }
    }
}
