package com.cgvsu.render_engine;

import com.cgvsu.math.vectors.Vector3f;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RasterizerTest {

    @Test
    public void depthBufferKeepsNearestTriangle() {
        int width = 5;
        int height = 5;
        int[] colorBuffer = new int[width * height];
        float[] depthBuffer = new float[width * height];
        Rasterizer.clearBuffers(width, height, colorBuffer, depthBuffer, 0xFF000000);

        Rasterizer.Vertex v0 = new Rasterizer.Vertex(0, 0, 0.5f, new Vector3f(), null, null);
        Rasterizer.Vertex v1 = new Rasterizer.Vertex(4, 0, 0.5f, new Vector3f(), null, null);
        Rasterizer.Vertex v2 = new Rasterizer.Vertex(4, 4, 0.5f, new Vector3f(), null, null);

        Rasterizer.rasterizeTriangle(
                v0,
                v1,
                v2,
                width,
                height,
                colorBuffer,
                depthBuffer,
                0xFFFF0000,
                null,
                null);

        Rasterizer.Vertex v0Near = new Rasterizer.Vertex(0, 0, 0.3f, new Vector3f(), null, null);
        Rasterizer.Vertex v1Near = new Rasterizer.Vertex(4, 0, 0.3f, new Vector3f(), null, null);
        Rasterizer.Vertex v2Near = new Rasterizer.Vertex(4, 4, 0.3f, new Vector3f(), null, null);

        Rasterizer.rasterizeTriangle(
                v0Near,
                v1Near,
                v2Near,
                width,
                height,
                colorBuffer,
                depthBuffer,
                0xFF0000FF,
                null,
                null);

        int index = 2 + 2 * width;
        assertEquals(0xFF0000FF, colorBuffer[index]);
    }

    @Test
    public void lineWritesDepthAndColor() {
        int width = 4;
        int height = 4;
        int[] colorBuffer = new int[width * height];
        float[] depthBuffer = new float[width * height];
        Rasterizer.clearBuffers(width, height, colorBuffer, depthBuffer, 0xFF000000);

        Rasterizer.Vertex v0 = new Rasterizer.Vertex(0, 0, 0.2f, new Vector3f(), null, null);
        Rasterizer.Vertex v1 = new Rasterizer.Vertex(3, 3, 0.2f, new Vector3f(), null, null);
        Rasterizer.rasterizeLine(v0, v1, width, height, colorBuffer, depthBuffer, 0xFFFFFFFF);

        int index = 1 + 1 * width;
        assertEquals(0xFFFFFFFF, colorBuffer[index]);
        assertTrue(depthBuffer[index] < Float.POSITIVE_INFINITY);
    }

    @Test
    public void textureSamplerOverridesBaseColor() {
        int width = 3;
        int height = 3;
        int[] colorBuffer = new int[width * height];
        float[] depthBuffer = new float[width * height];
        Rasterizer.clearBuffers(width, height, colorBuffer, depthBuffer, 0xFF000000);

        Rasterizer.Vertex v0 = new Rasterizer.Vertex(0, 0, 0.5f, new Vector3f(), null, new com.cgvsu.math.vectors.Vector2f(0, 0));
        Rasterizer.Vertex v1 = new Rasterizer.Vertex(2, 0, 0.5f, new Vector3f(), null, new com.cgvsu.math.vectors.Vector2f(1, 0));
        Rasterizer.Vertex v2 = new Rasterizer.Vertex(0, 2, 0.5f, new Vector3f(), null, new com.cgvsu.math.vectors.Vector2f(0, 1));

        TextureSampler sampler = (u, v) -> 0xFF123456;
        Rasterizer.rasterizeTriangle(
                v0,
                v1,
                v2,
                width,
                height,
                colorBuffer,
                depthBuffer,
                0xFFFF0000,
                sampler,
                null);

        int index = 0 + 0 * width;
        assertEquals(0xFF123456, colorBuffer[index]);
    }
}
