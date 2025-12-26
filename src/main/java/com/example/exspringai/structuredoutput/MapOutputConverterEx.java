package com.example.exspringai.structuredoutput;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MapOutputConverterEx {

    private final ChatClient chatClient;

    @GetMapping("/ai/structured-output/mapOutputConverterEx01")
    public Map<String, Object> mapOutputConverterEx01() {
        return chatClient
                .prompt()
                .user(u -> u.text("Provide me a List of {subject}")
                        .param("subject", "an array of numbers from 1 to 9 under they key name 'numbers'"))
                .call()
                .entity(new ParameterizedTypeReference<Map<String, Object>>() {});
    }
}
