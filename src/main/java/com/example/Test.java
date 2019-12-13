package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Test {
    @Value("${abc}")
    private String abc;

    @PostConstruct
    public void init(){
        System.out.println(abc);
    }
}
