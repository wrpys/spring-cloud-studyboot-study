package com.wrpys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author wrp
 * @Description com.wrpys.App
 * @Date 2017/7/19
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.wrpys.dao"})
@EnableTransactionManagement
public class App {

    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }

}
