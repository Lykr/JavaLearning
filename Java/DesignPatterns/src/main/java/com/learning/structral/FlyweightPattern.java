package com.learning.structral;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 运用共享技术来有效地支持大量细粒度对象的复用。
 * 它通过共享已经存在的对象来大幅度减少需要创建的对象数量、避免大量相似类的开销，从而提高系统资源的利用率。
 */
public class FlyweightPattern {

    public static void main(String[] args) {
        Position position1 = new Position(1, 2), position2 = new Position(10, 23);
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight flyweight1 = factory.getFlyweight("1");
        Flyweight flyweight2 = factory.getFlyweight("1");
        System.out.println(flyweight1 == flyweight2);
        flyweight1.show(position1);
        flyweight1.show(position2);
    }

    interface Flyweight {
        void show(Position position);
    }

    static class ConcreteFlyweight implements Flyweight {
        private String key;

        public ConcreteFlyweight(String key) {
            this.key = key;
        }

        @Override
        public void show(Position position) {
            System.out.println(position.x + ", " + position.y);
        }
    }

    static class FlyweightFactory {
        private final ConcurrentHashMap<String, Flyweight> map = new ConcurrentHashMap<>();

        public Flyweight getFlyweight(String key) {
            return map.computeIfAbsent(key, ConcreteFlyweight::new);
        }
    }

    static class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
