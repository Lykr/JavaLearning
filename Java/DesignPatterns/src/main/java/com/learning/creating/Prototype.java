package com.learning.creating;

/**
 * 用一个已经创建的实例作为原型，通过复制该原型对象来创建一个和原型相同或相似的新对象。
 * 核心思想是实现 Cloneable 接口，实现 clone() 方法返回原型对象的深拷贝。
 * 多线程模式下对于有状态的对象可以采用原型模式避免线程安全问题。
 */
public class Prototype {

    public static void main(String[] args) {
        Realizetype realizetype = new Realizetype();
        Realizetype realizetypeClone = realizetype.clone();
        System.out.println("realizetype.equals(realizetypeClone)：" + realizetype.equals(realizetypeClone)); //true
        System.out.println("realizetype == realizetypeClone：" + (realizetype == realizetypeClone)); //true
    }
}

//Cloneable 接口就是一个抽象原型类
class Realizetype implements Cloneable {
    public Realizetype() {
        System.out.println("实例化 Realizetype 成功");
    }

    public Realizetype clone() {
        System.out.println("复制 Realizetype 成功");
        Realizetype realizetype = null;
        try {
            realizetype = (Realizetype) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return realizetype;
    }
}
