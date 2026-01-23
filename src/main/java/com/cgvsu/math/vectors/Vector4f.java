package com.cgvsu.math.vectors;

import com.cgvsu.math.MathUtil;

public class Vector4f implements Vector<Vector4f> {
    private float x;
    private float y;
    private float z;
    private float w;

    // ------------------------------- КОНСТРУКТОРЫ ----------------------------
    // Без параметров (нулевые координаты)
    public Vector4f() {
        this(0, 0, 0, 0);
    }

    // Передаются x, y, z и w
    public Vector4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    // Передается массив компонентов
    public Vector4f(float[] components) {
        checkComponentsLength(components);
        this.x = components[0];
        this.y = components[1];
        this.z = components[2];
        this.w = components[3];
    }

    // Обнуление вектора
    public static Vector4f zero() {
        return new Vector4f();
    }

    // Превращение вектора v4 в вектор v3 (1 в конце)
    public static Vector4f fromPoint(Vector3f v3) {
        checkNullVector(v3);
        return new Vector4f(v3.getX(), v3.getY(), v3.getZ(), 1.0f);
    }

    // Превращение вектора v4 в вектор v3 (1 в конце)
    public static Vector4f fromDirection(Vector3f v3) {
        checkNullVector(v3);
        return new Vector4f(v3.getX(), v3.getY(), v3.getZ(), 0.0f);
    }

    public Vector3f toVec3() {
        return new Vector3f(x, y, z);
    }

    // ---------------------------- ГЕТТЕРЫ -------------------------------
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getW() {
        return w;
    }

    // ---------------------------- СЕТТЕРЫ -------------------------------
    public void setX(float newX) {
        this.x = newX;
    }

    public void setY(float newY) {
        this.y = newY;
    }

    public void setZ(float newZ) {
        this.z = newZ;
    }

    public void setW(float newW) {
        this.w = newW;
    }

    public void set(float newX, float newY, float newZ, float newW) {
        this.x = newX;
        this.y = newY;
        this.z = newZ;
        this.w = newW;
    }

    // ----------------- ОПЕРАЦИИ НАД ВЕКТОРАМИ ----------------------
    // Сложение
    @Override
    public Vector4f add(Vector4f other) {
        checkNullVector(other);
        return new Vector4f(this.x + other.x, this.y + other.y, this.z + other.z, this.w + other.w);
    }

    // Вычитание
    @Override
    public Vector4f sub(Vector4f other) {
        checkNullVector(other);
        return new Vector4f(this.x - other.x, this.y - other.y, this.z - other.z, this.w - other.w);
    }

    // Умножение на скаляр
    @Override
    public Vector4f mult(float scalar) {
        return new Vector4f(this.x * scalar, this.y * scalar, this.z * scalar, this.w * scalar);
    }

    // Деление на скаляр
    @Override
    public Vector4f div(float scalar) {
        checkDivisionByZero(scalar);
        return new Vector4f(this.x / scalar, this.y / scalar, this.z / scalar, this.w / scalar);
    }

    // ------------------------- ДРУГИЕ МЕТОДЫ ----------------------------
    // Длина вектора
    @Override
    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z + w * w);
    }

    // Нормализация вектора
    @Override
    public Vector4f normalize() {
        float len = length();

        if (Math.abs(len) < MathUtil.EPS) {
            return Vector4f.zero();
        }
        return this.div(len);
    }

    // Скалярное произведение
    @Override
    public float scalarProd(Vector4f other) {
        checkNullVector(other);
        return this.x * other.x + this.y * other.y + this.z * other.z + this.w * other.w;
    }

    // Найти расстояние до вектора
    public float distanceTo(Vector4f other) {
        checkNullVector(other);
        float fx = this.x - other.x;
        float fy = this.y - other.y;
        float fz = this.z - other.z;
        float fw = this.w - other.w;

        return (float) Math.sqrt(fx * fx + fy * fy + fz * fz + fw * fw);
    }

    // Линейная интерполяция
    public Vector4f linearInterpolation(Vector4f other, float t) {
        checkNullVector(other);
        if (t < 0.0) {
            t = 0.0f;
        } else if (t > 1.0) {
            t = 1.0f;
        }

        float nx = this.x + (other.x - this.x) * t;
        float ny = this.y + (other.y - this.y) * t;
        float nz = this.z + (other.z - this.z) * t;
        float nw = this.w + (other.w - this.w) * t;

        return new Vector4f(nx, ny, nz, nw);
    }

    // ------------------------- ВЫВОД -------------------------------------
    @Override
    public String toString() {
        return "(" + x + "; " + y + "; " + z + "; " + w + ")";
    }

    // ----------------------- ОШИБКИ --------------------------------------
    // Проверка длины массива компонентов
    private static void checkComponentsLength(float[] components) {
        if (components.length != 4) {
            throw new IllegalArgumentException("Vector4f требует 4 компонента. Имеется: " +
                    components.length);
        }
    }

    // Проверка вектора v4 на значения null
    private static void checkNullVector(Vector4f v4) {
        if (v4 == null) {
            throw new NullPointerException("Вектор не может быть null");
        }
    }

    // Проверка вектора v3 на значения null
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
