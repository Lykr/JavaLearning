package com.learning.creating;

/**
 * 在有些系统中，为了节省内存资源、保证数据内容的一致性，对某些类要求只能创建一个实例，这就是所谓的单例模式。
 *
 * 单例模式有 3 个特点：
 * 1. 单例类只有一个实例对象；
 * 2. 该单例对象必须由单例类自行创建；
 * 3. 单例类对外提供一个访问该单例的全局访问点。
 */
public class Singleton {

    public static void main(String[] args) {
        System.out.println("第 1 次调用 LazySingleton 的 getInstance()");
        LazySingleton.getInstance();
        System.out.println("第 2 次调用 LazySingleton 的 getInstance()");
        LazySingleton.getInstance();
        System.out.println("第 1 次调用 HungrySingleton 的 getInstance()");
        HungrySingleton.getInstance();
        System.out.println("第 1 次调用 DoubleCheckSingleton 的 getInstance()");
        DoubleCheckSingleton.getInstance();
        System.out.println("第 2 次调用 DoubleCheckSingleton 的 getInstance()");
        DoubleCheckSingleton.getInstance();
    }
}

/**
 * 懒汉式单例
 * 该模式的特点是类加载时没有生成单例，只有当第一次调用 getlnstance 方法时才去创建这个单例。
 *
 * 如果编写的是多线程程序，则不要删除代码中的关键字 volatile 和 synchronized，否则将存在线程非安全的问题。
 * 如果不删除这两个关键字就能保证线程安全，但是每次访问时都要同步，会影响性能，且消耗更多的资源，这是懒汉式单例的缺点。
 */
class LazySingleton {

    private static volatile LazySingleton instance = null;

    private LazySingleton(){}

    public static synchronized LazySingleton getInstance() {
        System.out.println("LazySingleton 当前实例：" + instance);
        if (instance == null) {
            instance = new LazySingleton();
            System.out.println("生成 LazySingleton 实例");
        }
        System.out.println("返回 LazySingleton 实例");
        return instance;
    }
}

/**
 * 饿汉式单例
 * 该模式的特点是类一旦加载就创建一个单例，保证在调用 getInstance 方法之前单例已经存在了。
 *
 * 饿汉式单例在类创建的同时就已经创建好一个静态的对象供系统使用，以后不再改变，所以是线程安全的，可以直接用于多线程而不会出现问题。
 */
class HungrySingleton {
    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton(){}

    public static HungrySingleton getInstance() {
        System.out.println("HungrySingleton 当前实例：" + instance);
        System.out.println("返回 HungrySingleton 实例");
        return instance;
    }
}

/**
 * 双端检测的单例模式
 * 预防多线程下对实例的重复生成
 */
class DoubleCheckSingleton {
    private static volatile DoubleCheckSingleton instance = null;

    private DoubleCheckSingleton() {}

    public static DoubleCheckSingleton getInstance() {
        System.out.println("DoubleCheckSingleton 第一次检查");
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                System.out.println("DoubleCheckSingleton 进入同步");
                System.out.println("DoubleCheckSingleton 第二次检查");
                if (instance == null) {
                    System.out.println("生成 DoubleCheckSingleton 实例");
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        System.out.println("返回 DoubleCheckSingleton 实例");
        return instance;
    }
}
