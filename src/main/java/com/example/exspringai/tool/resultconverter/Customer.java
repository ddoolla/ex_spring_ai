package com.example.exspringai.tool.resultconverter;

import lombok.Getter;

@Getter
public class Customer {

    private final Long id;
    private final String name;
    private final String email;
    private final String phone;

    public Customer(Long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
