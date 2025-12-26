package com.example.exspringai;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"actor", "movies"}) // 순서 보장
public record ActorFilms(String actor, List<String> movies) {
}
