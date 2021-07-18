package com.yapikredi.annualleave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class AnnualLeaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnnualLeaveApplication.class, args);
    }

}
