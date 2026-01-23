package com.cgvsu.math.vectors;

import com.cgvsu.math.MathUtil;

public class Vector2f implements Vector<Vector2f> {
    private float x;
    private float y;

    // --------------- КОНСТРУКТОРЫ --------------------
    // Без параметров (нулевые координаты)
    public Vector2f() {
        this(0,0);
    }

    // Передаются x и y
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    // Передается массив компонентов
    public Vector2f(float[] components) {
        checkComponentsLength(components);
        this.x = components[0];
        this.y = components[1];
    }

    // Обнуление вектора
    public static Vector2f zero() {
        return new Vector2f();
    }

    // ---------------- ГЕТЕРЫ ---------------------------
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    // ----------------- СЕТТЕРЫ --------------------------
    public void setX(float newX) {
        this.x = newX;
    }

    public void setY(float newY) {
        this.y = newY;
    }

    public void set(float newX, float newY) {
        this.x = newX;
        this.y = newY;
    }

    // ----------------- ОПЕРАЦИИ НАД ВЕКТОРАМИ ---------------
    // Сложение
    @Override
    public Vector2f add(Vector2f other) {
        checkNullVector(other);
        return new Vector2f(this.x + other.x, this.y + other.y);
    }

    // Вычитание
    @Override
    public Vector2f sub(Vector2f other) {
        checkNullVector(other);
        return new Vector2f(this.x - other.x, this.y - other.y);
    }

    // Умножение на скаляр
    @Override
    public Vector2f mult(float scalar) {
        return new Vector2f(this.x * scalar, this.y * scalar);
    }

    // Деление на скаляр
    @Override
    public Vector2f div(float scalar) {
        checkDivisionByZero(scalar);
        return new Vector2f(this.x / scalar, this.y / scalar);
    }

    // ---------------------- ДРУГИЕ МЕТОДЫ -------------------
    // Длина вектора
    @Override
    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    // Нормализация вектора
    @Override
    public Vector2f normalize() {
        float len = length();

        if (Math.abs(len) < MathUtil.EPS) {
            return Vector2f.zero();
        }
        return this.div(len);
    }

    // Скалярное произведение
    @Override
    public float scalarProd(Vector2f other) {
        checkNullVector(other);
        return this.x * other.x + this.y * other.y;
    }

    // Найти расстояние до вектора
    public float distanceTo(Vector2f other) {
        checkNullVector(other);
        float fx = this.x - other.x;
        float fy = this.y - other.y;

        return (float) Math.sqrt(fx * fx + fy * fy);
    }

    // Линейная интерполяция
    public Vector2f linearInterpolation(Vector2f other, float t) {
        checkNullVector(other);
        if (t < 0.0) {
            t = 0.0f;
        } else if (t > 1.0) {
            t = 1.0f;
        }

        float nx = this.x + (other.x - this.x) * t;
        float ny = this.y + (other.y - this.y) * t;

        return new Vector2f(nx, ny);
    }

    // ---------------------- ВЫВОД ВЕКТОРА -------------------
    @Override
    public String toString() {
        return "(" + x + "; " + y + ")";
    }

    // ---------------------- ОШИБКИ --------------------------
    // Проверка длины массива компонентов
    private static void checkComponentsLength(float[] components) {
        if (components.length != 2) {
            throw new IllegalArgumentException("Vector2f требует 2 компонента. Имеется: " +
                    components.length);
        }
    }

    // Проверка вектора на значения null
    private static void checkNullVector(Vector2f v2) {
        if (v2 == null) {
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
