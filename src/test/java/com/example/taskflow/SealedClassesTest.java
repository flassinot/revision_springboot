package com.example.taskflow;

import org.junit.jupiter.api.Test;

public class SealedClassesTest {

    sealed interface Shape permits Circle, Square {}
    final class Circle implements Shape {
        public double r;
        public Circle(double r) {this.r = r;}
    }
    final class Square implements Shape {
        public double s;
        public Square(double s) {this.s = s;}
    }

    @Test
    public void test() {
        Shape shape = new Circle(5);

        double surface = switch (shape) {
            case Circle c -> Math.PI * c.r * c.r;
            case Square s -> s.s * s.s;
        };

        System.out.println(surface);
    }
}
