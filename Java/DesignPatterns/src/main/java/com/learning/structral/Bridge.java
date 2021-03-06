package com.learning.structral;

/**
 * 将抽象与实现分离，使它们可以独立变化。它是用组合关系代替继承关系来实现，从而降低了抽象和实现这两个可变维度的耦合度。
 */
public class Bridge {

    public static void main(String[] args) {
        Color white = new White(), black = new Black();
        Shape square = new Square(white);
        Shape triangle = new Triangle(black);
        square.draw();
        triangle.draw();
    }

    static abstract class Shape {
        private final Color color;

        public Shape(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }

        public abstract void draw();
    }

    static class Square extends Shape {
        public Square(Color color) {
            super(color);
        }

        @Override
        public void draw() {
            System.out.println("画一个" + getColor().drawColor() + "的正方形");
        }
    }

    static class Triangle extends Shape {
        public Triangle(Color color) {
            super(color);
        }

        @Override
        public void draw() {
            System.out.println("画一个" + getColor().drawColor() + "的三角形");
        }
    }

    interface Color {
        String drawColor();
    }

    static class White implements Color {
        @Override
        public String drawColor() {
            return "白色";
        }
    }

    static class Black implements Color {
        @Override
        public String drawColor() {
            return "黑色";
        }
    }
}
