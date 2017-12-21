package com.sk.brook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by songk on 17/10/19.
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("com.sk.brook.dao.mapper")
public class Application {


    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SpringApplication.run(Application.class,args);
    }
}
