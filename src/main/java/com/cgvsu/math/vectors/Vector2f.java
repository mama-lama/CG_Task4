package com.cgvsu.math.vectors;

import com.cgvsu.math.vectors.Vector;

public class Vector2f implements Vector<Vector2f> {
    private float x;
    private float y;

    // --------------- КОНСТРУКТОРЫ --------------------
    public Vector2f() {
        this(0,0);
    }
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public Vector2f(float[] components) {
        if (components.length != 2) {
            throw new IllegalArgumentException("Vector2f требует 2 компонента");
        }
        this.x = components[0];
        this.y = components[1];
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
        return new Vector2f(this.x + other.x, this.y + other.y);
    }
    // Вычитание
    @Override
    public Vector2f sub(Vector2f other) {
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
        if (Math.abs(len) < 1e-7) {
            return new Vector2f();
        }
        return this.div(len);
    }
    // Скалярное произведение
    @Override
    public float scalarProd(Vector2f other) {
        return this.x * other.x + this.y * other.y;
    }

    // ---------------------- ВЫВОД ВЕКТОРА -------------------
    @Override
    public String toString() {
        return "(" + x + "; " + y + ")";
    }

    // ---------------------- ОШИБКИ --------------------------
    private static void checkDivisionByZero(float scalar) {
        if (Math.abs(scalar) < 1e-7) {
            throw new ArithmeticException("Деление на ноль не допускается! Получено: " + scalar);
        }
    }
}
