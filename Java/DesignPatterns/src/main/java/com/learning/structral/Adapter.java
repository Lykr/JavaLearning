package com.learning.structral;

/**
 * 将一个类的接口转换成客户希望的另外一个接口，使得原本由于接口不兼容而不能一起工作的那些类能一起工作。
 * 适配器模式分为类结构型模式和对象结构型模式两种，前者类之间的耦合度比后者高，且要求程序员了解现有组件库中的相关组件的内部结构，所以应用相对较少些。
 */
public class Adapter {

    public static void main(String[] args) {
        Hello classAdapter = new ClassAdapter();
        classAdapter.hello();

        ObjectAdapter objectAdapter = new ObjectAdapter(new Adaptee());
        objectAdapter.hello();
    }

    interface Hello {
        void hello();
    }

    static class Adaptee {
        public void hi() {
            System.out.println("Hi~");
        }
    }

    static class ClassAdapter extends Adaptee implements Hello {
        @Override
        public void hello() {
            this.hi();
        }
    }

    static class ObjectAdapter implements Hello {
        private Adaptee adaptee;

        public ObjectAdapter(Adaptee adaptee) {
            this.adaptee = adaptee;
        }

        @Override
        public void hello() {
            adaptee.hi();
        }
    }
}
