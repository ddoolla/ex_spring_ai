package com.example.exspringai.prompt;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PromptEx01 {

    private final ChatClient chatClient;

    @GetMapping("/ai/prompt/ex01")
    public String prompt_ex01() {

        PromptTemplate promptTemplate = new PromptTemplate("Tell me a {adjective} joke about {topic}");

        Prompt prompt = promptTemplate.create(Map.of("adjective", "funny", "topic", "dogs"));

        return chatClient.prompt(prompt).call().content();
    }


}
