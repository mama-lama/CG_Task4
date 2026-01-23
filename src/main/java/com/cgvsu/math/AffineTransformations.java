package com.cgvsu.math;
import com.cgvsu.model.Model;
import com.cgvsu.math.vectors.Vector3f;

import java.util.ArrayList;

public class AffineTransformations {
    public static void scale(ArrayList<Vector3f> vertices, float scaleX, float scaleY, float scaleZ) {
        for (Vector3f vertex : vertices) {
            vertex.setX(vertex.getX() * scaleX);
            vertex.setY(vertex.getY() * scaleY);
            vertex.setZ(vertex.getZ() * scaleZ);
        }
    }

    public static void translate(ArrayList<Vector3f> vertices, float offsetX, float offsetY, float offsetZ) {
        for (Vector3f vertex : vertices) {
            vertex.setX(vertex.getX() + offsetX);
            vertex.setY(vertex.getY() + offsetY);
            vertex.setZ(vertex.getZ() + offsetZ);
        }
    }

    // Вспомогательный метод для поворота точки вокруг оси X
    public static void rotate(Model model, float angleX, float angleY, float angleZ) {
        // Поворот вершин
        for (int i = 0; i < model.vertices.size(); i++) {
            Vector3f rotatedVertex = rotatePoint(model.vertices.get(i), angleX, angleY, angleZ);
            model.vertices.set(i, rotatedVertex);
        }
    }

    // Вспомогательный метод для поворота точки вокруг осей X, Y и Z
    private static Vector3f rotatePoint(Vector3f point, float angleX, float angleY, float angleZ) {
        // Поворот вокруг оси X
        point = rotateX(point, angleX);

        // Поворот вокруг оси Y
        point = rotateY(point, angleY);

        // Поворот вокруг оси Z
        point = rotateZ(point, angleZ);

        return point;
    }

    // Вспомогательный метод для поворота точки вокруг оси X
    public static Vector3f rotateX(Vector3f point, float angle) {
        float sinA = (float) Math.sin(angle);
        float cosA = (float) Math.cos(angle);

        float y = point.getY() * cosA - point.getZ() * sinA;
        float z = point.getY() * sinA + point.getZ() * cosA;

        return new Vector3f(point.getX(), y, z);
    }

    // Вспомогательный метод для поворота точки вокруг оси Y
    public static Vector3f rotateY(Vector3f point, float angle) {
        float sinA = (float) Math.sin(angle);
        float cosA = (float) Math.cos(angle);

        float x = point.getX() * cosA + point.getZ() * sinA;
        float z = -point.getX() * sinA + point.getZ() * cosA;

        return new Vector3f(x, point.getY(), z);
    }

    // Вспомогательный метод для поворота точки вокруг оси Z
    public static Vector3f rotateZ(Vector3f point, float angle) {
        float sinA = (float) Math.sin(angle);
        float cosA = (float) Math.cos(angle);

        float x = point.getX() * cosA - point.getY() * sinA;
        float y = point.getX() * sinA + point.getY() * cosA;

        return new Vector3f(x, y, point.getZ());
    }
}
