package com.cgvsu.math;

import com.cgvsu.math.matrices.Matrix4;
import com.cgvsu.model.Model;
import com.cgvsu.math.vectors.Vector3f;

public class AffineTransformer {
    // ----------------- БАЗОВЫЕ ОПЕРАЦИИ -----------------------
    /**
     * Перемещение
     * @param v3 вектор, на который надо переместить
     * @return матрица 4х4
     */
    public static Matrix4 translation(Vector3f v3) {
        return new Matrix4(
                1, 0, 0, v3.getX(),
                0, 1, 0, v3.getY(),
                0, 0, 1, v3.getZ(),
                0, 0, 0, 1
        );
    }

    /**
     * Растягивание
     * @param v3 вектор, на который надо растянуть
     * @return матрица 4х4
     */
    public static Matrix4 scale(Vector3f v3) {
        return new Matrix4(
                v3.getX(), 0, 0, 0,
                0, v3.getY(), 0, 0,
                0, 0, v3.getZ(), 0,
                0, 0, 0, 1
        );
    }

    /**
     * Поворот вокруг оси X
     * @param angleRad угол поворота в радианах
     * @return матрица 4х4
     */
    public static Matrix4 rotationX(float angleRad) {
        float cosA = MathUtil.cos(angleRad);
        float sinA = MathUtil.sin(angleRad);

        return new Matrix4(
                1, 0, 0, 0,
                0, cosA, -sinA, 0,
                0, sinA, cosA, 0,
                0, 0, 0, 1
        );
    }

    /**
     * Поворот вокруг оси Y
     * @param angleRad угол поворота в радианах
     * @return матрица 4х4
     */
    public static Matrix4 rotationY(float angleRad) {
        float cosA = MathUtil.cos(angleRad);
        float sinA = MathUtil.sin(angleRad);

        return new Matrix4(
                cosA, 0, sinA, 0,
                0, 1, 0, 0,
                -sinA, 0, cosA, 0,
                0, 0, 0, 1
        );
    }

    /**
     * Поворот вокруг оси Z
     * @param angleRad угол поворота в радианах
     * @return матрица 4х4
     */
    public static Matrix4 rotationZ(float angleRad) {
        float cosA = MathUtil.cos(angleRad);
        float sinA = MathUtil.sin(angleRad);

        return new Matrix4(
                cosA, -sinA, 0, 0,
                sinA, cosA, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        );
    }
}
