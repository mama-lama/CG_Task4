package com.cgvsu.math;

public class Vector2f {
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

    // ---------------------- ВЫВОД ВЕКТОРА -------------------
    public String toString() {
        return "(" + x + "; " + y + ")";
    }
}
