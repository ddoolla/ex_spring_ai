package com.example.exspringai.tool.context;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class PersonRepository {

    private final Map<String, Person> persons = Map.of(
            "Lee", new Person("Lee", 30, "music"),
            "Kim", new Person("Kim", 40, "game")
    );

    public Person getPerson(String name) {
        return persons.get(name);
    }
}
