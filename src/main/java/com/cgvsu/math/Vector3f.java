package com.cgvsu.math;

// Это заготовка для собственной библиотеки для работы с линейной алгеброй
public class Vector3f {
    private float x;
    private float y;
    private float z;

    private final float EPS = 1e-7f;

    // ---------------------- КОНСТРУКТОРЫ ----------------------------
    public Vector3f() {
        this(0, 0, 0);
    }
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }
    public void setZ(float z) {
        this.z = z;
    }

}
