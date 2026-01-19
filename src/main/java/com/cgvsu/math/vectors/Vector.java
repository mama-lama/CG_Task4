package com.cgvsu.math.vectors;

public interface Vector<T extends Vector<T>> {
    // Операции над векторами
    T add(T other);
    T sub(T other);
    T mult(float scalar);
    T div(float scalar);

    // Другие методы
    float length();
    T normalize();
    float scalarProd(T other);

    // Вывод
    String toString();
}
