package com.itheima;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JobTest {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("applicationContext-jobs.xml");

    }
}
