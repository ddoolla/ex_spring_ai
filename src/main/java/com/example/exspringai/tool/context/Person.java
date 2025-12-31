package com.example.exspringai.tool.context;

import lombok.Getter;

@Getter
public class Person {

    private final String name;
    private final Integer age;
    private final String hobby;

    public Person(String name, Integer age, String hobby) {
        this.name = name;
        this.age = age;
        this.hobby = hobby;
    }
}
