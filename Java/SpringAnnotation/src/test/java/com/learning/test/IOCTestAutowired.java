package com.learning.test;

import com.learning.bean.Boss;
import com.learning.bean.Car;
import com.learning.config.MainConfigAutowired;
import com.learning.dao.BookDao;
import com.learning.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTestAutowired {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigAutowired.class);

    @Test
    public void test01() {
        Boss bean = applicationContext.getBean(Boss.class);
        System.out.println(bean);
        System.out.println(applicationContext.getBean(Car.class));
        applicationContext.close();
    }
}
