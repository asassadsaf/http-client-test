package com.sansec.httpclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fengkunpeng
 * @version 1.0
 * @description
 * @date 2023/12/11 19:17
 */
@SpringBootApplication
public class SpringMain {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(SpringMain.class, args);
        Thread.currentThread().join();
    }
}
