package com.learning.structral;

/**
 * 外观（Facade）模式又叫作门面模式，是一种通过为多个复杂的子系统提供一个一致的接口，而使这些子系统更加容易被访问的模式。
 * 该模式对外有一个统一接口，外部应用程序不用关心内部子系统的具体细节，这样会大大降低应用程序的复杂度，提高了程序的可维护性。
 * 最知名的例子 Simple Logging Facade for Java (Slf4J)。
 */
public class Facade {

    public static void main(String[] args) {
        TeaFacade teaFacade = new TeaFacade();
        teaFacade.makeTea();
    }
}

class TeaFacade {
    private Kettle kettle = new Kettle();
    private TeaCans teaCans = new TeaCans();

    public void makeTea() {
        kettle.getHotWater();
        teaCans.getTeaLeaves();
        System.out.println("Made a tea!");
    }
}

class Kettle {
    public void getHotWater() {
        System.out.println("Get hot water.");
    }
}

class TeaCans {
    public void getTeaLeaves() {
        System.out.println("Get tea leaves");
    }
}
