package com.learning.behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * 提供一个对象来顺序访问聚合对象中的一系列数据，而不暴露聚合对象的内部表示。
 * 迭代器模式是一种对象行为型模式，其主要优点如下。
 */
public class IteratorPattern {
    public static void main(String[] args) {
        Aggregate<String> aggregate = new ConcreteAggregate<>();
        aggregate.add("Hello");
        aggregate.add("World");
        aggregate.add("Hi");
        Iterator<String> iterator = aggregate.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

interface Aggregate<T> {
    Aggregate<T> add(T obj);

    Aggregate<T> remove(T obj);

    Iterator<T> iterator();
}

interface Iterator<T> {
    boolean hasNext();

    T next();
}

class ConcreteAggregate<T> implements Aggregate<T> {
    private List<T> list = new ArrayList<>();

    @Override
    public Aggregate<T> add(T obj) {
        list.add(obj);
        return this;
    }

    @Override
    public Aggregate<T> remove(T obj) {
        list.remove(obj);
        return this;
    }

    @Override
    public Iterator<T> iterator() {
        return new ConcreteIterator<T>(list);
    }
}

class ConcreteIterator<T> implements Iterator<T> {
    private List<T> list;
    private int nextIdx;

    public ConcreteIterator(List<T> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return nextIdx < list.size();
    }

    @Override
    public T next() {
        return hasNext() ? list.get(nextIdx++) : null;
    }
}
