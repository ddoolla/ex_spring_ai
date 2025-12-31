package com.example.exspringai.tool.resultconverter;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class CustomerRepository {

    private final Map<Long, Customer> customers = Map.of(
      1L, new Customer(1L, "홍길동", "hong@example.com", "010-1111-2222"),
      2L, new Customer(2L, "김철수", "kim@example.com", "010-3333-4444")
    );

    public Customer findById(Long id) {
        return customers.get(id);
    }
}
