package com.learning;

import java.lang.reflect.InvocationTargetException;

public class ReflectTest {
    public static void main(String[] args) {
        try {
            Class.forName("com.learning.Hello").getMethod("hello").invoke(null, null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
