package com.cgvsu.math;

public class MathUtil {
    // --------- Запрет на создание экземпляров --------
    private MathUtil() {}

    // Эпсилон
    public static final float EPS = 1e-7f;

    // Синус
    public static float sin(float x) {
        return (float) Math.sin(x);
    }

    // Косинус
    public static float cos(float x) {
        return (float) Math.cos(x);
    }

    // Радианы
    public static float rad(float x) {
        return (float) Math.toRadians(x);
    }
}
