package com.example.exspringai.structuredoutput;

import com.example.exspringai.ActorFilms;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParameterizedTypeReferenceEx {

    private final ChatClient chatClient;

    @GetMapping("/ai/structured-output/parameterizedTypeReferenceEx")
    public List<ActorFilms> parameterizedTypeReferenceEx() {
        return chatClient
                .prompt()
                .user("Generate the filmography of 5 movies for Tom Hanks and Bill Murray.")
                .call()
                .entity(new ParameterizedTypeReference<List<ActorFilms>>() {});
    }
}
